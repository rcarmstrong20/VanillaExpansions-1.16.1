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

public class VEMooshroomPlushBlock extends VECowPlushBlock
{
    protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_STEM_SHAPE = Block.box(7.5D, 11.0D, 2.0D, 8.5D,
            12.0D, 3.0D);
    protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_BOTTOM_SHAPE = Block.box(6.5D, 12.0D, 1.0D, 9.5D,
            13.0D, 4.0D);
    protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_TOP_SHAPE = Block.box(7.0D, 13.0D, 1.5D, 9.0D,
            13.5D, 3.5D);
    protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_STEM_SHAPE = Block.box(7.5D, 10.0D, 7.0D, 8.5D,
            11.0D, 8.0D);
    protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_BOTTOM_SHAPE = Block.box(6.5D, 11.0D, 6.0D, 9.5D,
            12.0D, 9.0D);
    protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_TOP_SHAPE = Block.box(7.0D, 12.0D, 6.5D, 9.0D,
            12.5D, 8.5D);
    protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_STEM_SHAPE = Block.box(7.5D, 10.0D, 11.0D, 8.5D,
            11.0D, 12.0D);
    protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_BOTTOM_SHAPE = Block.box(6.5D, 11.0D, 10.0D, 9.5D,
            12.0D, 13.0D);
    protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_TOP_SHAPE = Block.box(7.0D, 12.0D, 10.5D, 9.0D,
            12.5D, 12.5D);

    protected static final VoxelShape MOOSHROOM_NORTH_HEAD_MUSHROOM_SHAPE = VoxelShapes.or(
            MOOSHROOM_NORTH_HEAD_MUSHROOM_STEM_SHAPE, MOOSHROOM_NORTH_HEAD_MUSHROOM_BOTTOM_SHAPE,
            MOOSHROOM_NORTH_HEAD_MUSHROOM_TOP_SHAPE);

    protected static final VoxelShape MOOSHROOM_NORTH_BODY1_MUSHROOM_SHAPE = VoxelShapes.or(
            MOOSHROOM_NORTH_BODY1_MUSHROOM_STEM_SHAPE, MOOSHROOM_NORTH_BODY1_MUSHROOM_BOTTOM_SHAPE,
            MOOSHROOM_NORTH_BODY1_MUSHROOM_TOP_SHAPE);

    protected static final VoxelShape MOOSHROOM_NORTH_BODY2_MUSHROOM_SHAPE = VoxelShapes.or(
            MOOSHROOM_NORTH_BODY2_MUSHROOM_STEM_SHAPE, MOOSHROOM_NORTH_BODY2_MUSHROOM_BOTTOM_SHAPE,
            MOOSHROOM_NORTH_BODY2_MUSHROOM_TOP_SHAPE);

    protected static final VoxelShape MOOSHROOM_NORTH_SHAPE = VoxelShapes.or(MOOSHROOM_NORTH_HEAD_MUSHROOM_SHAPE,
            MOOSHROOM_NORTH_BODY1_MUSHROOM_SHAPE, MOOSHROOM_NORTH_BODY2_MUSHROOM_SHAPE, COW_NORTH_SHAPE);

    protected static final VoxelShape MOOSHROOM_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, MOOSHROOM_NORTH_SHAPE);
    protected static final VoxelShape MOOSHROOM_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, MOOSHROOM_NORTH_SHAPE);
    protected static final VoxelShape MOOSHROOM_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, MOOSHROOM_NORTH_SHAPE);

    public VEMooshroomPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, MOOSHROOM_NORTH_SHAPE, MOOSHROOM_SOUTH_SHAPE, MOOSHROOM_WEST_SHAPE,
                MOOSHROOM_EAST_SHAPE);
    }
}
