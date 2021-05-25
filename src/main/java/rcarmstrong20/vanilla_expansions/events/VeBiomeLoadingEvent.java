package rcarmstrong20.vanilla_expansions.events;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.config.VeFeatureGenConfig;
import rcarmstrong20.vanilla_expansions.core.VeConfiguredFeatures;
import rcarmstrong20.vanilla_expansions.core.VeConfiguredStructures;

public class VeBiomeLoadingEvent
{
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onLoadBiome(final BiomeLoadingEvent event)
    {
        List<String> endCityBiomes = Arrays.asList("end_barrens", "end_highlands", "end_midlands", "small_end_islands");
        List<String> darkForestBiomes = Arrays.asList("dark_forest", "dark_forest_hills");
        List<String> forestCabinBiomes = Arrays.asList("forest", "birch_forest", "birch_forest_hills",
                "tall_birch_forest", "tall_birch_hills");

        boolean netherSmokyQuartzFlag = VeFeatureGenConfig.VeNetherConfig.enableNetherSmokyQuartzOreSpawns.get();
        boolean netherRubyFlag = VeFeatureGenConfig.VeNetherConfig.enableNetherRubyOreSpawns.get();
        boolean blueberryBushFlag = VeFeatureGenConfig.VeOverworldConfig.enableBlueberryBushSpawns.get();
        boolean cranberryBushFlag = VeFeatureGenConfig.VeOverworldConfig.enableCranberryBushSpawns.get();
        boolean witchesCradleFlag = VeFeatureGenConfig.VeOverworldConfig.enableWitchsCradleSpawns.get();
        boolean darkMatterLakeFlag = VeFeatureGenConfig.VeEndConfig.enableDarkMatterLakeSpawns.get();
        boolean snapdragonAndEnderGrassFlag = VeFeatureGenConfig.VeEndConfig.enableSnapdragonAndEnderGrassSpawns.get();
        boolean hugePurpleMushroomFlag = VeFeatureGenConfig.VeOverworldConfig.enableHugePurpleMushroomSpawns.get();
        boolean taigaCabinFlag = VeFeatureGenConfig.VeOverworldConfig.enableTaigaCabinSpawns.get();
        boolean forestCabinFlag = VeFeatureGenConfig.VeOverworldConfig.enableForestCabinSpawns.get();
        boolean crimsonCabinFlag = VeFeatureGenConfig.VeNetherConfig.enableCrimsonCabinSpawns.get();
        boolean purpleMushroomFlag = VeFeatureGenConfig.VeOverworldConfig.enablePurpleMushroomSpawns.get();
        boolean swampMudFlag = VeFeatureGenConfig.VeOverworldConfig.enableSwampMudSpawns.get();
        boolean riverMudFlag = VeFeatureGenConfig.VeOverworldConfig.enableRiverMudSpawns.get();
        boolean cattailFlag = VeFeatureGenConfig.VeOverworldConfig.enableCattailSpawns.get();

        ConfiguredFeature<?, ?> sparseBlueberries = VeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_SPARSE;
        ConfiguredFeature<?, ?> decoratedBlueberries = VeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_DECORATED;
        ConfiguredFeature<?, ?> sparseCranberries = VeConfiguredFeatures.PATCH_CRANBERRY_BUSH_SPARSE;
        ConfiguredFeature<?, ?> decoratedCranberries = VeConfiguredFeatures.PATCH_CRANBERRY_BUSH_DECORATED;
        ConfiguredFeature<?, ?> sparseWitchsCradle = VeConfiguredFeatures.PATCH_WITCHS_CRADLE_SPARSE;
        ConfiguredFeature<?, ?> decoratedWitchsCradle = VeConfiguredFeatures.PATCH_WITCHS_CRADLE_DECORATED;

        Decoration ores = Decoration.UNDERGROUND_ORES;
        Decoration vegetal = Decoration.VEGETAL_DECORATION;
        Decoration lakes = Decoration.LAKES;

        Category nether = Category.NETHER;
        Category forest = Category.FOREST;
        Category swamp = Category.SWAMP;
        Category taiga = Category.TAIGA;

        RainType rain = RainType.RAIN;
        RainType snow = RainType.SNOW;

        addFeature(event, nether, ores, VeConfiguredFeatures.NETHER_SMOKY_QUARTZ_ORE, netherSmokyQuartzFlag);
        addFeature(event, nether, ores, VeConfiguredFeatures.BLACKSTONE_RUBY_ORE, netherRubyFlag);
        addBushFeature(event, forest, sparseBlueberries, decoratedBlueberries, blueberryBushFlag);
        addBushFeature(event, forest, sparseCranberries, decoratedCranberries, cranberryBushFlag);
        addBushFeature(event, swamp, sparseWitchsCradle, decoratedWitchsCradle, witchesCradleFlag);
        addFeature(event, endCityBiomes, vegetal, VeConfiguredFeatures.SNAPDRAGON_AND_GRASS,
                snapdragonAndEnderGrassFlag);
        addFeature(event, endCityBiomes, lakes, VeConfiguredFeatures.DARK_MATTER_LAKE, darkMatterLakeFlag);
        addFeature(event, darkForestBiomes, vegetal, VeConfiguredFeatures.HUGE_PURPLE_MUSHROOM_WG,
                hugePurpleMushroomFlag);
        addFeature(event, darkForestBiomes, vegetal, VeConfiguredFeatures.PURPLE_MUSHROOM_DARK_FOREST,
                purpleMushroomFlag);
        addFeature(event, Category.RIVER, Decoration.TOP_LAYER_MODIFICATION, VeConfiguredFeatures.DISK_RIVER_MUD,
                riverMudFlag);
        addFeature(event, Category.SWAMP, Decoration.TOP_LAYER_MODIFICATION, VeConfiguredFeatures.DISK_SWAMP_MUD,
                swampMudFlag);
        addFeature(event, Category.SWAMP, vegetal, VeConfiguredFeatures.CATTAIL_SWAMP, cattailFlag);
        addFeature(event, Category.EXTREME_HILLS, ores, VeConfiguredFeatures.ORE_MARBLE, true);

        addStructure(event, taiga, rain, VeConfiguredStructures.configuredTaigaCabin, taigaCabinFlag);
        addStructure(event, taiga, snow, VeConfiguredStructures.configuredIcyTaigaCabin, taigaCabinFlag);
        addStructure(event, forestCabinBiomes, VeConfiguredStructures.configuredForestCabin, forestCabinFlag);
        addStructure(event, "crimson_forest", VeConfiguredStructures.configuredCrimsonCabin, crimsonCabinFlag);
    }

