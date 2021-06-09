package rndmaccess.vanilla_expansions.core;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.item.crafting.VETransmutationRecipe;
import rndmaccess.vanilla_expansions.item.crafting.VEWoodcuttingRecipe;

/**
 * A class for holding every recipe type instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VERecipeTypes
{
    public static IRecipeType<VEWoodcuttingRecipe> woodcutting = register("woodcutting");
    public static IRecipeType<VETransmutationRecipe> transmutation = register("transmutation");

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