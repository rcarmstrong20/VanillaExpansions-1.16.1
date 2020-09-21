package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.google.common.collect.ImmutableList;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class CabinPools
{
    public static final JigsawPattern taiga_cabin_pattern = JigsawPatternRegistry.func_244094_a(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabins/taiga_cabin"), new ResourceLocation("empty"),
            ImmutableList.of(), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));

    public static final JigsawPattern forest_cabin_pattern = JigsawPatternRegistry.func_244094_a(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabins/forest_cabin"), new ResourceLocation("empty"),
            ImmutableList.of(), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING));
}
