package rndmaccess.vanilla_expansions.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.server.ServerWorld;
import rndmaccess.vanilla_expansions.core.VEBlocks;
import rndmaccess.vanilla_expansions.core.VEConfiguredFeatures;

public class VEMushroomBlock extends MushroomBlock
{
    public VEMushroomBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean growMushroom(ServerWorld world, BlockPos pos, BlockState state, Random rand)
    {
        world.removeBlock(pos, false);
        ConfiguredFeature<?, ?> hugeMushroom = Features.HUGE_RED_MUSHROOM;

        if (this == VEBlocks.purpleMushroom)
        {
            hugeMushroom = VEConfiguredFeatures.HUGE_PURPLE_MUSHROOM;
        }

        if (hugeMushroom.place(world, world.getChunkSource().getGenerator(), rand, pos))
        {
            return true;
        }
        else
        {
            world.setBlock(pos, state, 3);
            return false;
        }
    }
}
