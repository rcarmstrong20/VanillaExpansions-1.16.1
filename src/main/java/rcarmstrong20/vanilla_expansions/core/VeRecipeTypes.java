package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.item.crafting.VeTransmutationRecipe;
import rcarmstrong20.vanilla_expansions.item.crafting.VeWoodcuttingRecipe;

/**
 * A class for holding every recipe type instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VeRecipeTypes
{
    public static IRecipeType<VeWoodcuttingRecipe> woodcutting = register("woodcutting");
    public static IRecipeType<VeTransmutationRecipe> transmutation = register("transmutation");

    /**
     * @param <T>
     * @param Name The name for the recipe type.
     * @return New recipe type.
     */
    public static <T extends IRecipe<?>> IRecipeType<T> register(String name)
    {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(VanillaExpansions.MOD_ID, name),
                new IRecipeType<T>()
                {
                    @Override
                    public String toString()
                    {
                        return VanillaExpansions.MOD_ID + ":" + name;
                    }
                });
    }
}