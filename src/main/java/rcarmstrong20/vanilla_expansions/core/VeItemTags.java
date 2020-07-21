package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeItemTags
{
    public static final ITag<Item> PAINTINGS = VeItemTags.makeWrapperTag("paintings");
    public static final ITag<Item> FRAMES = VeItemTags.makeWrapperTag("frames");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Item> makeWrapperTag(String name)
    {
        return ItemTags.getCollection().getOrCreate(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
