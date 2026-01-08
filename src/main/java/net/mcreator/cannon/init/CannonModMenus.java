
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cannon.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.cannon.world.inventory.CompassSelectMenu;
import net.mcreator.cannon.CannonMod;

public class CannonModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CannonMod.MODID);
	public static final RegistryObject<MenuType<CompassSelectMenu>> COMPASS_SELECT = REGISTRY.register("compass_select", () -> IForgeMenuType.create(CompassSelectMenu::new));
}
