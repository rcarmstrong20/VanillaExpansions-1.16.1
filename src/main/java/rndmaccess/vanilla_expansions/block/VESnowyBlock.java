package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import rndmaccess.vanilla_expansions.util.VEBlockStateUtil;

public class VESnowyBlock extends Block
{
    public VESnowyBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean bool)
    {
        // This makes the block underneath snowy when this block is moved above it.
        makeSnowy(world, pos);
    }

    @Override
    public void updateIndirectNeighbourShapes(BlockState state, IWorld world, BlockPos pos, int p_196248_4_,
            int p_196248_5_)
    {
        // This makes the block underneath snowy when it's placed.
        makeSnowy(world, pos);
    }

    /**
     * This is a helper method that updates the block underneath and sets snowy to
     * true. If the block has that property.
     *
     * @param world
     * @param pos   The location of the this snowy block.
     */
    public static void makeSnowy(IWorld world, BlockPos pos)
    {
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);

        if (!world.isClientSide() && belowState.hasProperty(BlockStateProperties.SNOWY)
                && !VEBlockStateUtil.isBottomSlab(belowState))
        {
            world.setBlock(belowPos, belowState.setValue(BlockStateProperties.SNOWY, true), 3);
        }
    }
}
