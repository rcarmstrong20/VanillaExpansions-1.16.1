package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeNetherCabinStructure;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeOverworldCabinStructure;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructure
{
    public static final List<Structure<?>> STRUCTURES = new ArrayList<>();

    public static Structure<VillageConfig> overworldCabin = registerVillageStructure("overworld_cabin",
            new VeOverworldCabinStructure(VillageConfig.field_236533_a_));
    public static Structure<VillageConfig> netherCabin = registerVillageStructure("nether_cabin",
            new VeNetherCabinStructure(VillageConfig.field_236533_a_));

    /**
     * Helper method for registering all structures
     */
    private static Structure<VillageConfig> registerVillageStructure(String name, Structure<VillageConfig> structure)
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
        VanillaExpansions.LOGGER.info("Structure registered.");
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
