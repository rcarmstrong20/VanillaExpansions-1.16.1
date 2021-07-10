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

public class VEBaseVillagerPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_HEAD = Block.box(4.0D, 5.0D, 5.5D, 12.0D, 14.0D, 12.0D);
    protected static final VoxelShape NORTH_TORSO = Block.box(5.0D, 1.5D, 7.0D, 11.0D, 5.0D, 11.0D);

    protected static final VoxelShape NORTH_RIGHT_FOOT = Block.box(5.5D, 0.0D, 8.0D, 7.5D, 1.5D, 10.0D);
    protected static final VoxelShape NORTH_LEFT_FOOT = Block.box(8.5D, 0.0D, 8.0D, 10.5D, 1.5D, 10.0D);

    protected static final VoxelShape NORTH_ARM = Block.box(4.5D, 1.5D, 5.0D, 11.5D, 5.0D, 9.0D);

    protected static final VoxelShape NORTH_EYEBROW = Block.box(5.5D, 9.0D, 5.0D, 10.5D, 10.0D, 5.5D);
    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(8.5D, 8.0D, 5.0D, 10.5D, 9.0D, 5.5D);
    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(5.5D, 8.0D, 5.0D, 7.5D, 9.0D, 5.5D);
    protected static final VoxelShape NORTH_MOUTH = Block.box(6.0D, 6.0D, 5.0D, 10.0D, 7.0D, 5.5D);
    protected static final VoxelShape NORTH_NOSE = Block.box(7.0D, 4.0D, 3.5D, 9.0D, 7.5D, 5.5D);

    protected static final VoxelShape NORTH_FEET = VoxelShapes.or(NORTH_RIGHT_FOOT, NORTH_LEFT_FOOT);
    protected static final VoxelShape NORTH_FACE = VoxelShapes.or(NORTH_EYEBROW, NORTH_LEFT_EYE, NORTH_RIGHT_EYE,
            NORTH_MOUTH, NORTH_NOSE);
    protected static final VoxelShape NORTH_BODY = VoxelShapes.or(NORTH_HEAD, NORTH_TORSO, NORTH_ARM);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_FEET, NORTH_FACE, NORTH_BODY);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEBaseVillagerPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
