package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeItemTags
{
	public static final ITag.INamedTag<Item> PAINTINGS = ItemTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "paintings"));
	public static final ITag.INamedTag<Item> FRAMES = ItemTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "frames"));
	
	
}
