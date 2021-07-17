package rndmaccess.vanilla_expansions.block;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import rndmaccess.vanilla_expansions.util.VEBlockStateUtil;

public class VESnowyStairsBlock extends StairsBlock
{
    public VESnowyStairsBlock(Supplier<BlockState> state, Properties properties)
    {
        super(state, properties);
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos pos2, boolean bool)
    {
        // This makes the block underneath snowy when this block is moved above it.
        makeSnowy(world, pos, state);
    }

    @Override
    public void updateIndirectNeighbourShapes(BlockState state, IWorld world, BlockPos pos, int p_196248_4_,
            int p_196248_5_)
    {
        // This makes the block underneath snowy when it's placed.
        makeSnowy(world, pos, state);
    }

    /**
     * This is a helper method that updates the block underneath and sets snowy to
     * true. If the block has that property and the of the snowy stair is covering
     * the block underneath.
     *
     * @param world
     * @param pos   The location of the this snowy block.
     * @param state The snowy stairs as a block state.
     */
    private static void makeSnowy(IWorld world, BlockPos pos, BlockState state)
    {
        BlockPos belowPos = pos.below();
        BlockState belowState = world.getBlockState(belowPos);

        for (int i = 0; i < BOTTOM_SHAPES.length; i++)
        {
            if (belowState.hasProperty(BlockStateProperties.SNOWY)
                    && state.getFaceOcclusionShape(world, pos, Direction.DOWN).optimize() == BOTTOM_SHAPES[i]
                    && !VEBlockStateUtil.isBottomSlab(belowState))
            {
                world.setBlock(belowPos, belowState.setValue(BlockStateProperties.SNOWY, true), 3);
            }
        }
    }
}
