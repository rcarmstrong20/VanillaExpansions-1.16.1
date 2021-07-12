package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VECaveSpiderPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_BODY_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 10.0D, 4.0D, 13.0D);
    protected static final VoxelShape NORTH_HEAD_SHAPE = Block.box(6.0D, 0.0D, 2.5D, 9.0D, 3.0D, 5.0D);
    protected static final VoxelShape NORTH_RIGHT_FANG_SHAPE = Block.box(6.5D, 0.0D, 2.0D, 7.0D, 0.5D, 2.5D);
    protected static final VoxelShape NORTH_LEFT_FANG_SHAPE = Block.box(8.0D, 0.0D, 2.0D, 8.5D, 0.5D, 2.5D);
    protected static final VoxelShape NORTH_MOUTH_SHAPE = Block.box(7.0D, 0.5D, 2.0D, 8.0D, 1.0D, 2.5D);
    protected static final VoxelShape NORTH_MIDDLE_EYEBROW_SHAPE = Block.box(7.0D, 1.5D, 2.0D, 8.0D, 2.0D, 2.5D);
    protected static final VoxelShape NORTH_RIGHT_EYEBROW_SHAPE = Block.box(6.5D, 2.0D, 2.0D, 7.0D, 2.5D, 2.5D);
    protected static final VoxelShape NORTH_LEFT_EYEBROW_SHAPE = Block.box(8.0D, 2.0D, 2.0D, 8.5D, 2.5D, 2.5D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.box(6.0D, 1.0D, 2.0D, 6.5D, 1.5D, 2.5D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.box(8.5D, 1.0D, 2.0D, 9.0D, 1.5D, 2.5D);
    protected static final VoxelShape NORTH_RIGHT_LEG1_TOP_SHAPE = Block.box(3.0D, 1.0D, 5.0D, 5.0D, 2.0D, 6.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG2_TOP_SHAPE = Block.box(3.0D, 1.0D, 7.0D, 5.0D, 2.0D, 8.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG3_TOP_SHAPE = Block.box(3.5D, 1.0D, 9.0D, 5.0D, 2.0D, 10.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG4_TOP_SHAPE = Block.box(4.0D, 1.0D, 11.0D, 5.0D, 2.0D, 12.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG1_BOTTOM_SHAPE = Block.box(2.0D, 0.0D, 6.0D, 3.0D, 1.0D, 7.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG2_BOTTOM_SHAPE = Block.box(2.0D, 0.0D, 8.0D, 3.0D, 1.0D, 9.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG3_BOTTOM_SHAPE = Block.box(2.5D, 0.0D, 10.0D, 3.5D, 1.0D, 11.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG4_BOTTOM_SHAPE = Block.box(3.0D, 0.0D, 12.0D, 4.0D, 1.0D, 13.0D);
    protected static final VoxelShape NORTH_LEFT_LEG1_TOP_SHAPE = Block.box(10.0D, 1.0D, 5.0D, 12.0D, 2.0D, 6.0D);
    protected static final VoxelShape NORTH_LEFT_LEG2_TOP_SHAPE = Block.box(10.0D, 1.0D, 7.0D, 12.0D, 2.0D, 8.0D);
    protected static final VoxelShape NORTH_LEFT_LEG3_TOP_SHAPE = Block.box(10.0D, 1.0D, 9.0D, 11.5D, 2.0D, 10.0D);
    protected static final VoxelShape NORTH_LEFT_LEG4_TOP_SHAPE = Block.box(10.0D, 1.0D, 11.0D, 11.0D, 2.0D, 12.0D);
    protected static final VoxelShape NORTH_LEFT_LEG1_BOTTOM_SHAPE = Block.box(12.0D, 0.0D, 6.0D, 13.0D, 1.0D, 7.0D);
    protected static final VoxelShape NORTH_LEFT_LEG2_BOTTOM_SHAPE = Block.box(12.0D, 0.0D, 8.0D, 13.0D, 1.0D, 9.0D);
    protected static final VoxelShape NORTH_LEFT_LEG3_BOTTOM_SHAPE = Block.box(11.5D, 0.0D, 10.0D, 12.5D, 1.0D, 11.0D);
    protected static final VoxelShape NORTH_LEFT_LEG4_BOTTOM_SHAPE = Block.box(11.0D, 0.0D, 12.0D, 12.0D, 1.0D, 13.0D);

    protected static final VoxelShape NORTH_MOUTH_AND_FANGS_SHAPE = VoxelShapes.or(NORTH_RIGHT_FANG_SHAPE,
            NORTH_LEFT_FANG_SHAPE, NORTH_MOUTH_SHAPE);
    protected static final VoxelShape NORTH_EYEBROW_SHAPE = VoxelShapes.or(NORTH_MIDDLE_EYEBROW_SHAPE,
            NORTH_RIGHT_EYEBROW_SHAPE, NORTH_LEFT_EYEBROW_SHAPE);
    protected static final VoxelShape NORTH_EYES_SHAPE = VoxelShapes.or(NORTH_RIGHT_EYE_SHAPE, NORTH_LEFT_EYE_SHAPE);
    protected static final VoxelShape NORTH_RIGHT_LEGS_SHAPE = VoxelShapes.or(NORTH_RIGHT_LEG1_TOP_SHAPE,
            NORTH_RIGHT_LEG2_TOP_SHAPE, NORTH_RIGHT_LEG3_TOP_SHAPE, NORTH_RIGHT_LEG4_TOP_SHAPE,
            NORTH_RIGHT_LEG1_BOTTOM_SHAPE, NORTH_RIGHT_LEG2_BOTTOM_SHAPE, NORTH_RIGHT_LEG3_BOTTOM_SHAPE,
            NORTH_RIGHT_LEG4_BOTTOM_SHAPE);
    protected static final VoxelShape NORTH_LEFT_LEGS_SHAPE = VoxelShapes.or(NORTH_LEFT_LEG1_TOP_SHAPE,
            NORTH_LEFT_LEG2_TOP_SHAPE, NORTH_LEFT_LEG3_TOP_SHAPE, NORTH_LEFT_LEG4_TOP_SHAPE,
            NORTH_LEFT_LEG1_BOTTOM_SHAPE, NORTH_LEFT_LEG2_BOTTOM_SHAPE, NORTH_LEFT_LEG3_BOTTOM_SHAPE,
            NORTH_LEFT_LEG4_BOTTOM_SHAPE);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_HEAD_SHAPE,
            NORTH_MOUTH_AND_FANGS_SHAPE, NORTH_EYEBROW_SHAPE, NORTH_EYES_SHAPE, NORTH_RIGHT_LEGS_SHAPE,
            NORTH_LEFT_LEGS_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(NORTH_SHAPE);

    public VECaveSpiderPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
