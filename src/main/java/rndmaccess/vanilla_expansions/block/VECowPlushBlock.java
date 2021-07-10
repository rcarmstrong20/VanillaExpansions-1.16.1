package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VECowPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_TORSO_SHAPE = Block.box(3.5D, 2.0D, 6.0D, 12.5D, 8.0D, 14.5D);
    protected static final VoxelShape NORTH_HEAD_SHAPE = Block.box(4.0D, 5.5D, 3.0D, 12.0D, 13.0D, 10.5D);
    protected static final VoxelShape NORTH_SNOUT_SHAPE = Block.box(6.0D, 6.0D, 1.5D, 10.0D, 9.0D, 3.5D);
    protected static final VoxelShape NORTH_UTTER_SHAPE = Block.box(7.0D, 1.0D, 11.5D, 9.0D, 3.0D, 15.0D);

    protected static final VoxelShape NORTH_BACK_LEFT_LEG_SHAPE = Block.box(10.5D, 0.0D, 12.5D, 12.5D, 2.0D, 14.5D);
    protected static final VoxelShape NORTH_BACK_RIGHT_LEG_SHAPE = Block.box(3.5D, 0.0D, 12.5D, 5.5D, 2.0D, 14.5D);
    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG_SHAPE = Block.box(3.5D, 0.0D, 6.0D, 5.5D, 2.0D, 8.0D);
    protected static final VoxelShape NORTH_FRONT_LEFT_LEG_SHAPE = Block.box(10.5D, 0.0D, 6.0D, 12.5D, 2.0D, 8.0D);

    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.box(9.0D, 9.5D, 2.5D, 11.0D, 11.5D, 3.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.box(5.0D, 9.5D, 2.5D, 7.0D, 11.5D, 3.0D);

    protected static final VoxelShape NORTH_RIGHT_HORN_SHAPE = Block.box(2.5D, 10.5D, 5.0D, 5.0D, 14.0D, 8.0D);
    protected static final VoxelShape NORTH_LEFT_HORN_SHAPE = Block.box(11.0D, 10.5D, 5.0D, 13.5D, 14.0D, 8.0D);

    protected static final VoxelShape NORTH_LEGS_SHAPE = VoxelShapes.or(NORTH_BACK_LEFT_LEG_SHAPE,
            NORTH_BACK_RIGHT_LEG_SHAPE, NORTH_FRONT_RIGHT_LEG_SHAPE, NORTH_FRONT_LEFT_LEG_SHAPE);
    protected static final VoxelShape NORTH_EYES_SHAPE = VoxelShapes.or(NORTH_LEFT_EYE_SHAPE, NORTH_RIGHT_EYE_SHAPE);
    protected static final VoxelShape NORTH_HORNS_SHAPE = VoxelShapes.or(NORTH_RIGHT_HORN_SHAPE, NORTH_LEFT_HORN_SHAPE);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_TORSO_SHAPE, NORTH_LEGS_SHAPE,
            NORTH_HEAD_SHAPE, NORTH_SNOUT_SHAPE, NORTH_EYES_SHAPE, NORTH_HORNS_SHAPE, NORTH_UTTER_SHAPE);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VECowPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
