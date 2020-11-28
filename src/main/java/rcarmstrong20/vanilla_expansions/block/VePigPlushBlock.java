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

public class VePigPlushBlock extends VePlushBlock
{
    protected static final VoxelShape PIG_NORTH_BODY_SHAPE = Block.makeCuboidShape(4.5D, 4.0D, 4.0D, 11.5D, 9.0D,
            15.0D);
    protected static final VoxelShape PIG_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 5.0D, 1.0D, 10.5D, 10.0D,
            5.0D);
    protected static final VoxelShape PIG_NORTH_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 6.0D, 11.5D,
            4.0D, 8.0D);
    protected static final VoxelShape PIG_NORTH_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 6.0D, 7.0D,
            4.0D, 8.0D);
    protected static final VoxelShape PIG_NORTH_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 13.5D, 11.5D,
            4.0D, 15.5D);
    protected static final VoxelShape PIG_NORTH_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(4.5D, 0.0D, 13.5D, 7.0D,
            4.0D, 15.5D);
    protected static final VoxelShape PIG_NORTH_TAIL1_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 15.0D, 10.0D, 7.5D,
            15.5D);
    protected static final VoxelShape PIG_NORTH_TAIL2_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 15.0D, 9.0D, 5.0D,
            15.5D);
    protected static final VoxelShape PIG_NORTH_TAIL3_SHAPE = Block.makeCuboidShape(7.0D, 7.5D, 15.0D, 9.0D, 8.5D,
            15.5D);
    protected static final VoxelShape PIG_NORTH_TAIL4_SHAPE = Block.makeCuboidShape(6.0D, 6.5D, 15.0D, 7.0D, 7.5D,
            15.5D);
    protected static final VoxelShape PIG_NORTH_TAIL5_SHAPE = Block.makeCuboidShape(7.0D, 5.5D, 15.0D, 8.0D, 6.5D,
            15.5D);
    protected static final VoxelShape PIG_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 7.5D, 0.5D, 10.0D, 8.5D,
            1.0D);
    protected static final VoxelShape PIG_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 7.5D, 0.5D, 7.5D, 8.5D,
            1.0D);
    protected static final VoxelShape PIG_NORTH_SNOUT_SHAPE = Block.makeCuboidShape(7.0D, 6.0D, 0.0D, 9.0D, 7.0D, 1.0D);

    protected static final VoxelShape PIG_NORTH_LEGS_SHAPE = VoxelShapes.or(PIG_NORTH_FRONT_RIGHT_LEG_SHAPE,
            PIG_NORTH_FRONT_LEFT_LEG_SHAPE, PIG_NORTH_BACK_RIGHT_LEG_SHAPE, PIG_NORTH_BACK_LEFT_LEG_SHAPE);

    protected static final VoxelShape PIG_NORTH_TAIL_SHAPE = VoxelShapes.or(PIG_NORTH_TAIL1_SHAPE,
            PIG_NORTH_TAIL2_SHAPE, PIG_NORTH_TAIL3_SHAPE, PIG_NORTH_TAIL4_SHAPE, PIG_NORTH_TAIL5_SHAPE);

    protected static final VoxelShape PIG_NORTH_EYES_SHAPE = VoxelShapes.or(PIG_NORTH_RIGHT_EYE_SHAPE,
            PIG_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape PIG_NORTH_SHAPE = VoxelShapes.or(PIG_NORTH_BODY_SHAPE, PIG_NORTH_HEAD_SHAPE,
            PIG_NORTH_LEGS_SHAPE, PIG_NORTH_TAIL_SHAPE, PIG_NORTH_EYES_SHAPE, PIG_NORTH_SNOUT_SHAPE);

    protected static final VoxelShape PIG_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, PIG_NORTH_SHAPE);
    protected static final VoxelShape PIG_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, PIG_NORTH_SHAPE);
    protected static final VoxelShape PIG_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, PIG_NORTH_SHAPE);

    public VePigPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, PIG_NORTH_SHAPE, PIG_SOUTH_SHAPE, PIG_WEST_SHAPE, PIG_EAST_SHAPE);
    }
}
