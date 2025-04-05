package net.mcreator.cannon.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import net.mcreator.cannon.procedures.OnFenceRightClickProcedure;

public class CustomLeadItemItem extends Item {  // Make sure this class starts correctly
    public CustomLeadItemItem() {
        super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {  // Method must be inside the class
        super.useOn(context);

        // Call the procedure with the required parameters
        return OnFenceRightClickProcedure.execute(
            context.getLevel(),
            context.getClickedPos(),
            context.getPlayer(),
            context.getHand()
        );
    }
}
