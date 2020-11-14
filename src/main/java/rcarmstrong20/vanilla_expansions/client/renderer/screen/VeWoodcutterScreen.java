package rcarmstrong20.vanilla_expansions.client.renderer.screen;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;
import rcarmstrong20.vanilla_expansions.inventory.container.VeWoodcutterContainer;
import rcarmstrong20.vanilla_expansions.item.crafting.VeWoodcuttingRecipe;

@OnlyIn(Dist.CLIENT)
public class VeWoodcutterScreen extends ContainerScreen<VeWoodcutterContainer>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(
            "textures/gui/container/stonecutter.png");
    private float sliderProgress;
    /** Is {@code true} if the player clicked on the scroll wheel in the GUI. */
    private boolean clickedOnScroll;
    /**
     * The index of the first recipe to display. The number of recipes displayed at
     * any time is 12 (4 recipes per row, and 3 rows). If the player scrolled down
     * one row, this value would be 4 (representing the index of the first slot on
     * the second row).
     */
    private int recipeIndexOffset;
    private boolean hasItemsInInputSlot;

    public VeWoodcutterScreen(VeWoodcutterContainer container, PlayerInventory playerInv, ITextComponent title)
    {
        super(container, playerInv, title);
        container.setInventoryUpdateListener(this::onInventoryUpdate);
        --this.titleY;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        this.renderBackground(matrixStack);
        this.getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
        int k = (int) (41.0F * this.sliderProgress);
        this.blit(matrixStack, i + 119, j + 15 + k, 176 + (this.canScroll() ? 0 : 12), 0, 12, 15);
        int l = this.guiLeft + 52;
        int i1 = this.guiTop + 14;
        int j1 = this.recipeIndexOffset + 12;
        this.drawRecipesBackground(matrixStack, mouseX, mouseY, l, i1, j1);
        this.drawRecipesItems(l, i1, j1);
    }

    @Override
    protected void renderHoveredTooltip(MatrixStack matrixStack, int x, int y)
    {
        super.renderHoveredTooltip(matrixStack, x, y);
        if (this.hasItemsInInputSlot)
        {
            int i = this.guiLeft + 52;
            int j = this.guiTop + 14;
            int k = this.recipeIndexOffset + 12;
            List<VeWoodcuttingRecipe> list = this.container.getRecipeList();

            for (int l = this.recipeIndexOffset; l < k && l < this.container.getRecipeListSize(); ++l)
            {
                int i1 = l - this.recipeIndexOffset;
                int j1 = i + i1 % 4 * 16;
                int k1 = j + i1 / 4 * 18 + 2;
                if (x >= j1 && x < j1 + 16 && y >= k1 && y < k1 + 18)
                {
                    this.renderTooltip(matrixStack, list.get(l).getRecipeOutput(), x, y);
                }
            }
        }
    }

    /**
     * Draw the background for the screen.
     */
    private void drawRecipesBackground(MatrixStack matrixStack, int mouseX, int mouseY, int left, int top,
            int recipeIndexOffsetMax)
    {
        for (int i = this.recipeIndexOffset; i < recipeIndexOffsetMax && i < this.container.getRecipeListSize(); ++i)
        {
            int j = i - this.recipeIndexOffset;
            int k = left + j % 4 * 16;
            int l = j / 4;
            int i1 = top + l * 18 + 2;
            int j1 = this.ySize;
            if (i == this.container.getSelectedRecipe())
            {
                j1 += 18;
            }
            else if (mouseX >= k && mouseY >= i1 && mouseX < k + 16 && mouseY < i1 + 18)
            {
                j1 += 36;
            }
            this.blit(matrixStack, k, i1 - 1, 0, j1, 16, 18);
        }
    }

    /**
     * Draw the recipes item options into the screen.
     */
    private void drawRecipesItems(int left, int top, int recipeIndexOffsetMax)
    {
        List<VeWoodcuttingRecipe> list = this.container.getRecipeList();

        for (int i = this.recipeIndexOffset; i < recipeIndexOffsetMax && i < this.container.getRecipeListSize(); ++i)
        {
            int j = i - this.recipeIndexOffset;
            int k = left + j % 4 * 16;
            int l = j / 4;
            int i1 = top + l * 18 + 2;
            this.getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(list.get(i).getRecipeOutput(), k, i1);
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        this.clickedOnScroll = false;
        if (this.hasItemsInInputSlot)
        {
            int i = this.guiLeft + 52;
            int j = this.guiTop + 14;
            int k = this.recipeIndexOffset + 12;

            for (int l = this.recipeIndexOffset; l < k; ++l)
            {
                int i1 = l - this.recipeIndexOffset;
                double d0 = mouseX - (i + i1 % 4 * 16);
                double d1 = mouseY - (j + i1 / 4 * 18);
                if (d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D
                        && this.container.enchantItem(this.minecraft.player, l))
                {
                    Minecraft.getInstance().getSoundHandler()
                            .play(SimpleSound.master(VeSoundEvents.UI_WOODCUTTER_TAKE_RESULT, 1.0F));
                    this.minecraft.playerController.sendEnchantPacket((this.container).windowId, l);
                    return true;
                }
            }

            i = this.guiLeft + 119;
            j = this.guiTop + 9;
            if (mouseX >= i && mouseX < i + 12 && mouseY >= j && mouseY < j + 54)
            {
                this.clickedOnScroll = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    /**
     * Called when the mouse is dragged.
     */
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY)
    {
        if (this.clickedOnScroll && this.canScroll())
        {
            int i = this.guiTop + 14;
            int j = i + 54;
            this.sliderProgress = ((float) mouseY - i - 7.5F) / (j - i - 15.0F);
            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) (this.sliderProgress * this.getHiddenRows() + 0.5D) * 4;
            return true;
        }
        else
        {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_)
    {
        if (this.canScroll())
        {
            int i = this.getHiddenRows();
            this.sliderProgress = (float) (this.sliderProgress - p_mouseScrolled_5_ / i);
            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) (this.sliderProgress * i + 0.5D) * 4;
        }
        return true;
    }

    /**
     * Returns true if there are enough items in the inventory to scroll.
     */
    private boolean canScroll()
    {
        return this.hasItemsInInputSlot && this.container.getRecipeListSize() > 12;
    }

    protected int getHiddenRows()
    {
        return (this.container.getRecipeListSize() + 4 - 1) / 4 - 3;
    }

    /**
     * Called every time this screen's container is changed (is marked as dirty).
     */
    private void onInventoryUpdate()
    {
        this.hasItemsInInputSlot = this.getContainer().hasItemsinInputSlot();
        if (!this.hasItemsInInputSlot)
        {
            this.sliderProgress = 0.0F;
            this.recipeIndexOffset = 0;
        }
    }
}
