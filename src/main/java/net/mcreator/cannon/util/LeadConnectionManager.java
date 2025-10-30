package net.mcreator.cannon.util;

import net.mcreator.cannon.entity.LeadEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

public class LeadConnectionManager {
    private static final Map<BlockPos, BlockPos> connections = new HashMap<>();
    private static final Map<BlockPos, LeadEntity> leadEntities = new HashMap<>();
    private static final Map<BlockPos, BlockPos> connectionToEntity = new HashMap<>();
    private static final Set<BlockPos> pendingRemovals = new HashSet<>();

    public static boolean createConnection(Level world, BlockPos from, BlockPos to) {
        // Prevent self-connection and duplicate connections
        if (from == null || to == null || from.equals(to) || hasConnection(from) || hasConnection(to)) {
            return false;
        }

        // Check if blocks are too far apart (max 32 blocks)
        if (from.distSqr(to) > 32 * 32) {
            return false;
        }

        // Store the connection in both directions for easy lookup
        connections.put(from, to);
        connections.put(to, from);
        
        // Spawn the visual lead entity
        spawnLeadEntity(world, from, to);
        return true;
    }
    
    private static boolean hasConnection(BlockPos pos) {
        return connections.containsKey(pos) || connections.containsValue(pos);
    }

    private static void spawnLeadEntity(Level world, BlockPos from, BlockPos to) {
        if (!world.isClientSide && world instanceof ServerLevel serverLevel) {
            // Remove any existing lead entities for these positions
            removeLeadEntity(from);
            removeLeadEntity(to);
            
            // Create and spawn the new lead entity
            LeadEntity lead = LeadEntity.create(world, from, to);
            serverLevel.addFreshEntity(lead);
            
            // Store references to the lead entity
            leadEntities.put(from, lead);
            leadEntities.put(to, lead);
            connectionToEntity.put(from, to);
            connectionToEntity.put(to, from);
        }
    }

    public static boolean breakConnection(BlockPos pos) {
        BlockPos connectedTo = connections.remove(pos);
        if (connectedTo != null) {
            // Remove the reverse connection
            connections.remove(connectedTo);
            
            // Remove the lead entity and connection data
            removeLeadEntity(pos);
            removeLeadEntity(connectedTo);
            connectionToEntity.remove(pos);
            connectionToEntity.remove(connectedTo);
            
            return true;
        }
        return false;
    }

    private static void removeLeadEntity(BlockPos pos) {
        LeadEntity lead = leadEntities.remove(pos);
        if (lead != null && !lead.isRemoved() && lead.level() instanceof ServerLevel) {
            lead.discard();
        }
    }

    public static boolean isConnected(BlockPos pos) {
        return connections.containsKey(pos) || connections.containsValue(pos);
    }
    
    public static Optional<BlockPos> getConnectedPos(BlockPos pos) {
        return Optional.ofNullable(connectionToEntity.get(pos));
    }
    
    public static void updateConnection(LeadEntity lead, BlockPos from, BlockPos to) {
        if (lead == null || from == null || to == null) {
            return;
        }
        
        // Update the connection in our maps
        BlockPos oldTo = connectionToEntity.get(from);
        if (oldTo != null) {
            connectionToEntity.remove(oldTo);
        }
        
        connectionToEntity.put(from, to);
        connectionToEntity.put(to, from);
        
        // Update the lead entity's position
        lead.setPos(
            (from.getX() + to.getX()) / 2.0,
            (from.getY() + to.getY()) / 2.0,
            (from.getZ() + to.getZ()) / 2.0
        );
    }
    
    @SubscribeEvent
    public static void onServerTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.level.isClientSide()) {
            return;
        }
        
        ServerLevel level = (ServerLevel) event.level;
        long gameTime = level.getGameTime();
        
        // Check for broken connections every second (20 ticks)
        if (gameTime % 20 == 0) {
            // Create a copy to avoid concurrent modification
            Map<BlockPos, BlockPos> connectionsCopy = new HashMap<>(connections);
            
            for (Map.Entry<BlockPos, BlockPos> entry : connectionsCopy.entrySet()) {
                BlockPos from = entry.getKey();
                BlockPos to = entry.getValue();
                
                // Check if either block has been removed
                if (!isValidConnection(level, from, to)) {
                    breakConnection(from);
                }
            }
        }
    }
    
    private static boolean isValidConnection(Level level, BlockPos from, BlockPos to) {
        // Check if both blocks are still present and not too far apart
        return level.isLoaded(from) && level.isLoaded(to) && 
               level.getBlockState(from).isAir() == false && 
               level.getBlockState(to).isAir() == false &&
               from.distSqr(to) <= 32 * 32;
    }

    public static void tick(Level world) {
        // Clean up invalid connections
        pendingRemovals.clear();
        
        for (Map.Entry<BlockPos, BlockPos> entry : connections.entrySet()) {
            BlockPos from = entry.getKey();
            BlockPos to = entry.getValue();
            
            // Check if blocks still exist and are loaded
            if (!world.isLoaded(from) || !world.isLoaded(to) || 
                world.isEmptyBlock(from) || world.isEmptyBlock(to) ||
                from.distSqr(to) > 256) { // 16 blocks max distance
                pendingRemovals.add(from);
            }
        }
        
        // Process pending removals
        for (BlockPos pos : pendingRemovals) {
            breakConnection(pos);
        }
        
        // Refresh lead entities to prevent despawn
        if (world.getGameTime() % 200 == 0) { // Every 10 seconds
            for (LeadEntity lead : new ArrayList<>(leadEntities.values())) {
                if (lead != null && !lead.isRemoved()) {
                    lead.refreshLifespan();
                }
            }
        }
    }

    // VS Integration - Update positions when ship moves
    public static void onShipMove(BlockPos oldPos, BlockPos newPos) {
        if (connections.containsKey(oldPos)) {
            BlockPos connectedTo = connections.remove(oldPos);
            connections.put(newPos, connectedTo);
            
            // Update lead entity
            LeadEntity lead = leadEntities.remove(oldPos);
            if (lead != null) {
                leadEntities.put(newPos, lead);
                lead.refreshLifespan();
            }
        }
    }
}
