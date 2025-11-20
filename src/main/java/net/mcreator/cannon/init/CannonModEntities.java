
/*
*    MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.cannon.init;

import org.valkyrienskies.core.impl.shadow.of;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.cannon.entity.SwordoftritonprojectileEntity;
import net.mcreator.cannon.entity.QuartermasterEntity;
import net.mcreator.cannon.entity.PartOfTheCrewStrongEntity;
import net.mcreator.cannon.entity.PartOfTheCrewEntity;
import net.mcreator.cannon.entity.MermaidEntity;
import net.mcreator.cannon.entity.MaccusEntity;
import net.mcreator.cannon.entity.LeashHolderEntity;
import net.mcreator.cannon.entity.LeadEntity;
import net.mcreator.cannon.entity.KarbEntity;
import net.mcreator.cannon.entity.HeavyLeadEntity;
import net.mcreator.cannon.entity.FBulletEntity;
import net.mcreator.cannon.entity.DavyJonesBossEntity;
import net.mcreator.cannon.entity.CannonballosProjectileEntity;
import net.mcreator.cannon.entity.BulletEntity;
import net.mcreator.cannon.entity.BriishEntity;
import net.mcreator.cannon.entity.BlackBeardsCrewEntity;
import net.mcreator.cannon.CannonMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CannonModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CannonMod.MODID);
	public static final RegistryObject<EntityType<CannonballosProjectileEntity>> CANNONBALLOS_PROJECTILE = register("cannonballos_projectile", EntityType.Builder.<CannonballosProjectileEntity>of(CannonballosProjectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(CannonballosProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<PartOfTheCrewEntity>> PART_OF_THE_CREW = register("part_of_the_crew",
			EntityType.Builder.<PartOfTheCrewEntity>of(PartOfTheCrewEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PartOfTheCrewEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<BulletEntity>> BULLET = register("bullet",
			EntityType.Builder.<BulletEntity>of(BulletEntity::new, MobCategory.MISC).setCustomClientFactory(BulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<KarbEntity>> KARB = register("karb",
			EntityType.Builder.<KarbEntity>of(KarbEntity::new, MobCategory.WATER_AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KarbEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<PartOfTheCrewStrongEntity>> PART_OF_THE_CREW_STRONG = register("part_of_the_crew_strong", EntityType.Builder.<PartOfTheCrewStrongEntity>of(PartOfTheCrewStrongEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PartOfTheCrewStrongEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<BriishEntity>> BRIISH = register("briish",
			EntityType.Builder.<BriishEntity>of(BriishEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BriishEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<SwordoftritonprojectileEntity>> SWORDOFTRITONPROJECTILE = register("swordoftritonprojectile", EntityType.Builder.<SwordoftritonprojectileEntity>of(SwordoftritonprojectileEntity::new, MobCategory.MISC)
			.setCustomClientFactory(SwordoftritonprojectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<MermaidEntity>> MERMAID = register("mermaid",
			EntityType.Builder.<MermaidEntity>of(MermaidEntity::new, MobCategory.WATER_AMBIENT).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MermaidEntity::new).sized(0.6f, 2.5f));
	public static final RegistryObject<EntityType<QuartermasterEntity>> QUARTERMASTER = register("quartermaster",
			EntityType.Builder.<QuartermasterEntity>of(QuartermasterEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(QuartermasterEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<BlackBeardsCrewEntity>> BLACK_BEARDS_CREW = register("black_beards_crew", EntityType.Builder.<BlackBeardsCrewEntity>of(BlackBeardsCrewEntity::new, MobCategory.MONSTER)
			.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BlackBeardsCrewEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<LeashHolderEntity>> LEASH_HOLDER = register("leash_holder",
			EntityType.Builder.<LeashHolderEntity>of(LeashHolderEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(LeashHolderEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<MaccusEntity>> MACCUS = register("maccus",
			EntityType.Builder.<MaccusEntity>of(MaccusEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MaccusEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<DavyJonesBossEntity>> DAVY_JONES_BOSS = register("davy_jones_boss",
			EntityType.Builder.<DavyJonesBossEntity>of(DavyJonesBossEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(DavyJonesBossEntity::new).sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<FBulletEntity>> F_BULLET = register("f_bullet",
			EntityType.Builder.<FBulletEntity>of(FBulletEntity::new, MobCategory.MISC).setCustomClientFactory(FBulletEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));
	// Start of user code block custom entities
	public static final RegistryObject<EntityType<LeadEntity>> LEAD_ENTITY = register("lead_entity", EntityType.Builder.<LeadEntity>of(LeadEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1)
			.setCustomClientFactory((PlayMessages.SpawnEntity packet, Level level) -> new LeadEntity(packet, level)).sized(0.1f, 0.1f));
	public static final RegistryObject<EntityType<HeavyLeadEntity>> HEAVY_LEAD_ENTITY = register("heavy_lead_entity", EntityType.Builder.<HeavyLeadEntity>of(HeavyLeadEntity::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory((PlayMessages.SpawnEntity packet, Level level) -> new HeavyLeadEntity(packet, level)).sized(0.15f, 0.15f));

	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PartOfTheCrewEntity.init();
			KarbEntity.init();
			PartOfTheCrewStrongEntity.init();
			BriishEntity.init();
			MermaidEntity.init();
			QuartermasterEntity.init();
			BlackBeardsCrewEntity.init();
			LeashHolderEntity.init();
			MaccusEntity.init();
			DavyJonesBossEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(PART_OF_THE_CREW.get(), PartOfTheCrewEntity.createAttributes().build());
		event.put(KARB.get(), KarbEntity.createAttributes().build());
		event.put(PART_OF_THE_CREW_STRONG.get(), PartOfTheCrewStrongEntity.createAttributes().build());
		event.put(BRIISH.get(), BriishEntity.createAttributes().build());
		event.put(MERMAID.get(), MermaidEntity.createAttributes().build());
		event.put(QUARTERMASTER.get(), QuartermasterEntity.createAttributes().build());
		event.put(BLACK_BEARDS_CREW.get(), BlackBeardsCrewEntity.createAttributes().build());
		event.put(LEASH_HOLDER.get(), LeashHolderEntity.createAttributes().build());
		event.put(MACCUS.get(), MaccusEntity.createAttributes().build());
		event.put(DAVY_JONES_BOSS.get(), DavyJonesBossEntity.createAttributes().build());
	}
}
