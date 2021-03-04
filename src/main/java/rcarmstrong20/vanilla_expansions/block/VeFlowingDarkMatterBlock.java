package rcarmstrong20.vanilla_expansions.block;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeFluidTags;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;

public class VeFlowingDarkMatterBlock extends FlowingFluidBlock
{
    public VeFlowingDarkMatterBlock(Supplier<? extends FlowingFluid> supplier, Properties builder)
    {
        super(supplier, builder);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        if (reactWithNeighbors(world, pos))
        {
            world.getPendingFluidTicks().scheduleTick(pos, state.getFluidState().getFluid(),
                    this.getFluid().getTickRate(world));
        }
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
    {
        return false;
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
            world.getPendingFluidTicks().scheduleTick(pos, state.getFluidState().getFluid(),
                    this.getFluid().getTickRate(world));
        }
    }

    private boolean reactWithNeighbors(World world, BlockPos pos)
    {
        for (Direction direction : Direction.values())
        {
            if (world.getFluidState(pos.offset(direction)).isTagged(FluidTags.LAVA))
            {
                return generateBlocks(world, pos, direction, VeBlocks.bauxite, Blocks.END_STONE);
            }
            else if (world.getFluidState(pos.offset(direction)).isTagged(FluidTags.WATER))
            {
                return generateBlocks(world, pos, direction, VeBlocks.sodalite, Blocks.END_STONE);
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
     * @param sourceBlock    The block to generate when converting a source block.
     * @param otherBlock     The block to generate when converting a flowing block.
     * @return true when this method generates a block.
     */
    private boolean generateBlocks(World world, BlockPos pos, Direction foundDirection, Block sourceBlock,
            Block flowingBlock)
    {
        if (foundDirection == Direction.UP)
        {
            if (world.getFluidState(pos).isSource())
            {
                placeBlockAt(world, pos, sourceBlock);
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
            if (world.getFluidState(pos).isSource())
            {
                placeBlockAt(world, pos, sourceBlock);
                return true;
            }
            else
            {
                placeBlockAtOffset(world, pos, foundDirection, flowingBlock);
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
        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, block.getDefaultState()));
    }

    /**
     * @param world
     * @param pos       The position to start at.
     * @param direction The direction offset to place the block at.
     * @param block     The block to place.
     */
    private void placeBlockAtOffset(World world, BlockPos pos, Direction direction, Block block)
    {
        placeBlockAt(world, pos.offset(direction), block);
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

        spawnParticles(ParticleTypes.POOF, (ServerWorld) world, pos.up(), random);
        world.playSound(null, pos, VeSoundEvents.blockDarkMatterHardens, SoundCategory.BLOCKS,
                random.nextFloat() * 0.2F + 1F, random.nextFloat() * 0.6F);
    }

    private void spawnParticles(IParticleData particle, ServerWorld world, BlockPos pos, Random rand)
    {
        double x = pos.getX() + rand.nextDouble();
        double y = pos.getY() + rand.nextDouble();
        double z = pos.getZ() + rand.nextDouble();

        world.spawnParticle(particle, x, y, z, rand.nextInt(20) + 10, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity)
    {
        return world.getFluidState(pos).isTagged(VeFluidTags.darkMatter)
                && (entity.getMotion().y > 0 || entity.getMotion().y < 0);
    }

    /**
     * Called when an entity collides with this block.
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
    {
        Random random = new Random();

        if (!(entity instanceof PlayerEntity) && entity.handleFluidAcceleration(VeFluidTags.darkMatter, 0.005))
            return;
        /*
         * if (entity instanceof ItemEntity) { // Make items float on dark matter with
         * firework particles. if (random.nextInt(10) == 0) {
         * world.addParticle(ParticleTypes.FIREWORK,
         * entity.getPosXRandom(random.nextFloat()), entity.getPosY() +
         * random.nextFloat(), entity.getPosZRandom(random.nextFloat()), 0.0, 0.0, 0.0);
         * } entity.setMotion(0.0D, (entity.getMotion().getY() + 0.1D) / 2, 0.0D); }
         * else { double xMot = entity.getMotion().getX(); double yMot =
         * entity.getMotion().getY(); double zMot = entity.getMotion().getZ();
         *
         * if ((entity.getPosY() - entity.prevPosY) > 0.0 &&
         * world.getBlockState(pos.up()) != this.getBlock().getDefaultState()) {
         * entity.addVelocity(0.0, 0.05, 0.0); } else { entity.setMotion(xMot / 200000,
         * yMot / 200000, zMot / 200000); }
         *
         * if (random.nextInt(10) == 0) { world.addParticle(ParticleTypes.FIREWORK,
         * entity.getPosXRandom(random.nextFloat()), entity.getPosY() +
         * random.nextFloat(), entity.getPosZRandom(random.nextFloat()), 0.0, 0.0, 0.0);
         * } entity.fallDistance = 0; }
         */
    }
}