    /**
     * Adds a new spawner for monsters that allows these monsters to spawn in the
     * world.
     *
     * @param event    An instance of the biome loading event.
     * @param entity   The entity to use in the spawner.
     * @param weight   How likely the mob is to spawn. A higher weight equals a
     *                 higher spawn rate.
     * @param minCount The minimum number of spawns.
     * @param maxCount The maximum number of spawns.
     * @param biomes   The biomes that this entity can spawn in.
     */
    @SuppressWarnings("unused")
    private static void addMonsterSpawner(BiomeLoadingEvent event, EntityType<?> entity, int weight, int minCount,
            int maxCount, boolean enable, String... biomes)
    {
        if (enable)
        {
            for (String biome : biomes)
            {
                if (event.getName().equals(new ResourceLocation(biome)))
                {
                    event.getSpawns().getSpawner(EntityClassification.MONSTER)
                            .add(new Spawners(entity, weight, minCount, maxCount));
                }
            }
        }
    }

    /**
     * A helper method for adding bush features.
     *
     * @param event            The biome loading event to use.
     * @param category         The category of biomes to add this feature to.
     * @param decorationType   The decoration category that this feature belongs to.
     * @param featureSparse    The sparse bush feature to add.
     * @param featureDecorated The decorated bush feature to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this feature.
     */
    private static void addBushFeature(BiomeLoadingEvent event, Biome.Category category,
            ConfiguredFeature<?, ?> featureSparse, ConfiguredFeature<?, ?> featureDecorated, boolean enable)
    {
        Decoration vegetalDecoration = Decoration.VEGETAL_DECORATION;

        addFeature(event, category, vegetalDecoration, featureSparse, enable);
        addFeature(event, category, vegetalDecoration, featureDecorated, enable);
    }

