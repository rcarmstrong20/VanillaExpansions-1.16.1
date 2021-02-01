package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.util.VeShapeUtil;

public class VeBeePlushBlock extends VePlushBlock
{
    protected static final VoxelShape NORTH_BODY_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 2.0D, 11.5D, 6.0D, 14.0D);
    protected static final VoxelShape NORTH_STINGER_SHAPE = Block.makeCuboidShape(7.5D, 1.0D, 14.0D, 8.5D, 2.0D, 15.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(4.0D, 2.0D, 1.5D, 6.5D, 4.5D, 3.0D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.5D, 2.0D, 1.5D, 12.0D, 4.5D, 3.0D);
    protected static final VoxelShape NORTH_RIGHT_ANTENNA_BOTTOM_SHAPE = Block.makeCuboidShape(6.5D, 5.0D, 1.0D, 7.5D,
            6.0D, 2.0D);
    protected static final VoxelShape NORTH_RIGHT_ANTENNA_TOP_SHAPE = Block.makeCuboidShape(6.5D, 6.0D, 0.0D, 7.5D,
            7.0D, 1.0D);
    protected static final VoxelShape NORTH_LEFT_ANTENNA_BOTTOM_SHAPE = Block.makeCuboidShape(9.0D, 5.0D, 1.0D, 10.0D,
            6.0D, 2.0D);
    protected static final VoxelShape NORTH_LEFT_ANTENNA_TOP_SHAPE = Block.makeCuboidShape(9.0D, 6.0D, 0.0D, 10.0D,
            7.0D, 1.0D);
    protected static final VoxelShape NORTH_FRONT_MAIN_RIGHT_LEG_SHAPE = Block.makeCuboidShape(2.5D, 0.0D, 5.0D, 4.5D,
            1.0D, 6.0D);
    protected static final VoxelShape NORTH_FRONT_BEND_RIGHT_LEG_SHAPE = Block.makeCuboidShape(1.5D, 0.0D, 4.0D, 2.5D,
            1.0D, 5.0D);
    protected static final VoxelShape NORTH_MIDDLE_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(2.5D, 0.0D, 8.0D, 4.5D,
            1.0D, 9.0D);
    protected static final VoxelShape NORTH_MIDDLE_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(3.5D, 0.0D, 9.0D, 4.5D,
            1.0D, 10.0D);
    protected static final VoxelShape NORTH_BACK_MAIN_RIGHT_LEG_SHAPE = Block.makeCuboidShape(2.5D, 0.0D, 11.0D, 4.5D,
            1.0D, 12.0D);
    protected static final VoxelShape NORTH_BACK_BEND_RIGHT_LEG_SHAPE = Block.makeCuboidShape(1.5D, 0.0D, 12.0D, 2.5D,
            1.0D, 13.0D);
    protected static final VoxelShape NORTH_FRONT_MAIN_LEFT_LEG_SHAPE = Block.makeCuboidShape(11.5D, 0.0D, 5.0D, 13.5D,
            1.0D, 6.0D);
    protected static final VoxelShape NORTH_FRONT_BEND_LEFT_LEG_SHAPE = Block.makeCuboidShape(13.5D, 0.0D, 4.0D, 14.5D,
            1.0D, 5.0D);
    protected static final VoxelShape NORTH_MIDDLE_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(11.5D, 0.0D, 8.0D,
            13.5D, 1.0D, 9.0D);
    protected static final VoxelShape NORTH_MIDDLE_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(10.5D, 0.0D, 9.0D, 12.5D,
            1.0D, 10.0D);
    protected static final VoxelShape NORTH_BACK_MAIN_LEFT_LEG_SHAPE = Block.makeCuboidShape(11.5D, 0.0D, 11.0D, 13.5D,
            1.0D, 12.0D);
    protected static final VoxelShape NORTH_BACK_BEND_LEFT_LEG_SHAPE = Block.makeCuboidShape(13.5D, 0.0D, 12.0D, 14.5D,
            1.0D, 13.0D);
    protected static final VoxelShape NORTH_RIGHT_WING_MAIN_OUTSIDE_SHAPE = Block.makeCuboidShape(2.5D, 6.0D, 5.0D,
            7.0D, 7.0D, 9.0D);
    protected static final VoxelShape NORTH_RIGHT_WING_MAIN_INSIDE_SHAPE = Block.makeCuboidShape(3.5D, 6.0D, 6.0D, 6.0D,
            7.0D, 9.0D);
    protected static final VoxelShape NORTH_RIGHT_WING_END_SHAPE = Block.makeCuboidShape(2.5D, 6.0D, 9.0D, 6.0D, 7.0D,
            10.0D);
    protected static final VoxelShape NORTH_LEFT_WING_MAIN_OUTSIDE_SHAPE = Block.makeCuboidShape(9.0D, 6.0D, 5.0D,
            13.5D, 7.0D, 9.0D);
    protected static final VoxelShape NORTH_LEFT_WING_MAIN_INSIDE_SHAPE = Block.makeCuboidShape(10.0D, 6.0D, 6.0D,
            12.5D, 7.0D, 9.0D);
    protected static final VoxelShape NORTH_LEFT_WING_END_SHAPE = Block.makeCuboidShape(10.0D, 6.0D, 9.0D, 13.5D, 7.0D,
            10.0D);

    protected static final VoxelShape NORTH_RIGHT_MAIN_WING_SHAPE = VePlushBlock
            .cutShape(NORTH_RIGHT_WING_MAIN_OUTSIDE_SHAPE, NORTH_RIGHT_WING_MAIN_INSIDE_SHAPE);
    protected static final VoxelShape NORTH_LEFT_MAIN_WING_SHAPE = VePlushBlock
            .cutShape(NORTH_LEFT_WING_MAIN_OUTSIDE_SHAPE, NORTH_LEFT_WING_MAIN_INSIDE_SHAPE);

    protected static final VoxelShape NORTH_EYES_SHAPE = VoxelShapes.or(NORTH_RIGHT_EYE_SHAPE, NORTH_LEFT_EYE_SHAPE);
    protected static final VoxelShape NORTH_ANTENNAS_SHAPE = VoxelShapes.or(NORTH_RIGHT_ANTENNA_BOTTOM_SHAPE,
            NORTH_RIGHT_ANTENNA_TOP_SHAPE, NORTH_LEFT_ANTENNA_BOTTOM_SHAPE, NORTH_LEFT_ANTENNA_TOP_SHAPE);
    protected static final VoxelShape NORTH_RIGHT_LEGS_SHAPE = VoxelShapes.or(NORTH_FRONT_MAIN_RIGHT_LEG_SHAPE,
            NORTH_FRONT_BEND_RIGHT_LEG_SHAPE, NORTH_MIDDLE_FRONT_RIGHT_LEG_SHAPE, NORTH_MIDDLE_BACK_RIGHT_LEG_SHAPE,
            NORTH_BACK_MAIN_RIGHT_LEG_SHAPE, NORTH_BACK_BEND_RIGHT_LEG_SHAPE);
    protected static final VoxelShape NORTH_LEFT_LEGS_SHAPE = VoxelShapes.or(NORTH_FRONT_MAIN_LEFT_LEG_SHAPE,
            NORTH_FRONT_BEND_LEFT_LEG_SHAPE, NORTH_MIDDLE_FRONT_LEFT_LEG_SHAPE, NORTH_MIDDLE_BACK_LEFT_LEG_SHAPE,
            NORTH_BACK_MAIN_LEFT_LEG_SHAPE, NORTH_BACK_BEND_LEFT_LEG_SHAPE);
    protected static final VoxelShape NORTH_RIGHT_WING_SHAPE = VoxelShapes.or(NORTH_RIGHT_MAIN_WING_SHAPE,
            NORTH_RIGHT_WING_END_SHAPE);
    protected static final VoxelShape NORTH_LEFT_WING_SHAPE = VoxelShapes.or(NORTH_LEFT_MAIN_WING_SHAPE,
            NORTH_LEFT_WING_END_SHAPE);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_STINGER_SHAPE,
            NORTH_EYES_SHAPE, NORTH_ANTENNAS_SHAPE, NORTH_RIGHT_LEGS_SHAPE, NORTH_LEFT_LEGS_SHAPE,
            NORTH_RIGHT_WING_SHAPE, NORTH_LEFT_WING_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VeShapeUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VeShapeUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VeShapeUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VeBeePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
