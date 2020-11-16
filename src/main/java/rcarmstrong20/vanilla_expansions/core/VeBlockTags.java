package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeBlockTags
{
    public static ITag<Block> overworld_plantable = VeBlockTags.makeWrapperTag("overworld_plantable");
    public static ITag<Block> nether_plantable = VeBlockTags.makeWrapperTag("nether_plantable");
    public static ITag<Block> end_plantable = VeBlockTags.makeWrapperTag("end_plantable");
    public static ITag<Block> overworld_pottable = VeBlockTags.makeWrapperTag("overworld_pottable");
    public static ITag<Block> nether_pottable = VeBlockTags.makeWrapperTag("nether_pottable");
    public static ITag<Block> end_pottable = VeBlockTags.makeWrapperTag("end_pottable");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Block> makeWrapperTag(String name)
    {
        return BlockTags.getCollection().get(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
