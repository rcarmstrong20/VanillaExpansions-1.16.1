package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeBlockTags
{
	public static final ITag.INamedTag<Block> OVERWORLD_PLANTABLE = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "overworld_plantable"));
	public static final ITag.INamedTag<Block> NETHER_PLANTABLE = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "nether_plantable"));
	public static final ITag.INamedTag<Block> END_PLANTABLE = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "end_plantable"));
	public static final ITag.INamedTag<Block> OVERWORLD_POTTABLE = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "overworld_pottable"));
	public static final ITag.INamedTag<Block> NETHER_POTTABLE = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "nether_pottable"));
	public static final ITag.INamedTag<Block> END_POTTABLE = BlockTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "end_pottable"));
}
