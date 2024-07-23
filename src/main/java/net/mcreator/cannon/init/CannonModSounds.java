
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
	public static final RegistryObject<SoundEvent> CANNON_FIRED = REGISTRY.register("cannon-fired", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "cannon-fired")));
	public static final RegistryObject<SoundEvent> CANNONBALL_WOOSH = REGISTRY.register("cannonball-woosh", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "cannonball-woosh")));
	public static final RegistryObject<SoundEvent> CANNONBALL_EXPLOSION = REGISTRY.register("cannonball-explosion", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "cannonball-explosion")));
	public static final RegistryObject<SoundEvent> CANNON_LOAD = REGISTRY.register("cannon-load", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "cannon-load")));
	public static final RegistryObject<SoundEvent> SWORDFIRE = REGISTRY.register("swordfire", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "swordfire")));
	public static final RegistryObject<SoundEvent> GUNFIRE = REGISTRY.register("gunfire", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "gunfire")));
	public static final RegistryObject<SoundEvent> SWOWRDDRAW = REGISTRY.register("swowrddraw", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "swowrddraw")));
	public static final RegistryObject<SoundEvent> CANNONHIT = REGISTRY.register("cannonhit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("cannon", "cannonhit")));
}
