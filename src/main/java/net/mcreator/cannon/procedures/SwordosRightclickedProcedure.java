package net.mcreator.cannon.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class SwordosRightclickedProcedure {
    public static void execute(Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;

        // Set blocking flag
        CompoundTag tag = itemstack.getOrCreateTag();
        tag.putBoolean("isBlocking", true);
        itemstack.setTag(tag);
    }
}
