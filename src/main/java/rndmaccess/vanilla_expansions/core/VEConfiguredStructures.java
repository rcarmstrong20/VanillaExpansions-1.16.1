package rndmaccess.vanilla_expansions.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
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
            VEStructures.overworldCabin.configured(createVillageConfig(VECabinStructurePieces.TAIGA_START, 7)));
    public static StructureFeature<?, ?> configuredIcyTaigaCabin = register("icy_taiga_cabin",
            VEStructures.overworldCabin.configured(createVillageConfig(VECabinStructurePieces.ICY_TAIGA_START, 7)));
    public static StructureFeature<?, ?> configuredForestCabin = register("forest_cabin",
            VEStructures.overworldCabin.configured(createVillageConfig(VECabinStructurePieces.FOREST_START, 7)));
    public static StructureFeature<?, ?> configuredCrimsonCabin = register("crimson_cabin",
            VEStructures.netherCabin.configured(createVillageConfig(VECabinStructurePieces.CRIMSON_START, 7)));

    /**
     * When creating village config's the size needs to be between 0 and 7.
     *
     * @param jigsaw The starting jigsaw piece.
     * @param size   The size of the structure.
     * @return The new config.
     */
    private static VillageConfig createVillageConfig(JigsawPattern jigsaw, int size)
    {
        return new VillageConfig(() -> jigsaw, size);
    }

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
