package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

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

    public static Structure<VeCabinConfig> overworldCabin = registerCabinStructure("overworld_cabin",
            new VeOverworldCabinStructure(VeCabinConfig.cabinConfig));
    public static Structure<VeCabinConfig> netherCabin = registerCabinStructure("nether_cabin",
            new VeNetherCabinStructure(VeCabinConfig.cabinConfig));

    /**
     * @param name           The name of the structure.
     * @param cabinStructure An instance of a new Structure<VeCabinConfig>.
     * @return The new structure.
     */
    private static Structure<VeCabinConfig> registerCabinStructure(String name, Structure<VeCabinConfig> cabinStructure)
    {
        VanillaExpansions.LOGGER.info("Structures registered.");

        cabinStructure.setRegistryName(VanillaExpansions.MOD_ID, name);
        STRUCTURES.add(cabinStructure);
        return cabinStructure;
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
     * @param event         The registry event to use.
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
