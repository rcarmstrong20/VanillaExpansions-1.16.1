package rndmaccess.vanilla_expansions.core;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.item.crafting.VETransmutationRecipe;
import rndmaccess.vanilla_expansions.item.crafting.VEWoodcuttingRecipe;

/**
 * A class for holding every recipe serializer instance that vanilla expansions
 * has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VERecipeSerializers
{
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, VanillaExpansions.MOD_ID);

    public static IRecipeSerializer<VEWoodcuttingRecipe> woodcutting = register("woodcutting",
            new VEWoodcuttingRecipe.Serializer<>(VEWoodcuttingRecipe::new));
    public static IRecipeSerializer<VETransmutationRecipe> transmutation = register("transmutation",
            new VETransmutationRecipe.Serializer());

    /**
     * @param <S>
     * @param name   The name for the recipe serializer.
     * @param recipe A new instance of recipe serializer.
     * @return A new recipe serializer.
     */
    private static <S extends IRecipeSerializer<? extends IRecipe<?>>> S register(String name, S recipe)
    {
        RECIPE_SERIALIZERS.register(name, () -> recipe);
        return recipe;
    }
}
