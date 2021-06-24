package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.block.util.VEBoxBlockUtil;

public class VESlimePlushBlock extends VEMagmaCubePlushBlock
{
    // One Slime Bounding Boxes

    protected static final VoxelShape BOTTOM_NORTH_MOUTH_SHAPE = Block.box(7.0D, 2.0D, 3.0D, 6.0D, 3.0D, 2.5D);
    protected static final VoxelShape BOTTOM_NORTH_SHAPE = VoxelShapes.or(VEMagmaCubePlushBlock.BOTTOM_NORTH_SHAPE,
            BOTTOM_NORTH_MOUTH_SHAPE);

    protected static final VoxelShape BOTTOM_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, BOTTOM_NORTH_SHAPE);
    protected static final VoxelShape BOTTOM_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, BOTTOM_NORTH_SHAPE);
    protected static final VoxelShape BOTTOM_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, BOTTOM_NORTH_SHAPE);

    // Two Slime Bounding Boxes

    protected static final VoxelShape MIDDLE_NORTH_MOUTH_EYE_SHAPE = Block.box(6.5D, 10.5D, 4.0D, 7.5D, 11.5D, 3.5D);
    protected static final VoxelShape MIDDLE_NORTH_SHAPE = VoxelShapes.or(VEMagmaCubePlushBlock.MIDDLE_NORTH_SHAPE,
            MIDDLE_NORTH_MOUTH_EYE_SHAPE, BOTTOM_NORTH_SHAPE);

    protected static final VoxelShape MIDDLE_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, MIDDLE_NORTH_SHAPE);
    protected static final VoxelShape MIDDLE_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, MIDDLE_NORTH_SHAPE);
    protected static final VoxelShape MIDDLE_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, MIDDLE_NORTH_SHAPE);

    // Three Slime Bouding Boxes

    protected static final VoxelShape TOP_NORTH_MOUTH_EYE_SHAPE = Block.box(7.0D, 1.0D, 6.0D, 7.5D, 1.5D, 5.5D);
    protected static final VoxelShape TOP_NORTH_SHAPE = VoxelShapes.or(VEMagmaCubePlushBlock.TOP_NORTH_SHAPE,
            TOP_NORTH_MOUTH_EYE_SHAPE);

    protected static final VoxelShape TOP_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, TOP_NORTH_SHAPE);
    protected static final VoxelShape TOP_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, TOP_NORTH_SHAPE);
    protected static final VoxelShape TOP_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, TOP_NORTH_SHAPE);

    public VESlimePlushBlock(Properties properties)
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
