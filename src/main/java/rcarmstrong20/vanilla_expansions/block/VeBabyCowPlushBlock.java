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
    protected static final VoxelShape BABY_COW_NORTH_BODY_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 6.0D, 10.0D, 6.0D,
            12.0D);
    protected static final VoxelShape BABY_COW_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 2.0D, 11.0D, 7.0D,
            6.5D);
    protected static final VoxelShape BABY_COW_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(6.5D, 2.0D, 1.5D, 9.5D, 4.0D,
            2.0D);
    protected static final VoxelShape BABY_COW_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 4.0D, 1.5D, 7.0D,
            5.0D, 2.0D);
    protected static final VoxelShape BABY_COW_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 4.0D, 1.5D, 10.0D,
            5.0D, 2.0D);
    protected static final VoxelShape BABY_COW_NORTH_RIGHT_HORN_SHAPE = Block.makeCuboidShape(4.0D, 5.5D, 2.5D, 5.0D,
            7.5D, 3.5D);
    protected static final VoxelShape BABY_COW_NORTH_LEFT_HORN_SHAPE = Block.makeCuboidShape(11.0D, 5.5D, 2.5D, 12.0D,
            7.5D, 3.5D);
    protected static final VoxelShape BABY_COW_NORTH_RIGHT_FRONT_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 7.5D,
            7.5D, 2.0D, 9.0D);
    protected static final VoxelShape BABY_COW_NORTH_LEFT_FRONT_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 7.5D,
            10.0D, 2.0D, 9.0D);
    protected static final VoxelShape BABY_COW_NORTH_RIGHT_BACK_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 10.0D,
            7.5D, 2.0D, 11.5D);
    protected static final VoxelShape BABY_COW_NORTH_LEFT_BACK_LEG_SHAPE = Block.makeCuboidShape(8.5D, 0.0D, 10.0D,
            10.0D, 2.0D, 11.5D);
    protected static final VoxelShape BABY_COW_NORTH_UTTER_SHAPE = Block.makeCuboidShape(7.5D, 1.5D, 10.5D, 8.5D, 2.5D,
            12.5D);

    protected static final VoxelShape BABY_COW_NORTH_FACE_SHAPE = VoxelShapes.or(BABY_COW_NORTH_MOUTH_SHAPE,
            BABY_COW_NORTH_RIGHT_EYE_SHAPE, BABY_COW_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape BABY_COW_NORTH_HORNS_SHAPE = VoxelShapes.or(BABY_COW_NORTH_RIGHT_HORN_SHAPE,
            BABY_COW_NORTH_LEFT_HORN_SHAPE);

    protected static final VoxelShape BABY_COW_NORTH_LEGS_SHAPE = VoxelShapes.or(BABY_COW_NORTH_RIGHT_FRONT_LEG_SHAPE,
            BABY_COW_NORTH_LEFT_FRONT_LEG_SHAPE, BABY_COW_NORTH_RIGHT_BACK_LEG_SHAPE,
            BABY_COW_NORTH_LEFT_BACK_LEG_SHAPE);

    protected static final VoxelShape BABY_COW_NORTH_SHAPE = VoxelShapes.or(BABY_COW_NORTH_FACE_SHAPE,
            BABY_COW_NORTH_HORNS_SHAPE, BABY_COW_NORTH_LEGS_SHAPE, BABY_COW_NORTH_BODY_SHAPE, BABY_COW_NORTH_HEAD_SHAPE,
            BABY_COW_NORTH_UTTER_SHAPE);

    protected static final VoxelShape BABY_COW_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, BABY_COW_NORTH_SHAPE);
    protected static final VoxelShape BABY_COW_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, BABY_COW_NORTH_SHAPE);
    protected static final VoxelShape BABY_COW_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, BABY_COW_NORTH_SHAPE);

    public VeBabyCowPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, BABY_COW_NORTH_SHAPE, BABY_COW_SOUTH_SHAPE, BABY_COW_WEST_SHAPE,
                BABY_COW_EAST_SHAPE);
    }
}
