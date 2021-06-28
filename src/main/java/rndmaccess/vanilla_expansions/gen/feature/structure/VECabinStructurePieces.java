package rndmaccess.vanilla_expansions.gen.feature.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern.PlacementBehaviour;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.template.ProcessorLists;
import rndmaccess.vanilla_expansions.VanillaExpansions;

public class VECabinStructurePieces
{
    public static final String TAIGA_LOCATION = "cabin/taiga/taiga_cabin";
    public static final String ICY_TAIGA_LOCATION = "cabin/taiga/icy_taiga_cabin";
    public static final String FOREST_LOCATION = "cabin/forest/forest_cabin";
    public static final String CRIMSON_LOCATION = "cabin/crimson/crimson_cabin";

    public static final JigsawPattern TAIGA_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, TAIGA_LOCATION), new ResourceLocation("empty"),
            ImmutableList.of(Pair.of(JigsawPiece.single(VanillaExpansions.MOD_ID + ":" + TAIGA_LOCATION,
                    ProcessorLists.MOSSIFY_70_PERCENT), 1)),
            PlacementBehaviour.RIGID));

    public static final JigsawPattern ICY_TAIGA_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, ICY_TAIGA_LOCATION), new ResourceLocation("empty"),
            ImmutableList.of(Pair.of(JigsawPiece.single(VanillaExpansions.MOD_ID + ":" + ICY_TAIGA_LOCATION,
                    ProcessorLists.MOSSIFY_20_PERCENT), 1)),
            PlacementBehaviour.RIGID));

    public static final JigsawPattern FOREST_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, FOREST_LOCATION), new ResourceLocation("empty"),
            ImmutableList.of(Pair.of(JigsawPiece.single(VanillaExpansions.MOD_ID + ":" + FOREST_LOCATION,
                    ProcessorLists.MOSSIFY_70_PERCENT), 1)),
            PlacementBehaviour.RIGID));

    public static final JigsawPattern CRIMSON_START = JigsawPatternRegistry.register(new JigsawPattern(
            new ResourceLocation(VanillaExpansions.MOD_ID, CRIMSON_LOCATION), new ResourceLocation("empty"),
            ImmutableList.of(Pair.of(JigsawPiece.single(VanillaExpansions.MOD_ID + ":" + CRIMSON_LOCATION,
                    VEProcessorLists.CRIMSON_MOSSIFY_70_PERCENT), 1)),
            PlacementBehaviour.RIGID));
}
