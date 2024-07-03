
package net.mcreator.cannon.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PlanksOfTheFlyingDutchmanBlock extends Block {
	public PlanksOfTheFlyingDutchmanBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(1f, 10f));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}