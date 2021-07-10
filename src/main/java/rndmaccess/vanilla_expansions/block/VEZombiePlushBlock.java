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

public class VEZombiePlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_HEAD = Block.box(3.5D, 7.0D, 5.0D, 12.5D, 15.0D, 13.5D);
    protected static final VoxelShape NORTH_TORSO = Block.box(4.0D, 2.0D, 7.0D, 12.0D, 7.0D, 12.0D);

    protected static final VoxelShape NORTH_LEFT_LEG = Block.box(8.5D, 0.0D, 8.0D, 11.5D, 2.0D, 11.0D);
    protected static final VoxelShape NORTH_RIGHT_LEG = Block.box(4.5D, 0.0D, 8.0D, 7.5D, 2.0D, 11.0D);

    protected static final VoxelShape NORTH_LEFT_ARM = Block.box(11.0D, 5.0D, 3.0D, 14.0D, 8.0D, 10.5D);
    protected static final VoxelShape NORTH_RIGHT_ARM = Block.box(2.0D, 5.0D, 3.0D, 5.0D, 8.0D, 10.5D);

    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(9.0D, 11.0D, 4.5D, 11.0D, 13.0D, 5.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(5.0D, 11.0D, 4.5D, 7.0D, 13.0D, 5.0D);

    protected static final VoxelShape NORTH_LEGS = VoxelShapes.or(NORTH_LEFT_LEG, NORTH_RIGHT_LEG);
    protected static final VoxelShape NORTH_ARMS = VoxelShapes.or(NORTH_LEFT_ARM, NORTH_RIGHT_ARM);
    protected static final VoxelShape NORTH_EYES = VoxelShapes.or(NORTH_LEFT_EYE, NORTH_RIGHT_EYE);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_HEAD, NORTH_TORSO, NORTH_LEGS, NORTH_ARMS,
            NORTH_EYES);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEZombiePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
