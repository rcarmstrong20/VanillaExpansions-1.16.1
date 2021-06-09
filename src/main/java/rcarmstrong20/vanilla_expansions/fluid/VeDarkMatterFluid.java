package rcarmstrong20.vanilla_expansions.fluid;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VEBlocks;
import rcarmstrong20.vanilla_expansions.core.VEFluidTags;
import rcarmstrong20.vanilla_expansions.core.VEFluids;
import rcarmstrong20.vanilla_expansions.core.VEItems;
import rcarmstrong20.vanilla_expansions.core.VEParticleTypes;
import rcarmstrong20.vanilla_expansions.core.VESoundEvents;

public abstract class VEDarkMatterFluid extends WaterFluid
{
    @Override
    public Fluid getFlowing()
    {
        return VEFluids.flowingDarkMatter;
    }

    @Override
    public Fluid getSource()
    {
        return VEFluids.darkMatter;
    }

    @Override
    public Item getBucket()
    {
        return VEItems.darkMatterBucket;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World worldIn, BlockPos pos, FluidState state, Random random)
    {
        if (random.nextInt(100) == 0)
        {
            worldIn.addParticle(VEParticleTypes.underDarkMatter, pos.getX() + random.nextFloat(),
                    pos.getY() + random.nextFloat(), pos.getZ() + random.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
        else if (random.nextInt(600) == 0)
        {
            worldIn.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), VESoundEvents.blockDarkMatterAmbient,
                    SoundCategory.BLOCKS, random.nextFloat() * 0.2F + 0.2F, random.nextFloat() + 0.5F, false);
        }
    }

    @Override
    public boolean canBeReplacedWith(FluidState state, IBlockReader reader, BlockPos pos, Fluid fluid,
            Direction direction)
    {
        return direction == Direction.DOWN && !fluid.is(VEFluidTags.darkMatter);
    }

    @Override
    protected FluidAttributes createAttributes()
    {
        return FluidAttributes
                .builder(new ResourceLocation(VanillaExpansions.MOD_ID, "block/dark_matter_still"),
                        new ResourceLocation(VanillaExpansions.MOD_ID, "block/dark_matter_flow"))
                .luminosity(20).density(1000).viscosity(3000).build(this);
    }

    @Override
    @Nullable
    @OnlyIn(Dist.CLIENT)
    public IParticleData getDripParticle()
    {
        return VEParticleTypes.drippingDarkMatter;
    }

    @Override
    public BlockState createLegacyBlock(FluidState state)
    {
        return VEBlocks.darkMatter.defaultBlockState().setValue(FlowingFluidBlock.LEVEL,
                Integer.valueOf(getLegacyLevel(state)));
    }

    @Override
    public boolean isSame(Fluid fluidIn)
    {
        return fluidIn == VEFluids.darkMatter || fluidIn == VEFluids.flowingDarkMatter;
    }

    @Override
    public int getTickDelay(IWorldReader reader)
    {
        return 10;
    }

    public static class Flowing extends VEDarkMatterFluid
    {
        @Override
        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> builder)
        {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state)
        {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state)
        {
            return false;
        }
    }

    public static class Source extends VEDarkMatterFluid
    {
        @Override
        public int getAmount(FluidState fluidState)
        {
            return 8;
        }

        @Override
        public boolean isSource(FluidState fluidState)
        {
            return true;
        }
    }
}
