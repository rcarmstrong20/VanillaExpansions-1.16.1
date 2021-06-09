package rcarmstrong20.vanilla_expansions.block;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import rcarmstrong20.vanilla_expansions.core.VEBlocks;
import rcarmstrong20.vanilla_expansions.core.VEFluidTags;
import rcarmstrong20.vanilla_expansions.core.VESoundEvents;

public class VEFlowingDarkMatterBlock extends FlowingFluidBlock
{
    public VEFlowingDarkMatterBlock(Supplier<? extends FlowingFluid> supplier, Properties builder)
    {
        super(supplier, builder);
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        if (reactWithNeighbors(world, pos))
        {
            world.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(),
                    this.getFluid().getTickDelay(world));
        }
    }

    /**
     * Called when a block next to this block changes.
     */
    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos,
            boolean isMoving)
    {
        if (reactWithNeighbors(world, pos))
        {
            world.getLiquidTicks().scheduleTick(pos, state.getFluidState().getType(),
                    this.getFluid().getTickDelay(world));
        }
    }

    private boolean reactWithNeighbors(World world, BlockPos pos)
    {
        for (Direction direction : Direction.values())
        {
            if (world.getFluidState(pos.relative(direction)).is(FluidTags.LAVA))
            {
                return generateBlocks(world, pos, direction, VEBlocks.bauxite, Blocks.END_STONE, Blocks.END_STONE);
            }
            else if (world.getFluidState(pos.relative(direction)).is(FluidTags.WATER))
            {
                return generateBlocks(world, pos, direction, VEBlocks.sodalite, Blocks.BLUE_ICE, Blocks.END_STONE);
            }
        }
        return true; // This method needs to always return true to keep flowing functionality intact.
    }

    /**
     * This method generates the appropriate blocks when fluids interact.
     *
     * @param world
     * @param pos
     * @param foundDirection The direction where the fluid is located.
     * @param sourceMatter   The block to place when converting a dark matter source
     *                       block.
     * @param sourceOther    The block to place when converting a lava or water
     *                       source block.
     * @param flowingBlock   The block to place when converting a flowing block.
     * @return true when this method places a block.
     */
    private boolean generateBlocks(World world, BlockPos pos, Direction foundDirection, Block sourceMatter,
            Block sourceOther, Block flowingBlock)
    {
        BlockPos belowPos = pos.below();

        if (foundDirection.equals(Direction.UP))
        {
            if (world.getFluidState(pos).isSource())
            {
                placeBlockAt(world, pos, sourceMatter);
                return true;
            }
            else
            {
                placeBlockAt(world, pos, flowingBlock);
                return true;
            }
        }
        else
        {
            if (world.getFluidState(belowPos).isSource())
            {
                placeBlockAt(world, belowPos, sourceOther);
                return true;
            }
            else
            {
                placeBlockAtRelativePos(world, pos, foundDirection, flowingBlock);
                return true;
            }
        }
    }

    /**
     * @param world
     * @param pos   The position to start at.
     * @param block The block to place.
     */
    private void placeBlockAt(World world, BlockPos pos, Block block)
    {
        this.triggerMixEffects(world, pos);
        world.setBlock(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, block.defaultBlockState()), 2);
    }

    /**
     * @param world
     * @param pos       The position to start at.
     * @param direction The direction offset to place the block at.
     * @param block     The block to place.
     */
    private void placeBlockAtRelativePos(World world, BlockPos pos, Direction direction, Block block)
    {
        placeBlockAt(world, pos.relative(direction), block);
    }

    /**
     * A helper method that plays the undervoid sound and plays smoke particles when
     * called.
     *
     * @param world The current world.
     * @param pos   The position to play the sound and particles at.
     */
    private void triggerMixEffects(World world, BlockPos pos)
    {
        Random random = new Random();

        spawnParticles(ParticleTypes.POOF, (ServerWorld) world, pos.above(), random);
        world.playSound(null, pos, VESoundEvents.blockDarkMatterHardens, SoundCategory.BLOCKS,
                random.nextFloat() * 0.2F + 1F, random.nextFloat() * 0.6F);
    }

    private void spawnParticles(IParticleData particle, ServerWorld world, BlockPos pos, Random rand)
    {
        double x = pos.getX() + rand.nextDouble();
        double y = pos.getY() + rand.nextDouble();
        double z = pos.getZ() + rand.nextDouble();

        world.sendParticles(particle, x, y, z, rand.nextInt(20) + 10, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity)
    {
        return world.getFluidState(pos).is(VEFluidTags.darkMatter)
                && (entity.getDeltaMovement().y > 0 || entity.getDeltaMovement().y < 0);
    }

    /**
     * Called when an entity collides with this block.
     */
    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity)
    {
        entity.updateFluidHeightAndDoFluidPushing(VEFluidTags.darkMatter, 0.007D);
    }
}
