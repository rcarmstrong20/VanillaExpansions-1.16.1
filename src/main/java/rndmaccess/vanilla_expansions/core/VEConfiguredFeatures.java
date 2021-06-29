package rndmaccess.vanilla_expansions.core;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.block.VEBerryBushBlock;
import rndmaccess.vanilla_expansions.config.VEConfig;

/**
 * A class for holding every feature configuration instance that vanilla
 * expansions has.
 *
 * @author Ryan
 */
public class VEConfiguredFeatures
{
    public static final ConfiguredFeature<?, ?> NETHER_SMOKY_QUARTZ_ORE = register("nether_smoky_quartz_ore",
            Feature.ORE
                    .configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
                            VEConfiguredFeatures.States.NETHER_SMOKY_QUARTZ_ORE,
                            VEConfig.VEOreDataConfig.netherSmokyQuartzOreVeinSize.get()))
                    .decorated(Features.Placements.RANGE_10_20_ROOFED).squared()
                    .count(VEConfig.VEOreDataConfig.netherSmokyQuartzOreSpread.get()));
    public static final ConfiguredFeature<?, ?> BLACKSTONE_RUBY_ORE = register("blackstone_ruby_ore",
            Feature.ORE
                    .configured(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.BLACKSTONE),
                            VEConfiguredFeatures.States.NETHER_RUBY_ORE,
                            VEConfig.VEOreDataConfig.blackstoneRubyOreVeinSize.get()))
                    .decorated(Features.Placements.RANGE_10_20_ROOFED).squared()
                    .count(VEConfig.VEOreDataConfig.blackstoneRubyOreSpread.get()));
    protected static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH = Feature.RANDOM_PATCH
            .configured(VEConfiguredFeatures.Configs.BLUEBERRY_BUSH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_SPARSE = register("patch_blueberry_bush_sparse",
            PATCH_BLUEBERRY_BUSH.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_DECORATED = register(
            "patch_blueberry_bush_decorated",
            PATCH_BLUEBERRY_BUSH.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(12));
    protected static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH = Feature.RANDOM_PATCH
            .configured(VEConfiguredFeatures.Configs.CRANBERRY_BUSH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH_SPARSE = register("patch_cranberry_bush_sparse",
            PATCH_CRANBERRY_BUSH.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH_DECORATED = register(
            "patch_cranberry_bush_decorated",
            PATCH_CRANBERRY_BUSH.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(12));
    protected static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE = Feature.RANDOM_PATCH
            .configured(VEConfiguredFeatures.Configs.WITCHS_CRADLE_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE_SPARSE = register("patch_witchs_cradle_sparse",
            PATCH_WITCHS_CRADLE.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));
    public static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE_DECORATED = register(
            "patch_witchs_cradle_decorated",
            PATCH_WITCHS_CRADLE.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(12));
    public static final ConfiguredFeature<?, ?> HUGE_PURPLE_MUSHROOM = register("huge_purple_mushroom",
            Feature.HUGE_RED_MUSHROOM.configured(VEConfiguredFeatures.Configs.BIG_PURPLE_MUSHROOM_CONFIG));
    public static final ConfiguredFeature<?, ?> HUGE_PURPLE_MUSHROOM_WG = register("huge_purple_mushroom_wg",
            HUGE_PURPLE_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_WORLD_SURFACE).count(1));
    public static final ConfiguredFeature<?, ?> PATCH_PURPLE_MUSHROOM = register("patch_purple_mushroom",
            Feature.RANDOM_PATCH.configured(VEConfiguredFeatures.Configs.PURPLE_MUSHROOM_CONFIG));
    public static final ConfiguredFeature<?, ?> PURPLE_MUSHROOM_DARK_FOREST = register("purple_mushroom_normal",
            PATCH_PURPLE_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(12));
    public static final ConfiguredFeature<?, ?> DARK_MATTER_LAKE = register("dark_matter_lake",
            Feature.LAKE.configured(new BlockStateFeatureConfig(VEConfiguredFeatures.States.DARK_MATTER))
                    .decorated(Placement.WATER_LAKE.configured(new ChanceConfig(4))));
    public static final ConfiguredFeature<?, ?> SNAPDRAGON_AND_GRASS = register("snapdragon_and_grass",
            Feature.FLOWER.configured(VEConfiguredFeatures.Configs.SNAPDRAGON_AND_GRASS_CONFIG)
                    .decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP).count(4));
    public static final ConfiguredFeature<?, ?> DISK_RIVER_MUD = register("disk_mud", Feature.DISK
            .configured(Configs.MUD_RIVER_CONFIG).squared().decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> DISK_SWAMP_MUD = register("disk_mud", Feature.DISK
            .configured(Configs.MUD_SWAMP_CONFIG).squared().decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> CATTAIL_SWAMP = register("cattail_swamp",
            VEFeature.CATTAIL.configured(new ProbabilityConfig(0.6F)).count(32)
                    .decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
    public static final ConfiguredFeature<?, ?> ORE_MARBLE = register("ore_marble",
            Feature.ORE.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    VEConfiguredFeatures.States.MARBLE, 33)).range(80).squared().count(10));

    /**
     * A helper method for automatically registering every new configured feature.
     *
     * @param name           The name of this configured feature.
     * @param structurePiece An instance of a new configured feature.
     * @return A new registered configured feature.
     */
    private static ConfiguredFeature<?, ?> register(String name, ConfiguredFeature<?, ?> feature)
    {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

        return Registry.register(registry, new ResourceLocation(VanillaExpansions.MOD_ID, name), feature);
    }

    /**
     * A sub-class used to store some of the more complex feature configurations.
     *
     * @author Ryan
     *
     */
    public static final class Configs
    {
        public static final BlockClusterFeatureConfig BLUEBERRY_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VEConfiguredFeatures.States.BLUEBERRY_BUSH), SimpleBlockPlacer.INSTANCE))
                        .tries(64).whitelist(ImmutableSet.of(VEConfiguredFeatures.States.GRASS_BLOCK.getBlock()))
                        .noProjection().build();
        public static final BlockClusterFeatureConfig CRANBERRY_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VEConfiguredFeatures.States.CRANBERRY_BUSH), SimpleBlockPlacer.INSTANCE))
                        .tries(64).whitelist(ImmutableSet.of(VEConfiguredFeatures.States.GRASS_BLOCK.getBlock()))
                        .noProjection().build();
        public static final BlockClusterFeatureConfig SNAPDRAGON_AND_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(
                (new WeightedBlockStateProvider()).add(VEConfiguredFeatures.States.ENDER_GRASS, 2)
                        .add(VEConfiguredFeatures.States.SNAPDRAGON, 1),
                SimpleBlockPlacer.INSTANCE)).tries(64)
                        .whitelist(ImmutableSet.of(VEConfiguredFeatures.States.END_STONE.getBlock())).noProjection()
                        .build();
        public static final BlockClusterFeatureConfig WITCHS_CRADLE_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VEConfiguredFeatures.States.WITCHS_CRADLE), SimpleBlockPlacer.INSTANCE))
                        .tries(64).whitelist(ImmutableSet.of(VEConfiguredFeatures.States.GRASS_BLOCK.getBlock()))
                        .noProjection().build();
        public static final BigMushroomFeatureConfig BIG_PURPLE_MUSHROOM_CONFIG = new BigMushroomFeatureConfig(
                new SimpleBlockStateProvider(VEConfiguredFeatures.States.PURPLE_MUSHROOM_BLOCK_DOWN),
                new SimpleBlockStateProvider(VEConfiguredFeatures.States.MUSHROOM_STEM), 2);
        public static final BlockClusterFeatureConfig PURPLE_MUSHROOM_CONFIG = new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VEConfiguredFeatures.States.PURPLE_MUSHROOM), SimpleBlockPlacer.INSTANCE)
                        .tries(64).noProjection().build();
        public static final SphereReplaceConfig MUD_SWAMP_CONFIG = new SphereReplaceConfig(
                VEConfiguredFeatures.States.MUD, FeatureSpread.of(3, 2), 1,
                ImmutableList.of(VEConfiguredFeatures.States.DIRT, VEConfiguredFeatures.States.MUD));
        public static final SphereReplaceConfig MUD_RIVER_CONFIG = new SphereReplaceConfig(
                VEConfiguredFeatures.States.MUD, FeatureSpread.of(2, 1), 1,
                ImmutableList.of(VEConfiguredFeatures.States.DIRT, VEConfiguredFeatures.States.MUD));
    }

    /**
     * A sub-class used to store block states used in the feature configurations.
     *
     * @author Ryan
     *
     */
    public static final class States
    {
        protected static final BlockState DARK_MATTER = VEBlocks.darkMatter.defaultBlockState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.defaultBlockState();
        protected static final BlockState END_STONE = Blocks.END_STONE.defaultBlockState();
        protected static final BlockState SNAPDRAGON = VEBlocks.snapdragon.defaultBlockState();
        protected static final BlockState ENDER_GRASS = VEBlocks.enderGrass.defaultBlockState();
        protected static final BlockState PURPLE_MUSHROOM = VEBlocks.purpleMushroom.defaultBlockState();
        protected static final BlockState PURPLE_MUSHROOM_BLOCK_DOWN = VEBlocks.purpleMushroomBlock.defaultBlockState()
                .setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
        protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.defaultBlockState()
                .setValue(HugeMushroomBlock.UP, Boolean.valueOf(false))
                .setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
        protected static final BlockState WITCHS_CRADLE = VEBlocks.witchsCradle.defaultBlockState()
                .setValue(VEBerryBushBlock.AGE, 3);
        protected static final BlockState NETHER_SMOKY_QUARTZ_ORE = VEBlocks.smokyQuartzOre.defaultBlockState();
        protected static final BlockState NETHER_RUBY_ORE = VEBlocks.rubyOre.defaultBlockState();
        protected static final BlockState BLUEBERRY_BUSH = VEBlocks.blueberryBush.defaultBlockState()
                .setValue(VEBerryBushBlock.AGE, 3);
        protected static final BlockState CRANBERRY_BUSH = VEBlocks.cranberryBush.defaultBlockState()
                .setValue(VEBerryBushBlock.AGE, 3);
        protected static final BlockState MUD = VEBlocks.mud.defaultBlockState();
        protected static final BlockState DIRT = Blocks.DIRT.defaultBlockState();
        protected static final BlockState CATTAIL = VEBlocks.smokyQuartzBlock.defaultBlockState();
        protected static final BlockState WATER_BLOCK = Blocks.WATER.defaultBlockState();
        protected static final BlockState AIR = Blocks.AIR.defaultBlockState();
        protected static final BlockState MARBLE = VEBlocks.marble.defaultBlockState();
    }
}
