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
public class VETotemParticle extends SimpleAnimatedParticle
{
    public VETotemParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY,
            double motionZ, IAnimatedSprite spriteWithAge)
    {
        super(world, x, y, z, spriteWithAge, -0.05F);
        this.xd = motionX;
        this.yd = motionY;
        this.zd = motionZ;
        this.quadSize *= 0.75F;
        this.lifetime = 60 + this.random.nextInt(12);
        this.setSpriteFromAge(spriteWithAge);
        this.setBaseAirFriction(0.6F);
    }

    public static class VeTotemOfTheBruteParticle extends VETotemParticle
    {
        public VeTotemOfTheBruteParticle(ClientWorld world, double x, double y, double z, double motionX,
                double motionY, double motionZ, IAnimatedSprite spriteWithAge)
        {
            super(world, x, y, z, motionX, motionY, motionZ, spriteWithAge);

            // Red and black
            if (this.random.nextInt(4) == 0)
            {
                this.setColor(1.5F + this.random.nextFloat() * 0.2F, this.random.nextFloat() * 0.2F,
                        this.random.nextFloat() * 0.2F);
            }
            else
            {
                this.setColor(this.random.nextFloat(), 0.0F, 0.0F);
            }
        }
    }

    public static class VeTotemOfTheGuardianParticle extends VETotemParticle
    {
        public VeTotemOfTheGuardianParticle(ClientWorld world, double x, double y, double z, double motionX,
                double motionY, double motionZ, IAnimatedSprite spriteWithAge)
        {
            super(world, x, y, z, motionX, motionY, motionZ, spriteWithAge);

            // Cyan and dark blue
            if (this.random.nextInt(4) == 0)
            {
                this.setColor(this.random.nextFloat() * 0.5F, 0.6F + this.random.nextFloat() * 0.3F,
                        0.6F + this.random.nextFloat() * 0.2F);
            }
            else
            {
                this.setColor(0.1F + this.random.nextFloat() * 0.2F, 0.1F + this.random.nextFloat() * 0.2F,
                        0.3F + this.random.nextFloat() * 0.2F);
            }
        }
    }

    public static class VeTotemOfTheFortunateParticle extends VETotemParticle
    {
        public VeTotemOfTheFortunateParticle(ClientWorld world, double x, double y, double z, double motionX,
                double motionY, double motionZ, IAnimatedSprite spriteWithAge)
        {
            super(world, x, y, z, motionX, motionY, motionZ, spriteWithAge);

            // Green and Blue
            if (this.random.nextInt(4) == 0)
            {
                this.setColor(0.0F, 0.6F + this.random.nextFloat() * 0.3F, 0.5F + this.random.nextFloat() * 0.2F);
            }
            else
            {
                this.setColor(0.0F, 0.5F + this.random.nextFloat() * 0.1F, 0.0F);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class VeTotemOfTheBruteFactory implements IParticleFactory<BasicParticleType>
    {
        private final IAnimatedSprite spriteSet;

        public VeTotemOfTheBruteFactory(IAnimatedSprite spriteSet)
        {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed)
        {
            return new VeTotemOfTheBruteParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class VeTotemOfTheGuardianFactory implements IParticleFactory<BasicParticleType>
    {
        private final IAnimatedSprite spriteSet;

        public VeTotemOfTheGuardianFactory(IAnimatedSprite spriteSet)
        {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed)
        {
            return new VeTotemOfTheGuardianParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class VeTotemOfTheFortunateFactory implements IParticleFactory<BasicParticleType>
    {
        private final IAnimatedSprite spriteSet;

        public VeTotemOfTheFortunateFactory(IAnimatedSprite spriteSet)
        {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed)
        {
            return new VeTotemOfTheFortunateParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}
