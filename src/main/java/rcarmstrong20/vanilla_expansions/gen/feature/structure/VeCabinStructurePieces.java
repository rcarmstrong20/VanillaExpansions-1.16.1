package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern.PlacementBehaviour;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeCabinStructurePieces
{
    public static final JigsawPattern TAIGA_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/taiga_cabin"), new ResourceLocation("empty"),
            ImmutableList.of(Pair.of(JigsawPiece.single("ve:cabin/taiga_cabin", ProcessorLists.MOSSIFY_70_PERCENT), 1)),
            PlacementBehaviour.RIGID));

    public static final JigsawPattern ICY_TAIGA_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/icy_taiga_cabin"), new ResourceLocation("empty"),
            ImmutableList
                    .of(Pair.of(JigsawPiece.single("ve:cabin/icy_taiga_cabin", ProcessorLists.MOSSIFY_20_PERCENT), 1)),
            PlacementBehaviour.RIGID));

    public static final JigsawPattern FOREST_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/forest_cabin"), new ResourceLocation("empty"),
            ImmutableList
                    .of(Pair.of(JigsawPiece.single("ve:cabin/forest_cabin", ProcessorLists.MOSSIFY_70_PERCENT), 1)),
            PlacementBehaviour.RIGID));

    public static final JigsawPattern CRIMSON_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, "cabin/crimson_cabin"), new ResourceLocation("empty"),
            ImmutableList.of(Pair
                    .of(JigsawPiece.single("ve:cabin/crimson_cabin", VeProcessorLists.CRIMSON_MOSSIFY_70_PERCENT), 1)),
            PlacementBehaviour.RIGID));
}
