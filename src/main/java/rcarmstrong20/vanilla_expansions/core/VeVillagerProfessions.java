package rcarmstrong20.vanilla_expansions.core;

import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvent;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
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
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister
            .create(ForgeRegistries.PROFESSIONS, VanillaExpansions.MOD_ID);

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
        VILLAGER_PROFESSIONS.register(name, () -> profession);
        return profession;
    }
}