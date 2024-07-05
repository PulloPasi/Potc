
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.cannon.client.model.Modeljonetsiversiokaksi;
import net.mcreator.cannon.client.model.Modelbillvkaks;
import net.mcreator.cannon.client.model.Modelbillkolme;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class CannonModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelbillkolme.LAYER_LOCATION, Modelbillkolme::createBodyLayer);
		event.registerLayerDefinition(Modeljonetsiversiokaksi.LAYER_LOCATION, Modeljonetsiversiokaksi::createBodyLayer);
		event.registerLayerDefinition(Modelbillvkaks.LAYER_LOCATION, Modelbillvkaks::createBodyLayer);
	}
}
