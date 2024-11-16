
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.cannon.client.particle.MuzzleflareParticle;
import net.mcreator.cannon.client.particle.CannontrailParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CannonModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(CannonModParticleTypes.CANNONTRAIL.get(), CannontrailParticle::provider);
		event.registerSpriteSet(CannonModParticleTypes.MUZZLEFLARE.get(), MuzzleflareParticle::provider);
	}
}
