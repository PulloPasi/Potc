package net.mcreator.cannon.client;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.client.renderer.LeadRenderer;
import net.mcreator.cannon.init.CannonModEntities;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CannonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Register the renderer for LeadEntity
        EntityRenderers.register(CannonModEntities.LEAD.get(), LeadRenderer::new);
    }
}
