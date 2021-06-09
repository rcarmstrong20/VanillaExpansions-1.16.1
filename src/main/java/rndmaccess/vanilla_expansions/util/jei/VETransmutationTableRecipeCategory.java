package rndmaccess.vanilla_expansions.util.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.core.VEBlocks;
import rndmaccess.vanilla_expansions.item.crafting.VETransmutationRecipe;

public class VETransmutationTableRecipeCategory implements IRecipeCategory<VETransmutationRecipe>
{
    private static final int essenceSlotIndex = 0;
    private static final int tierSlotIndex = 1;
    private static final int rubySlotIndex = 2;
    private static final int outputSlotIndex = 3;

    public static final int width = 124;
    public static final int height = 54;

    private static final ResourceLocation BG_LOCATION = new ResourceLocation(VanillaExpansions.MOD_ID,
            "textures/gui/container/transmutation_table_jei.png");
    public IDrawable background;
    public IDrawable icon;
    private final ITextComponent localizedName;

    public VETransmutationTableRecipeCategory(IGuiHelper guiHelper)
    {
        background = guiHelper.createDrawable(BG_LOCATION, 0, 0, width, height);
        icon = guiHelper.createDrawableIngredient(new ItemStack(VEBlocks.transmutationTable));
        localizedName = new TranslationTextComponent("gui.jei.vanillaexpansions.category.transmutationTable");
    }

    @Override
    public ResourceLocation getUid()
    {
        return VERecipeCategories.TRANSMUTATION;
    }

    @Override
    public Class<? extends VETransmutationRecipe> getRecipeClass()
    {
        return VETransmutationRecipe.class;
    }

    @Override
    public String getTitle()
    {
        return getTitleAsTextComponent().getString();
    }

    @Override
    public ITextComponent getTitleAsTextComponent()
    {
        return localizedName;
    }

    @Override
    public IDrawable getBackground()
    {
        return background;
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public void setIngredients(VETransmutationRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, VETransmutationRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(essenceSlotIndex, true, 9, 17);
        stacks.init(tierSlotIndex, true, 36, 2);
        stacks.init(rubySlotIndex, true, 36, 34);
        stacks.init(outputSlotIndex, false, 94, 18);

        stacks.set(ingredients);
    }
}
