package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.block.util.VEBoxBlockUtil;

public class VERabbitPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_RIGHT_EAR_SHAPE = Block.box(6.0D, 10.5D, 3.0D, 7.5D, 14.0D, 4.0D);
    protected static final VoxelShape NORTH_LEFT_EAR_SHAPE = Block.box(8.5D, 10.5D, 3.0D, 10.0D, 14.0D, 4.0D);
    protected static final VoxelShape NORTH_HEAD_SHAPE = Block.box(5.5D, 6.5D, 1.5D, 10.5D, 10.5D, 6.0D);
    protected static final VoxelShape NORTH_BODY_SHAPE = Block.box(5.5D, 0.0D, 3.5D, 10.5D, 8.0D, 13.0D);
    protected static final VoxelShape NORTH_TAIL_SHAPE = Block.box(6.5D, 1.5D, 13.0D, 9.5D, 4.5D, 15.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.box(6.5D, 8.5D, 1.0D, 7.5D, 10.0D, 1.5D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.box(8.5D, 8.5D, 1.0D, 9.5D, 10.0D, 1.5D);
    protected static final VoxelShape NORTH_NOSE_SHAPE = Block.box(7.5D, 7.5D, 1.0D, 8.5D, 8.5D, 1.5D);
    protected static final VoxelShape NORTH_BACK_RIGHT_ANKLE_SHAPE = Block.box(3.5D, 1.5D, 5.0D, 5.5D, 7.0D, 11.5D);
    protected static final VoxelShape NORTH_BACK_LEFT_ANKLE_SHAPE = Block.box(11.0D, 1.5D, 5.0D, 12.5D, 7.0D, 11.5D);
    protected static final VoxelShape NORTH_BACK_RIGHT_LEG_SHAPE = Block.box(3.0D, 0.0D, 5.0D, 5.5D, 1.5D, 10.5D);
    protected static final VoxelShape NORTH_BACK_LEFT_LEG_SHAPE = Block.box(10.5D, 0.0D, 5.0D, 13.0D, 1.5D, 10.5D);
    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG_SHAPE = Block.box(4.0D, 0.0D, 3.5D, 5.5D, 6.5D, 5.0D);
    protected static final VoxelShape NORTH_FRONT_LEFT_LEG_SHAPE = Block.box(10.5D, 0.0D, 3.5D, 12.0D, 6.5D, 5.0D);

    protected static final VoxelShape NORTH_EARS_SHAPE = VoxelShapes.or(NORTH_RIGHT_EAR_SHAPE, NORTH_LEFT_EAR_SHAPE);
    protected static final VoxelShape NORTH_EYES_SHAPE = VoxelShapes.or(NORTH_RIGHT_EYE_SHAPE, NORTH_LEFT_EYE_SHAPE);
    protected static final VoxelShape NORTH_LEGS_SHAPE = VoxelShapes.or(NORTH_BACK_RIGHT_ANKLE_SHAPE,
            NORTH_BACK_LEFT_ANKLE_SHAPE, NORTH_BACK_RIGHT_LEG_SHAPE, NORTH_BACK_LEFT_LEG_SHAPE,
            NORTH_FRONT_RIGHT_LEG_SHAPE, NORTH_FRONT_LEFT_LEG_SHAPE);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_HEAD_SHAPE, NORTH_BODY_SHAPE, NORTH_TAIL_SHAPE,
            NORTH_NOSE_SHAPE, NORTH_EARS_SHAPE, NORTH_EYES_SHAPE, NORTH_LEGS_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VERabbitPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
