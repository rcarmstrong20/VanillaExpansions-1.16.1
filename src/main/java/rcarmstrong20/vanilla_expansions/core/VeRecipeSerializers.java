package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.item.crafting.VeWoodcuttingRecipe;

/**
 * A class for holding every recipe serializer instance that vanilla expansions
 * has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeRecipeSerializers
{
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, VanillaExpansions.MOD_ID);

    public static IRecipeSerializer<VeWoodcuttingRecipe> woodcutting = register("woodcutting",
            new VeWoodcuttingRecipe.Serializer<>(VeWoodcuttingRecipe::new));

    /**
     * @param <S>
     * @param name   The name for the recipe serializer.
     * @param recipe A new instance of recipe serializer.
     * @return A new recipe serializer.
     */
    private static <S extends IRecipeSerializer<? extends IRecipe<?>>> S register(String name, S recipe)
    {
        RECIPES.register(name, () -> recipe);
        return recipe;
    }
}
