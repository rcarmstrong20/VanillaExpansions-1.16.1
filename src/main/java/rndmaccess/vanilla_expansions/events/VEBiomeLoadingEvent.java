package rndmaccess.vanilla_expansions.events;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rndmaccess.vanilla_expansions.config.VEConfig;
import rndmaccess.vanilla_expansions.core.VEConfiguredFeatures;
import rndmaccess.vanilla_expansions.core.VEConfiguredStructures;
import rndmaccess.vanilla_expansions.core.VEEntityTypes;

public class VEBiomeLoadingEvent
{
    private boolean smokyQuartzFlag = VEConfig.smokyQuartzOreFlag.get();
    private boolean rubyFlag = VEConfig.rubyOreFlag.get();
    private boolean blueberryFlag = VEConfig.blueberryBushFlag.get();
    private boolean cranberryFlag = VEConfig.cranberryBushFlag.get();
    private boolean witchesCradleFlag = VEConfig.witchsCradleFlag.get();
    private boolean darkMatterLakeFlag = VEConfig.darkMatterLakeFlag.get();
    private boolean snapdragonAndEnderGrassFlag = VEConfig.snapdragonAndEnderGrassFlag.get();
    private boolean hugePurpleMushroomFlag = VEConfig.hugePurpleMushroomFlag.get();
    private boolean taigaCabinFlag = VEConfig.taigaCabinFlag.get();
    private boolean forestCabinFlag = VEConfig.forestCabinFlag.get();
    private boolean crimsonCabinFlag = VEConfig.crimsonCabinFlag.get();
    private boolean purpleMushroomFlag = VEConfig.purpleMushroomFlag.get();
    private boolean swampMudFlag = VEConfig.swampMudFlag.get();
    private boolean riverMudFlag = VEConfig.riverMudFlag.get();
    private boolean cattailFlag = VEConfig.cattailFlag.get();

    private static final List<String> END_CITY_BIOMES = Arrays.asList("minecraft:end_barrens",
            "minecraft:end_highlands", "minecraft:end_midlands", "minecraft:small_end_islands");
    private static final List<String> DARK_FOREST_BIOMES = Arrays.asList("minecraft:dark_forest",
            "minecraft:dark_forest_hills");
    private static final List<String> FOREST_BIOMES = Arrays.asList("minecraft:forest", "minecraft:birch_forest",
            "minecraft:birch_forest_hills", "minecraft:tall_birch_forest", "minecraft:tall_birch_hills");

    private ConfiguredFeature<?, ?> sparseBlueberries = VEConfiguredFeatures.PATCH_BLUEBERRY_BUSH_SPARSE;
    private ConfiguredFeature<?, ?> decoratedBlueberries = VEConfiguredFeatures.PATCH_BLUEBERRY_BUSH_DECORATED;
    private ConfiguredFeature<?, ?> sparseCranberries = VEConfiguredFeatures.PATCH_CRANBERRY_BUSH_SPARSE;
    private ConfiguredFeature<?, ?> decoratedCranberries = VEConfiguredFeatures.PATCH_CRANBERRY_BUSH_DECORATED;
    private ConfiguredFeature<?, ?> sparseWitchsCradle = VEConfiguredFeatures.PATCH_WITCHS_CRADLE_SPARSE;
    private ConfiguredFeature<?, ?> decoratedWitchsCradle = VEConfiguredFeatures.PATCH_WITCHS_CRADLE_DECORATED;

    private Decoration ores = Decoration.UNDERGROUND_ORES;
    private Decoration vegetal = Decoration.VEGETAL_DECORATION;
    private Decoration lakes = Decoration.LAKES;
    private Decoration topLayerMod = Decoration.TOP_LAYER_MODIFICATION;

    private Category nether = Category.NETHER;
    private Category forest = Category.FOREST;
    private Category swamp = Category.SWAMP;
    private Category taiga = Category.TAIGA;
    private Category river = Category.RIVER;

