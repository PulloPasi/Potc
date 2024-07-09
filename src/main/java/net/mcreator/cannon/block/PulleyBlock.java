
package net.mcreator.cannon.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PulleyBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final EnumProperty<AttachFace> FACE = FaceAttachedHorizontalDirectionalBlock.FACE;

	public PulleyBlock() {
		super(BlockBehaviour.Properties.of().ignitedByLava().instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> switch (state.getValue(FACE)) {
				case FLOOR -> box(6.5, 0, 6, 9.5, 16, 10);
				case WALL -> box(6.5, 6, 0, 9.5, 10, 16);
				case CEILING -> box(6.5, 0, 6, 9.5, 16, 10);
			};
			case NORTH -> switch (state.getValue(FACE)) {
				case FLOOR -> box(6.5, 0, 6, 9.5, 16, 10);
				case WALL -> box(6.5, 6, 0, 9.5, 10, 16);
				case CEILING -> box(6.5, 0, 6, 9.5, 16, 10);
			};
			case EAST -> switch (state.getValue(FACE)) {
				case FLOOR -> box(6, 0, 6.5, 10, 16, 9.5);
				case WALL -> box(0, 6, 6.5, 16, 10, 9.5);
				case CEILING -> box(6, 0, 6.5, 10, 16, 9.5);
			};
			case WEST -> switch (state.getValue(FACE)) {
				case FLOOR -> box(6, 0, 6.5, 10, 16, 9.5);
				case WALL -> box(0, 6, 6.5, 16, 10, 9.5);
				case CEILING -> box(6, 0, 6.5, 10, 16, 9.5);
			};
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, FACE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		if (context.getClickedFace().getAxis() == Direction.Axis.Y)
			return this.defaultBlockState().setValue(FACE, context.getClickedFace().getOpposite() == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection());
		return this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, context.getClickedFace());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}