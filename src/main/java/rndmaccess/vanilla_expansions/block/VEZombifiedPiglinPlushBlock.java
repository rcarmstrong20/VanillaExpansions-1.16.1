package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.block.util.VEBoxBlockUtil;

public class VEZombifiedPiglinPlushBlock extends VEZombiePlushBlock
{
    protected static final VoxelShape NORTH_SNOUT = Block.box(6.0D, 8.0D, 4.0D, 10.0D, 10.0D, 5.0D);
    protected static final VoxelShape NORTH_RIGHT_EAR = Block.box(1.5D, 9.0D, 8.0D, 3.5D, 12.0D, 12.0D);
    protected static final VoxelShape NORTH_LEFT_EAR = Block.box(11.5D, 9.0D, 8.0D, 14.0D, 12.0D, 12.0D);

    protected static final VoxelShape NORTH_EARS = VoxelShapes.or(NORTH_RIGHT_EAR, NORTH_LEFT_EAR);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_SNOUT, NORTH_EARS,
            VEZombiePlushBlock.NORTH_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEZombifiedPiglinPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
