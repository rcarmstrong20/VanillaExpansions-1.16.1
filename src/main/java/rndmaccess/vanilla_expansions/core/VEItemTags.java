package rndmaccess.vanilla_expansions.core;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every item tag instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VEItemTags
{
    public static ITag<Item> packet_seeds = VEItemTags.bind("packet_seeds");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Item> bind(String name)
    {
        return ForgeTagHandler.createOptionalTag(ForgeRegistries.ITEMS,
                new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
