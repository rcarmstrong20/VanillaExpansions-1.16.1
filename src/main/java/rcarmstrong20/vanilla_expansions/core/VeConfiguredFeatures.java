package rcarmstrong20.vanilla_expansions.core;

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
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.block.VeBerryBushBlock;
import rcarmstrong20.vanilla_expansions.config.VeOreDataGenConfig;

/**
 * A class for holding every feature configuration instance that vanilla
 * expansions has.
 *
 * @author Ryan
 */
public class VeConfiguredFeatures
{
    public static final ConfiguredFeature<?, ?> NETHER_SMOKY_QUARTZ_ORE = register("nether_smoky_quartz_ore",
            Feature.ORE
                    .withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
                            VeConfiguredFeatures.States.NETHER_SMOKY_QUARTZ_ORE,
                            VeOreDataGenConfig.VeVeinSizeConfig.netherSmokyQuartzOreVeinSize.get()))
                    .withPlacement(Features.Placements.NETHER_SPRING_ORE_PLACEMENT).square()
                    .func_242731_b(VeOreDataGenConfig.VeSpreadConfig.netherSmokyQuartzOreSpread.get()));
    public static final ConfiguredFeature<?, ?> BLACKSTONE_RUBY_ORE = register("blackstone_ruby_ore",
            Feature.ORE
                    .withConfiguration(new OreFeatureConfig(new BlockMatchRuleTest(Blocks.BLACKSTONE),
                            VeConfiguredFeatures.States.NETHER_RUBY_ORE,
                            VeOreDataGenConfig.VeVeinSizeConfig.blackstoneRubyOreVeinSize.get()))
                    .withPlacement(Features.Placements.NETHER_SPRING_ORE_PLACEMENT).square()
                    .func_242731_b(VeOreDataGenConfig.VeSpreadConfig.blackstoneRubyOreSpread.get()));
    protected static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH = Feature.RANDOM_PATCH
            .withConfiguration(VeConfiguredFeatures.Configs.BLUEBERRY_BUSH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_SPARSE = register("patch_blueberry_bush_sparse",
            PATCH_BLUEBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT));
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_DECORATED = register(
            "patch_blueberry_bush_decorated",
            PATCH_BLUEBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12));
    protected static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH = Feature.RANDOM_PATCH
            .withConfiguration(VeConfiguredFeatures.Configs.CRANBERRY_BUSH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH_SPARSE = register("patch_cranberry_bush_sparse",
            PATCH_CRANBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT));
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH_DECORATED = register(
            "patch_cranberry_bush_decorated",
            PATCH_CRANBERRY_BUSH.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12));
    protected static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE = Feature.RANDOM_PATCH
            .withConfiguration(VeConfiguredFeatures.Configs.WITCHS_CRADLE_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE_SPARSE = register("patch_witchs_cradle_sparse",
            PATCH_WITCHS_CRADLE.withPlacement(Features.Placements.PATCH_PLACEMENT));
    public static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE_DECORATED = register(
            "patch_witchs_cradle_decorated",
            PATCH_WITCHS_CRADLE.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12));
    public static final ConfiguredFeature<?, ?> HUGE_PURPLE_MUSHROOM = register("huge_purple_mushroom",
            Feature.HUGE_RED_MUSHROOM.withConfiguration(VeConfiguredFeatures.Configs.BIG_PURPLE_MUSHROOM_CONFIG));
    public static final ConfiguredFeature<?, ?> HUGE_PURPLE_MUSHROOM_WG = register("huge_purple_mushroom_wg",
            HUGE_PURPLE_MUSHROOM.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1));
    public static final ConfiguredFeature<?, ?> PATCH_PURPLE_MUSHROOM = register("patch_purple_mushroom",
            Feature.RANDOM_PATCH.withConfiguration(VeConfiguredFeatures.Configs.PURPLE_MUSHROOM_CONFIG));
    public static final ConfiguredFeature<?, ?> PURPLE_MUSHROOM_DARK_FOREST = register("purple_mushroom_normal",
            PATCH_PURPLE_MUSHROOM.withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12));
    public static final ConfiguredFeature<?, ?> DARK_MATTER_LAKE = register("dark_matter_lake",
            Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(VeConfiguredFeatures.States.DARK_MATTER))
                    .withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4))));
    public static final ConfiguredFeature<?, ?> SNAPDRAGON_AND_GRASS = register("snapdragon_and_grass",
            Feature.FLOWER.withConfiguration(VeConfiguredFeatures.Configs.SNAPDRAGON_AND_GRASS_CONFIG)
                    .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(4));

    public static final ConfiguredFeature<?, ?> DISK_MUD = register("disk_mud", Feature.DISK
            .withConfiguration(
                    new SphereReplaceConfig(VeConfiguredFeatures.States.MUD, FeatureSpread.func_242253_a(2, 1), 1,
                            ImmutableList.of(VeConfiguredFeatures.States.DIRT, VeConfiguredFeatures.States.MUD)))
            .square().withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));
    public static final ConfiguredFeature<?, ?> DISK_MUD_SWAMP = register("disk_mud", Feature.DISK
            .withConfiguration(
                    new SphereReplaceConfig(VeConfiguredFeatures.States.MUD, FeatureSpread.func_242253_a(3, 2), 1,
                            ImmutableList.of(VeConfiguredFeatures.States.DIRT, VeConfiguredFeatures.States.MUD)))
            .square().withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT));

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
                new SimpleBlockStateProvider(VeConfiguredFeatures.States.BLUEBERRY_BUSH), SimpleBlockPlacer.PLACER))
                        .tries(64).whitelist(ImmutableSet.of(VeConfiguredFeatures.States.GRASS_BLOCK.getBlock()))
                        .func_227317_b_().build();
        public static final BlockClusterFeatureConfig CRANBERRY_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VeConfiguredFeatures.States.CRANBERRY_BUSH), SimpleBlockPlacer.PLACER))
                        .tries(64).whitelist(ImmutableSet.of(VeConfiguredFeatures.States.GRASS_BLOCK.getBlock()))
                        .func_227317_b_().build();
        public static final BlockClusterFeatureConfig SNAPDRAGON_AND_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(
                (new WeightedBlockStateProvider()).addWeightedBlockstate(VeConfiguredFeatures.States.ENDER_GRASS, 2)
                        .addWeightedBlockstate(VeConfiguredFeatures.States.SNAPDRAGON, 1),
                SimpleBlockPlacer.PLACER)).tries(64)
                        .whitelist(ImmutableSet.of(VeConfiguredFeatures.States.END_STONE.getBlock())).func_227317_b_()
                        .build();
        public static final BlockClusterFeatureConfig WITCHS_CRADLE_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VeConfiguredFeatures.States.WITCHS_CRADLE), SimpleBlockPlacer.PLACER))
                        .tries(64).whitelist(ImmutableSet.of(VeConfiguredFeatures.States.GRASS_BLOCK.getBlock()))
                        .func_227317_b_().build();
        public static final BigMushroomFeatureConfig BIG_PURPLE_MUSHROOM_CONFIG = new BigMushroomFeatureConfig(
                new SimpleBlockStateProvider(VeConfiguredFeatures.States.PURPLE_MUSHROOM_BLOCK_DOWN),
                new SimpleBlockStateProvider(VeConfiguredFeatures.States.MUSHROOM_STEM), 2);
        public static final BlockClusterFeatureConfig PURPLE_MUSHROOM_CONFIG = new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VeConfiguredFeatures.States.PURPLE_MUSHROOM), SimpleBlockPlacer.PLACER)
                        .tries(64).func_227317_b_().build();
    }

    /**
     * A sub-class used to store block states used in the feature configurations.
     *
     * @author Ryan
     *
     */
    public static final class States
    {
        protected static final BlockState DARK_MATTER = VeBlocks.darkMatter.getDefaultState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        protected static final BlockState END_STONE = Blocks.END_STONE.getDefaultState();
        protected static final BlockState SNAPDRAGON = VeBlocks.snapdragon.getDefaultState();
        protected static final BlockState ENDER_GRASS = VeBlocks.enderGrass.getDefaultState();
        protected static final BlockState PURPLE_MUSHROOM = VeBlocks.purpleMushroom.getDefaultState();
        protected static final BlockState PURPLE_MUSHROOM_BLOCK_DOWN = VeBlocks.purpleMushroomBlock.getDefaultState()
                .with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
        protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState()
                .with(HugeMushroomBlock.UP, Boolean.valueOf(false))
                .with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
        protected static final BlockState WITCHS_CRADLE = VeBlocks.witchsCradle.getDefaultState()
                .with(VeBerryBushBlock.AGE, 3);
        protected static final BlockState NETHER_SMOKY_QUARTZ_ORE = VeBlocks.smokyQuartzOre.getDefaultState();
        protected static final BlockState NETHER_RUBY_ORE = VeBlocks.rubyOre.getDefaultState();
        protected static final BlockState BLUEBERRY_BUSH = VeBlocks.blueberryBush.getDefaultState()
                .with(VeBerryBushBlock.AGE, 3);
        protected static final BlockState CRANBERRY_BUSH = VeBlocks.cranberryBush.getDefaultState()
                .with(VeBerryBushBlock.AGE, 3);
        protected static final BlockState MUD = VeBlocks.mud.getDefaultState();
        protected static final BlockState DIRT = Blocks.DIRT.getDefaultState();
    }
}
