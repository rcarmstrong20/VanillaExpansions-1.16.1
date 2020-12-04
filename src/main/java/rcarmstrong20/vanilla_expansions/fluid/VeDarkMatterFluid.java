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
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeFluidTags;
import rcarmstrong20.vanilla_expansions.core.VeFluids;
import rcarmstrong20.vanilla_expansions.core.VeItems;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;

public abstract class VeDarkMatterFluid extends WaterFluid
{
    @Override
    public Fluid getFlowingFluid()
    {
        return VeFluids.flowingDarkMatter;
    }

    @Override
    public Fluid getStillFluid()
    {
        return VeFluids.darkMatter;
    }

    @Override
    public Item getFilledBucket()
    {
        return VeItems.darkMatterBucket;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World worldIn, BlockPos pos, FluidState state, Random random)
    {
        if (random.nextInt(100) == 0)
        {
            worldIn.addParticle(VeParticleTypes.underDarkMatter, (double) pos.getX() + (double) random.nextFloat(),
                    (double) pos.getY() + (double) random.nextFloat(),
                    (double) pos.getZ() + (double) random.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
        else if (random.nextInt(600) == 0)
        {
            worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), VeSoundEvents.blockDarkMatterAmbient,
                    SoundCategory.BLOCKS, random.nextFloat() * 0.2F + 0.2F, random.nextFloat() + 0.5F, false);
        }
    }

    @Override
    public boolean canDisplace(FluidState state, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction)
    {
        return direction == Direction.DOWN && !fluid.isIn(VeFluidTags.darkMatter);
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
    public IParticleData getDripParticleData()
    {
        return VeParticleTypes.drippingDarkMatter;
    }

    @Override
    public BlockState getBlockState(FluidState state)
    {
        return VeBlocks.darkMatter.getDefaultState().with(FlowingFluidBlock.LEVEL,
                Integer.valueOf(getLevelFromState(state)));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn)
    {
        return fluidIn == VeFluids.darkMatter || fluidIn == VeFluids.flowingDarkMatter;
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_)
    {
        return 10;
    }

    public static class Flowing extends VeDarkMatterFluid
    {
        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder)
        {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public int getLevel(FluidState state)
        {
            return state.get(LEVEL_1_8);
        }

        @Override
        public boolean isSource(FluidState state)
        {
            return false;
        }
    }

    public static class Source extends VeDarkMatterFluid
    {
        @Override
        public int getLevel(FluidState fluidState)
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
