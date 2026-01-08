package net.mcreator.cannon.init;

import com.mojang.serialization.Codec;

import net.mcreator.cannon.CannonMod;
import net.mcreator.cannon.loot.AddDeadMansChestModifier;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class CannonModLootModifiers {
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY =
			DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, CannonMod.MODID);

	public static final RegistryObject<Codec<AddDeadMansChestModifier>> ADD_DEAD_MANS_CHEST =
			REGISTRY.register("add_dead_mans_chest", () -> AddDeadMansChestModifier.CODEC);

	private CannonModLootModifiers() {
	}

	public static void register(IEventBus bus) {
		REGISTRY.register(bus);
	}
}
