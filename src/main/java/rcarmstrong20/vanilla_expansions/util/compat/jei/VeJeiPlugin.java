package rcarmstrong20.vanilla_expansions.util.compat.jei;

import java.util.List;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeRecipeTypes;
import rcarmstrong20.vanilla_expansions.inventory.container.VeTransmutationTableContainer;
import rcarmstrong20.vanilla_expansions.item.crafting.VeTransmutationRecipe;
import rcarmstrong20.vanilla_expansions.item.crafting.VeWoodcuttingRecipe;

@JeiPlugin
public class VeJeiPlugin implements IModPlugin
{
    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(VanillaExpansions.MOD_ID, "plugin_" + VanillaExpansions.MOD_ID);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry)
    {
        ResourceLocation campfire = VanillaRecipeCategoryUid.CAMPFIRE;

        registry.addRecipeCatalyst(new ItemStack(VeBlocks.transmutationTable), VeRecipeCategories.TRANSMUTATION);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.woodcutter), VeRecipeCategories.WOODCUTTING);

        // Add each colored campfire icon to the display next to the campfire recipe.
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.whiteCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.orangeCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.magentaCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.lightBlueCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.yellowCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.limeCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.pinkCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.grayCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.lightGrayCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.cyanCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.purpleCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.blueCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.brownCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.greenCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.redCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VeBlocks.blackCampfire), campfire);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        final IJeiHelpers helpers = registry.getJeiHelpers();
        final IGuiHelper gui = helpers.getGuiHelper();

        registry.addRecipeCategories(new VeTransmutationTableRecipeCategory(gui), new VeWoodcutterRecipeCategory(gui));
    }

    @Override
    @SuppressWarnings("resource")
    public void registerRecipes(IRecipeRegistration registry)
    {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        List<VeTransmutationRecipe> transmutationRecipes = manager.getAllRecipesFor(VeRecipeTypes.transmutation);
        List<VeWoodcuttingRecipe> woodcuttingRecipes = manager.getAllRecipesFor(VeRecipeTypes.woodcutting);

        registry.addRecipes(transmutationRecipes, VeRecipeCategories.TRANSMUTATION);
        registry.addRecipes(woodcuttingRecipes, VeRecipeCategories.WOODCUTTING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry)
    {
        registry.addRecipeTransferHandler(VeTransmutationTableContainer.class, VeRecipeCategories.TRANSMUTATION, 0, 3,
                4, 36);
    }
}
