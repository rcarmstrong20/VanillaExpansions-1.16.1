package rcarmstrong20.vanilla_expansions.util.compat.jei;

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
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.item.crafting.VeWoodcuttingRecipe;

public class VeWoodcutterRecipeCategory implements IRecipeCategory<VeWoodcuttingRecipe>
{
    private static final int inputSlot = 0;
    private static final int outputSlot = 1;

    public static final int width = 82;
    public static final int height = 34;

    private final IDrawable background;
    private final IDrawable icon;
    private final ITextComponent localizedName;

    public VeWoodcutterRecipeCategory(IGuiHelper guiHelper)
    {
        ResourceLocation location = VeConstants.RECIPE_GUI_VANILLA;
        background = guiHelper.createDrawable(location, 0, 220, width, height);
        icon = guiHelper.createDrawableIngredient(new ItemStack(VeBlocks.woodcutter));
        localizedName = new TranslationTextComponent("gui.jei.ve.category.woodcutter");
    }

    @Override
    public ResourceLocation getUid()
    {
        return VeRecipeCategories.WOODCUTTING;
    }

    @Override
    public Class<? extends VeWoodcuttingRecipe> getRecipeClass()
    {
        return VeWoodcuttingRecipe.class;
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
    public void setIngredients(VeWoodcuttingRecipe recipe, IIngredients ingredients)
    {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, VeWoodcuttingRecipe recipe, IIngredients ingredients)
    {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(inputSlot, true, 0, 8);
        guiItemStacks.init(outputSlot, false, 60, 8);

        guiItemStacks.set(ingredients);
    }
}
