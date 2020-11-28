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

public class VeCatPlushBlock extends VePlushBlock
{
    protected static final VoxelShape CAT_NORTH_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 7.5D, 10.0D,
            2.0D, 8.5D);
    protected static final VoxelShape CAT_NORTH_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 7.5D, 7.0D,
            2.0D, 8.5D);
    protected static final VoxelShape CAT_NORTH_TORSO_SHAPE = Block.makeCuboidShape(5.5D, 2.0D, 6.0D, 10.5D, 6.0D,
            11.5D);
    protected static final VoxelShape CAT_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.0D, 3.0D, 1.0D, 11.0D, 8.5D, 6.0D);
    protected static final VoxelShape CAT_NORTH_RIGHT_EAR_SHAPE = Block.makeCuboidShape(9.0D, 8.5D, 4.0D, 10.0D, 9.5D,
            6.0D);
    protected static final VoxelShape CAT_NORTH_LEFT_EAR_SHAPE = Block.makeCuboidShape(6.0D, 8.5D, 4.0D, 7.0D, 9.5D,
            6.0D);
    protected static final VoxelShape CAT_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 6.0D, 0.5D, 10.5D, 7.0D,
            1.0D);
    protected static final VoxelShape CAT_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(5.5D, 6.0D, 0.5D, 7.5D, 7.0D,
            1.0D);
    protected static final VoxelShape CAT_NORTH_NOSE_SHAPE = Block.makeCuboidShape(6.5D, 3.5D, 0.0D, 9.5D, 5.5D, 1.0D);
    protected static final VoxelShape CAT_NORTH_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 10.0D, 10.0D,
            2.0D, 11.0D);
    protected static final VoxelShape CAT_NORTH_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 10.0D, 7.0D,
            2.0D, 11.0D);
    protected static final VoxelShape CAT_NORTH_PIECE_TAIL1_SHAPE = Block.makeCuboidShape(7.5D, 3.0D, 11.5D, 8.5D, 4.0D,
            14.0D);
    protected static final VoxelShape CAT_NORTH_PIECE_TAIL2_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 13.0D, 8.5D, 3.0D,
            16.0D);

    protected static final VoxelShape CAT_NORTH_LEGS_SHAPE = VoxelShapes.or(CAT_NORTH_FRONT_RIGHT_LEG_SHAPE,
            CAT_NORTH_FRONT_LEFT_LEG_SHAPE, CAT_NORTH_BACK_RIGHT_LEG_SHAPE, CAT_NORTH_BACK_LEFT_LEG_SHAPE);

    protected static final VoxelShape CAT_NORTH_TAIL_SHAPE = VoxelShapes.or(CAT_NORTH_PIECE_TAIL1_SHAPE,
            CAT_NORTH_PIECE_TAIL2_SHAPE);

    protected static final VoxelShape CAT_NORTH_EARS_SHAPE = VoxelShapes.or(CAT_NORTH_RIGHT_EAR_SHAPE,
            CAT_NORTH_LEFT_EAR_SHAPE);

    protected static final VoxelShape CAT_NORTH_EYES_SHAPE = VoxelShapes.or(CAT_NORTH_RIGHT_EYE_SHAPE,
            CAT_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape CAT_NORTH_BODY_SHAPE = VoxelShapes.or(CAT_NORTH_TORSO_SHAPE, CAT_NORTH_HEAD_SHAPE,
            CAT_NORTH_LEGS_SHAPE, CAT_NORTH_TAIL_SHAPE, CAT_NORTH_EARS_SHAPE, CAT_NORTH_EYES_SHAPE);

    protected static final VoxelShape CAT_NORTH_SHAPE = VoxelShapes.or(CAT_NORTH_BODY_SHAPE, CAT_NORTH_NOSE_SHAPE);

    protected static final VoxelShape CAT_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, CAT_NORTH_SHAPE);
    protected static final VoxelShape CAT_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, CAT_NORTH_SHAPE);
    protected static final VoxelShape CAT_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, CAT_NORTH_SHAPE);

    public VeCatPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, CAT_NORTH_SHAPE, CAT_SOUTH_SHAPE, CAT_WEST_SHAPE, CAT_EAST_SHAPE);
    }
}
