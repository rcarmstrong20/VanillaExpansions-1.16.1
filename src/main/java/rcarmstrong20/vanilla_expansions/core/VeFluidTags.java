package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeFluidTags
{
	public static final ITag.INamedTag<Fluid> VOID = FluidTags.makeWrapperTag(VanillaExpansions.compileName(VanillaExpansions.MOD_ID, "void"));
}
