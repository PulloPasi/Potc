package net.mcreator.cannon.event;

import net.mcreator.cannon.util.LeadConnectionManager;
import net.mcreator.cannon.util.HeavyLeadConnectionManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerTickHandler {
    @SubscribeEvent
    public static void onServerTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.level.isClientSide()) {
            ServerLevel level = (ServerLevel) event.level;
            LeadConnectionManager.tick(level);
            HeavyLeadConnectionManager.tick(level);
        }
    }
}
