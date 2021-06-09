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

public class VEBabyMooshroomPlushBlock extends VEBabyCowPlushBlock
{
    protected static final VoxelShape MUSHROOM_STEM = Block.box(7.5D, 6.0D, 9.0D, 8.5D, 7.0D, 10.0D);
    protected static final VoxelShape MUSHROOM_BOTTOM = Block.box(6.0D, 7.0D, 7.5D, 10.0D, 8.0D, 11.5D);
    protected static final VoxelShape MUSHROOM_TOP = Block.box(6.5D, 8.0D, 8.0D, 9.5D, 8.5D, 11.0D);
    protected static final VoxelShape MUSHROOM = VoxelShapes.or(MUSHROOM_STEM, MUSHROOM_BOTTOM, MUSHROOM_TOP);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(MUSHROOM, VEBabyCowPlushBlock.NORTH_SHAPE);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEBabyMooshroomPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
