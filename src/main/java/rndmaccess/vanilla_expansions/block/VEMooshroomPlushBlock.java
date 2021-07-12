package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VEMooshroomPlushBlock extends VECowPlushBlock
{
    protected static final VoxelShape NORTH_HEAD_MUSHROOM_STEM_SHAPE = Block.box(7.5D, 13.0D, 6.0D, 8.5D, 14.0D, 7.0D);
    protected static final VoxelShape NORTH_HEAD_MUSHROOM_BOTTOM_SHAPE = Block.box(6.0D, 14.0D, 4.5D, 10.0D, 15.0D,
            8.5D);
    protected static final VoxelShape NORTH_HEAD_MUSHROOM_TOP_SHAPE = Block.box(7.0D, 15.0D, 5.5D, 9.0D, 16.0D, 7.5D);

    protected static final VoxelShape NORTH_HEAD_MUSHROOM_SHAPE = VoxelShapes.or(NORTH_HEAD_MUSHROOM_STEM_SHAPE,
            NORTH_HEAD_MUSHROOM_BOTTOM_SHAPE, NORTH_HEAD_MUSHROOM_TOP_SHAPE);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_HEAD_MUSHROOM_SHAPE,
            VECowPlushBlock.NORTH_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(NORTH_SHAPE);

    public VEMooshroomPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
