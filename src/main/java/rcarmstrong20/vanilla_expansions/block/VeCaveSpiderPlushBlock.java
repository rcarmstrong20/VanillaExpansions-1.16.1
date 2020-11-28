package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.util.VeCollisionUtil;

public class VeCaveSpiderPlushBlock extends VePlushBlock
{
    protected static final VoxelShape CAVE_SPIDER_NORTH_BODY_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 10.0D,
            4.0D, 13.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_HEAD_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 2.5D, 9.0D, 3.0D,
            5.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_FANG_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 2.0D, 7.0D,
            0.5D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_FANG_SHAPE = Block.makeCuboidShape(8.0D, 0.0D, 2.0D, 8.5D,
            0.5D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(7.0D, 0.5D, 2.0D, 8.0D,
            1.0D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_MIDDLE_EYEBROW_SHAPE = Block.makeCuboidShape(7.0D, 1.5D, 2.0D,
            8.0D, 2.0D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_EYEBROW_SHAPE = Block.makeCuboidShape(6.5D, 2.0D, 2.0D,
            7.0D, 2.5D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_EYEBROW_SHAPE = Block.makeCuboidShape(8.0D, 2.0D, 2.0D,
            8.5D, 2.5D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 1.0D, 2.0D, 6.5D,
            1.5D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 1.0D, 2.0D, 9.0D,
            1.5D, 2.5D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG1_TOP_SHAPE = Block.makeCuboidShape(3.0D, 1.0D, 5.0D,
            5.0D, 2.0D, 6.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG2_TOP_SHAPE = Block.makeCuboidShape(3.0D, 1.0D, 7.0D,
            5.0D, 2.0D, 8.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG3_TOP_SHAPE = Block.makeCuboidShape(3.5D, 1.0D, 9.0D,
            5.0D, 2.0D, 10.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG4_TOP_SHAPE = Block.makeCuboidShape(4.0D, 1.0D, 11.0D,
            5.0D, 2.0D, 12.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG1_BOTTOM_SHAPE = Block.makeCuboidShape(2.0D, 0.0D,
            6.0D, 3.0D, 1.0D, 7.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG2_BOTTOM_SHAPE = Block.makeCuboidShape(2.0D, 0.0D,
            8.0D, 3.0D, 1.0D, 9.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG3_BOTTOM_SHAPE = Block.makeCuboidShape(2.5D, 0.0D,
            10.0D, 3.5D, 1.0D, 11.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEG4_BOTTOM_SHAPE = Block.makeCuboidShape(3.0D, 0.0D,
            12.0D, 4.0D, 1.0D, 13.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG1_TOP_SHAPE = Block.makeCuboidShape(10.0D, 1.0D, 5.0D,
            12.0D, 2.0D, 6.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG2_TOP_SHAPE = Block.makeCuboidShape(10.0D, 1.0D, 7.0D,
            12.0D, 2.0D, 8.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG3_TOP_SHAPE = Block.makeCuboidShape(10.0D, 1.0D, 9.0D,
            11.5D, 2.0D, 10.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG4_TOP_SHAPE = Block.makeCuboidShape(10.0D, 1.0D, 11.0D,
            11.0D, 2.0D, 12.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG1_BOTTOM_SHAPE = Block.makeCuboidShape(12.0D, 0.0D,
            6.0D, 13.0D, 1.0D, 7.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG2_BOTTOM_SHAPE = Block.makeCuboidShape(12.0D, 0.0D,
            8.0D, 13.0D, 1.0D, 9.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG3_BOTTOM_SHAPE = Block.makeCuboidShape(11.5D, 0.0D,
            10.0D, 12.5D, 1.0D, 11.0D);
    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEG4_BOTTOM_SHAPE = Block.makeCuboidShape(11.0D, 0.0D,
            12.0D, 12.0D, 1.0D, 13.0D);

    protected static final VoxelShape CAVE_SPIDER_NORTH_MOUTH_AND_FANGS_SHAPE = VoxelShapes
            .or(CAVE_SPIDER_NORTH_RIGHT_FANG_SHAPE, CAVE_SPIDER_NORTH_LEFT_FANG_SHAPE, CAVE_SPIDER_NORTH_MOUTH_SHAPE);

    protected static final VoxelShape CAVE_SPIDER_NORTH_EYEBROW_SHAPE = VoxelShapes.or(
            CAVE_SPIDER_NORTH_MIDDLE_EYEBROW_SHAPE, CAVE_SPIDER_NORTH_RIGHT_EYEBROW_SHAPE,
            CAVE_SPIDER_NORTH_LEFT_EYEBROW_SHAPE);

    protected static final VoxelShape CAVE_SPIDER_NORTH_EYES_SHAPE = VoxelShapes.or(CAVE_SPIDER_NORTH_RIGHT_EYE_SHAPE,
            CAVE_SPIDER_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape CAVE_SPIDER_NORTH_RIGHT_LEGS_SHAPE = VoxelShapes.or(
            CAVE_SPIDER_NORTH_RIGHT_LEG1_TOP_SHAPE, CAVE_SPIDER_NORTH_RIGHT_LEG2_TOP_SHAPE,
            CAVE_SPIDER_NORTH_RIGHT_LEG3_TOP_SHAPE, CAVE_SPIDER_NORTH_RIGHT_LEG4_TOP_SHAPE,
            CAVE_SPIDER_NORTH_RIGHT_LEG1_BOTTOM_SHAPE, CAVE_SPIDER_NORTH_RIGHT_LEG2_BOTTOM_SHAPE,
            CAVE_SPIDER_NORTH_RIGHT_LEG3_BOTTOM_SHAPE, CAVE_SPIDER_NORTH_RIGHT_LEG4_BOTTOM_SHAPE);

    protected static final VoxelShape CAVE_SPIDER_NORTH_LEFT_LEGS_SHAPE = VoxelShapes.or(
            CAVE_SPIDER_NORTH_LEFT_LEG1_TOP_SHAPE, CAVE_SPIDER_NORTH_LEFT_LEG2_TOP_SHAPE,
            CAVE_SPIDER_NORTH_LEFT_LEG3_TOP_SHAPE, CAVE_SPIDER_NORTH_LEFT_LEG4_TOP_SHAPE,
            CAVE_SPIDER_NORTH_LEFT_LEG1_BOTTOM_SHAPE, CAVE_SPIDER_NORTH_LEFT_LEG2_BOTTOM_SHAPE,
            CAVE_SPIDER_NORTH_LEFT_LEG3_BOTTOM_SHAPE, CAVE_SPIDER_NORTH_LEFT_LEG4_BOTTOM_SHAPE);

    protected static final VoxelShape CAVE_SPIDER_NORTH_SHAPE = VoxelShapes.or(CAVE_SPIDER_NORTH_BODY_SHAPE,
            CAVE_SPIDER_NORTH_HEAD_SHAPE, CAVE_SPIDER_NORTH_MOUTH_AND_FANGS_SHAPE, CAVE_SPIDER_NORTH_EYEBROW_SHAPE,
            CAVE_SPIDER_NORTH_EYES_SHAPE, CAVE_SPIDER_NORTH_RIGHT_LEGS_SHAPE, CAVE_SPIDER_NORTH_LEFT_LEGS_SHAPE);

    protected static final VoxelShape CAVE_SPIDER_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y,
            CAVE_SPIDER_NORTH_SHAPE);
    protected static final VoxelShape CAVE_SPIDER_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y,
            CAVE_SPIDER_NORTH_SHAPE);
    protected static final VoxelShape CAVE_SPIDER_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y,
            CAVE_SPIDER_NORTH_SHAPE);

    public VeCaveSpiderPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, CAVE_SPIDER_NORTH_SHAPE, CAVE_SPIDER_SOUTH_SHAPE, CAVE_SPIDER_WEST_SHAPE,
                CAVE_SPIDER_EAST_SHAPE);
    }
}
