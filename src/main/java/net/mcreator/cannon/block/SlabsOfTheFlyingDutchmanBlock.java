
package net.mcreator.cannon.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class SlabsOfTheFlyingDutchmanBlock extends SlabBlock {
	public SlabsOfTheFlyingDutchmanBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(1f, 10f).dynamicShape());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}