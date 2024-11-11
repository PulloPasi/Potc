
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.cannon.client.model.Modeltrihat;
import net.mcreator.cannon.client.model.Modelpipo;
import net.mcreator.cannon.client.model.Modeljonetsiversiokaksi;
import net.mcreator.cannon.client.model.Modeljackkaks;
import net.mcreator.cannon.client.model.Modeljack;
import net.mcreator.cannon.client.model.Modelgibbs;
import net.mcreator.cannon.client.model.Modelblackbeard;
import net.mcreator.cannon.client.model.Modelbillvkaks;
import net.mcreator.cannon.client.model.Modelbillkolme;
import net.mcreator.cannon.client.model.Modelbarbossakaks;
import net.mcreator.cannon.client.model.Modelbandana;
import net.mcreator.cannon.client.model.Modelangelica;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class CannonModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelbillkolme.LAYER_LOCATION, Modelbillkolme::createBodyLayer);
		event.registerLayerDefinition(Modeljonetsiversiokaksi.LAYER_LOCATION, Modeljonetsiversiokaksi::createBodyLayer);
		event.registerLayerDefinition(Modeljack.LAYER_LOCATION, Modeljack::createBodyLayer);
		event.registerLayerDefinition(Modelbandana.LAYER_LOCATION, Modelbandana::createBodyLayer);
		event.registerLayerDefinition(Modelpipo.LAYER_LOCATION, Modelpipo::createBodyLayer);
		event.registerLayerDefinition(Modelbillvkaks.LAYER_LOCATION, Modelbillvkaks::createBodyLayer);
		event.registerLayerDefinition(Modelgibbs.LAYER_LOCATION, Modelgibbs::createBodyLayer);
		event.registerLayerDefinition(Modelangelica.LAYER_LOCATION, Modelangelica::createBodyLayer);
		event.registerLayerDefinition(Modeltrihat.LAYER_LOCATION, Modeltrihat::createBodyLayer);
		event.registerLayerDefinition(Modelblackbeard.LAYER_LOCATION, Modelblackbeard::createBodyLayer);
		event.registerLayerDefinition(Modelbarbossakaks.LAYER_LOCATION, Modelbarbossakaks::createBodyLayer);
		event.registerLayerDefinition(Modeljackkaks.LAYER_LOCATION, Modeljackkaks::createBodyLayer);
	}
}
