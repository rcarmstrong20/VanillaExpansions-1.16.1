package rndmaccess.vanilla_expansions.events;

import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.core.VEEntityTypes;

public class VEEntityAttributeCreationEvent
{
    @SubscribeEvent
    public void registerEntityAttributes(final EntityAttributeCreationEvent event)
    {
        event.put(VEEntityTypes.enderHorse, HorseEntity.createBaseHorseAttributes().build());
        event.put(VEEntityTypes.charredRemnant, ZombieEntity.createAttributes().build());

        event.setResult(Result.ALLOW);

        VanillaExpansions.LOGGER.info("Added entity attributes.");
    }
}
