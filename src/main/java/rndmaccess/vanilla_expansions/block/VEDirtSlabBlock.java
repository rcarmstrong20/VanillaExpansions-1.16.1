package rndmaccess.vanilla_expansions.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import rndmaccess.vanilla_expansions.core.VEBlocks;

public class VEDirtSlabBlock extends VESoilSlabBlock
{
    public VEDirtSlabBlock(Properties properties)
    {
        super(properties);
    }

    private static boolean canPropagate(BlockState state, IWorldReader worldReader, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return VEGrassSlabBlock.canBeGrass(state, worldReader, pos)
                && !worldReader.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (world.getMaxLocalRawBrightness(pos.above()) >= 9)
        {
            BlockState blockstate = VEBlocks.grassSlab.defaultBlockState();

            for (int i = 0; i < 4; ++i)
            {
                BlockPos atPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                BlockPos abovePos = atPos.above();
                BlockState stateAt = world.getBlockState(atPos);

                if (stateAt.is(VEBlocks.grassSlab)
                        || stateAt.is(Blocks.GRASS_BLOCK) && canPropagate(blockstate, world, atPos))
                {
                    world.setBlockAndUpdate(pos,
                            blockstate.setValue(TYPE, state.getValue(TYPE)).setValue(VESnowyDirtSlabBlock.SNOWY,
                                    Boolean.valueOf(world.getBlockState(abovePos).is(Blocks.SNOW))));
                }
            }
        }
    }
}
