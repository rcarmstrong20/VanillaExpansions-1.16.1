package rcarmstrong20.vanilla_expansions.client.renderer.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.inventory.container.VeTransmutationTableContainer;

public class VeTransmutationTableScreen extends ContainerScreen<VeTransmutationTableContainer>
{
    private static final ResourceLocation BG_LOCATION = new ResourceLocation(VanillaExpansions.MOD_ID,
            "textures/gui/container/transmutation_table.png");
    private static final int Y_OFFSET = 83;

    public VeTransmutationTableScreen(VeTransmutationTableContainer screenContainer, PlayerInventory inv,
            ITextComponent titleIn)
    {
        super(screenContainer, inv, titleIn);

        this.inventoryLabelY = this.imageHeight - Y_OFFSET; // Decreases the height of the "inventory" label.
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        this.renderBackground(matrixStack);
        this.getMinecraft().getTextureManager().bind(BG_LOCATION);
        int i = this.leftPos;
        int j = this.topPos;
        this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight + Y_OFFSET);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        super.renderTooltip(matrixStack, mouseX, mouseY);
    }
}
