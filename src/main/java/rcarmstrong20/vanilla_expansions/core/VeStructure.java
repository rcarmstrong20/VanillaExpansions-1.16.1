package rcarmstrong20.vanilla_expansions.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeNetherCabinStructure;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeOverworldCabinStructure;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructure
{
    public static final List<Structure<?>> STRUCTURES = new ArrayList<>();

    public static Structure<VillageConfig> overworldCabin = registerVillage("overworld_cabin",
            new VeOverworldCabinStructure(VillageConfig.field_236533_a_));
    public static Structure<VillageConfig> netherCabin = registerVillage("nether_cabin",
            new VeNetherCabinStructure(VillageConfig.field_236533_a_));

    /**
     * Helper method for registering all structures
     */
    private static Structure<VillageConfig> registerVillage(String name, Structure<VillageConfig> structure)
    {
        VanillaExpansions.LOGGER.info("Structures registered.");

        structure.setRegistryName(VanillaExpansions.MOD_ID, name);
        STRUCTURES.add(structure);
        return structure;
    }

    @SubscribeEvent
    public static void registerStructures(final RegistryEvent.Register<Structure<?>> event)
    {
        STRUCTURES.forEach(structure -> event.getRegistry().register(structure));
        STRUCTURES.forEach(
                structure -> Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure));
        STRUCTURES.clear();
        // register();

        VanillaExpansions.LOGGER.info("Structure registered.");
    }

    public static void register()
    {
        // StructureSeperationSettings(max spawn attempts (chunks), min spawn attempts
        // (chunks), structure seed);

        register(overworldCabin, new StructureSeparationSettings(10, 5, 724628428), true);
        // register(overworldCabin, new StructureSeparationSettings(10, 5, 724628428),
        // true);
    }

    /**
     * Adds the provided structure to the registry, and adds the separation
     * settings. The rarity of the structure is determined based on the values
     * passed into this method in the structureSeparationSettings argument. Called
     * by registerFeatures.
     */
    private static <F extends Structure<?>> void register(F structure, StructureSeparationSettings separationSettings,
            boolean transformSurroundingLand)
    {
        /**
         * Used in the locate command and will cause errors if our structures are not
         * added.
         */
        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

        /*
         * Will add land at the base of the structure like it does for Villages and
         * Outposts. Doesn't work well on structure that have pieces stacked vertically
         * or change in heights.
         */
        if (transformSurroundingLand)
        {
            // Structure.field_236384_t_.add(structure);
        }

        try
        {/*
          * setFinalStatic(ObfuscationReflectionHelper.findField(
          * DimensionStructuresSettings.class, "field_236191_b_"), false);
          */
            /*
             * DimensionStructuresSettings.field_236191_b_ = ImmutableMap .<Structure<?>,
             * StructureSeparationSettings>builder()
             * .putAll(DimensionStructuresSettings.field_236191_b_).put(structure,
             * separationSettings).build();
             */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void setFinalStatic(Field field, Object newValue) throws Exception
    {
        field.setAccessible(true);

        // remove final modifier from field
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }
}
