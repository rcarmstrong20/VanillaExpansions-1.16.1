package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeBlockTags
{
    public static final ITag<Block> OVERWORLD_PLANTABLE = VeBlockTags.makeWrapperTag("overworld_plantable");
    public static final ITag<Block> NETHER_PLANTABLE = VeBlockTags.makeWrapperTag("nether_plantable");
    public static final ITag<Block> END_PLANTABLE = VeBlockTags.makeWrapperTag("end_plantable");
    public static final ITag<Block> OVERWORLD_POTTABLE = VeBlockTags.makeWrapperTag("overworld_pottable");
    public static final ITag<Block> NETHER_POTTABLE = VeBlockTags.makeWrapperTag("nether_pottable");
    public static final ITag<Block> END_POTTABLE = VeBlockTags.makeWrapperTag("end_pottable");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Block> makeWrapperTag(String name)
    {
        return BlockTags.getCollection().getOrCreate(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
