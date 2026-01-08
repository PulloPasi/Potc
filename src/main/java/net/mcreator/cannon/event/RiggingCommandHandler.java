package net.mcreator.cannon.event;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.util.HeavyLeadConnectionManager;
import net.mcreator.cannon.util.LeadConnectionManager;
import net.mcreator.cannon.util.RiggingConnection;
import net.mcreator.cannon.util.RiggingStructureHelper;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.NbtUtils;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Mod.EventBusSubscriber
public final class RiggingCommandHandler {

    private RiggingCommandHandler() {
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(buildRoot());
    }

    private static LiteralArgumentBuilder<CommandSourceStack> buildRoot() {
        return Commands.literal("riggingsave")
            .requires(source -> source.hasPermission(2))
            .then(Commands.argument("name", ResourceLocationArgument.id())
                .then(Commands.argument("corner_a", BlockPosArgument.blockPos())
                    .then(Commands.argument("corner_b", BlockPosArgument.blockPos())
                        .executes(context -> executeSave(context.getSource(),
                            ResourceLocationArgument.getId(context, "name"),
                            BlockPosArgument.getLoadedBlockPos(context, "corner_a"),
                            BlockPosArgument.getLoadedBlockPos(context, "corner_b"))))));
    }

    private static int executeSave(CommandSourceStack source, ResourceLocation riggingId, BlockPos cornerA, BlockPos cornerB) throws CommandSyntaxException {
        ServerLevel level = source.getLevel();
        BlockPos origin = new BlockPos(
            Math.min(cornerA.getX(), cornerB.getX()),
            Math.min(cornerA.getY(), cornerB.getY()),
            Math.min(cornerA.getZ(), cornerB.getZ()));

        List<RiggingConnection> lightConnections = LeadConnectionManager.collectConnections(cornerA, cornerB);
        List<RiggingConnection> heavyConnections = HeavyLeadConnectionManager.collectConnections(cornerA, cornerB);

        CompoundTag data = new CompoundTag();
        data.put(RiggingStructureHelper.LIGHT_KEY, LeadConnectionManager.serializeConnections(lightConnections, origin));
        data.put(RiggingStructureHelper.HEAVY_KEY, HeavyLeadConnectionManager.serializeConnections(heavyConnections, origin));
        data.put(RiggingStructureHelper.ORIGIN_KEY, NbtUtils.writeBlockPos(origin));

        try {
            writeToDisk(level.getServer(), riggingId, data);
        } catch (IOException ex) {
            CannonMod.LOGGER.error("Failed to save rigging data {}", riggingId, ex);
            source.sendFailure(Component.literal("Failed to save rigging data '" + riggingId + "'"));
            return 0;
        }

        source.sendSuccess(() -> Component.literal(
            "Saved " + lightConnections.size() + " ropes and " + heavyConnections.size() + " heavy ropes as '" + riggingId + "'"), true);
        return lightConnections.size() + heavyConnections.size();
    }

    private static void writeToDisk(MinecraftServer server, ResourceLocation id, CompoundTag data) throws IOException {
        Path directory = server.getWorldPath(LevelResource.ROOT).resolve("rigging").resolve(id.getNamespace());
        Files.createDirectories(directory);
        Path output = directory.resolve(id.getPath() + ".nbt");
        net.minecraft.nbt.NbtIo.writeCompressed(data, output.toFile());
    }
}
