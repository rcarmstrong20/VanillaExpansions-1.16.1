package rndmaccess.vanilla_expansions.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import rndmaccess.vanilla_expansions.core.VEBlocks;
import rndmaccess.vanilla_expansions.util.VEBlockUtil;

public class VEGrassSlabBlock extends VESnowyDirtSlabBlock
{
    public VEGrassSlabBlock(Properties properties)
    {
        super(properties);
    }

    public static boolean canBeGrass(BlockState state, IWorldReader worldReader, BlockPos pos)
    {
        BlockPos abovepos = pos.above();
        BlockState aboveState = worldReader.getBlockState(abovepos);

        if (aboveState.is(Blocks.SNOW) && aboveState.getValue(SnowBlock.LAYERS) == 1)
        {
            return true;
        }
        else if (aboveState.getFluidState().getAmount() == 8)
        {
            return false;
        }
        else
        {
            return !aboveState.isSolidRender(worldReader, abovepos) || VEBlockUtil.isBottom(state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (!canBeGrass(state, world, pos))
        {
            if (!world.isAreaLoaded(pos, 3))
                return;

            BlockState stateAt = world.getBlockState(pos);
            world.setBlockAndUpdate(pos, VEBlocks.dirtSlab.defaultBlockState().setValue(TYPE, stateAt.getValue(TYPE)));
        }
    }
}
