package rcarmstrong20.vanilla_expansions.client.renderer.tile_entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.block.VeColoredCampfireBlock;
import rcarmstrong20.vanilla_expansions.tile_entity.VeColoredCampfireTileEntity;

@OnlyIn(Dist.CLIENT)
public class VeColoredCampfireTileEntityRenderer extends TileEntityRenderer<VeColoredCampfireTileEntity>
{
    public VeColoredCampfireTileEntityRenderer(TileEntityRendererDispatcher dispatcher)
    {
        super(dispatcher);
    }

    @Override
    public void render(VeColoredCampfireTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
            IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        Direction direction = tileEntityIn.getBlockState().getValue(VeColoredCampfireBlock.FACING);
        NonNullList<ItemStack> nonnulllist = tileEntityIn.getItems();

        for (int i = 0; i < nonnulllist.size(); ++i)
        {
            ItemStack itemstack = nonnulllist.get(i);
            if (itemstack != ItemStack.EMPTY)
            {
                matrixStackIn.pushPose();
                matrixStackIn.translate(0.5D, 0.44921875D, 0.5D);
                Direction direction1 = Direction.from2DDataValue((i + direction.get2DDataValue()) % 4);
                float f = -direction1.toYRot();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
                matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(90.0F));
                matrixStackIn.translate(-0.3125D, -0.3125D, 0.0D);
                matrixStackIn.scale(0.375F, 0.375F, 0.375F);
                Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, TransformType.FIXED, combinedLightIn,
                        combinedOverlayIn, matrixStackIn, bufferIn);
                matrixStackIn.popPose();
            }
        }
    }
}
