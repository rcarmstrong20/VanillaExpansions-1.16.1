package rndmaccess.vanilla_expansions.events;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rndmaccess.vanilla_expansions.core.VEEntityTypes;

public class VELivingDeathEvent
{
    @SubscribeEvent
    public void onLivingDeath(final LivingDeathEvent event)
    {
        LivingEntity entity = event.getEntityLiving();
        World world = entity.getCommandSenderWorld();
        BlockPos pos = entity.blockPosition();
        BlockState state = world.getBlockState(pos);
        Random random = entity.getRandom();

        if (entity.getType().equals(EntityType.ZOMBIE) && entity.getHealth() <= 2)
        {
            if (state.getFluidState().is(FluidTags.LAVA))
            {
                convertZombie(entity, world, pos, random);
            }
            else
            {
                if (random.nextFloat() <= 0.05 && event.getSource().isFire())
                {
                    convertZombie(entity, world, pos, random);
                }
            }
        }
        else
        {
            event.setResult(Result.DEFAULT);
        }
    }

    private static void convertZombie(LivingEntity entity, World world, BlockPos pos, Random random)
    {
        world.playSound((PlayerEntity) null, pos, SoundEvents.LAVA_AMBIENT, SoundCategory.BLOCKS,
                2.0F + random.nextFloat(), 2.0F + random.nextFloat());
        ((MobEntity) entity).convertTo(VEEntityTypes.charredRemnant, true);
    }
}
