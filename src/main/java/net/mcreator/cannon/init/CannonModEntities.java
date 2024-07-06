
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.cannon.entity.PartOfTheCrewStrongEntity;
import net.mcreator.cannon.entity.PartOfTheCrewEntity;
import net.mcreator.cannon.entity.KarbEntity;
import net.mcreator.cannon.entity.CannonballosProjectileEntity;
import net.mcreator.cannon.entity.BulletEntity;
import net.mcreator.cannon.CannonMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CannonModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CannonMod.MODID);
	public static final RegistryObject<EntityType<CannonballosProjectileEntity>> CANNONBALLOS_PROJECTILE = register("cannonballos_projectile", EntityType.Builder.<CannonballosProjectileEntity>of(CannonballosProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(CannonballosProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<PartOfTheCrewEntity>> PART_OF_THE_CREW = register("part_of_the_crew",
			EntityType.Builder.<PartOfTheCrewEntity>of(PartOfTheCrewEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PartOfTheCrewEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<BulletEntity>> BULLET = register("bullet",
			EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC).setCustomClientFactory(BulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<KarbEntity>> KARB = register("karb",
			EntityType.Builder.<KarbEntity>of(KarbEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KarbEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<PartOfTheCrewStrongEntity>> PART_OF_THE_CREW_STRONG = register("part_of_the_crew_strong",
			EntityType.Builder.<PartOfTheCrewStrongEntity>of(PartOfTheCrewStrongEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PartOfTheCrewStrongEntity::new)

					.sized(0.6f, 1.8f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PartOfTheCrewEntity.init();
			KarbEntity.init();
			PartOfTheCrewStrongEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(PART_OF_THE_CREW.get(), PartOfTheCrewEntity.createAttributes().build());
		event.put(KARB.get(), KarbEntity.createAttributes().build());
		event.put(PART_OF_THE_CREW_STRONG.get(), PartOfTheCrewStrongEntity.createAttributes().build());
	}
}
