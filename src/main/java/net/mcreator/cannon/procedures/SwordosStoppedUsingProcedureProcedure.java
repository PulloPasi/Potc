package net.mcreator.cannon.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class SwordosStoppedUsingProcedureProcedure {
    public static void execute(Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;

        // Remove blocking flag
        CompoundTag tag = itemstack.getOrCreateTag();
        tag.putBoolean("isBlocking", false);
        itemstack.setTag(tag);
    }
}
