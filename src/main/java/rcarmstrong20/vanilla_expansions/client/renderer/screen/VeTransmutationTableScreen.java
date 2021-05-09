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
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(VanillaExpansions.MOD_ID,
            "textures/gui/container/transmutation_table.png");

    public VeTransmutationTableScreen(VeTransmutationTableContainer screenContainer, PlayerInventory inv,
            ITextComponent titleIn)
    {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y)
    {
        this.renderBackground(matrixStack);
        this.getMinecraft().getTextureManager().bind(BACKGROUND_TEXTURE);
    }
}
