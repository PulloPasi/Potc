
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.cannon.client.renderer.QuartermasterRenderer;
import net.mcreator.cannon.client.renderer.PartOfTheCrewStrongRenderer;
import net.mcreator.cannon.client.renderer.PartOfTheCrewRenderer;
import net.mcreator.cannon.client.renderer.MermaidRenderer;
import net.mcreator.cannon.client.renderer.KarbRenderer;
import net.mcreator.cannon.client.renderer.BriishRenderer;
import net.mcreator.cannon.client.renderer.BlackBeardsCrewRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CannonModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(CannonModEntities.CANNONBALLOS_PROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(CannonModEntities.PART_OF_THE_CREW.get(), PartOfTheCrewRenderer::new);
		event.registerEntityRenderer(CannonModEntities.BULLET.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(CannonModEntities.KARB.get(), KarbRenderer::new);
		event.registerEntityRenderer(CannonModEntities.PART_OF_THE_CREW_STRONG.get(), PartOfTheCrewStrongRenderer::new);
		event.registerEntityRenderer(CannonModEntities.BRIISH.get(), BriishRenderer::new);
		event.registerEntityRenderer(CannonModEntities.SWORDOFTRITONPROJECTILE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(CannonModEntities.MERMAID.get(), MermaidRenderer::new);
		event.registerEntityRenderer(CannonModEntities.QUARTERMASTER.get(), QuartermasterRenderer::new);
		event.registerEntityRenderer(CannonModEntities.BLACK_BEARDS_CREW.get(), BlackBeardsCrewRenderer::new);
	}
}
