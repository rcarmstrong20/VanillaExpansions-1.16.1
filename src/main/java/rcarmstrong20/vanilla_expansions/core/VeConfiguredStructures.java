package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.StructureFeature;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinConfig;

/**
 * A class for holding every configured structure instance that vanilla
 * expansions has.
 *
 * @author Ryan
 */
public class VeConfiguredStructures
{
    public static StructureFeature<?, ?> configuredTaigaCabin = register("taiga_cabin", VeStructure.overworldCabin
            .withConfiguration(new VeCabinConfig(new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/taiga_cabin"))));
    public static StructureFeature<?, ?> configuredForestCabin = register("forest_cabin",
            VeStructure.overworldCabin.withConfiguration(
                    new VeCabinConfig(new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/forest_cabin"))));
    public static StructureFeature<?, ?> configuredCrimsonCabin = register("crimson_cabin",
            VeStructure.netherCabin.withConfiguration(
                    new VeCabinConfig(new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/crimson_cabin"))));

    /**
     * A helper method for automatically registering every new configured structure
     * feature.
     *
     * @param name      The name of this configured structure.
     * @param structure An instance of a new configured structure.
     * @return A new registered configured structure feature.
     */
    private static StructureFeature<?, ?> register(String name, StructureFeature<?, ?> structure)
    {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

        return Registry.register(registry, new ResourceLocation(VanillaExpansions.MOD_ID, name), structure);
    }
}
