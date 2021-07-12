package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VEGuardianPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_LEFT_HORN1_SHAPE = Block.box(10.5D, 7.0D, 5.0D, 12.5D, 9.5D, 6.0D);
    protected static final VoxelShape NORTH_LEFT_HORN2_SHAPE = Block.box(11.5D, 8.5D, 5.0D, 13.5D, 10.5D, 6.0D);
    protected static final VoxelShape NORTH_RIGHT_HORN1_SHAPE = Block.box(3.5D, 7.0D, 5.0D, 5.5D, 9.5D, 6.0D);
    protected static final VoxelShape NORTH_RIGHT_HORN2_SHAPE = Block.box(2.5D, 8.5D, 5.0D, 4.5D, 10.5D, 6.0D);
    protected static final VoxelShape NORTH_FRONT_HORN1_SHAPE = Block.box(7.5D, 7.0D, 1.0D, 8.5D, 9.5D, 3.0D);
    protected static final VoxelShape NORTH_FRONT_HORN2_SHAPE = Block.box(7.5D, 8.5D, 0.0D, 8.5D, 10.5D, 2.0D);
    protected static final VoxelShape NORTH_BACK_HORN1_SHAPE = Block.box(7.5D, 7.0D, 8.0D, 8.5D, 9.5D, 10.0D);
    protected static final VoxelShape NORTH_BACK_HORN2_SHAPE = Block.box(7.5D, 8.5D, 9.0D, 8.5D, 10.5D, 11.0D);
    protected static final VoxelShape NORTH_LEFT_BOTTOM_HORN_SHAPE = Block.box(10.5D, 0.0D, 5.0D, 12.0D, 2.0D, 6.0D);
    protected static final VoxelShape NORTH_RIGHT_BOTTOM_HORN_SHAPE = Block.box(4.0D, 0.0D, 5.0D, 5.5D, 2.0D, 6.0D);
    protected static final VoxelShape NORTH_FRONT_BOTTOM_HORN_SHAPE = Block.box(7.5D, 0.0D, 1.5D, 8.5D, 2.0D, 3.0D);
    protected static final VoxelShape NORTH_BACK_BOTTOM_HORN_SHAPE = Block.box(7.5D, 0.0D, 8.0D, 8.5D, 2.0D, 9.5D);
    protected static final VoxelShape NORTH_BODY1_SHAPE = Block.box(5.5D, 1.0D, 3.0D, 10.5D, 8.0D, 8.0D);
    protected static final VoxelShape NORTH_BODY2_SHAPE = Block.box(4.5D, 2.0D, 3.0D, 11.5D, 7.0D, 8.0D);
    protected static final VoxelShape NORTH_BODY3_SOLID_SHAPE = Block.box(5.5D, 2.0D, 2.0D, 10.5D, 7.0D, 9.0D);
    protected static final VoxelShape NORTH_EYE_HOLE_SHAPE = Block.box(6.5D, 4.0D, 2.0D, 9.5D, 5.0D, 3.0D);
    protected static final VoxelShape NORTH_EYE_SHAPE = Block.box(7.5D, 4.0D, 2.5D, 8.5D, 5.0D, 3.0D);
    protected static final VoxelShape NORTH_TAIL1_SHAPE = Block.box(6.5D, 3.0D, 9.0D, 9.5D, 6.0D, 10.0D);
    protected static final VoxelShape NORTH_TAIL2_SHAPE = Block.box(7.0D, 3.5D, 10.0D, 9.0D, 5.5D, 12.0D);
    protected static final VoxelShape NORTH_TAIL3_SHAPE = Block.box(7.5D, 4.0D, 12.0D, 8.5D, 5.0D, 13.0D);
    protected static final VoxelShape NORTH_TAIL_TIP1_SHAPE = Block.box(7.0D, 3.0D, 13.0D, 9.0D, 6.0D, 14.0D);
    protected static final VoxelShape NORTH_TAIL_TIP2_TOP_SHAPE = Block.box(7.0D, 6.0D, 14.0D, 9.0D, 7.0D, 15.0D);
    protected static final VoxelShape NORTH_TAIL_TIP2_MIDDLE_SHAPE = Block.box(7.5D, 3.0D, 14.0D, 8.5D, 6.0D, 15.0D);
    protected static final VoxelShape NORTH_TAIL_TIP2_BOTTOM_SHAPE = Block.box(7.0D, 2.0D, 14.0D, 9.0D, 3.0D, 15.0D);
    protected static final VoxelShape NORTH_TAIL_TIP3_END_TOP_SHAPE = Block.box(7.5D, 5.0D, 15.0D, 8.5D, 6.0D, 15.5D);
    protected static final VoxelShape NORTH_TAIL_TIP3_END_BOTTOM_SHAPE = Block.box(7.5D, 3.0D, 15.0D, 8.5D, 4.0D,
            15.5D);

    protected static final VoxelShape NORTH_BODY3_SHAPE = VEBoxUtil.cutBox(NORTH_BODY3_SOLID_SHAPE,
            NORTH_EYE_HOLE_SHAPE);

    protected static final VoxelShape NORTH_HORNS_SHAPE = VoxelShapes.or(NORTH_LEFT_HORN1_SHAPE, NORTH_LEFT_HORN2_SHAPE,
            NORTH_RIGHT_HORN1_SHAPE, NORTH_RIGHT_HORN2_SHAPE, NORTH_FRONT_HORN1_SHAPE, NORTH_FRONT_HORN2_SHAPE,
            NORTH_BACK_HORN1_SHAPE, NORTH_BACK_HORN2_SHAPE, NORTH_LEFT_BOTTOM_HORN_SHAPE, NORTH_RIGHT_BOTTOM_HORN_SHAPE,
            NORTH_FRONT_BOTTOM_HORN_SHAPE, NORTH_BACK_BOTTOM_HORN_SHAPE);

    protected static final VoxelShape NORTH_BODY_SHAPE = VoxelShapes.or(NORTH_BODY1_SHAPE, NORTH_BODY2_SHAPE,
            NORTH_BODY3_SHAPE);

    protected static final VoxelShape NORTH_TAIL_SHAPE = VoxelShapes.or(NORTH_TAIL1_SHAPE, NORTH_TAIL2_SHAPE,
            NORTH_TAIL3_SHAPE);

    protected static final VoxelShape NORTH_TAIL_TIP_SHAPE = VoxelShapes.or(NORTH_TAIL_TIP1_SHAPE,
            NORTH_TAIL_TIP2_TOP_SHAPE, NORTH_TAIL_TIP2_MIDDLE_SHAPE, NORTH_TAIL_TIP2_BOTTOM_SHAPE,
            NORTH_TAIL_TIP3_END_TOP_SHAPE, NORTH_TAIL_TIP3_END_BOTTOM_SHAPE);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_HORNS_SHAPE,
            NORTH_TAIL_SHAPE, NORTH_TAIL_TIP_SHAPE, NORTH_EYE_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(NORTH_SHAPE);

    public VEGuardianPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
