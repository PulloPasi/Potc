package net.mcreator.cannon.ships;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.util.LeadConnectionManager;
import net.mcreator.cannon.util.HeavyLeadConnectionManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.valkyrienskies.core.impl.hooks.VSEvents;
import org.valkyrienskies.core.impl.game.ships.ShipObjectServerWorld;

@Mod.EventBusSubscriber(modid = CannonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LeadShipEvents {
    private LeadShipEvents() {
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> VSEvents.TickEndEvent.Companion.on(tickEndEvent -> {
            ShipObjectServerWorld shipWorld = tickEndEvent.getWorld();
            if (shipWorld == null) {
                return;
            }

            LeadConnectionManager.handleShipWorldTick(shipWorld);
            HeavyLeadConnectionManager.handleShipWorldTick(shipWorld);
        }));
    }
}
