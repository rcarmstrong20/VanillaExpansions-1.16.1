package rcarmstrong20.vanilla_expansions.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinConfig;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeConfiguredStructures
{
    public static final Map<String, StructureFeature<?, ?>> CONFIGURED_STRUCTURES = new HashMap<>();

    public static StructureFeature<?, ?> configuredTaigaCabin = register("taiga_cabin", VeStructure.overworldCabin
            .withConfiguration(new VeCabinConfig(new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/taiga_cabin"))));
    public static StructureFeature<?, ?> configuredForestCabin = register("forest_cabin",
            VeStructure.overworldCabin.withConfiguration(
                    new VeCabinConfig(new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/forest_cabin"))));
    public static StructureFeature<?, ?> configuredCrimsonCabin = register("crimson_cabin",
            VeStructure.netherCabin.withConfiguration(
                    new VeCabinConfig(new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/crimson_cabin"))));

    /**
     * @param name      The name of this configured structure.
     * @param structure An instance of a new configured structure.
     * @return The instance of this configured structure.
     */
    private static StructureFeature<?, ?> register(String name, StructureFeature<?, ?> structure)
    {
        if (!CONFIGURED_STRUCTURES.containsKey(name))
        {
            CONFIGURED_STRUCTURES.put(name, structure);
        }
        return structure;
    }

    public static void register()
    {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;

        CONFIGURED_STRUCTURES.forEach((name, structure) -> Registry.register(registry,
                new ResourceLocation(VanillaExpansions.MOD_ID, name), structure));
        CONFIGURED_STRUCTURES.clear();

        VanillaExpansions.LOGGER.info("Configured structures registered.");
    }

    /*
     * private static StructureFeature<?, ?> register(String name,
     * StructureFeature<?, ?> structure) { Registry<StructureFeature<?, ?>> registry
     * = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
     *
     * return Registry.register(registry, new
     * ResourceLocation(VanillaExpansions.MOD_ID, name), structure); }
     */
}
