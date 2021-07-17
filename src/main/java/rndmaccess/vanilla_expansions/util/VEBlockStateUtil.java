package rndmaccess.vanilla_expansions.util;

import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.properties.SlabType;

/**
 * A utility class that holds helper methods for block states.
 *
 * @author Ryan
 */
public class VEBlockStateUtil
{
    /**
     * @param state The slab block.
     * @return True if the slab is the bottom half.
     */
    public static boolean isBottomSlab(BlockState state)
    {
        EnumProperty<SlabType> slabType = BlockStateProperties.SLAB_TYPE;

        return state.hasProperty(slabType) && state.getValue(slabType).equals(SlabType.BOTTOM);
    }

    /**
     * @param state The state to check.
     * @return True if the state is the top half.
     */
    public static boolean isUpperHalf(BlockState state)
    {
        return isHalf(state, DoubleBlockHalf.UPPER);
    }

    /**
     * @param state The state to check.
     * @return True if the state is the bottom half.
     */
    public static boolean isLowerHalf(BlockState state)
    {
        return isHalf(state, DoubleBlockHalf.LOWER);
    }

    private static boolean isHalf(BlockState state, DoubleBlockHalf type)
    {
        EnumProperty<DoubleBlockHalf> half = BlockStateProperties.DOUBLE_BLOCK_HALF;

        return state.hasProperty(half) && state.getValue(half).equals(type);
    }
}
