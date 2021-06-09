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

public class VECreeperPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape CREEPER_NORTH_HEAD_SHAPE = Block.box(4.5D, 9.0D, 4.5D, 11.5D, 16.0D, 11.5D);
    protected static final VoxelShape CREEPER_NORTH_TORSO_SHAPE = Block.box(5.5D, 3.0D, 5.5D, 10.5D, 9.0D, 10.5D);
    protected static final VoxelShape CREEPER_NORTH_FRONT_FOOT_SHAPE = Block.box(5.5D, 0.0D, 2.5D, 10.5D, 3.0D, 5.5D);
    protected static final VoxelShape CREEPER_NORTH_BACK_FOOT_SHAPE = Block.box(5.5D, 0.0D, 10.5D, 10.5D, 3.0D, 13.5D);
    protected static final VoxelShape CREEPER_NORTH_RIGHT_EYE_SHAPE = Block.box(8.5D, 12.5D, 4.0D, 10.5D, 14.5D, 4.5D);
    protected static final VoxelShape CREEPER_NORTH_LEFT_EYE_SHAPE = Block.box(5.5D, 12.5D, 4.0D, 7.5D, 14.5D, 4.5D);
    protected static final VoxelShape CREEPER_NORTH_MIDDLE_MOUTH_SHAPE = Block.box(7.5D, 10.5D, 4.0D, 8.5D, 12.5D,
            4.5D);
    protected static final VoxelShape CREEPER_NORTH_RIGHT_MOUTH_SHAPE = Block.box(8.5D, 9.5D, 4.0D, 9.5D, 11.5D, 4.5D);
    protected static final VoxelShape CREEPER_NORTH_LEFT_MOUTH_SHAPE = Block.box(6.5D, 9.5D, 4.0D, 7.5D, 11.5D, 4.5D);

    protected static final VoxelShape CREEPER_NORTH_FEET_SHAPE = VoxelShapes.or(CREEPER_NORTH_FRONT_FOOT_SHAPE,
            CREEPER_NORTH_BACK_FOOT_SHAPE);

    protected static final VoxelShape CREEPER_NORTH_EYES_SHAPE = VoxelShapes.or(CREEPER_NORTH_RIGHT_EYE_SHAPE,
            CREEPER_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape CREEPER_NORTH_MOUTH_SHAPE = VoxelShapes.or(CREEPER_NORTH_MIDDLE_MOUTH_SHAPE,
            CREEPER_NORTH_RIGHT_MOUTH_SHAPE, CREEPER_NORTH_LEFT_MOUTH_SHAPE);

    protected static final VoxelShape CREEPER_NORTH_SHAPE = VoxelShapes.or(CREEPER_NORTH_HEAD_SHAPE,
            CREEPER_NORTH_TORSO_SHAPE, CREEPER_NORTH_FEET_SHAPE, CREEPER_NORTH_EYES_SHAPE, CREEPER_NORTH_MOUTH_SHAPE);

    protected static final VoxelShape CREEPER_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, CREEPER_NORTH_SHAPE);
    protected static final VoxelShape CREEPER_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, CREEPER_NORTH_SHAPE);
    protected static final VoxelShape CREEPER_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, CREEPER_NORTH_SHAPE);

    public VECreeperPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, CREEPER_NORTH_SHAPE, CREEPER_SOUTH_SHAPE, CREEPER_WEST_SHAPE, CREEPER_WEST_SHAPE);
    }
}
