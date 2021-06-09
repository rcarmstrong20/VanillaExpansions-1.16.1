package rcarmstrong20.vanilla_expansions.util.jei;

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
import rcarmstrong20.vanilla_expansions.core.VEBlocks;
import rcarmstrong20.vanilla_expansions.core.VERecipeTypes;
import rcarmstrong20.vanilla_expansions.inventory.container.VETransmutationTableContainer;
import rcarmstrong20.vanilla_expansions.item.crafting.VETransmutationRecipe;
import rcarmstrong20.vanilla_expansions.item.crafting.VEWoodcuttingRecipe;

@JeiPlugin
public class VEPlugin implements IModPlugin
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

        registry.addRecipeCatalyst(new ItemStack(VEBlocks.transmutationTable), VERecipeCategories.TRANSMUTATION);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.woodcutter), VERecipeCategories.WOODCUTTING);

        // Add each colored campfire icon to the display next to the campfire recipe.
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.whiteCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.orangeCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.magentaCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.lightBlueCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.yellowCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.limeCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.pinkCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.grayCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.lightGrayCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.cyanCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.purpleCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.blueCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.brownCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.greenCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.redCampfire), campfire);
        registry.addRecipeCatalyst(new ItemStack(VEBlocks.blackCampfire), campfire);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        final IJeiHelpers helpers = registry.getJeiHelpers();
        final IGuiHelper gui = helpers.getGuiHelper();

        registry.addRecipeCategories(new VETransmutationTableRecipeCategory(gui), new VEWoodcutterRecipeCategory(gui));
    }

    @Override
    @SuppressWarnings("resource")
    public void registerRecipes(IRecipeRegistration registry)
    {
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        List<VETransmutationRecipe> transmutationRecipes = manager.getAllRecipesFor(VERecipeTypes.transmutation);
        List<VEWoodcuttingRecipe> woodcuttingRecipes = manager.getAllRecipesFor(VERecipeTypes.woodcutting);

        registry.addRecipes(transmutationRecipes, VERecipeCategories.TRANSMUTATION);
        registry.addRecipes(woodcuttingRecipes, VERecipeCategories.WOODCUTTING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry)
    {
        registry.addRecipeTransferHandler(VETransmutationTableContainer.class, VERecipeCategories.TRANSMUTATION, 0, 3,
                4, 36);
    }
}
