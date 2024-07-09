
package net.mcreator.cannon.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SailsOfTheBlackPearlBlock extends Block {
	public SailsOfTheBlackPearlBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava().sound(SoundType.WOOL).strength(1f, 10f));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}