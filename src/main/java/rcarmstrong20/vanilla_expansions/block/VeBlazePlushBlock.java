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

public class VeBlazePlushBlock extends VePlushBlock
{
    protected static final VoxelShape BLAZE_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 8.0D, 5.5D, 10.5D, 13.0D,
            10.5D);
    protected static final VoxelShape BLAZE_NORTH_WEST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(4.5D, 1.0D, 4.5D, 5.5D,
            7.0D, 5.5D);
    protected static final VoxelShape BLAZE_NORTH_EAST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(10.5D, 1.0D, 4.5D,
            11.5D, 7.0D, 5.5D);
    protected static final VoxelShape BLAZE_SOUTH_WEST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(4.5D, 1.0D, 10.5D, 5.5D,
            7.0D, 11.5D);
    protected static final VoxelShape BLAZE_SOUTH_EAST_BOTTOM_LEG_SHAPE = Block.makeCuboidShape(10.5D, 1.0D, 10.5D,
            11.5D, 7.0D, 11.5D);
    protected static final VoxelShape BLAZE_SOUTH_TOP_LEG_SHAPE = Block.makeCuboidShape(7.5D, 7.0D, 11.5D, 8.5D, 13.0D,
            12.5D);
    protected static final VoxelShape BLAZE_WEST_TOP_LEG_SHAPE = Block.makeCuboidShape(3.5D, 7.0D, 7.5D, 4.5D, 13.0D,
            8.5D);
    protected static final VoxelShape BLAZE_EAST_TOP_LEG_SHAPE = Block.makeCuboidShape(11.5D, 7.0D, 7.5D, 12.5D, 13.0D,
            8.5D);
    protected static final VoxelShape BLAZE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 11.0D, 5.0D, 7.5D,
            12.0D, 5.5D);
    protected static final VoxelShape BLAZE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 11.0D, 5.0D, 10.5D,
            12.0D, 5.5D);

    protected static final VoxelShape BLAZE_NORTH_LEGS_SHAPE = VoxelShapes.or(BLAZE_NORTH_WEST_BOTTOM_LEG_SHAPE,
            BLAZE_NORTH_EAST_BOTTOM_LEG_SHAPE, BLAZE_SOUTH_WEST_BOTTOM_LEG_SHAPE, BLAZE_SOUTH_EAST_BOTTOM_LEG_SHAPE,
            BLAZE_SOUTH_TOP_LEG_SHAPE, BLAZE_WEST_TOP_LEG_SHAPE, BLAZE_EAST_TOP_LEG_SHAPE);

    protected static final VoxelShape BLAZE_NORTH_EYES_SHAPE = VoxelShapes.or(BLAZE_NORTH_RIGHT_EYE_SHAPE,
            BLAZE_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape BLAZE_NORTH_SHAPE = VoxelShapes.or(BLAZE_NORTH_HEAD_SHAPE, BLAZE_NORTH_LEGS_SHAPE,
            BLAZE_NORTH_EYES_SHAPE);

    protected static final VoxelShape BLAZE_SOUTH_SHAPE = VeShapeUtil.rotate180(Axis.Y, BLAZE_NORTH_SHAPE);
    protected static final VoxelShape BLAZE_WEST_SHAPE = VeShapeUtil.rotate270(Axis.Y, BLAZE_NORTH_SHAPE);
    protected static final VoxelShape BLAZE_EAST_SHAPE = VeShapeUtil.rotate90(Axis.Y, BLAZE_NORTH_SHAPE);

    public VeBlazePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, BLAZE_NORTH_SHAPE, BLAZE_SOUTH_SHAPE, BLAZE_WEST_SHAPE, BLAZE_EAST_SHAPE);
    }
}
