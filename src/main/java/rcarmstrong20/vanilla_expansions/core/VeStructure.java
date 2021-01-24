package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinConfig;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeNetherCabinStructure;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeOverworldCabinStructure;

/**
 *
 * @author Ryan
 *
 *         A class for holding every structure instance that vanilla expansions
 *         has.
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructure
{
    public static final List<Structure<?>> STRUCTURES = new ArrayList<>();

    public static Structure<VeCabinConfig> overworldCabin = registerStructure("overworld_cabin",
            new VeOverworldCabinStructure(VeCabinConfig.cabinConfig));
    public static Structure<VeCabinConfig> netherCabin = registerStructure("nether_cabin",
            new VeNetherCabinStructure(VeCabinConfig.cabinConfig));

    /**
     * Register a new cabin structure.
     *
     * @param name           The name of the structure.
     * @param cabinStructure An instance of a new Structure<C extends
     *                       IFeatureConfig>.
     * @return The new cabin structure.
     */

    /**
     * Add and set the registry name for the new structure.
     *
     * @param <C>       The feature config for the structure.
     * @param name      The name of the structure.
     * @param structure An instance of a new structure.
     * @return The new Structure.
     */
    private static <C extends IFeatureConfig> Structure<C> registerStructure(String name, Structure<C> structure)
    {
        VanillaExpansions.LOGGER.info("Structures registered.");

        structure.setRegistryName(VanillaExpansions.MOD_ID, name);
        STRUCTURES.add(structure);
        return structure;
    }

    @SubscribeEvent
    public static void registerStructures(final Register<Structure<?>> event)
    {
        register(event, STRUCTURES);
        VanillaExpansions.LOGGER.info("Structures registered.");
    }

    /**
     * A helper method that registers and adds them to the name structure bimap
     * which is used in the locate command.
     *
     * @param event         The registry structure event to use.
     * @param structureList The list of structures to register.
     */
    private static void register(Register<Structure<?>> event, List<Structure<?>> structureList)
    {
        for (Structure<?> structure : structureList)
        {
            event.getRegistry().register(structure);
            Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);
        }
    }
}
