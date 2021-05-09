package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every fluid tag instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VeFluidTags
{
    public static ITag<Fluid> darkMatter = bind("dark_matter");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Fluid> bind(String name)
    {
        return FluidTags.createOptional(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
