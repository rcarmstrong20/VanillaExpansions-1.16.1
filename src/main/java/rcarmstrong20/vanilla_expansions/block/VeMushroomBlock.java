package rcarmstrong20.vanilla_expansions.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.server.ServerWorld;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeConfiguredFeatures;

public class VeMushroomBlock extends MushroomBlock
{
    public VeMushroomBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean grow(ServerWorld world, BlockPos pos, BlockState state, Random rand)
    {
        world.removeBlock(pos, false);
        ConfiguredFeature<?, ?> hugeMushroom = Features.HUGE_RED_MUSHROOM;

        if (this == VeBlocks.purpleMushroom)
        {
            hugeMushroom = VeConfiguredFeatures.HUGE_PURPLE_MUSHROOM;
        }

        if (hugeMushroom.generate(world, world.getChunkProvider().getChunkGenerator(), rand, pos))
        {
            return true;
        }
        else
        {
            world.setBlockState(pos, state, 3);
            return false;
        }
    }
}
