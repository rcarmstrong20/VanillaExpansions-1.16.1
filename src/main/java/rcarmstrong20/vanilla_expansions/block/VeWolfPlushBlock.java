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

public class VeWolfPlushBlock extends VePlushBlock
{
    protected static final VoxelShape WOLF_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 4.0D, 11.0D, 8.5D,
            8.0D);
    protected static final VoxelShape WOLF_NORTH_TORSO_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 8.0D, 10.0D, 7.0D,
            13.0D);
    protected static final VoxelShape WOLF_NORTH_SNOUT_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 0.5D, 10.0D, 5.5D,
            4.0D);
    protected static final VoxelShape WOLF_NORTH_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 9.0D, 9.5D,
            2.0D, 10.0D);
    protected static final VoxelShape WOLF_NORTH_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 9.0D, 7.5D,
            2.0D, 10.0D);
    protected static final VoxelShape WOLF_NORTH_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 11.5D, 9.5D,
            2.0D, 12.5D);
    protected static final VoxelShape WOLF_NORTH_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 11.5D, 7.5D,
            2.0D, 12.5D);
    protected static final VoxelShape WOLF_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 6.0D, 3.5D, 7.5D, 7.0D,
            4.0D);
    protected static final VoxelShape WOLF_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 6.0D, 3.5D, 10.5D, 7.0D,
            4.0D);
    protected static final VoxelShape WOLF_NORTH_RIGHT_EAR_SHAPE = Block.makeCuboidShape(9.0D, 8.5D, 6.0D, 10.5D, 10.5D,
            7.0D);
    protected static final VoxelShape WOLF_NORTH_LEFT_EAR_SHAPE = Block.makeCuboidShape(5.5D, 8.5D, 6.0D, 7.0D, 10.5D,
            7.0D);
    protected static final VoxelShape WOLF_NORTH_TAIL1_SHAPE = Block.makeCuboidShape(7.0D, 3.0D, 13.0D, 9.0D, 6.0D,
            14.0D);
    protected static final VoxelShape WOLF_NORTH_TAIL2_SHAPE = Block.makeCuboidShape(7.0D, 2.0D, 14.0D, 9.0D, 5.0D,
            16.0D);

    protected static final VoxelShape WOLF_NORTH_LEGS_SHAPE = VoxelShapes.or(WOLF_NORTH_FRONT_RIGHT_LEG_SHAPE,
            WOLF_NORTH_FRONT_LEFT_LEG_SHAPE, WOLF_NORTH_BACK_RIGHT_LEG_SHAPE, WOLF_NORTH_BACK_LEFT_LEG_SHAPE);

    protected static final VoxelShape WOLF_NORTH_EYES_SHAPE = VoxelShapes.or(WOLF_NORTH_RIGHT_EYE_SHAPE,
            WOLF_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape WOLF_NORTH_EARS_SHAPE = VoxelShapes.or(WOLF_NORTH_RIGHT_EAR_SHAPE,
            WOLF_NORTH_LEFT_EAR_SHAPE);

    protected static final VoxelShape WOLF_NORTH_TAIL_SHAPE = VoxelShapes.or(WOLF_NORTH_TAIL1_SHAPE,
            WOLF_NORTH_TAIL2_SHAPE);

    protected static final VoxelShape WOLF_NORTH_SHAPE = VoxelShapes.or(WOLF_NORTH_HEAD_SHAPE, WOLF_NORTH_TORSO_SHAPE,
            WOLF_NORTH_SNOUT_SHAPE, WOLF_NORTH_LEGS_SHAPE, WOLF_NORTH_EYES_SHAPE, WOLF_NORTH_EARS_SHAPE,
            WOLF_NORTH_TAIL_SHAPE);

    protected static final VoxelShape WOLF_SOUTH_SHAPE = VeShapeUtil.rotate180(Axis.Y, WOLF_NORTH_SHAPE);
    protected static final VoxelShape WOLF_WEST_SHAPE = VeShapeUtil.rotate270(Axis.Y, WOLF_NORTH_SHAPE);
    protected static final VoxelShape WOLF_EAST_SHAPE = VeShapeUtil.rotate90(Axis.Y, WOLF_NORTH_SHAPE);

    public VeWolfPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, WOLF_NORTH_SHAPE, WOLF_SOUTH_SHAPE, WOLF_WEST_SHAPE, WOLF_EAST_SHAPE);
    }
}
