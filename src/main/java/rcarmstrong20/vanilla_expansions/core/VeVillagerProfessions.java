package rcarmstrong20.vanilla_expansions.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeVillagerProfessions
{
    private static final Constructor<VillagerProfession> VILLAGER_POFESSION_CONSTRUCTOR = ObfuscationReflectionHelper
            .findConstructor(VillagerProfession.class, String.class, PointOfInterestType.class, ImmutableSet.class,
                    ImmutableSet.class, SoundEvent.class);
    private static final List<VillagerProfession> VILLAGER_PROFESSIONS = new ArrayList<>();

    public static final VillagerProfession LUMBERJACK = register("lumberjack", VePointOfInterestTypes.LUMBERJACK,
            VeSoundEvents.UI_WOODCUTTER_TAKE_RESULT);

    /**
     * @param name            The name for associated with the villager profession.
     * @param pointOfInterest The villagers work block.
     * @param sound           The sound played when the villager works at the
     *                        workstation.
     * @return The new profession.
     */
    private static VillagerProfession register(String name, PointOfInterestType pointOfInterest, SoundEvent sound)
    {
        VillagerProfession profession = null;

        try
        {
            profession = VILLAGER_POFESSION_CONSTRUCTOR.newInstance(VanillaExpansions.MOD_ID + ":" + name,
                    pointOfInterest, ImmutableSet.of(), ImmutableSet.of(), sound);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e)
        {
            e.printStackTrace();
        }

        profession.setRegistryName(VanillaExpansions.MOD_ID, name);
        VILLAGER_PROFESSIONS.add(profession);
        return profession;
    }

    @SubscribeEvent
    public static void registerVillagerProffesions(final RegistryEvent.Register<VillagerProfession> event)
    {
        VILLAGER_PROFESSIONS.forEach(villager_profession -> event.getRegistry().register(villager_profession));
        VILLAGER_PROFESSIONS.clear();
        VanillaExpansions.LOGGER.info("Villager professions registered.");
    }
}