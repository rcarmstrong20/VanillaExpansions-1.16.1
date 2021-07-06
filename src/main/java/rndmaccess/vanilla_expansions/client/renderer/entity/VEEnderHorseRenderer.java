package rndmaccess.vanilla_expansions.client.renderer.entity;

import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.client.renderer.entity.layers.VEEnderHorseFeatureLayer;
import rndmaccess.vanilla_expansions.client.renderer.entity.layers.VELeatherHorseArmorLayer;
import rndmaccess.vanilla_expansions.entity.passive.VEEnderHorseEntity;

public class VEEnderHorseRenderer extends AbstractHorseRenderer<VEEnderHorseEntity, HorseModel<VEEnderHorseEntity>>
{
    private static final ResourceLocation ENDER_HORSE_LOCATION = new ResourceLocation(VanillaExpansions.MOD_ID,
            "textures/entity/horse/horse_ender.png");

    public VEEnderHorseRenderer(EntityRendererManager manager)
    {
        super(manager, new HorseModel<>(0.0F), 1.1F);
        this.addLayer(new VEEnderHorseFeatureLayer<>(this));
        this.addLayer(new VELeatherHorseArmorLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(VEEnderHorseEntity enderHorseEntity)
    {
        return ENDER_HORSE_LOCATION;
    }

    public static class RenderFactory implements IRenderFactory<VEEnderHorseEntity>
    {
        @Override
        public EntityRenderer<? super VEEnderHorseEntity> createRenderFor(EntityRendererManager manager)
        {
            return new VEEnderHorseRenderer(manager);
        }
    }
}
