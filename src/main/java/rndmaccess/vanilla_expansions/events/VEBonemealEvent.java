package rndmaccess.vanilla_expansions.events;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rndmaccess.vanilla_expansions.core.VEBlockTags;

public class VEBonemealEvent
{
    /**
     * Called when the player attempts to use bone meal on a block.
     *
     * @param event A new instance of the BonemealEvent.
     */
    @SubscribeEvent
    public void onBonemeal(final BonemealEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Random random = new Random();

        // Used to add functionality for growing snapdragons on end stone when using
        // bone meal.
        if (VEBlockTags.endBoneMealable.contains(event.getBlock().getBlock()))
        {
            if (!world.isClientSide())
            {
                for (int i = 0; i < 128; ++i)
                {
                    BlockPos blockpos = pos;

                    for (int j = 0; j < i / 16; ++j)
                    {
                        blockpos = blockpos.offset(random.nextInt(3) - 1,
                                (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
                        BlockState state = world.getBlockState(blockpos);
                        BlockState belowState = world.getBlockState(blockpos.below());

                        if (VEBlockTags.endBoneMealable.contains(belowState.getBlock()) && isAir(state))
                        {
                            world.setBlock(blockpos,
                                    VEBlockTags.endBoneMealPlants.getRandomElement(random).defaultBlockState(), 3);
                        }
                    }
                }
            }
            event.setResult(Result.ALLOW);
        }
    }

    /**
     * @param state The block state to check.
     * @return true if the block state's material is air.
     */
    private static boolean isAir(BlockState state)
    {
        return state.getMaterial().equals(Material.AIR);
    }
}
