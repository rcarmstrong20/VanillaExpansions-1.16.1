package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.block.util.VEBoxBlockUtil;

public class VEBeePlushBlock extends VEPlushBlock
{
    protected static final VoxelShape BODY = Block.box(4.5D, 0.0D, 2.0D, 11.5D, 6.0D, 14.0D);
    protected static final VoxelShape STINGER = Block.box(7.5D, 1.0D, 14.0D, 8.5D, 2.0D, 15.0D);
    protected static final VoxelShape RIGHT_EYE = Block.box(4.0D, 2.0D, 1.5D, 6.5D, 4.5D, 3.0D);
    protected static final VoxelShape LEFT_EYE = Block.box(9.5D, 2.0D, 1.5D, 12.0D, 4.5D, 3.0D);
    protected static final VoxelShape RIGHT_ANTENNA_BOTTOM = Block.box(6.5D, 5.0D, 1.0D, 7.5D, 6.0D, 2.0D);
    protected static final VoxelShape RIGHT_ANTENNA_TOP = Block.box(6.5D, 6.0D, 0.0D, 7.5D, 7.0D, 1.0D);
    protected static final VoxelShape LEFT_ANTENNA_BOTTOM = Block.box(9.0D, 5.0D, 1.0D, 10.0D, 6.0D, 2.0D);
    protected static final VoxelShape LEFT_ANTENNA_TOP = Block.box(9.0D, 6.0D, 0.0D, 10.0D, 7.0D, 1.0D);
    protected static final VoxelShape FRONT_MAIN_RIGHT_LEG = Block.box(2.5D, 0.0D, 5.0D, 4.5D, 1.0D, 6.0D);
    protected static final VoxelShape FRONT_BEND_RIGHT_LEG = Block.box(1.5D, 0.0D, 4.0D, 2.5D, 1.0D, 5.0D);
    protected static final VoxelShape MIDDLE_FRONT_RIGHT_LEG = Block.box(2.5D, 0.0D, 8.0D, 4.5D, 1.0D, 9.0D);
    protected static final VoxelShape MIDDLE_BACK_RIGHT_LEG = Block.box(3.5D, 0.0D, 9.0D, 4.5D, 1.0D, 10.0D);
    protected static final VoxelShape BACK_MAIN_RIGHT_LEG = Block.box(2.5D, 0.0D, 11.0D, 4.5D, 1.0D, 12.0D);
    protected static final VoxelShape BACK_BEND_RIGHT_LEG = Block.box(1.5D, 0.0D, 12.0D, 2.5D, 1.0D, 13.0D);
    protected static final VoxelShape FRONT_MAIN_LEFT_LEG = Block.box(11.5D, 0.0D, 5.0D, 13.5D, 1.0D, 6.0D);
    protected static final VoxelShape FRONT_BEND_LEFT_LEG = Block.box(13.5D, 0.0D, 4.0D, 14.5D, 1.0D, 5.0D);
    protected static final VoxelShape MIDDLE_FRONT_LEFT_LEG = Block.box(11.5D, 0.0D, 8.0D, 13.5D, 1.0D, 9.0D);
    protected static final VoxelShape MIDDLE_BACK_LEFT_LEG = Block.box(10.5D, 0.0D, 9.0D, 12.5D, 1.0D, 10.0D);
    protected static final VoxelShape BACK_MAIN_LEFT_LEG = Block.box(11.5D, 0.0D, 11.0D, 13.5D, 1.0D, 12.0D);
    protected static final VoxelShape BACK_BEND_LEFT_LEG = Block.box(13.5D, 0.0D, 12.0D, 14.5D, 1.0D, 13.0D);
    protected static final VoxelShape RIGHT_WING_MAIN_OUTSIDE = Block.box(2.5D, 6.0D, 5.0D, 7.0D, 7.0D, 9.0D);
    protected static final VoxelShape RIGHT_WING_MAIN_INSIDE = Block.box(3.5D, 6.0D, 6.0D, 6.0D, 7.0D, 9.0D);
    protected static final VoxelShape RIGHT_WING_END = Block.box(2.5D, 6.0D, 9.0D, 6.0D, 7.0D, 10.0D);
    protected static final VoxelShape LEFT_WING_MAIN_OUTSIDE = Block.box(9.0D, 6.0D, 5.0D, 13.5D, 7.0D, 9.0D);
    protected static final VoxelShape LEFT_WING_MAIN_INSIDE = Block.box(10.0D, 6.0D, 6.0D, 12.5D, 7.0D, 9.0D);
    protected static final VoxelShape LEFT_WING_END = Block.box(10.0D, 6.0D, 9.0D, 13.5D, 7.0D, 10.0D);
    protected static final VoxelShape RIGHT_MAIN_WING = VEBoxBlockUtil.cutBox(RIGHT_WING_MAIN_OUTSIDE,
            RIGHT_WING_MAIN_INSIDE);
    protected static final VoxelShape LEFT_MAIN_WING = VEBoxBlockUtil.cutBox(LEFT_WING_MAIN_OUTSIDE, LEFT_WING_MAIN_INSIDE);
    protected static final VoxelShape EYES = VoxelShapes.or(RIGHT_EYE, LEFT_EYE);
    protected static final VoxelShape ANTENNAS = VoxelShapes.or(RIGHT_ANTENNA_BOTTOM, RIGHT_ANTENNA_TOP,
            LEFT_ANTENNA_BOTTOM, LEFT_ANTENNA_TOP);
    protected static final VoxelShape RIGHT_LEGS = VoxelShapes.or(FRONT_MAIN_RIGHT_LEG, FRONT_BEND_RIGHT_LEG,
            MIDDLE_FRONT_RIGHT_LEG, MIDDLE_BACK_RIGHT_LEG, BACK_MAIN_RIGHT_LEG, BACK_BEND_RIGHT_LEG);
    protected static final VoxelShape LEFT_LEGS = VoxelShapes.or(FRONT_MAIN_LEFT_LEG, FRONT_BEND_LEFT_LEG,
            MIDDLE_FRONT_LEFT_LEG, MIDDLE_BACK_LEFT_LEG, BACK_MAIN_LEFT_LEG, BACK_BEND_LEFT_LEG);
    protected static final VoxelShape RIGHT_WING = VoxelShapes.or(RIGHT_MAIN_WING, RIGHT_WING_END);
    protected static final VoxelShape LEFT_WING = VoxelShapes.or(LEFT_MAIN_WING, LEFT_WING_END);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BODY, STINGER, EYES, ANTENNAS, RIGHT_LEGS, LEFT_LEGS,
            RIGHT_WING, LEFT_WING);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEBeePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
