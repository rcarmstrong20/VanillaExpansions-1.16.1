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
import rcarmstrong20.vanilla_expansions.inventory.container.VEWoodcutterContainer;
import rcarmstrong20.vanilla_expansions.item.crafting.VEWoodcuttingRecipe;

@OnlyIn(Dist.CLIENT)
public class VEWoodcutterScreen extends ContainerScreen<VEWoodcutterContainer>
{
    private static final ResourceLocation BG_LOCATION = new ResourceLocation("textures/gui/container/stonecutter.png");
    private float scrollOffs;
    private boolean scrolling;
    private int startIndex;
    private boolean displayRecipes;

    public VEWoodcutterScreen(VEWoodcutterContainer menu, PlayerInventory playerInv, ITextComponent title)
    {
        super(menu, playerInv, title);
        menu.registerUpdateListener(this::containerChanged);
        --this.titleLabelY;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        this.renderBackground(matrixStack);
        this.getMinecraft().getTextureManager().bind(BG_LOCATION);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
        int k = (int) (41.0F * this.scrollOffs);
        this.blit(matrixStack, i + 119, j + 15 + k, 176 + (this.isScrollBarActive() ? 0 : 12), 0, 12, 15);
        int l = this.leftPos + 52;
        int i1 = this.topPos + 14;
        int j1 = this.startIndex + 12;
        this.renderButtons(matrixStack, mouseX, mouseY, l, i1, j1);
        this.renderRecipes(l, i1, j1);
    }

    @Override
    protected void renderTooltip(MatrixStack matrixStack, int x, int y)
    {
        super.renderTooltip(matrixStack, x, y);
        if (this.displayRecipes)
        {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 12;
            List<VEWoodcuttingRecipe> list = this.menu.getRecipes();

            for (int l = this.startIndex; l < k && l < this.menu.getNumRecipes(); ++l)
            {
                int i1 = l - this.startIndex;
                int j1 = i + i1 % 4 * 16;
                int k1 = j + i1 / 4 * 18 + 2;
                if (x >= j1 && x < j1 + 16 && y >= k1 && y < k1 + 18)
                {
                    this.renderTooltip(matrixStack, list.get(l).getResultItem(), x, y);
                }
            }
        }
    }

    private void renderButtons(MatrixStack matrixStack, int mouseX, int mouseY, int left, int top,
            int recipeIndexOffsetMax)
    {
        for (int i = this.startIndex; i < recipeIndexOffsetMax && i < this.menu.getNumRecipes(); ++i)
        {
            int j = i - this.startIndex;
            int k = left + j % 4 * 16;
            int l = j / 4;
            int i1 = top + l * 18 + 2;
            int j1 = this.imageHeight;
            if (i == this.menu.getSelectedRecipeIndex())
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

    private void renderRecipes(int left, int top, int recipeIndexOffsetMax)
    {
        List<VEWoodcuttingRecipe> list = this.menu.getRecipes();

        for (int i = this.startIndex; i < recipeIndexOffsetMax && i < this.menu.getNumRecipes(); ++i)
        {
            int j = i - this.startIndex;
            int k = left + j % 4 * 16;
            int l = j / 4;
            int i1 = top + l * 18 + 2;
            this.getMinecraft().getItemRenderer().renderAndDecorateItem(list.get(i).getResultItem(), k, i1);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        this.scrolling = false;
        if (this.displayRecipes)
        {
            int i = this.leftPos + 52;
            int j = this.topPos + 14;
            int k = this.startIndex + 12;

            for (int l = this.startIndex; l < k; ++l)
            {
                int i1 = l - this.startIndex;
                double d0 = mouseX - (i + i1 % 4 * 16);
                double d1 = mouseY - (j + i1 / 4 * 18);
                if (d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D
                        && this.menu.clickMenuButton(this.minecraft.player, l))
                {
                    Minecraft.getInstance().getSoundManager()
                            .play(SimpleSound.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                    this.minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, l);
                    return true;
                }
            }

            i = this.leftPos + 119;
            j = this.topPos + 9;
            if (mouseX >= i && mouseX < i + 12 && mouseY >= j && mouseY < j + 54)
            {
                this.scrolling = true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY)
    {
        if (this.scrolling && this.isScrollBarActive())
        {
            int i = this.topPos + 14;
            int j = i + 54;
            this.scrollOffs = ((float) mouseY - i - 7.5F) / (j - i - 15.0F);
            this.scrollOffs = MathHelper.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int) (this.scrollOffs * this.getOffscreenRows() + 0.5D) * 4;
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
        if (this.isScrollBarActive())
        {
            int i = this.getOffscreenRows();
            this.scrollOffs = (float) (this.scrollOffs - p_mouseScrolled_5_ / i);
            this.scrollOffs = MathHelper.clamp(this.scrollOffs, 0.0F, 1.0F);
            this.startIndex = (int) (this.scrollOffs * i + 0.5D) * 4;
        }
        return true;
    }

    private boolean isScrollBarActive()
    {
        return this.displayRecipes && this.menu.getNumRecipes() > 12;
    }

    protected int getOffscreenRows()
    {
        return (this.menu.getNumRecipes() + 4 - 1) / 4 - 3;
    }

    private void containerChanged()
    {
        this.displayRecipes = this.menu.hasInputItem();
        if (!this.displayRecipes)
        {
            this.scrollOffs = 0.0F;
            this.startIndex = 0;
        }
    }
}
