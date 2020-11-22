package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeItemTags
{
    public static ITag<Item> packet_seeds = VeItemTags.makeWrapperTag("packet_seeds");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Item> makeWrapperTag(String name)
    {
        return ItemTags.getCollection().get(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
