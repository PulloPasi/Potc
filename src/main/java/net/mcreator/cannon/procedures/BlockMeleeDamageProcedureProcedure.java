package net.mcreator.cannon.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.mcreator.cannon.CannonMod;

public class BlockMeleeDamageProcedureProcedure {

    public BlockMeleeDamageProcedureProcedure() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityAttacked(LivingAttackEvent event) {
        Entity target = event.getEntity();
        Entity attacker = event.getSource().getEntity();

        if (!(attacker instanceof LivingEntity)) return;
        if (attacker instanceof Projectile) return;

        LivingEntity livingAttacker = (LivingEntity) attacker;
        ItemStack mainHand = livingAttacker.getMainHandItem();

        String itemName = mainHand.getItem().builtInRegistryHolder().key().location().toString();
        CannonMod.LOGGER.info("Attacker's main hand item: " + itemName);

        if ("cannon:swordos".equals(itemName)) {
            if (mainHand.hasTag() && mainHand.getTag().getBoolean("isBlocking")) {
                CannonMod.LOGGER.info("Blocking active - cancelling damage!");
                event.setCanceled(true);
            } else {
                CannonMod.LOGGER.info("Not blocking, damage allowed");
            }
        }
    }
}