    private RainType rain = RainType.RAIN;
    private RainType snow = RainType.SNOW;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onLoadBiome(final BiomeLoadingEvent event)
    {
        BiomeGenerationSettingsBuilder genBuilder = event.getGeneration();
        MobSpawnInfoBuilder entityBuilder = event.getSpawns();
        Category category = event.getCategory();
        ResourceLocation biome = event.getName();
        RainType weather = event.getClimate().precipitation;

        if (biome != null)
        {
            addFeature(genBuilder, category, nether, ores, VEConfiguredFeatures.NETHER_SMOKY_QUARTZ_ORE,
                    smokyQuartzFlag);
            addFeature(genBuilder, category, nether, ores, VEConfiguredFeatures.BLACKSTONE_RUBY_ORE, rubyFlag);
            addBushFeature(genBuilder, category, forest, sparseBlueberries, decoratedBlueberries, blueberryFlag);
            addBushFeature(genBuilder, category, forest, sparseCranberries, decoratedCranberries, cranberryFlag);
            addBushFeature(genBuilder, category, swamp, sparseWitchsCradle, decoratedWitchsCradle, witchesCradleFlag);
            addFeature(genBuilder, biome, END_CITY_BIOMES, vegetal, VEConfiguredFeatures.SNAPDRAGON_AND_GRASS,
                    snapdragonAndEnderGrassFlag);
            addFeature(genBuilder, biome, END_CITY_BIOMES, lakes, VEConfiguredFeatures.DARK_MATTER_LAKE,
                    darkMatterLakeFlag);
            addFeature(genBuilder, biome, DARK_FOREST_BIOMES, vegetal, VEConfiguredFeatures.HUGE_PURPLE_MUSHROOM_WG,
                    hugePurpleMushroomFlag);
            addFeature(genBuilder, biome, DARK_FOREST_BIOMES, vegetal, VEConfiguredFeatures.PURPLE_MUSHROOM_DARK_FOREST,
                    purpleMushroomFlag);
            addFeature(genBuilder, category, river, topLayerMod, VEConfiguredFeatures.DISK_RIVER_MUD, riverMudFlag);
            addFeature(genBuilder, category, swamp, topLayerMod, VEConfiguredFeatures.DISK_SWAMP_MUD, swampMudFlag);
            addFeature(genBuilder, category, swamp, vegetal, VEConfiguredFeatures.CATTAIL_SWAMP, cattailFlag);
            addFeatureToAllExcept(genBuilder, category, nether, ores, VEConfiguredFeatures.ORE_MARBLE, true);
            addStructure(genBuilder, category, weather, taiga, rain, VEConfiguredStructures.configuredTaigaCabin,
                    taigaCabinFlag);
            addStructure(genBuilder, category, weather, taiga, snow, VEConfiguredStructures.configuredIcyTaigaCabin,
                    taigaCabinFlag);
            addStructure(genBuilder, biome, FOREST_BIOMES, VEConfiguredStructures.configuredForestCabin,
                    forestCabinFlag);
            addStructure(genBuilder, biome, "minecraft:crimson_forest", VEConfiguredStructures.configuredCrimsonCabin,
                    crimsonCabinFlag);
            addSpawner(entityBuilder, VEEntityTypes.charredRemnant, 100, 2, 4, true);
            addSpawner(entityBuilder, biome, VEEntityTypes.enderHorse, 5, 2, 6, true, END_CITY_BIOMES);
        }
    }

    /**
     * Adds a new spawner for monsters that allows these monsters to spawn naturally
     * in the biomes contained in the list.
     *
     * @param <T>
     * @param builder
     * @param selectedBiome
     * @param entity        The entity to use in the spawner.
     * @param weight        How likely the mob is to spawn. A higher weight equals a
     *                      higher spawn rate.
     * @param minCount      The minimum number of spawns.
     * @param maxCount      The maximum number of spawns.
     * @param enable        A boolean from the config used to enable and disable
     *                      this feature.
     * @param biomes        The biomes to add spawns for.
     */
    private static <T extends MobEntity> void addSpawner(MobSpawnInfoBuilder builder, ResourceLocation selectedBiome,
            EntityType<T> entity, int weight, int minCount, int maxCount, boolean enable, List<String> biomes)
    {
        if (biomes.contains(selectedBiome.toString()))
        {
            addSpawner(builder, entity, weight, minCount, maxCount, enable);
        }
    }

    /**
     * Adds a new spawner for monsters that allows these monsters to spawn naturally
     * in every biome.
     *
     * @param <T>
     * @param builder
     * @param entity   The entity to use in the spawner.
     * @param weight   How likely the mob is to spawn. A higher weight equals a
     *                 higher spawn rate.
     * @param minCount The minimum number of spawns.
     * @param maxCount The maximum number of spawns.
     * @param enable   A boolean from the config used to enable and disable this
     *                 feature.
     */
    private static <T extends MobEntity> void addSpawner(MobSpawnInfoBuilder builder, EntityType<T> entity, int weight,
            int minCount, int maxCount, boolean enable)
    {
        if (enable)
        {
            builder.getSpawner(entity.getCategory()).add(new Spawners(entity, weight, minCount, maxCount));
        }
    }

    /**
     * A helper method for adding bush features.
     *
     * @param builder          The generation builder to add to.
     * @param selectedCategory The current category loaded.
     * @param category         The category of biomes to add this feature to.
     * @param decorationType   The decoration category that this feature belongs to.
     * @param featureSparse    The sparse bush feature to add.
     * @param featureDecorated The decorated bush feature to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this feature.
     */
    private static void addBushFeature(BiomeGenerationSettingsBuilder builder, Category selectedCategory,
            Biome.Category category, ConfiguredFeature<?, ?> featureSparse, ConfiguredFeature<?, ?> featureDecorated,
            boolean enable)
    {
        Decoration vegetalDecoration = Decoration.VEGETAL_DECORATION;

        addFeature(builder, selectedCategory, category, vegetalDecoration, featureSparse, enable);
        addFeature(builder, selectedCategory, category, vegetalDecoration, featureDecorated, enable);
    }

