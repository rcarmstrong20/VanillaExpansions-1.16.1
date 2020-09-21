package rcarmstrong20.vanilla_expansions.block;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;

public class VeFlowingVoidBlock extends FlowingFluidBlock
{
    public VeFlowingVoidBlock(Supplier<? extends FlowingFluid> supplier, Properties builder)
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

    /**
     * This method forms the new blocks when the correct liquids make contact.
     *
     * @param world The current world.
     * @param pos   The position where the fluids collide and where the new block
     *              will be formed.
     * @return True if a block touching this block changes.
     */
    private boolean reactWithNeighbors(World world, BlockPos pos)
    {
        // Find the direction that corresponds to where the lava or water is at.
        for (Direction direction : Direction.values())
        {
            if (world.getFluidState(pos.offset(direction)).isTagged(FluidTags.WATER))
            {
                return generateBlocks(world, pos, direction, VeBlocks.nephilite, Blocks.END_STONE);
            }
            else if (world.getFluidState(pos.offset(direction)).isTagged(FluidTags.LAVA))
            {
                return generateBlocks(world, pos, direction, VeBlocks.snowflake_obsidian, Blocks.END_STONE);
            }
        }
        return true;
    }

    /**
     * Generates the blocks from the fluids reacting with one another based off
     * their positions in the world.
     *
     * @param world          The current world.
     * @param pos            The position where the fluids collide and where the new
     *                       block will be formed.
     * @param foundDirection The direction that the fluid is located at.
     * @param sourceBlock    The block that should be formed when the fluid is a
     *                       source.
     * @param otherBlock     The alternative block that should be formed.
     * @return True if the block is formed.
     */
    private boolean generateBlocks(World world, BlockPos pos, Direction foundDirection, Block sourceBlock,
            Block otherBlock)
    {
        if (world.getFluidState(pos).isSource() && foundDirection == Direction.UP)
        {
            this.triggerMixEffects(world, pos);
            world.setBlockState(pos,
                    ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, sourceBlock.getDefaultState()));
            return true;
        }
        else if (world.getBlockState(pos.offset(foundDirection)).getFluidState().getFluid() != Fluids.EMPTY)
        {
            this.triggerMixEffects(world, pos);
            world.setBlockState(pos.offset(foundDirection),
                    ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, otherBlock.getDefaultState()));
            return true;
        }
        return false;
    }

    /**
     * A helper method that plays the undervoid sound and plays smoke particles when
     * called.
     *
     * @param world The current world.
     * @param pos   The position to play the sound and particles at.
     */
    private void triggerMixEffects(IWorld world, BlockPos pos)
    {
        Random random = new Random();
        /*
         * for(int i = 0; i < (random.nextInt(20) + 10); i++) {
         * world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX(), pos.up().getY(),
         * pos.getZ(), 0.0, 0.0, 0.0); }
         */
        world.playSound(null, pos, VeSoundEvents.BLOCK_VOID_HARDENS, SoundCategory.BLOCKS,
                random.nextFloat() * 0.2F + 1F, random.nextFloat() * 0.6F);
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
    {
        return false;
    }

    /**
     * Called when an entity collides with this block.
     */
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
    {
        Random random = new Random();

        if (!(entity instanceof ItemEntity))
        {
            double xMot = entity.getMotion().getX();
            double yMot = entity.getMotion().getY();
            double zMot = entity.getMotion().getZ();

            // Slow the players descent when falling.
            if (yMot < 0.0D)
            {
                entity.setMotion(0.0, -0.01, 0.0);
            }
            // Play firework particles and raise the player.
            else if (yMot > 0.0D)
            {
                world.addParticle(ParticleTypes.FIREWORK, entity.getPosXRandom(random.nextFloat()),
                        entity.getPosY() + random.nextFloat(), entity.getPosZRandom(random.nextFloat()), 0.0, 0.0, 0.0);
                entity.setMotion(new Vector3d(0.0D, 0.2D, 0.0D));
            }
            // Slow the players movement when walking
            else if (yMot == 0.0D && xMot != 0.0D || zMot != 0.0D)
            {
                entity.setMotion(0.01, 0.0, 0.01);
            }
            entity.fallDistance = 0;
        }
        else
        {
            // Make items float on void with firework particles.
            world.addParticle(ParticleTypes.FIREWORK, entity.getPosXRandom(random.nextFloat()),
                    entity.getPosY() + random.nextFloat(), entity.getPosZRandom(random.nextFloat()), 0.0, 0.0, 0.0);
            entity.setMotion(new Vector3d(0.0D, 0.2D, 0.0D));
        }
    }
}
