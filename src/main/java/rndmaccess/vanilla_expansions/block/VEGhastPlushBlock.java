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

public class VEGhastPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape GHAST_NORTH_BODY_SHAPE = Block.box(3.0D, 6.0D, 3.0D, 13.0D, 15.5D, 13.0D);
    protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_SHAPE = Block.box(3.5D, 13.0D, 2.5D, 6.5D, 14.0D, 3.0D);
    protected static final VoxelShape GHAST_NORTH_LEFT_EYE_SHAPE = Block.box(8.5D, 13.0D, 2.5D, 11.5D, 14.0D, 3.0D);
    protected static final VoxelShape GHAST_NORTH_MOUTH_SHAPE = Block.box(6.5D, 9.0D, 2.5D, 8.5D, 10.0D, 3.0D);
    protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_TEAR_TOP_SHAPE = Block.box(4.0D, 12.0D, 2.5D, 6.0D, 13.0D,
            3.0D);
    protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_TEAR_MIDDLE_SHAPE = Block.box(4.0D, 11.0D, 2.5D, 5.0D,
            12.0D, 3.0D);
    protected static final VoxelShape GHAST_NORTH_RIGHT_EYE_TEAR_BOTTOM_SHAPE = Block.box(4.0D, 9.0D, 2.5D, 5.0D, 10.0D,
            3.0D);
    protected static final VoxelShape GHAST_NORTH_LEFT_EYE_TEAR_TOP_SHAPE = Block.box(9.0D, 12.0D, 2.5D, 11.0D, 13.0D,
            3.0D);
    protected static final VoxelShape GHAST_NORTH_LEFT_EYE_TEAR_MIDDLE_SHAPE = Block.box(10.0D, 10.0D, 2.5D, 11.0D,
            12.0D, 3.0D);
    protected static final VoxelShape GHAST_NORTH_LEFT_EYE_TEAR_BOTTOM_SHAPE = Block.box(10.0D, 8.0D, 2.5D, 11.0D, 9.0D,
            3.0D);
    protected static final VoxelShape GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_1_SHAPE = Block.box(4.0D, 3.0D, 4.0D, 5.0D,
            6.0D, 5.0D);
    protected static final VoxelShape GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_2_SHAPE = Block.box(4.0D, 2.0D, 5.0D, 5.0D,
            3.0D, 6.0D);
    protected static final VoxelShape GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_1_SHAPE = Block.box(4.0D, 2.0D, 8.0D,
            5.0D, 6.0D, 9.0D);
    protected static final VoxelShape GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_2_SHAPE = Block.box(4.0D, 1.0D, 9.0D,
            5.0D, 2.0D, 10.0D);
    protected static final VoxelShape GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_1_SHAPE = Block.box(4.0D, 3.0D, 11.0D, 5.0D,
            6.0D, 12.0D);
    protected static final VoxelShape GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_2_SHAPE = Block.box(4.0D, 2.0D, 12.0D, 5.0D,
            3.0D, 13.0D);
    protected static final VoxelShape GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_1_SHAPE = Block.box(7.5D, 3.0D, 4.0D,
            8.5D, 6.0D, 5.0D);
    protected static final VoxelShape GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_2_SHAPE = Block.box(7.5D, 2.0D, 5.0D,
            8.5D, 3.0D, 6.0D);
    protected static final VoxelShape GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_1_SHAPE = Block.box(7.5D, 3.0D, 8.0D,
            8.5D, 6.0D, 9.0D);
    protected static final VoxelShape GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_2_SHAPE = Block.box(7.5D, 2.0D, 9.0D,
            8.5D, 3.0D, 10.0D);
    protected static final VoxelShape GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_1_SHAPE = Block.box(7.5D, 2.0D, 11.0D,
            8.5D, 6.0D, 12.0D);
    protected static final VoxelShape GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_2_SHAPE = Block.box(7.5D, 1.0D, 12.0D,
            8.5D, 2.0D, 13.0D);
    protected static final VoxelShape GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_1_SHAPE = Block.box(11.0D, 3.0D, 4.0D,
            12.0D, 6.0D, 5.0D);
    protected static final VoxelShape GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_2_SHAPE = Block.box(11.0D, 2.0D, 5.0D,
            12.0D, 3.0D, 6.0D);
    protected static final VoxelShape GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_1_SHAPE = Block.box(11.0D, 2.0D, 8.0D,
            12.0D, 6.0D, 9.0D);
    protected static final VoxelShape GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_2_SHAPE = Block.box(11.0D, 1.0D, 9.0D,
            12.0D, 2.0D, 10.0D);
    protected static final VoxelShape GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_1_SHAPE = Block.box(11.0D, 3.0D, 11.0D,
            12.0D, 6.0D, 12.0D);
    protected static final VoxelShape GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_2_SHAPE = Block.box(11.0D, 2.0D, 12.0D,
            12.0D, 3.0D, 13.0D);

    protected static final VoxelShape GHAST_NORTH_EYE_SHAPES = VoxelShapes.or(GHAST_NORTH_RIGHT_EYE_SHAPE,
            GHAST_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape GHAST_NORTH_RIGHT_TEAR_SHAPES = VoxelShapes.or(
            GHAST_NORTH_RIGHT_EYE_TEAR_TOP_SHAPE, GHAST_NORTH_RIGHT_EYE_TEAR_MIDDLE_SHAPE,
            GHAST_NORTH_RIGHT_EYE_TEAR_BOTTOM_SHAPE);

    protected static final VoxelShape GHAST_NORTH_LEFT_TEAR_SHAPES = VoxelShapes.or(GHAST_NORTH_LEFT_EYE_TEAR_TOP_SHAPE,
            GHAST_NORTH_LEFT_EYE_TEAR_MIDDLE_SHAPE, GHAST_NORTH_LEFT_EYE_TEAR_BOTTOM_SHAPE);

    protected static final VoxelShape GHAST_NORTH_RIGHT_TENTACLES_SHAPES = VoxelShapes.or(
            GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_1_SHAPE, GHAST_NORTH_FRONT_RIGHT_TENTACE_1_PART_2_SHAPE,
            GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_1_SHAPE, GHAST_NORTH_MIDDLE_RIGHT_TENTACE_2_PART_2_SHAPE,
            GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_1_SHAPE, GHAST_NORTH_BACK_RIGHT_TENTACE_3_PART_2_SHAPE);

    protected static final VoxelShape GHAST_NORTH_MIDDLE_TENTACLES_SHAPES = VoxelShapes.or(
            GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_1_SHAPE, GHAST_NORTH_FRONT_MIDDLE_TENTACE_1_PART_2_SHAPE,
            GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_1_SHAPE, GHAST_NORTH_MIDDLE_MIDDLE_TENTACE_2_PART_2_SHAPE,
            GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_1_SHAPE, GHAST_NORTH_BACK_MIDDLE_TENTACE_3_PART_2_SHAPE);

    protected static final VoxelShape GHAST_NORTH_LEFT_TENTACLES_SHAPES = VoxelShapes.or(
            GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_1_SHAPE, GHAST_NORTH_FRONT_LEFT_TENTACE_1_PART_2_SHAPE,
            GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_1_SHAPE, GHAST_NORTH_MIDDLE_LEFT_TENTACE_2_PART_2_SHAPE,
            GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_1_SHAPE, GHAST_NORTH_BACK_LEFT_TENTACE_3_PART_2_SHAPE);

    protected static final VoxelShape GHAST_NORTH_SHAPE = VoxelShapes.or(GHAST_NORTH_EYE_SHAPES,
            GHAST_NORTH_RIGHT_TEAR_SHAPES, GHAST_NORTH_LEFT_TEAR_SHAPES, GHAST_NORTH_RIGHT_TENTACLES_SHAPES,
            GHAST_NORTH_MIDDLE_TENTACLES_SHAPES, GHAST_NORTH_LEFT_TENTACLES_SHAPES, GHAST_NORTH_BODY_SHAPE,
            GHAST_NORTH_MOUTH_SHAPE);

    protected static final VoxelShape GHAST_SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, GHAST_NORTH_SHAPE);
    protected static final VoxelShape GHAST_WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, GHAST_NORTH_SHAPE);
    protected static final VoxelShape GHAST_EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, GHAST_NORTH_SHAPE);

    public VEGhastPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, GHAST_NORTH_SHAPE, GHAST_SOUTH_SHAPE, GHAST_WEST_SHAPE, GHAST_EAST_SHAPE);
    }
}
