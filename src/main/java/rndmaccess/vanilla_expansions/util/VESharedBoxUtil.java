package rndmaccess.vanilla_expansions.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

/**
 * This class stores data for shared bounding boxes for blocks.
 *
 * @author Ryan
 */
public class VESharedBoxUtil
{
    public static final VoxelShape NORTH_ZOMBIE_VILLAGER_LEFT_ARM = Block.box(11.0D, 2.0D, 3.0D, 14.0D, 5.0D, 10.5D);
    public static final VoxelShape NORTH_ZOMBIE_VILLAGER_RIGHT_ARM = Block.box(2.0D, 2.0D, 3.0D, 5.0D, 5.0D, 10.5D);
    public static final VoxelShape NORTH_ZOMBIE_VILLAGER_ARMS = VoxelShapes.or(NORTH_ZOMBIE_VILLAGER_LEFT_ARM,
            NORTH_ZOMBIE_VILLAGER_RIGHT_ARM);
}
