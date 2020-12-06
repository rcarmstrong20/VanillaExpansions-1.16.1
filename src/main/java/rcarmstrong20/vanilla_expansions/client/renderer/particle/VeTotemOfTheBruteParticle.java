package rcarmstrong20.vanilla_expansions.client.renderer.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SimpleAnimatedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VeTotemOfTheBruteParticle extends SimpleAnimatedParticle
{
    private VeTotemOfTheBruteParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY,
            double motionZ, IAnimatedSprite spriteWithAge)
    {
        super(world, x, y, z, spriteWithAge, -0.05F);
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
        this.particleScale *= 0.75F;
        this.maxAge = 60 + this.rand.nextInt(12);
        this.selectSpriteWithAge(spriteWithAge);

        // Red and black
        if (this.rand.nextInt(4) == 0)
        {
            this.setColor(1.5F + this.rand.nextFloat() * 0.2F, this.rand.nextFloat() * 0.2F,
                    this.rand.nextFloat() * 0.2F);
        }
        else
        {
            this.setColor(this.rand.nextFloat(), 0.0F, 0.0F);
        }

        this.setBaseAirFriction(0.6F);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType>
    {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet)
        {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed)
        {
            return new VeTotemOfTheBruteParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
