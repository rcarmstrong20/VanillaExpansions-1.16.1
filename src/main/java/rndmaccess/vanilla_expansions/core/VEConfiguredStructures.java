package rndmaccess.vanilla_expansions.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraftforge.fml.common.Mod;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.gen.feature.structure.VECabinStructurePieces;

/**
 * A class for holding every configured structure instance that vanilla
 * expansions has.
 *
 * @author Ryan
 */

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEConfiguredStructures
{
    public static StructureFeature<?, ?> configuredTaigaCabin = register("taiga_cabin",
            VEStructures.overworldCabin.configured(new VillageConfig(() ->
            {
                return VECabinStructurePieces.TAIGA_START;
            }, 10)));
    public static StructureFeature<?, ?> configuredIcyTaigaCabin = register("icy_taiga_cabin",
            VEStructures.overworldCabin.configured(new VillageConfig(() ->
            {
                return VECabinStructurePieces.ICY_TAIGA_START;
            }, 10)));
    public static StructureFeature<?, ?> configuredForestCabin = register("forest_cabin",
            VEStructures.overworldCabin.configured(new VillageConfig(() ->
            {
                return VECabinStructurePieces.FOREST_START;
            }, 10)));
    public static StructureFeature<?, ?> configuredCrimsonCabin = register("crimson_cabin",
            VEStructures.netherCabin.configured(new VillageConfig(() ->
            {
                return VECabinStructurePieces.CRIMSON_START;
            }, 10)));

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

        return WorldGenRegistries.register(registry, new ResourceLocation(VanillaExpansions.MOD_ID, name), structure);
    }
}
