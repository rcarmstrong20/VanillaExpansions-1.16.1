package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VESpiderPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape SPIDER_NORTH_BODY_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 5.5D, 14.0D);
    protected static final VoxelShape SPIDER_NORTH_HEAD_SHAPE = Block.box(5.5D, 0.0D, 1.5D, 10.5D, 4.5D, 5.0D);
    protected static final VoxelShape SPIDER_NORTH_MIDDLE_EYE_SHAPE = Block.box(7.0D, 1.5D, 1.0D, 9.0D, 2.5D, 1.5D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_EYE_SHAPE = Block.box(9.5D, 2.0D, 1.0D, 10.5D, 3.0D, 1.5D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_EYE_SHAPE = Block.box(5.5D, 2.0D, 1.0D, 6.5D, 3.0D, 1.5D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_BOTTOM_EYEBROW_SHAPE = Block.box(7.0D, 3.0D, 1.0D, 7.5D, 3.5D,
            1.5D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_TOP_EYEBROW_SHAPE = Block.box(6.5D, 3.5D, 1.0D, 7.0D, 4.0D,
            1.5D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_BOTTOM_EYEBROW_SHAPE = Block.box(8.5D, 3.0D, 1.0D, 9.0D, 3.5D,
            1.5D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_TOP_EYEBROW_SHAPE = Block.box(9.0D, 3.5D, 1.0D, 9.5D, 4.0D,
            1.5D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_FANG_SHAPE = Block.box(6.5D, 0.0D, 1.0D, 7.0D, 1.0D, 1.5D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_FANG_SHAPE = Block.box(9.0D, 0.0D, 1.0D, 9.5D, 1.0D, 1.5D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG1_TOP_SHAPE = Block.box(2.0D, 1.0D, 5.0D, 5.0D, 2.0D, 6.0D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG1_BOTTOM_SHAPE = Block.box(1.0D, 0.0D, 6.0D, 2.0D, 1.0D,
            7.0D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG2_TOP_SHAPE = Block.box(2.0D, 1.0D, 7.0D, 5.0D, 2.0D, 8.0D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG2_BOTTOM_SHAPE = Block.box(1.0D, 0.0D, 8.0D, 2.0D, 1.0D,
            9.0D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG3_TOP_SHAPE = Block.box(2.0D, 1.0D, 9.0D, 5.0D, 2.0D,
            10.0D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG3_BOTTOM_SHAPE = Block.box(1.0D, 0.0D, 10.0D, 2.0D, 1.0D,
            11.0D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG4_TOP_SHAPE = Block.box(3.0D, 1.0D, 11.0D, 5.0D, 2.0D,
            12.0D);
    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEG4_BOTTOM_SHAPE = Block.box(2.0D, 0.0D, 12.0D, 3.0D, 1.0D,
            13.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG1_TOP_SHAPE = Block.box(11.0D, 1.0D, 5.0D, 14.0D, 2.0D,
            6.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG1_BOTTOM_SHAPE = Block.box(14.0D, 0.0D, 6.0D, 15.0D, 1.0D,
            7.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG2_TOP_SHAPE = Block.box(11.0D, 1.0D, 7.0D, 14.0D, 2.0D,
            8.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG2_BOTTOM_SHAPE = Block.box(14.0D, 0.0D, 8.0D, 15.0D, 1.0D,
            9.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG3_TOP_SHAPE = Block.box(11.0D, 1.0D, 9.0D, 14.0D, 2.0D,
            10.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG3_BOTTOM_SHAPE = Block.box(14.0D, 0.0D, 10.0D, 15.0D, 1.0D,
            11.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG4_TOP_SHAPE = Block.box(11.0D, 1.0D, 11.0D, 13.0D, 2.0D,
            12.0D);
    protected static final VoxelShape SPIDER_NORTH_LEFT_LEG4_BOTTOM_SHAPE = Block.box(13.0D, 0.0D, 12.0D, 14.0D, 1.0D,
            13.0D);

    protected static final VoxelShape SPIDER_NORTH_EYES_SHAPE = VoxelShapes.or(SPIDER_NORTH_MIDDLE_EYE_SHAPE,
            SPIDER_NORTH_RIGHT_EYE_SHAPE, SPIDER_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape SPIDER_NORTH_EYEBROWS_SHAPE = VoxelShapes.or(
            SPIDER_NORTH_RIGHT_BOTTOM_EYEBROW_SHAPE, SPIDER_NORTH_RIGHT_TOP_EYEBROW_SHAPE,
            SPIDER_NORTH_LEFT_BOTTOM_EYEBROW_SHAPE, SPIDER_NORTH_LEFT_TOP_EYEBROW_SHAPE);

    protected static final VoxelShape SPIDER_NORTH_FANGS_SHAPE = VoxelShapes.or(SPIDER_NORTH_RIGHT_FANG_SHAPE,
            SPIDER_NORTH_LEFT_FANG_SHAPE);

    protected static final VoxelShape SPIDER_NORTH_RIGHT_LEGS_SHAPE = VoxelShapes.or(SPIDER_NORTH_RIGHT_LEG1_TOP_SHAPE,
            SPIDER_NORTH_RIGHT_LEG1_BOTTOM_SHAPE, SPIDER_NORTH_RIGHT_LEG2_TOP_SHAPE,
            SPIDER_NORTH_RIGHT_LEG2_BOTTOM_SHAPE, SPIDER_NORTH_RIGHT_LEG3_TOP_SHAPE,
            SPIDER_NORTH_RIGHT_LEG3_BOTTOM_SHAPE, SPIDER_NORTH_RIGHT_LEG4_TOP_SHAPE,
            SPIDER_NORTH_RIGHT_LEG4_BOTTOM_SHAPE);

    protected static final VoxelShape SPIDER_NORTH_LEFT_LEGS_SHAPE = VoxelShapes.or(SPIDER_NORTH_LEFT_LEG1_TOP_SHAPE,
            SPIDER_NORTH_LEFT_LEG1_BOTTOM_SHAPE, SPIDER_NORTH_LEFT_LEG2_TOP_SHAPE, SPIDER_NORTH_LEFT_LEG2_BOTTOM_SHAPE,
            SPIDER_NORTH_LEFT_LEG3_TOP_SHAPE, SPIDER_NORTH_LEFT_LEG3_BOTTOM_SHAPE, SPIDER_NORTH_LEFT_LEG4_TOP_SHAPE,
            SPIDER_NORTH_LEFT_LEG4_BOTTOM_SHAPE);

    protected static final VoxelShape SPIDER_NORTH_SHAPE = VoxelShapes.or(SPIDER_NORTH_BODY_SHAPE,
            SPIDER_NORTH_HEAD_SHAPE, SPIDER_NORTH_EYES_SHAPE, SPIDER_NORTH_EYEBROWS_SHAPE, SPIDER_NORTH_FANGS_SHAPE,
            SPIDER_NORTH_RIGHT_LEGS_SHAPE, SPIDER_NORTH_LEFT_LEGS_SHAPE);

    protected static final VoxelShape SPIDER_SOUTH_SHAPE = VEBoxUtil.rotate180(SPIDER_NORTH_SHAPE);
    protected static final VoxelShape SPIDER_WEST_SHAPE = VEBoxUtil.rotate270(SPIDER_NORTH_SHAPE);
    protected static final VoxelShape SPIDER_EAST_SHAPE = VEBoxUtil.rotate90(SPIDER_NORTH_SHAPE);

    public VESpiderPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, SPIDER_NORTH_SHAPE, SPIDER_SOUTH_SHAPE, SPIDER_WEST_SHAPE, SPIDER_EAST_SHAPE);
    }
}
