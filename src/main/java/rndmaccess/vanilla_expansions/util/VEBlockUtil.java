package rndmaccess.vanilla_expansions.util;

import net.minecraft.block.BlockState;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;

/**
 * A utility class that holds helper methods for blocks.
 *
 * @author Ryan
 */
public class VEBlockUtil
{
    /**
     * @param state The slab block.
     * @return True if the slab is the bottom half.
     */
    public static boolean isBottom(BlockState state)
    {
        EnumProperty<SlabType> slabType = BlockStateProperties.SLAB_TYPE;

        return state.hasProperty(slabType) && state.getValue(slabType).equals(SlabType.BOTTOM);
    }
}
