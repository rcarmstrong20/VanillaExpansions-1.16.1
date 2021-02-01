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

public class VeZombiePlushBlock extends VePlushBlock
{
    protected static final VoxelShape ZOMBIE_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 11.0D, 7.5D, 11.0D, 16.0D,
            12.0D);
    protected static final VoxelShape ZOMBIE_NORTH_TORSO_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 8.5D, 10.5D, 11.0D,
            11.5D);
    protected static final VoxelShape ZOMBIE_NORTH_RIGHT_ARM_SHAPE = Block.makeCuboidShape(10.5D, 9.0D, 4.0D, 12.5D,
            11.0D, 11.0D);
    protected static final VoxelShape ZOMBIE_NORTH_LEFT_ARM_SHAPE = Block.makeCuboidShape(3.5D, 9.0D, 4.0D, 5.5D, 11.0D,
            11.0D);
    protected static final VoxelShape ZOMBIE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 14.0D, 7.0D, 10.5D,
            15.0D, 7.5D);
    protected static final VoxelShape ZOMBIE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 14.0D, 7.0D, 7.0D,
            15.0D, 7.5D);
    protected static final VoxelShape ZOMBIE_NORTH_MIDDLE_MOUTH_SHAPE = Block.makeCuboidShape(7.0D, 13.0D, 7.0D, 9.0D,
            14.0D, 7.5D);
    protected static final VoxelShape ZOMBIE_NORTH_RIGHT_MOUTH_SHAPE = Block.makeCuboidShape(9.0D, 12.0D, 7.0D, 10.0D,
            13.0D, 7.5D);
    protected static final VoxelShape ZOMBIE_NORTH_LEFT_MOUTH_SHAPE = Block.makeCuboidShape(6.0D, 11.0D, 7.0D, 7.0D,
            13.0D, 7.5D);

    protected static final VoxelShape ZOMBIE_NORTH_ARMS_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_RIGHT_ARM_SHAPE,
            ZOMBIE_NORTH_LEFT_ARM_SHAPE);

    protected static final VoxelShape ZOMBIE_NORTH_EYES_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_RIGHT_EYE_SHAPE,
            ZOMBIE_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape ZOMBIE_NORTH_MOUTH_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_MIDDLE_MOUTH_SHAPE,
            ZOMBIE_NORTH_RIGHT_MOUTH_SHAPE, ZOMBIE_NORTH_LEFT_MOUTH_SHAPE);

    protected static final VoxelShape ZOMBIE_NORTH_BODY_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_HEAD_SHAPE,
            ZOMBIE_NORTH_TORSO_SHAPE, ZOMBIE_NORTH_ARMS_SHAPE);

    protected static final VoxelShape ZOMBIE_NORTH_SHAPE = VoxelShapes.or(ZOMBIE_NORTH_BODY_SHAPE,
            ZOMBIE_NORTH_EYES_SHAPE, ZOMBIE_NORTH_MOUTH_SHAPE);

    protected static final VoxelShape ZOMBIE_SOUTH_SHAPE = VeShapeUtil.rotate180(Axis.Y, ZOMBIE_NORTH_SHAPE);
    protected static final VoxelShape ZOMBIE_WEST_SHAPE = VeShapeUtil.rotate270(Axis.Y, ZOMBIE_NORTH_SHAPE);
    protected static final VoxelShape ZOMBIE_EAST_SHAPE = VeShapeUtil.rotate90(Axis.Y, ZOMBIE_NORTH_SHAPE);

    public VeZombiePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, ZOMBIE_NORTH_SHAPE, ZOMBIE_SOUTH_SHAPE, ZOMBIE_WEST_SHAPE, ZOMBIE_EAST_SHAPE);
    }
}
