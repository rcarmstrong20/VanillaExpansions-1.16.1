package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeFluidTags
{
    public static final ITag<Fluid> VOID = VeFluidTags.makeWrapperTag("void");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Fluid> makeWrapperTag(String name)
    {
        return FluidTags.getCollection().getOrCreate(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
