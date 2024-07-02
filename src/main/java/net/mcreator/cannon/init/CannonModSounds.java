
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.cannon.CannonMod;

public class CannonModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CannonMod.MODID);
	public static final RegistryObject<SoundEvent> CANNONFIRE = REGISTRY.register("cannonfire", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "cannonfire")));
}
