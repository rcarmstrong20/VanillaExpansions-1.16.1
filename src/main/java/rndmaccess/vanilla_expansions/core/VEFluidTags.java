package rndmaccess.vanilla_expansions.core;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every fluid tag instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VEFluidTags
{
    public static ITag<Fluid> darkMatter = VEFluidTags.bind("dark_matter");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Fluid> bind(String name)
    {
        return ForgeTagHandler.createOptionalTag(ForgeRegistries.FLUIDS,
                new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
