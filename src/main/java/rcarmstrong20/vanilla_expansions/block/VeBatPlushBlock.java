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

public class VeBatPlushBlock extends VePlushBlock
{
    protected static final VoxelShape NORTH_BODY_SHAPE = Block.makeCuboidShape(6.5D, 1.0D, 6.0D, 10.5D, 6.5D, 10.0D);
    protected static final VoxelShape NORTH_HEAD_SHAPE = Block.makeCuboidShape(6.0D, 5.5D, 5.0D, 11.0D, 10.0D, 8.5D);
    protected static final VoxelShape NORTH_RIGHT_EAR_SHAPE = Block.makeCuboidShape(5.5D, 9.0D, 6.0D, 7.5D, 11.5D,
            7.0D);
    protected static final VoxelShape NORTH_LEFT_EAR_SHAPE = Block.makeCuboidShape(9.5D, 9.0D, 6.0D, 11.5D, 11.5D,
            7.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.5D, 8.0D, 4.5D, 8.0D, 9.0D, 5.0D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 8.0D, 4.5D, 10.5D, 9.0D, 5.0D);
    protected static final VoxelShape NORTH_MOUTH_SHAPE = Block.makeCuboidShape(7.5D, 6.0D, 4.5D, 9.5D, 7.0D, 5.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.5D, 8.0D, 1.0D, 8.5D);
    protected static final VoxelShape NORTH_LEFT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 7.5D, 10.0D, 1.0D, 8.5D);
    protected static final VoxelShape NORTH_LEFT_WING1_SHAPE = Block.makeCuboidShape(10.5D, 1.5D, 7.0D, 11.5D, 6.0D,
            9.5D);
    protected static final VoxelShape NORTH_LEFT_WING2_SHAPE = Block.makeCuboidShape(11.5D, 1.5D, 6.5D, 13.5D, 5.5D,
            9.0D);
    protected static final VoxelShape NORTH_LEFT_WING3_SHAPE = Block.makeCuboidShape(13.5D, 1.5D, 6.0D, 14.5D, 5.5D,
            8.5D);
    protected static final VoxelShape NORTH_LEFT_WING4_SHAPE = Block.makeCuboidShape(14.5D, 1.0D, 5.5D, 15.5D, 5.0D,
            8.0D);
    protected static final VoxelShape NORTH_RIGHT_WING1_SHAPE = Block.makeCuboidShape(5.5D, 1.5D, 7.0D, 6.5D, 6.0D,
            9.5D);
    protected static final VoxelShape NORTH_RIGHT_WING2_SHAPE = Block.makeCuboidShape(4.5D, 1.5D, 6.5D, 5.5D, 5.0D,
            9.5D);
    protected static final VoxelShape NORTH_RIGHT_WING3_SHAPE = Block.makeCuboidShape(3.5D, 1.5D, 6.5D, 4.5D, 5.5D,
            10.0D);
    protected static final VoxelShape NORTH_RIGHT_WING4_SHAPE = Block.makeCuboidShape(2.5D, 1.0D, 6.5D, 3.5D, 5.0D,
            9.0D);
    protected static final VoxelShape NORTH_RIGHT_WING5_SHAPE = Block.makeCuboidShape(1.5D, 0.5D, 5.5D, 2.5D, 4.5D,
            8.5D);

    protected static final VoxelShape NORTH_EARS_SHAPE = VoxelShapes.or(NORTH_RIGHT_EAR_SHAPE, NORTH_LEFT_EAR_SHAPE);
    protected static final VoxelShape NORTH_EYES_AND_MOUTH_SHAPE = VoxelShapes.or(NORTH_RIGHT_EYE_SHAPE,
            NORTH_LEFT_EYE_SHAPE, NORTH_MOUTH_SHAPE);
    protected static final VoxelShape NORTH_LEGS_SHAPE = VoxelShapes.or(NORTH_RIGHT_LEG_SHAPE, NORTH_LEFT_LEG_SHAPE);
    protected static final VoxelShape NORTH_LEFT_WING_SHAPE = VoxelShapes.or(NORTH_LEFT_WING1_SHAPE,
            NORTH_LEFT_WING2_SHAPE, NORTH_LEFT_WING3_SHAPE, NORTH_LEFT_WING4_SHAPE);
    protected static final VoxelShape NORTH_RIGHT_WING_SHAPE = VoxelShapes.or(NORTH_RIGHT_WING1_SHAPE,
            NORTH_RIGHT_WING2_SHAPE, NORTH_RIGHT_WING3_SHAPE, NORTH_RIGHT_WING4_SHAPE, NORTH_RIGHT_WING5_SHAPE);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_HEAD_SHAPE, NORTH_EARS_SHAPE,
            NORTH_EYES_AND_MOUTH_SHAPE, NORTH_LEGS_SHAPE, NORTH_LEFT_WING_SHAPE, NORTH_RIGHT_WING_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VeBatPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
