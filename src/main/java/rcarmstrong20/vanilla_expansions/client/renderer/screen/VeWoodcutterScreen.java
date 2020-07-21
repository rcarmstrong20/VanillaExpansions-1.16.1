package rcarmstrong20.vanilla_expansions.client.renderer.screen;

import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
        --this.field_238743_q_;
    }

    @Override
    public void func_230430_a_(MatrixStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_) // Same as
                                                                                                             // render
    {
        super.func_230430_a_(matrixStack, p_230430_2_, p_230430_3_, p_230430_4_); // Same as render
        this.func_230459_a_(matrixStack, p_230430_2_, p_230430_3_); // Same as renderHoveredToolTip
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the
     * items)
     */
    /*
     * protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
     * this.font.drawString(this.title.getFormattedText(), 8.0F, 4.0F, 4210752);
     * this.font.drawString(this.playerInventory.getDisplayName().getFormattedText()
     * , 8.0F, (float)(this.ySize - 94), 4210752); }
     */

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) // Same as
                                                                                                       // drawGuiContainerBackgroundLayer
    {
        this.func_230446_a_(matrixStack); // Same as renderBackground
        this.getMinecraft().getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.func_238474_b_(matrixStack, i, j, 0, 0, this.xSize, this.ySize); // Same as blit
        int k = (int) (41.0F * this.sliderProgress);
        this.func_238474_b_(matrixStack, i + 119, j + 15 + k, 176 + (this.canScroll() ? 0 : 12), 0, 12, 15); // Same as
                                                                                                             // blit
        int l = this.guiLeft + 52;
        int i1 = this.guiTop + 14;
        int j1 = this.recipeIndexOffset + 12;
        this.drawRecipesBackground(matrixStack, mouseX, mouseY, l, i1, j1);
        this.drawRecipesItems(l, i1, j1);
    }

    @Override
    protected void func_230459_a_(MatrixStack matrixStack, int p_230459_2_, int p_230459_3_)
    {
        super.func_230459_a_(matrixStack, p_230459_2_, p_230459_3_);
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
                if (p_230459_2_ >= j1 && p_230459_2_ < j1 + 16 && p_230459_3_ >= k1 && p_230459_3_ < k1 + 18)
                {
                    this.func_230457_a_(matrixStack, list.get(l).getRecipeOutput(), p_230459_2_, p_230459_3_);
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
            } else if (mouseX >= k && mouseY >= i1 && mouseX < k + 16 && mouseY < i1 + 18)
            {
                j1 += 36;
            }
            this.func_238474_b_(matrixStack, k, i1 - 1, 0, j1, 16, 18); // Same as blit
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
     * Called when the mouse is clicked. Same as mouseClicked
     */
    @Override
    public boolean func_231044_a_(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_)
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
                double d0 = p_mouseClicked_1_ - (double) (i + i1 % 4 * 16);
                double d1 = p_mouseClicked_3_ - (double) (j + i1 / 4 * 18);
                if (d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D
                        && this.container.enchantItem(this.getMinecraft().player, l))
                {
                    Minecraft.getInstance().getSoundHandler()
                            .play(SimpleSound.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.getMinecraft().playerController.sendEnchantPacket((this.container).windowId, l);
                    return true;
                }
            }

            i = this.guiLeft + 119;
            j = this.guiTop + 9;
            if (p_mouseClicked_1_ >= (double) i && p_mouseClicked_1_ < (double) (i + 12)
                    && p_mouseClicked_3_ >= (double) j && p_mouseClicked_3_ < (double) (j + 54))
            {
                this.clickedOnScroll = true;
            }
        }
        return super.func_231044_a_(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_); // Same as mouseClicked
    }

    /**
     * Called when the mouse is dragged. Same as mouseDragged.
     */
    @Override
    public boolean func_231045_a_(double p_mouseDragged_1_, double p_mouseDragged_3_, int p_mouseDragged_5_,
            double p_mouseDragged_6_, double p_mouseDragged_8_)
    {
        if (this.clickedOnScroll && this.canScroll())
        {
            int i = this.guiTop + 14;
            int j = i + 54;
            this.sliderProgress = ((float) p_mouseDragged_3_ - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) ((double) (this.sliderProgress * (float) this.getHiddenRows()) + 0.5D) * 4;
            return true;
        } else
        {
            return super.func_231045_a_(p_mouseDragged_1_, p_mouseDragged_3_, p_mouseDragged_5_, p_mouseDragged_6_,
                    p_mouseDragged_8_); // Same as mouseDragged
        }
    }

    /**
     * Same as mouseScrolled.
     */
    @Override
    public boolean func_231043_a_(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_)
    {
        if (this.canScroll())
        {
            int i = this.getHiddenRows();
            this.sliderProgress = (float) ((double) this.sliderProgress - p_mouseScrolled_5_ / (double) i);
            this.sliderProgress = MathHelper.clamp(this.sliderProgress, 0.0F, 1.0F);
            this.recipeIndexOffset = (int) ((double) (this.sliderProgress * (float) i) + 0.5D) * 4;
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
