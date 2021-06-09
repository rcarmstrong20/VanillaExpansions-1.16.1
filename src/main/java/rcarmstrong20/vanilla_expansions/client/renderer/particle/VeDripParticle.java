package rcarmstrong20.vanilla_expansions.client.renderer.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.core.VEFluids;
import rcarmstrong20.vanilla_expansions.core.VEParticleTypes;

@OnlyIn(Dist.CLIENT)
public class VEDripParticle extends SpriteTexturedParticle
{
    private final Fluid type;

    protected VEDripParticle(ClientWorld world, double x, double y, double z, Fluid typeIn)
    {
        super(world, x, y, z);
        this.setSize(0.01F, 0.01F);
        this.gravity = 0.06F;
        this.type = typeIn;
    }

    @Override
    public IParticleRenderType getRenderType()
    {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    protected int getLightColor(float f)
    {
        return 240;
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.preMoveUpdate();
        if (!this.removed)
        {
            this.yd -= this.gravity;
            this.move(this.xd, this.yd, this.zd);
            this.postMoveUpdate();
            if (!this.removed)
            {
                this.xd *= 0.98F;
                this.yd *= 0.98F;
                this.zd *= 0.98F;
                BlockPos blockpos = new BlockPos(this.x, this.y, this.z);
                FluidState fluidstate = this.level.getFluidState(blockpos);
                if (fluidstate.getType() == this.type
                        && this.y < blockpos.getY() + fluidstate.getHeight(this.level, blockpos))
                {
                    this.remove();
                }
            }
        }
    }

    protected void preMoveUpdate()
    {
        if (this.lifetime-- <= 0)
        {
            this.remove();
        }
    }

    protected void postMoveUpdate()
    {}

    @OnlyIn(Dist.CLIENT)
    static class VeDripping extends VEDripParticle
    {
        private final IParticleData fallingParticle;

        private VeDripping(ClientWorld world, double x, double y, double z, Fluid type, IParticleData fallingParticle)
        {
            super(world, x, y, z, type);
            this.fallingParticle = fallingParticle;
            this.gravity *= 0.02F;
            this.lifetime = 40;
        }

        @Override
        protected void preMoveUpdate()
        {
            if (this.lifetime-- <= 0)
            {
                this.remove();
                this.level.addParticle(this.fallingParticle, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

        @Override
        protected void postMoveUpdate()
        {
            this.xd *= 0.02D;
            this.yd *= 0.02D;
            this.zd *= 0.02D;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class VeFalling extends VEDripParticle
    {
        protected final IParticleData landingParticle;

        private VeFalling(ClientWorld world, double x, double y, double z, Fluid type, IParticleData landingParticle)
        {
            super(world, x, y, z, type);
            this.landingParticle = landingParticle;
        }

        @Override
        protected void postMoveUpdate()
        {
            if (this.onGround)
            {
                this.remove();
                this.level.addParticle(this.landingParticle, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class VeLanding extends VEDripParticle
    {
        private VeLanding(ClientWorld world, double x, double y, double z, Fluid type)
        {
            super(world, x, y, z, type);
            this.lifetime = (int) (16.0D / (Math.random() * 0.8D + 0.2D));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class VeDrippingDarkMatterFactory implements IParticleFactory<BasicParticleType>
    {
        protected final IAnimatedSprite sprite;

        public VeDrippingDarkMatterFactory(IAnimatedSprite spriteIn)
        {
            this.sprite = spriteIn;
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed)
        {
            VeDripping dripParticle = new VEDripParticle.VeDripping(world, x, y, z, VEFluids.darkMatter,
                    VEParticleTypes.fallingDarkMatter);
            dripParticle.gravity *= 0.01F;
            dripParticle.lifetime = 100;
            dripParticle.setColor(0.1F, 0.1F, 0.1F);
            dripParticle.pickSprite(this.sprite);
            return dripParticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class VeFallingDarkMatterFactory implements IParticleFactory<BasicParticleType>
    {
        protected final IAnimatedSprite sprite;

        public VeFallingDarkMatterFactory(IAnimatedSprite sprite)
        {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed)
        {
            VeFalling fallingParticle = new VEDripParticle.VeFalling(world, x, y, z, VEFluids.darkMatter,
                    VEParticleTypes.landingDarkMatter);
            fallingParticle.setColor(0.1F, 0.1F, 0.1F);
            fallingParticle.pickSprite(this.sprite);
            return fallingParticle;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class VeLandingDarkMatterFactory implements IParticleFactory<BasicParticleType>
    {
        protected final IAnimatedSprite sprite;

        public VeLandingDarkMatterFactory(IAnimatedSprite sprite)
        {
            this.sprite = sprite;
        }

        @Override
        public Particle createParticle(BasicParticleType type, ClientWorld world, double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed)
        {
            VeLanding landParticle = new VEDripParticle.VeLanding(world, x, y, z, VEFluids.darkMatter);
            landParticle.lifetime = (int) (128.0D / (Math.random() * 0.8D + 0.2D));
            landParticle.setColor(0.1F, 0.1F, 0.1F);
            landParticle.pickSprite(this.sprite);
            return landParticle;
        }
    }
}