package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.item.crafting.VeEaselRecipe;
import rcarmstrong20.vanilla_expansions.item.crafting.VeWoodcuttingRecipe;

public class VeRecipeTypes
{
	public static IRecipeType<VeWoodcuttingRecipe> woodcutting = register("woodcutting");
	public static IRecipeType<VeEaselRecipe> easel = register("easel");
	
	/**
	 * @param <T>
	 * @param  Name The name for the recipe type.
	 * @return New recipe type.
	 */
	static <T extends IRecipe<?>> IRecipeType<T> register(String name)
	{
		return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(VanillaExpansions.MOD_ID, name), new IRecipeType<T>()
		{
			public String toString()
			{
				return name;
			}
		});
	}
}