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

public class VEBeePlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_BODY = Block.box(4.0D, 0.0D, 2.0D, 12.0D, 7.0D, 13.0D);
    protected static final VoxelShape NORTH_STINGER = Block.box(7.5D, 3.0D, 13.0D, 8.5D, 4.0D, 14.0D);

    protected static final VoxelShape NORTH_LEFT_WING = Block.box(9.0D, 7.0D, 4.0D, 14.0D, 8.0D, 9.0D);
    protected static final VoxelShape NORTH_RIGHT_WING = Block.box(2.0D, 7.0D, 4.0D, 7.0D, 8.0D, 9.0D);

    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(3.5D, 2.0D, 1.5D, 7.0D, 5.0D, 4.0D);
    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(9.0D, 2.0D, 1.5D, 12.5D, 5.0D, 4.0D);

    protected static final VoxelShape NORTH_FRONT_LEFT_LEG_FRONT = Block.box(12.0D, 0.0D, 4.0D, 14.0D, 1.0D, 5.0D);
    protected static final VoxelShape NORTH_FRONT_LEFT_LEG_BACK = Block.box(12.0D, 0.0D, 5.0D, 13.0D, 1.0D, 6.0D);

    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG_FRONT = Block.box(2.0D, 0.0D, 4.0D, 4.0D, 1.0D, 5.0D);
    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG_BACK = Block.box(3.0D, 0.0D, 5.0D, 4.0D, 1.0D, 6.0D);

    protected static final VoxelShape NORTH_BACK_LEFT_LEG = Block.box(12.0D, 0.0D, 11.0D, 13.0D, 1.0D, 12.0D);
    protected static final VoxelShape NORTH_BACK_RIGHT_LEG = Block.box(3.0D, 0.0D, 11.0D, 4.0D, 1.0D, 12.0D);

    protected static final VoxelShape NORTH_LEGS = VoxelShapes.or(NORTH_FRONT_LEFT_LEG_FRONT, NORTH_FRONT_LEFT_LEG_BACK,
            NORTH_FRONT_RIGHT_LEG_FRONT, NORTH_FRONT_RIGHT_LEG_BACK, NORTH_BACK_LEFT_LEG, NORTH_BACK_RIGHT_LEG);
    protected static final VoxelShape NORTH_EYES = VoxelShapes.or(NORTH_RIGHT_EYE, NORTH_LEFT_EYE);
    protected static final VoxelShape NORTH_WINGS = VoxelShapes.or(NORTH_LEFT_WING, NORTH_RIGHT_WING);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY, NORTH_STINGER, NORTH_LEGS, NORTH_EYES,
            NORTH_WINGS);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEBeePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
