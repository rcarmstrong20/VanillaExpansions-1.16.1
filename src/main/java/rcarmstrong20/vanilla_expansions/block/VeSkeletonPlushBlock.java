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

public class VeSkeletonPlushBlock extends VePlushBlock
{
    protected static final VoxelShape SKELETON_NORTH_SKULL_OUTSIDE_SHAPE = Block.makeCuboidShape(5.0D, 7.0D, 6.0D,
            11.0D, 12.0D, 10.0D);
    protected static final VoxelShape SKELETON_NORTH_SKULL_INSIDE_SHAPE = Block.makeCuboidShape(6.0D, 6.0D, 7.0D, 10.0D,
            11.0D, 9.0D);
    protected static final VoxelShape SKELETON_NORTH_SPINE_SHAPE = Block.makeCuboidShape(6.5D, 1.0D, 8.0D, 9.5D, 12.0D,
            9.0D);
    protected static final VoxelShape SKELETON_NORTH_RIB_TOP_HOLE_SHAPE = Block.makeCuboidShape(7.0D, 6.0D, 7.0D, 9.0D,
            7.0D, 8.0D);
    protected static final VoxelShape SKELETON_NORTH_RIB_TOP_SOLID_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 7.0D,
            10.0D, 7.0D, 8.0D);
    protected static final VoxelShape SKELETON_NORTH_RIB_BOTTOM_SOLID_SHAPE = Block.makeCuboidShape(6.5D, 1.0D, 7.0D,
            9.5D, 2.0D, 8.0D);
    protected static final VoxelShape SKELETON_NORTH_RIB_BOTTOM_LEFT_HOLE_SHAPE = Block.makeCuboidShape(8.5D, 1.5D,
            7.0D, 9.0D, 2.0D, 8.0D);
    protected static final VoxelShape SKELETON_NORTH_RIB_BOTTOM_RIGHT_HOLE_SHAPE = Block.makeCuboidShape(7.0D, 1.5D,
            7.0D, 7.5D, 2.0D, 8.0D);
    protected static final VoxelShape SKELETON_NORTH_RIGHT_ARM_SHAPE = Block.makeCuboidShape(5.0D, 0.5D, 7.5D, 6.0D,
            6.0D, 8.5D);
    protected static final VoxelShape SKELETON_NORTH_LEFT_ARM_SHAPE = Block.makeCuboidShape(10.0D, 0.5D, 7.5D, 11.0D,
            6.0D, 8.5D);
    protected static final VoxelShape SKELETON_NORTH_BOW_SHAPE = Block.makeCuboidShape(9.5D, 1.0D, 4.5D, 10.5D, 4.0D,
            11.5D);
    protected static final VoxelShape SKELETON_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 10.0D, 5.5D, 7.0D,
            11.0D, 6.0D);
    protected static final VoxelShape SKELETON_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 10.0D, 5.5D, 10.0D,
            11.0D, 6.0D);
    protected static final VoxelShape SKELETON_NORTH_MOUTH_TOP_SHAPE = Block.makeCuboidShape(9.0D, 9.0D, 5.5D, 7.0D,
            10.0D, 6.0D);
    protected static final VoxelShape SKELETON_NORTH_MOUTH_BOTTOM_SHAPE = Block.makeCuboidShape(6.0D, 8.0D, 5.5D, 10.0D,
            9.0D, 6.0D);
    protected static final VoxelShape SKELETON_NORTH_LEGS_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 3.0D, 11.0D, 1.0D,
            9.0D);

    protected static final VoxelShape SKELETON_NORTH_SKULL_SHAPE = VeShapeUtil
            .cutShape(SKELETON_NORTH_SKULL_OUTSIDE_SHAPE, SKELETON_NORTH_SKULL_INSIDE_SHAPE);

    protected static final VoxelShape SKELETON_NORTH_RIB_TOP_SHAPE = VeShapeUtil
            .cutShape(SKELETON_NORTH_RIB_TOP_SOLID_SHAPE, SKELETON_NORTH_RIB_TOP_HOLE_SHAPE);

    protected static final VoxelShape SKELETON_NORTH_RIB_BOTTOM_SHAPE = VeShapeUtil.cutShape(
            SKELETON_NORTH_RIB_BOTTOM_SOLID_SHAPE, SKELETON_NORTH_RIB_BOTTOM_LEFT_HOLE_SHAPE,
            SKELETON_NORTH_RIB_BOTTOM_RIGHT_HOLE_SHAPE);

    protected static final VoxelShape SKELETON_NORTH_ARMS_SHAPE = VoxelShapes.or(SKELETON_NORTH_RIGHT_ARM_SHAPE,
            SKELETON_NORTH_LEFT_ARM_SHAPE);

    protected static final VoxelShape SKELETON_NORTH_EYES_SHAPE = VoxelShapes.or(SKELETON_NORTH_RIGHT_EYE_SHAPE,
            SKELETON_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape SKELETON_NORTH_MOUTH_SHAPE = VoxelShapes.or(SKELETON_NORTH_MOUTH_TOP_SHAPE,
            SKELETON_NORTH_MOUTH_BOTTOM_SHAPE);

    protected static final VoxelShape SKELETON_NORTH_SHAPE = VoxelShapes.or(SKELETON_NORTH_SPINE_SHAPE,
            SKELETON_NORTH_SKULL_SHAPE, SKELETON_NORTH_RIB_TOP_SHAPE, SKELETON_NORTH_RIB_BOTTOM_SHAPE,
            SKELETON_NORTH_ARMS_SHAPE, SKELETON_NORTH_BOW_SHAPE, SKELETON_NORTH_EYES_SHAPE, SKELETON_NORTH_MOUTH_SHAPE,
            SKELETON_NORTH_LEGS_SHAPE);

    protected static final VoxelShape SKELETON_SOUTH_SHAPE = VeShapeUtil.rotate180(Axis.Y, SKELETON_NORTH_SHAPE);
    protected static final VoxelShape SKELETON_WEST_SHAPE = VeShapeUtil.rotate270(Axis.Y, SKELETON_NORTH_SHAPE);
    protected static final VoxelShape SKELETON_EAST_SHAPE = VeShapeUtil.rotate90(Axis.Y, SKELETON_NORTH_SHAPE);

    public VeSkeletonPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, SKELETON_NORTH_SHAPE, SKELETON_SOUTH_SHAPE, SKELETON_WEST_SHAPE,
                SKELETON_EAST_SHAPE);
    }
}