    /**
     * Adds a new feature to a category of biomes.
     *
     * @param builder          The generation builder to add to.
     * @param selectedCategory The current category loaded.
     * @param category         The category of biomes to add this feature to.
     * @param decoration       The decoration category that this feature belongs to.
     * @param feature          The feature to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this feature.
     */
    private static void addFeature(BiomeGenerationSettingsBuilder builder, Category selectedCategory,
            Biome.Category category, Decoration decorationType, ConfiguredFeature<?, ?> feature, boolean enable)
    {
        if (selectedCategory.equals(category) && enable)
        {
            builder.getFeatures(decorationType).add(() -> feature);
        }
    }

    /**
     * Adds a new feature to every biome that is not in a category.
     *
     * @param builder          The generation builder to add to.
     * @param selectedCategory The current category loaded.
     * @param category         The category of biomes to exclude.
     * @param decoration       The decoration category that this feature belongs to.
     * @param feature          The feature to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this feature.
     */
    private static void addFeatureToAllExcept(BiomeGenerationSettingsBuilder builder, Category selectedCategory,
            Category category, Decoration decoration, ConfiguredFeature<?, ?> feature, boolean enable)
    {
        if (!(selectedCategory.equals(category)) && enable)
        {
            builder.getFeatures(decoration).add(() -> feature);
        }
    }

    /**
     * A helper method that only adds the feature to one biome.
     *
     * @param builder       The generation builder to add to.
     * @param selectedBiome The current biome loaded.
     * @param biome         The biome's name to add the feature to.
     * @param decoration    The decoration category that this feature belongs to.
     * @param feature       The feature to add.
     * @param enable        A boolean from the config used to enable and disable
     *                      this feature.
     */
    @SuppressWarnings("unused")
    private static void addFeature(BiomeGenerationSettingsBuilder builder, ResourceLocation selectedBiome, String biome,
            Decoration decoration, ConfiguredFeature<?, ?> feature, boolean enable)
    {
        addFeature(builder, selectedBiome, Arrays.asList(biome), decoration, feature, enable);
    }

    /**
     * Adds a new feature to specific existing biomes using the minecraft name
     * space.
     *
     * @param builder       The generation builder to add to.
     * @param selectedBiome The current biome loaded.
     * @param biomes        The biome names to add the feature to.
     * @param decoration    The decoration category that this feature belongs to.
     * @param feature       The feature to add.
     * @param enable        A boolean from the config used to enable and disable the
     *                      feature.
     */
    private static void addFeature(BiomeGenerationSettingsBuilder builder, ResourceLocation selectedBiome,
            List<String> biomes, Decoration decoration, ConfiguredFeature<?, ?> feature, boolean enable)
    {
        if (enable)
        {
            if (biomes.contains(selectedBiome.toString()))
            {
                builder.getFeatures(decoration).add(() -> feature);
            }
        }
    }

    /**
     * Adds a new structure to a category of biomes with the rain type provided.
     *
     * @param builder          The generation builder to add to.
     * @param selectedCategory The current category loaded.
     * @param selectedRainType The rain type loaded.
     * @param category         The category of biomes to add this structure to.
     * @param rainType         The rain type of the biomes to spawn in.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeGenerationSettingsBuilder builder, Category selectedCategory,
            RainType selectedRainType, Category category, RainType rainType, StructureFeature<?, ?> structureFeature,
            boolean enable)
    {
        if (selectedRainType.equals(rainType))
        {
            addStructure(builder, selectedCategory, category, structureFeature, enable);
        }
    }

    /**
     * Adds a new structure to a category of biomes.
     *
     * @param builder          The generation builder to add to.
     * @param selectedCategory The current category loaded.
     * @param category         The category of biomes to add this structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeGenerationSettingsBuilder builder, Category selectedCategory,
            Category category, StructureFeature<?, ?> structureFeature, boolean enable)
    {
        if (enable)
        {
            if (selectedCategory.equals(category))
            {
                builder.getStructures().add(() -> structureFeature);
            }
        }
    }

    /**
     * A helper method that only adds the feature to one biome.
     *
     * @param builder          The generation builder to add to.
     * @param selectedBiome    The current biome loaded.
     * @param biome            The biome's name to add the structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeGenerationSettingsBuilder builder, ResourceLocation selectedBiome,
            String biome, StructureFeature<?, ?> structureFeature, boolean enable)
    {
        addStructure(builder, selectedBiome, Arrays.asList(biome), structureFeature, enable);
    }

    /**
     * Adds a new structure to specific existing biomes using the minecraft name
     * space.
     *
     * @param builder       The generation builder to add to.
     * @param selectedBiome The current biome loaded.
     * @param biomes        The biome names to add the structure to.
     * @param structure     The structure to add.
     * @param enable        A boolean from the config used to enable and disable
     *                      this structure.
     */
    private static void addStructure(BiomeGenerationSettingsBuilder builder, ResourceLocation selectedBiome,
            List<String> biomes, StructureFeature<?, ?> structure, boolean enable)
    {
        if (enable)
        {
            if (biomes.contains(selectedBiome.toString()))
            {
                builder.getStructures().add(() -> structure);
            }
        }
    }
}
