package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every villager profession instance that vanilla
 * expansions has.
 * 
 * @author Ryan
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeVillagerProfessions
{
    private static final List<VillagerProfession> VILLAGER_PROFESSIONS = new ArrayList<>();

    public static VillagerProfession lumberjack = register("lumberjack", VePointOfInterestTypes.lumberjack,
            VeSoundEvents.uiWoodcutterTakeResult);

    /**
     * @param name            The name for associated with the villager profession.
     * @param pointOfInterest The villagers work block.
     * @param sound           The sound played when the villager works at the
     *                        workstation.
     * @return The new profession.
     */
    private static VillagerProfession register(String name, PointOfInterestType poiType, SoundEvent sound)
    {
        VillagerProfession profession = new VillagerProfession(VanillaExpansions.MOD_ID + ":" + name, poiType,
                ImmutableSet.of(), ImmutableSet.of(), sound);
        profession.setRegistryName(VanillaExpansions.MOD_ID, name);
        VILLAGER_PROFESSIONS.add(profession);

        return profession;
    }

    @SubscribeEvent
    public static void registerVillagerProffesions(final RegistryEvent.Register<VillagerProfession> event)
    {
        VILLAGER_PROFESSIONS.forEach(villagerProfession -> event.getRegistry().register(villagerProfession));
        VILLAGER_PROFESSIONS.clear();
        VanillaExpansions.LOGGER.info("Villager professions registered.");
    }
}