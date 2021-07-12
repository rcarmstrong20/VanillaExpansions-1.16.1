package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;
import rndmaccess.vanilla_expansions.util.VESharedBoxUtil;

public class VESnowZombieVillagerPlushBlock extends VESnowVillagerPlushBlock
{
    protected static final VoxelShape NORTH_BODY = VoxelShapes.or(NORTH_HEAD, NORTH_TORSO,
            VESharedBoxUtil.NORTH_ZOMBIE_VILLAGER_ARMS);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_FEET, NORTH_FACE, NORTH_BODY, NORTH_HAT);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VESnowZombieVillagerPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
