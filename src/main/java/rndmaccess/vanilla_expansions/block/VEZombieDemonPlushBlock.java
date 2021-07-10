package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VEZombieDemonPlushBlock extends VEZombiePlushBlock
{
    protected static final VoxelShape NORTH_LEFT_HORN = Block.box(11.0D, 12.0D, 7.5D, 14.0D, 16.0D, 10.5D);
    protected static final VoxelShape NORTH_RIGHT_HORN = Block.box(2.0D, 12.0D, 7.5D, 5.0D, 16.0D, 10.5D);

    protected static final VoxelShape NORTH_HORNS = VoxelShapes.or(NORTH_LEFT_HORN, NORTH_RIGHT_HORN);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(VEZombiePlushBlock.NORTH_SHAPE, NORTH_HORNS);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEZombieDemonPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