    /**
     * Adds a new feature to a category of biomes.
     *
     * @param event      The biome loading event to use.
     * @param category   The category of biomes to add this feature to.
     * @param decoration The decoration category that this feature belongs to.
     * @param feature    The feature to add.
     * @param enable     A boolean from the config used to enable and disable this
     *                   feature.
     */
    private static void addFeature(BiomeLoadingEvent event, Biome.Category category, Decoration decorationType,
            ConfiguredFeature<?, ?> feature, boolean enable)
    {
        if (event.getCategory().equals(category) && enable)
        {
            event.getGeneration().getFeatures(decorationType).add(() -> feature);
        }
    }

    /**
     * A helper method that only adds the feature to one biome.
     *
     * @param event      The biome loading event to use.
     * @param biome      The biome's name to add the feature to.
     * @param decoration The decoration category that this feature belongs to.
     * @param feature    The feature to add.
     * @param enable     A boolean from the config used to enable and disable this
     *                   feature.
     */
    @SuppressWarnings("unused")
    private static void addFeature(BiomeLoadingEvent event, String biome, Decoration decoration,
            ConfiguredFeature<?, ?> feature, boolean enable)
    {
        addFeature(event, Arrays.asList(biome), decoration, feature, enable);
    }

    /**
     * Adds a new feature to specific existing biomes using the minecraft name
     * space.
     *
     * @param event      The biome loading event to use.
     * @param biomes     The biome names to add the feature to.
     * @param decoration The decoration category that this feature belongs to.
     * @param feature    The feature to add.
     * @param enable     A boolean from the config used to enable and disable the
     *                   feature.
     */
    private static void addFeature(BiomeLoadingEvent event, List<String> biomes, Decoration decoration,
            ConfiguredFeature<?, ?> feature, boolean enable)
    {
        if (enable)
        {
            for (String biome : biomes)
            {
                if (event.getName().equals(new ResourceLocation(biome)))
                {
                    event.getGeneration().getFeatures(decoration).add(() -> feature);
                }
            }
        }
    }

    /**
     * Adds a new structure to a category of biomes with the rain type provided.
     *
     * @param event            The biome loading event to use.
     * @param category         The category of biomes to add this structure to.
     * @param rainType         The rain type of the biomes to spawn in.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, Category category, RainType rainType,
            StructureFeature<?, ?> structureFeature, boolean enable)
    {
        if (event.getClimate().precipitation == rainType)
        {
            addStructure(event, category, structureFeature, enable);
        }
    }

    /**
     * Adds a new structure to a category of biomes.
     *
     * @param event            The biome loading event to use.
     * @param category         The category of biomes to add this structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, Category category,
            StructureFeature<?, ?> structureFeature, boolean enable)
    {
        if (enable)
        {
            if (event.getCategory().equals(category))
            {
                event.getGeneration().getStructures().add(() -> structureFeature);
            }
        }
    }

    /**
     * A helper method that only adds the feature to one biome.
     *
     * @param event            The biome loading event to use.
     * @param biome            The biome's name to add the structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, String biome, StructureFeature<?, ?> structureFeature,
            boolean enable)
    {
        addStructure(event, Arrays.asList(biome), structureFeature, enable);
    }

    /**
     * Adds a new structure to specific existing biomes using the minecraft name
     * space.
     *
     * @param event            The biome loading event to use.
     * @param biomes           The biome names to add the structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, List<String> biomes,
            StructureFeature<?, ?> structureFeature, boolean enable)
    {
        if (enable)
        {
            for (String biome : biomes)
            {
                if (event.getName().equals(new ResourceLocation(biome)))
                {
                    event.getGeneration().getStructures().add(() -> structureFeature);
                }
            }
        }
    }
}
