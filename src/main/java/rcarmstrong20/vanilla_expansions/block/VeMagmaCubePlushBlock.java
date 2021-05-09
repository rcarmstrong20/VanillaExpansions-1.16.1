package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.util.VeBoxUtil;

public class VeMagmaCubePlushBlock extends VeTallPlushBlock
{
    // Bottom Magma Cube Bounding Boxes

    protected static final VoxelShape BOTTOM_BODY_SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
    protected static final VoxelShape BOTTOM_NORTH_LEFT_EYE_SHAPE = Block.box(10.0D, 5.0D, 3.0D, 12.0D, 7.0D, 2.5D);
    protected static final VoxelShape BOTTOM_NORTH_RIGHT_EYE_SHAPE = Block.box(4.0D, 5.0D, 3.0D, 6.0D, 7.0D, 2.5D);
    protected static final VoxelShape BOTTOM_NORTH_EYES_SHAPES = VoxelShapes.or(BOTTOM_NORTH_LEFT_EYE_SHAPE,
            BOTTOM_NORTH_RIGHT_EYE_SHAPE);
    protected static final VoxelShape BOTTOM_NORTH_SHAPE = VoxelShapes.or(BOTTOM_BODY_SHAPE, BOTTOM_NORTH_EYES_SHAPES);

    protected static final VoxelShape BOTTOM_SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, BOTTOM_NORTH_SHAPE);
    protected static final VoxelShape BOTTOM_WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, BOTTOM_NORTH_SHAPE);
    protected static final VoxelShape BOTTOM_EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, BOTTOM_NORTH_SHAPE);

    // Middle Magma Cube Bounding Box

    protected static final VoxelShape MIDDLE_BODY_SHAPE = Block.box(4.0D, 8.0D, 4.0D, 12.0D, 17.0D, 12.0D);
    protected static final VoxelShape MIDDLE_NORTH_LEFT_EYE_SHAPE = Block.box(9.0D, 14.0D, 4.0D, 10.5D, 15.5D, 3.5D);
    protected static final VoxelShape MIDDLE_NORTH_RIGHT_EYE_SHAPE = Block.box(5.0D, 14.0D, 4.0D, 6.5D, 15.5D, 3.5D);
    protected static final VoxelShape MIDDLE_NORTH_EYES_SHAPE = VoxelShapes.or(MIDDLE_NORTH_LEFT_EYE_SHAPE,
            MIDDLE_NORTH_RIGHT_EYE_SHAPE);
    protected static final VoxelShape MIDDLE_NORTH_SHAPE = VoxelShapes.or(MIDDLE_BODY_SHAPE, MIDDLE_NORTH_EYES_SHAPE,
            BOTTOM_NORTH_SHAPE);

    protected static final VoxelShape MIDDLE_SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, MIDDLE_NORTH_SHAPE);
    protected static final VoxelShape MIDDLE_WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, MIDDLE_NORTH_SHAPE);
    protected static final VoxelShape MIDDLE_EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, MIDDLE_NORTH_SHAPE);

    // Top Magma Cube Bounding Box

    protected static final VoxelShape TOP_BODY_SHAPE = Block.box(6.0D, 1.0D, 6.0D, 10.0D, 5.0D, 10.0D);
    protected static final VoxelShape TOP_NORTH_LEFT_EYE_SHAPE = Block.box(6.5D, 3.0D, 6.0D, 7.5D, 4.0D, 5.5D);
    protected static final VoxelShape TOP_NORTH_RIGHT_EYE_SHAPE = Block.box(8.5D, 3.0D, 6.0D, 9.5D, 4.0D, 5.5D);
    protected static final VoxelShape TOP_NORTH_EYES_SHAPE = VoxelShapes.or(TOP_NORTH_LEFT_EYE_SHAPE,
            TOP_NORTH_RIGHT_EYE_SHAPE);
    protected static final VoxelShape TOP_NORTH_SHAPE = VoxelShapes.or(TOP_BODY_SHAPE, TOP_NORTH_EYES_SHAPE);

    protected static final VoxelShape TOP_SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, TOP_NORTH_SHAPE);
    protected static final VoxelShape TOP_WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, TOP_NORTH_SHAPE);
    protected static final VoxelShape TOP_EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, TOP_NORTH_SHAPE);

    public VeMagmaCubePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        switch (state.getValue(FACING))
        {
            case NORTH:
                return getStackSizeShapes(state, BOTTOM_NORTH_SHAPE, MIDDLE_NORTH_SHAPE, TOP_NORTH_SHAPE);
            case SOUTH:
                return getStackSizeShapes(state, BOTTOM_SOUTH_SHAPE, MIDDLE_SOUTH_SHAPE, TOP_SOUTH_SHAPE);
            case WEST:
                return getStackSizeShapes(state, BOTTOM_WEST_SHAPE, MIDDLE_WEST_SHAPE, TOP_WEST_SHAPE);
            default:
                return getStackSizeShapes(state, BOTTOM_EAST_SHAPE, MIDDLE_EAST_SHAPE, TOP_EAST_SHAPE);
        }
    }
}
