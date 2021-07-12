package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VESnowVillagerPlushBlock extends VEBaseVillagerPlushBlock
{
    protected static final VoxelShape NORTH_HAT = Block.box(3.0D, 11.0D, 4.5D, 13.0D, 15.0D, 13.0D);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(VEBaseVillagerPlushBlock.NORTH_SHAPE, NORTH_HAT);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(NORTH_SHAPE);

    public VESnowVillagerPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
