package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinConfig;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeNetherCabinStructure;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeOverworldCabinStructure;

/**
 * A class for holding every structure instance that vanilla expansions has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructure
{
    public static final List<Structure<?>> STRUCTURES = new ArrayList<>();

    public static Structure<VeCabinConfig> overworldCabin = register("overworld_cabin",
            new VeOverworldCabinStructure(VeCabinConfig.cabinConfig));
    public static Structure<VeCabinConfig> netherCabin = register("nether_cabin",
            new VeNetherCabinStructure(VeCabinConfig.cabinConfig));

    /**
     * Add this structure to the registry list and set its registry name.
     *
     * @param <C>       The feature config for the structure.
     * @param name      The name of the structure.
     * @param structure An instance for this new structure.
     * @return The new Structure.
     */
    private static <C extends IFeatureConfig> Structure<C> register(String name, Structure<C> structure)
    {
        structure.setRegistryName(VanillaExpansions.MOD_ID, name);
        STRUCTURES.add(structure);
        return structure;
    }

    @SubscribeEvent
    public static void registerStructures(final Register<Structure<?>> event)
    {
        register(event.getRegistry(), STRUCTURES);
    }

    /**
     * A helper method that registers and adds structures to the name structure
     * bimap which is used in the locate command. For this reason the built-in forge
     * registry for structures does not work.
     *
     * @param registry
     * @param structureList A list of structures to register.
     */
    private static void register(IForgeRegistry<Structure<?>> registry, List<Structure<?>> structureList)
    {
        for (Structure<?> structure : structureList)
        {
            registry.register(structure);
            Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);
        }
    }
}
