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

public class VESwampVillagerPlushBlock extends VEBaseVillagerPlushBlock
{
    protected static final VoxelShape NORTH_HAT_TOP = Block.box(3.0, 13.0, 4.5, 13.0, 15.0, 13.0);
    protected static final VoxelShape NORTH_LEFT_BACK_HAT_DETAIL = Block.box(9.0, 12.0, 9.0, 13.0, 13.0, 13.0);
    protected static final VoxelShape NORTH_RIGHT_BACK_HAT_DETAIL = Block.box(3.0, 11.0, 9.0, 6.5, 13.0, 13.0);
    protected static final VoxelShape NORTH_LEFT_FRONT_HAT_DETAIL = Block.box(11.0, 12.0, 4.5, 13.0, 13.0, 6.5);
    protected static final VoxelShape NORTH_MIDDLE_FRONT_HAT_DETAIL0 = Block.box(5.0, 12.0, 4.5, 9.0, 13.0, 5.5);
    protected static final VoxelShape NORTH_MIDDLE_FRONT_HAT_DETAIL1 = Block.box(8.0, 11.0, 4.5, 9.0, 12.0, 5.5);

    protected static final VoxelShape NORTH_HAT = VoxelShapes.or(NORTH_HAT_TOP, NORTH_LEFT_BACK_HAT_DETAIL,
            NORTH_RIGHT_BACK_HAT_DETAIL, NORTH_LEFT_FRONT_HAT_DETAIL, NORTH_MIDDLE_FRONT_HAT_DETAIL0,
            NORTH_MIDDLE_FRONT_HAT_DETAIL1);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(VEBaseVillagerPlushBlock.NORTH_SHAPE, NORTH_HAT);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VESwampVillagerPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
