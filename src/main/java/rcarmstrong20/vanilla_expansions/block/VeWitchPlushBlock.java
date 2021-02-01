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

public class VeWitchPlushBlock extends VePlushBlock
{
    protected static final VoxelShape NORTH_HEAD_AND_HAT_MIDDLE_SHAPE = Block.makeCuboidShape(5.0D, 8.0D, 6.0D, 11.0D,
            15.0D, 11.0D);
    protected static final VoxelShape NORTH_HAT_TOP_SHAPE = Block.makeCuboidShape(6.0D, 15.0D, 7.0D, 10.0D, 19.0D,
            10.0D);
    protected static final VoxelShape NORTH_HAT_BRIM_SHAPE = Block.makeCuboidShape(4.0D, 12.0D, 5.0D, 12.0D, 13.0D,
            12.0D);
    protected static final VoxelShape NORTH_HAT_STRAP_SHAPE = Block.makeCuboidShape(4.5D, 13.5D, 5.5D, 11.5D, 14.5D,
            11.5D);
    protected static final VoxelShape NORTH_HAT_STRAP_ACCENT_SHAPE = Block.makeCuboidShape(7.0D, 13.0D, 5.5D, 9.0D,
            15.0D, 6.0D);
    protected static final VoxelShape NORTH_EYEBROW_SHAPE = Block.makeCuboidShape(6.0D, 10.5D, 5.5D, 10.0D, 11.5D,
            6.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.5D, 10.0D, 5.5D, 7.5D, 10.5D,
            6.0D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 10.0D, 5.5D, 9.5D, 10.5D,
            6.0D);
    protected static final VoxelShape NORTH_NOSE_SHAPE = Block.makeCuboidShape(7.5D, 8.0D, 5.0D, 8.5D, 10.0D, 6.0D);
    protected static final VoxelShape NORTH_NOSE_WART_SHAPE = Block.makeCuboidShape(7.5D, 8.5D, 4.5D, 8.0D, 9.0D, 5.0D);
    protected static final VoxelShape NORTH_TORSO_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 6.5D, 11.0D, 8.0D, 10.5D);
    protected static final VoxelShape NORTH_FEET_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 7.0D, 10.0D, 2.0D, 10.0D);
    protected static final VoxelShape NORTH_NECKTIE_MIDDLE_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 6.0D, 8.5D, 6.0D,
            6.5D);
    protected static final VoxelShape NORTH_NECKTIE_STRAP_MIDDLE_SHAPE = Block.makeCuboidShape(7.0D, 6.0D, 6.0D, 9.0D,
            7.0D, 6.5D);
    protected static final VoxelShape NORTH_NECKTIE_STRAP_RIGHT_SHAPE = Block.makeCuboidShape(9.0D, 7.0D, 6.0D, 10.0D,
            8.0D, 6.5D);
    protected static final VoxelShape NORTH_NECKTIE_STRAP_LEFT_SHAPE = Block.makeCuboidShape(6.0D, 7.0D, 6.0D, 7.0D,
            8.0D, 6.5D);
    protected static final VoxelShape NORTH_MIDDLE_ARM_SHAPE = Block.makeCuboidShape(4.0D, 4.5D, 5.0D, 12.0D, 6.5D,
            6.5D);
    protected static final VoxelShape NORTH_RIGHT_ARM_SHAPE = Block.makeCuboidShape(4.0D, 4.5D, 5.0D, 5.0D, 7.5D, 8.0D);
    protected static final VoxelShape NORTH_LEFT_ARM_SHAPE = Block.makeCuboidShape(11.0D, 4.5D, 5.0D, 12.0D, 7.5D,
            8.0D);

    protected static final VoxelShape NORTH_HEAD_AND_HAT_SHAPE = VoxelShapes.or(NORTH_HEAD_AND_HAT_MIDDLE_SHAPE,
            NORTH_HAT_TOP_SHAPE, NORTH_HAT_BRIM_SHAPE, NORTH_HAT_STRAP_SHAPE, NORTH_HAT_STRAP_ACCENT_SHAPE);
    protected static final VoxelShape NORTH_FACE_SHAPE = VoxelShapes.or(NORTH_EYEBROW_SHAPE, NORTH_RIGHT_EYE_SHAPE,
            NORTH_LEFT_EYE_SHAPE, NORTH_NOSE_SHAPE, NORTH_NOSE_WART_SHAPE);
    protected static final VoxelShape NORTH_NECKTIE_SHAPE = VoxelShapes.or(NORTH_NECKTIE_MIDDLE_SHAPE,
            NORTH_NECKTIE_STRAP_MIDDLE_SHAPE, NORTH_NECKTIE_STRAP_RIGHT_SHAPE, NORTH_NECKTIE_STRAP_LEFT_SHAPE);
    protected static final VoxelShape NORTH_ARMS_SHAPE = VoxelShapes.or(NORTH_MIDDLE_ARM_SHAPE, NORTH_RIGHT_ARM_SHAPE,
            NORTH_LEFT_ARM_SHAPE);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_TORSO_SHAPE, NORTH_FEET_SHAPE,
            NORTH_HEAD_AND_HAT_SHAPE, NORTH_FACE_SHAPE, NORTH_NECKTIE_SHAPE, NORTH_ARMS_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VeShapeUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VeShapeUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VeShapeUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VeWitchPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
