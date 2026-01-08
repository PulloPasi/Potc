package net.mcreator.cannon.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;

public class AddDeadMansChestModifier extends LootModifier {
	public static final Codec<AddDeadMansChestModifier> CODEC = RecordCodecBuilder.create(instance ->
			codecStart(instance)
				.and(ItemStack.CODEC.fieldOf("stack").forGetter(modifier -> modifier.stack))
				.apply(instance, AddDeadMansChestModifier::new));

	private final ItemStack stack;

	protected AddDeadMansChestModifier(LootItemCondition[] conditions, ItemStack stack) {
		super(conditions);
		this.stack = stack;
	}

	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (!this.stack.isEmpty()) {
			generatedLoot.add(this.stack.copy());
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC;
	}
}
