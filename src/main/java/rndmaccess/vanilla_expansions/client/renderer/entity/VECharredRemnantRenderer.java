package rndmaccess.vanilla_expansions.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.DrownedModel;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.client.renderer.entity.layers.VECharredRemnantOuterLayer;
import rndmaccess.vanilla_expansions.entity.hostile.VECharredRemnantEntity;

@OnlyIn(Dist.CLIENT)
public class VECharredRemnantRenderer
        extends AbstractZombieRenderer<VECharredRemnantEntity, DrownedModel<VECharredRemnantEntity>>
{
    private static final ResourceLocation CHARRED_REMNANT_LOCATION = new ResourceLocation(VanillaExpansions.MOD_ID,
            "textures/entity/zombie/charred_remnant.png");

    public VECharredRemnantRenderer(EntityRendererManager manager)
    {
        super(manager, new DrownedModel<>(0.0F, 0.0F, 64, 64), new DrownedModel<>(0.5F, true),
                new DrownedModel<>(1.0F, true));
        this.addLayer(new VECharredRemnantOuterLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ZombieEntity zombieEntity)
    {
        return CHARRED_REMNANT_LOCATION;
    }

    @Override
    protected void setupRotations(VECharredRemnantEntity charredRemnantEntity, MatrixStack stack, float p_225621_3_,
            float p_225621_4_, float p_225621_5_)
    {
        super.setupRotations(charredRemnantEntity, stack, p_225621_3_, p_225621_4_, p_225621_5_);
        float f = charredRemnantEntity.getSwimAmount(p_225621_5_);
        if (f > 0.0F)
        {
            stack.mulPose(Vector3f.XP.rotationDegrees(
                    MathHelper.lerp(f, charredRemnantEntity.xRot, -10.0F - charredRemnantEntity.xRot)));
        }
    }

    @Override
    protected int getBlockLightLevel(VECharredRemnantEntity charredRemnantEntity, BlockPos pos)
    {
        return 15;
    }

    public static class RenderFactory implements IRenderFactory<VECharredRemnantEntity>
    {
        @Override
        public EntityRenderer<? super VECharredRemnantEntity> createRenderFor(EntityRendererManager manager)
        {
            return new VECharredRemnantRenderer(manager);
        }

    }
}
