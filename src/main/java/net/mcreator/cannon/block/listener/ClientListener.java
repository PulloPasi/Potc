package net.mcreator.cannon.block.listener;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.cannon.init.CannonModBlockEntities;
import net.mcreator.cannon.block.renderer.DeadMansChestTileRenderer;
import net.mcreator.cannon.CannonMod;

@Mod.EventBusSubscriber(modid = CannonMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(CannonModBlockEntities.DEAD_MANS_CHEST.get(), context -> new DeadMansChestTileRenderer());
	}
}
