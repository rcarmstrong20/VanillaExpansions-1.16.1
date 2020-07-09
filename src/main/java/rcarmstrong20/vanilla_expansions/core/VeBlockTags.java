package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeBlockTags
{
	public static final ITag.INamedTag<Block> OVERWORLD_PLANTING_POTS = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "overworld_planting_pots"));
	public static final ITag.INamedTag<Block> NETHER_PLANTING_POTS = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "nether_planting_pots"));
	public static final ITag.INamedTag<Block> END_PLANTING_POTS = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "end_planting_pots"));
	public static final ITag.INamedTag<Block> OVERWORLD_PLANTS = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "overworld_plants"));
	public static final ITag.INamedTag<Block> NETHER_PLANTS = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "nether_plants"));
	public static final ITag.INamedTag<Block> END_PLANTS = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "end_plants"));
}
