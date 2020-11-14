package rcarmstrong20.vanilla_expansions.core;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import rcarmstrong20.vanilla_expansions.block.VeBerryBushBlock;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.CabinPools;

public class VeFeatures
{
    public static final ConfiguredFeature<?, ?> NETHER_SMOKY_QUARTZ_ORE = Feature.ORE
            .withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
                    VeFeatures.States.NETHER_SMOKY_QUARTZ_ORE, 14))
            .withPlacement(Features.Placements.NETHER_SPRING_ORE_PLACEMENT).square().func_242731_b(16);
    public static final ConfiguredFeature<?, ?> NETHER_RUBY_ORE = Feature.ORE
            .withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
                    VeFeatures.States.NETHER_RUBY_ORE, 3))
            .withPlacement(Features.Placements.NETHER_SPRING_ORE_PLACEMENT).square().func_242731_b(7);
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH = Feature.RANDOM_PATCH
            .withConfiguration(VeFeatures.Configs.BLUEBERRY_BUSH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_SPARSE = PATCH_BLUEBERRY_BUSH
            .withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> PATCH_BLUEBERRY_BUSH_DECORATED = PATCH_BLUEBERRY_BUSH
            .withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH = Feature.RANDOM_PATCH
            .withConfiguration(VeFeatures.Configs.CRANBERRY_BUSH_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH_SPARSE = PATCH_CRANBERRY_BUSH
            .withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> PATCH_CRANBERRY_BUSH_DECORATED = PATCH_CRANBERRY_BUSH
            .withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);
    public static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE = Feature.RANDOM_PATCH
            .withConfiguration(VeFeatures.Configs.WITCHS_CRADLE_CONFIG);
    public static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE_SPARSE = PATCH_WITCHS_CRADLE
            .withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static final ConfiguredFeature<?, ?> PATCH_WITCHS_CRADLE_DECORATED = PATCH_WITCHS_CRADLE
            .withPlacement(Features.Placements.PATCH_PLACEMENT).chance(12);

    public static final ConfiguredFeature<?, ?> HUGE_PURPLE_MUSHROOM = Feature.HUGE_RED_MUSHROOM
            .withConfiguration(VeFeatures.Configs.BIG_PURPLE_MUSHROOM_CONFIG)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(1);
    public static final ConfiguredFeature<?, ?> VOID_LAKE = Feature.LAKE
            .withConfiguration(new BlockStateFeatureConfig(VeFeatures.States.VOID_LIQUID))
            .withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4)));

    public static final ConfiguredFeature<?, ?> SNAPDRAGON_AND_GRASS = Feature.FLOWER
            .withConfiguration(VeFeatures.Configs.SNAPDRAGON_AND_GRASS_CONFIG)
            .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(4);
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> TAIGA_CABIN = VeStructure.cabin
            .withConfiguration(new VillageConfig(() -> CabinPools.taiga_cabin_pattern, 2));
    public static final StructureFeature<VillageConfig, ? extends Structure<VillageConfig>> FOREST_CABIN = VeStructure.cabin
            .withConfiguration(new VillageConfig(() -> CabinPools.forest_cabin_pattern, 2));

    public static final class Configs
    {
        public static final BlockClusterFeatureConfig BLUEBERRY_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VeFeatures.States.BLUEBERRY_BUSH), SimpleBlockPlacer.PLACER)).tries(64)
                        .whitelist(ImmutableSet.of(VeFeatures.States.GRASS_BLOCK.getBlock())).func_227317_b_().build();
        public static final BlockClusterFeatureConfig CRANBERRY_BUSH_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VeFeatures.States.CRANBERRY_BUSH), SimpleBlockPlacer.PLACER)).tries(64)
                        .whitelist(ImmutableSet.of(VeFeatures.States.GRASS_BLOCK.getBlock())).func_227317_b_().build();

        public static final BlockClusterFeatureConfig SNAPDRAGON_AND_GRASS_CONFIG = (new BlockClusterFeatureConfig.Builder(
                (new WeightedBlockStateProvider()).addWeightedBlockstate(VeFeatures.States.ENDER_GRASS, 2)
                        .addWeightedBlockstate(VeFeatures.States.SNAPDRAGON, 1),
                SimpleBlockPlacer.PLACER)).tries(64).whitelist(ImmutableSet.of(VeFeatures.States.END_STONE.getBlock()))
                        .func_227317_b_().build();

        public static final BlockClusterFeatureConfig WITCHS_CRADLE_CONFIG = (new BlockClusterFeatureConfig.Builder(
                new SimpleBlockStateProvider(VeFeatures.States.WITCHS_CRADLE), SimpleBlockPlacer.PLACER)).tries(64)
                        .whitelist(ImmutableSet.of(VeFeatures.States.GRASS_BLOCK.getBlock())).func_227317_b_().build();
        public static final BigMushroomFeatureConfig BIG_PURPLE_MUSHROOM_CONFIG = new BigMushroomFeatureConfig(
                new SimpleBlockStateProvider(VeFeatures.States.PURPLE_MUSHROOM_BLOCK),
                new SimpleBlockStateProvider(VeFeatures.States.MUSHROOM_STEM), 2);
    }

    public static final class States
    {
        protected static final BlockState VOID_LIQUID = VeBlocks.void_liquid.getDefaultState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        protected static final BlockState END_STONE = Blocks.END_STONE.getDefaultState();
        protected static final BlockState SNAPDRAGON = VeBlocks.snapdragon.getDefaultState();
        protected static final BlockState ENDER_GRASS = VeBlocks.ender_grass.getDefaultState();
        protected static final BlockState PURPLE_MUSHROOM_BLOCK = VeBlocks.purple_mushroom_block.getDefaultState()
                .with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
        protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState()
                .with(HugeMushroomBlock.UP, Boolean.valueOf(false))
                .with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));
        protected static final BlockState WITCHS_CRADLE = VeBlocks.witchs_cradle.getDefaultState()
                .with(VeBerryBushBlock.AGE, 3);
        protected static final BlockState NETHER_SMOKY_QUARTZ_ORE = VeBlocks.smoky_quartz_ore.getDefaultState();
        protected static final BlockState NETHER_RUBY_ORE = VeBlocks.ruby_ore.getDefaultState();
        protected static final BlockState BLUEBERRY_BUSH = VeBlocks.blueberry_bush.getDefaultState()
                .with(VeBerryBushBlock.AGE, 3);
        protected static final BlockState CRANBERRY_BUSH = VeBlocks.cranberry_bush.getDefaultState()
                .with(VeBerryBushBlock.AGE, 3);
    }
}
