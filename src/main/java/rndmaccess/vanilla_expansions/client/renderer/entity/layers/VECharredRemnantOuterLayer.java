package rndmaccess.vanilla_expansions.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.entity.hostile.VECharredRemnantEntity;

@OnlyIn(Dist.CLIENT)
public class VECharredRemnantOuterLayer<T extends VECharredRemnantEntity> extends LayerRenderer<T, DrownedModel<T>>
{
    private static final ResourceLocation CHARRED_REMNANT_OUTER_LAYER_LOCATION = new ResourceLocation(
            VanillaExpansions.MOD_ID, "textures/entity/zombie/charred_remnant_outer_layer.png");
    private final DrownedModel<T> model = new DrownedModel<>(0.25F, 0.0F, 64, 64);

    public VECharredRemnantOuterLayer(IEntityRenderer<T, DrownedModel<T>> render)
    {
        super(render);
    }

    @Override
    public void render(MatrixStack stack, IRenderTypeBuffer buffer, int p_225628_3_, T p_225628_4_, float p_225628_5_,
            float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_)
    {
        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, CHARRED_REMNANT_OUTER_LAYER_LOCATION,
                stack, buffer, p_225628_3_, p_225628_4_, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_,
                p_225628_10_, p_225628_7_, 1.0F, 1.0F, 1.0F);
    }
}
