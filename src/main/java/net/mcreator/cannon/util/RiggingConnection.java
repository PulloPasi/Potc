package net.mcreator.cannon.util;

import net.minecraft.core.BlockPos;

/**
 * Simple value object describing a rope connection between two block positions.
 */
public record RiggingConnection(BlockPos from, BlockPos to) {
}
