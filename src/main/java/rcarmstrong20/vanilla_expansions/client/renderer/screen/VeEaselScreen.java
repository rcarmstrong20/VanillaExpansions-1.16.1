package rcarmstrong20.vanilla_expansions.client.renderer.screen;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.inventory.container.VeEaselContainer;
import rcarmstrong20.vanilla_expansions.item.crafting.VeEaselRecipe;

@OnlyIn(Dist.CLIENT)
public class VeEaselScreen extends ContainerScreen<VeEaselContainer>
{
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(VanillaExpansions.MOD_ID,
            "textures/gui/container/easel.png");
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

    public VeEaselScreen(VeEaselContainer containerIn, PlayerInventory playerInv, ITextComponent titleIn)
    {
        super(containerIn, playerInv, titleIn);
        containerIn.setInventoryUpdateListener(this::onInventoryUpdate);
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
        int startXPos = this.guiLeft;
        int startYPos = this.guiTop;
        this.blit(matrixStack, startXPos, startYPos, 0, 0, this.xSize, this.ySize); // Render background
        int sliderProgressPos = (int) (41.0F * this.sliderProgress);
        this.blit(matrixStack, startXPos + 119, startYPos + 13 + sliderProgressPos, 232 + (this.canScroll() ? 0 : 12),
                0, 12, 15); // Render scroll wheel
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
            List<VeEaselRecipe> list = this.container.getRecipeList();

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
        int guiXPos = this.guiLeft;
        int guiYPos = this.guiTop;

        Slot paperSlot = this.container.getPaperInventorySlot();
        Slot dyeSlot1 = this.container.getDyeInventorySlot();
        Slot dyeSlot2 = this.container.getDyeInventorySlot2();

        // Draw the paper icon when the slot is empty
        if (!paperSlot.getHasStack())
        {
            this.blit(matrixStack, guiXPos + paperSlot.xPos, guiYPos + paperSlot.yPos, this.xSize, 0, 16, 16);
        }

        // Draw the first dye slot icon when the slot is empty
        if (!dyeSlot1.getHasStack())
        {
            this.blit(matrixStack, guiXPos + dyeSlot1.xPos, guiYPos + dyeSlot1.yPos, this.xSize + 16, 0, 16, 16);
        }

        // Draw the second dye slot icon when the slot is empty
        if (!dyeSlot2.getHasStack())
        {
            this.blit(matrixStack, guiXPos + dyeSlot2.xPos, guiYPos + dyeSlot2.yPos, this.xSize + 16, 0, 16, 16);
        }

        int i2 = this.guiLeft + 60;
        int k2 = this.guiTop + 13;

        // Draw the recipe buttons
        for (int i = this.recipeIndexOffset; i < recipeIndexOffsetMax && i < this.container.getRecipeListSize(); ++i)
        {
            int j = i - this.recipeIndexOffset;
            int j1 = i2 + j % 4 * 14;
            int k1 = k2 + j / 4 * 14;
            int l1 = this.ySize;
            if (i == this.container.getSelectedRecipe())
            {
                l1 += 14;
            }
            else if (mouseX >= j1 && mouseY >= k1 && mouseX < j1 + 14 && mouseY < k1 + 14)
            {
                l1 += 28;
            }
            this.blit(matrixStack, j1, k1, 0, l1, 14, 14);
        }
    }

    /**
     * Draw the recipes item options into the screen.
     */
    private void drawRecipesItems(int left, int top, int recipeIndexOffsetMax)
    {
        List<VeEaselRecipe> list = this.container.getRecipeList();

        int i2 = this.guiLeft + 60;
        int k2 = this.guiTop + 13;

        for (int i = this.recipeIndexOffset; i < recipeIndexOffsetMax && i < this.container.getRecipeListSize(); ++i)
        {
            int j = i - this.recipeIndexOffset;
            int j1 = i2 + j % 4 * 14;
            int k1 = k2 + j / 4 * 14;

            this.getMinecraft().getItemRenderer().renderItemAndEffectIntoGUI(list.get(i).getRecipeOutput(), j1 - 1,
                    k1 - 1);
        }
    }

    /**
     * Called when the mouse is clicked
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        this.clickedOnScroll = false;
        if (this.hasItemsInInputSlot)
        {
            int i = this.guiLeft + 60;
            int j = this.guiTop + 13;
            int k = this.recipeIndexOffset + 16;

            for (int l = this.recipeIndexOffset; l < k; ++l)
            {
                int i1 = l - this.recipeIndexOffset;
                double d0 = mouseX - (i + i1 % 4 * 14);
                double d1 = mouseY - (j + i1 / 4 * 14);
                if (d0 >= 0.0D && d1 >= 0.0D && d0 < 14.0D && d1 < 14.0D
                        && this.container.enchantItem(this.minecraft.player, l))
                {
                    Minecraft.getInstance().getSoundHandler()
                            .play(SimpleSound.master(SoundEvents.UI_LOOM_SELECT_PATTERN, 1.0F));
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
            int i = this.guiTop + 13;
            int j = i + 67;
            this.sliderProgress = ((float) mouseY - i - 7.5F) / (j - i - 15.0F);
            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) (this.sliderProgress * this.getHiddenRows() + 0.5D) / 4;
            return true;
        }
        else
        {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta)
    {
        if (this.canScroll())
        {
            int i = this.getHiddenRows();
            this.sliderProgress = (float) (this.sliderProgress - delta / i);
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
