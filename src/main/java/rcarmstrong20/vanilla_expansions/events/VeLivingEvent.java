package rcarmstrong20.vanilla_expansions.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.config.VeEntityConfig;

public class VeLivingEvent
{
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        // Cancels rabbit fall damage.
        if (VeEntityConfig.VeOverworldConfig.enableSaveTheBunnies.get() && livingEntity instanceof RabbitEntity)
        {
            event.setCanceled(true);
        }
    }
}