package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeCabinPools
{
    public static JigsawPattern taigaCabinPattern = JigsawPatternRegistry.func_244094_a(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/taiga_cabin_start_pool"),
            new ResourceLocation("empty"), ImmutableList.of(), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

    public static JigsawPattern forestCabinPattern = JigsawPatternRegistry.func_244094_a(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabins/overworld/forest_cabin"),
            new ResourceLocation("empty"), ImmutableList.of(), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

    public static JigsawPattern crimsonCabinPattern = JigsawPatternRegistry.func_244094_a(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabins/nether/crimson_cabin"),
            new ResourceLocation("empty"), ImmutableList.of(), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
}
