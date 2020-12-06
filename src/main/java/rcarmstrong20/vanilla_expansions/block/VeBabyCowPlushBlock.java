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

public class VeBabyCowPlushBlock extends VePlushBlock
{
    protected static final VoxelShape NORTH_BODY_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 6.0D, 10.0D, 6.0D, 12.0D);
    protected static final VoxelShape NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 2.0D, 11.0D, 7.0D, 6.5D);
    protected static final VoxelShape NORTH_MOUTH_SHAPE = Block.makeCuboidShape(6.5D, 2.0D, 1.5D, 9.5D, 4.0D, 2.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 1.5D, 7.0D, 5.0D, 2.0D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 1.5D, 10.0D, 5.0D, 2.0D);
    protected static final VoxelShape NORTH_RIGHT_HORN_SHAPE = Block.makeCuboidShape(4.0D, 5.5D, 2.5D, 5.0D, 7.5D,
            3.5D);
    protected static final VoxelShape NORTH_LEFT_HORN_SHAPE = Block.makeCuboidShape(11.0D, 5.5D, 2.5D, 12.0D, 7.5D,
            3.5D);
    protected static final VoxelShape NORTH_RIGHT_FRONT_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 7.5D, 7.5D, 2.0D,
            9.0D);
    protected static final VoxelShape NORTH_LEFT_FRONT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 7.5D, 10.0D, 2.0D,
            9.0D);
    protected static final VoxelShape NORTH_RIGHT_BACK_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 10.0D, 7.5D, 2.0D,
            11.5D);
    protected static final VoxelShape NORTH_LEFT_BACK_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 10.0D, 10.0D, 2.0D,
            11.5D);
    protected static final VoxelShape NORTH_UTTER_SHAPE = Block.makeCuboidShape(7.5D, 1.5D, 10.5D, 8.5D, 2.5D, 12.5D);

    protected static final VoxelShape NORTH_FACE_SHAPE = VoxelShapes.or(NORTH_MOUTH_SHAPE, NORTH_RIGHT_EYE_SHAPE,
            NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape NORTH_HORNS_SHAPE = VoxelShapes.or(NORTH_RIGHT_HORN_SHAPE, NORTH_LEFT_HORN_SHAPE);

    protected static final VoxelShape NORTH_LEGS_SHAPE = VoxelShapes.or(NORTH_RIGHT_FRONT_LEG_SHAPE,
            NORTH_LEFT_FRONT_LEG_SHAPE, NORTH_RIGHT_BACK_LEG_SHAPE, NORTH_LEFT_BACK_LEG_SHAPE);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_FACE_SHAPE, NORTH_HORNS_SHAPE,
            NORTH_LEGS_SHAPE, NORTH_BODY_SHAPE, NORTH_HEAD_SHAPE, NORTH_UTTER_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VeBabyCowPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
