package rndmaccess.vanilla_expansions.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rndmaccess.vanilla_expansions.config.VEConfig;

public class VELivingEvent
{
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        // Cancels rabbit fall damage.
        if (VEConfig.enableSaveTheBunnies.get() && livingEntity instanceof RabbitEntity)
        {
            event.setCanceled(true);
        }
    }
}
