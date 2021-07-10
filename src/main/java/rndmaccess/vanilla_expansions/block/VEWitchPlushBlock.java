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

public class VEWitchPlushBlock extends VEBaseVillagerPlushBlock
{
    protected static final VoxelShape NORTH_HAT_RIM = Block.box(3.0D, 11.0D, 4.5D, 13.0D, 12.0D, 13.0D);
    protected static final VoxelShape NORTH_HAT_MIDDLE = Block.box(4.0D, 12.0D, 5.5D, 12.0D, 15.0D, 12.0D);
    protected static final VoxelShape NORTH_HAT_TOP = Block.box(6.0D, 15.0D, 7.5D, 10.0D, 17.5D, 10.0D);
    protected static final VoxelShape NORTH_HAT_POINT = Block.box(6.5D, 17.5D, 8.5D, 9.0D, 19.5D, 9.5D);
    protected static final VoxelShape NORTH_WART = Block.box(7.0D, 5.0D, 2.5D, 8.0D, 6.0D, 3.5D);

    protected static final VoxelShape NORTH_FACE = VoxelShapes.or(NORTH_EYEBROW, NORTH_LEFT_EYE, NORTH_RIGHT_EYE,
            NORTH_NOSE, NORTH_WART);
    protected static final VoxelShape NORTH_HAT = VoxelShapes.or(NORTH_HAT_RIM, NORTH_HAT_MIDDLE, NORTH_HAT_TOP,
            NORTH_HAT_POINT);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_FEET, NORTH_FACE, NORTH_BODY, NORTH_HAT);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEWitchPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
