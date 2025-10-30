package net.mcreator.cannon.procedures;

import org.valkyrienskies.core.impl.shadow.z;
import org.valkyrienskies.core.impl.shadow.y;
import org.valkyrienskies.core.impl.shadow.x;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.cannon.network.CannonModVariables;
import net.mcreator.cannon.init.CannonModBlocks;

public class RiggingTooluseProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CannonModBlocks.ROPE_ANCHOR.get()) {
			if ((entity.getCapability(CannonModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new CannonModVariables.PlayerVariables())).riggingHasFirst == false) {
				{
					double _setval = x;
					entity.getCapability(CannonModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.riggingAnchorX = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = y;
					entity.getCapability(CannonModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.riggingAnchorX = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = z;
					entity.getCapability(CannonModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.riggingAnchorX = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					boolean _setval = true;
					entity.getCapability(CannonModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.riggingHasFirst = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("First anchor set"), true);
			}
			{
				boolean _setval = false;
				entity.getCapability(CannonModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.riggingHasFirst = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Rope Placed!"), false);
		}
	}
}
