package rndmaccess.vanilla_expansions.client.renderer.entity.layers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.util.ResourceLocation;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.entity.passive.VEEnderHorseEntity;

public class VEEnderHorseFeatureLayer<T extends VEEnderHorseEntity> extends AbstractEyesLayer<T, HorseModel<T>>
{
    private static final RenderType FEATURES = RenderType
            .eyes(new ResourceLocation(VanillaExpansions.MOD_ID, "textures/entity/horse/horse_ender_features.png"));

    public VEEnderHorseFeatureLayer(IEntityRenderer<T, HorseModel<T>> renderer)
    {
        super(renderer);
    }

    @Override
    public RenderType renderType()
    {
        return FEATURES;
    }
}
