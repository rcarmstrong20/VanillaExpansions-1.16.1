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
import net.minecraft.fluid.Fluid;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeFluidTags;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;

public class VeFlowingVoidBlock extends FlowingFluidBlock
{
	public VeFlowingVoidBlock(Supplier<? extends FlowingFluid> supplier, Properties builder)
	{
		super(supplier, builder);
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
	{
		if(reactWithNeighbors(worldIn, pos, state))
		{
			worldIn.getPendingFluidTicks().scheduleTick(pos, state.getFluidState().getFluid(), this.getFluid().getTickRate(worldIn));
		}
	}
	
	/*
	 * 
	 */
	private boolean reactWithNeighbors(World worldIn, BlockPos pos, BlockState state)
	{
		//Check to see if this block has the fluid tag void.
		if (this.getFluid().isIn(VeFluidTags.VOID))
		{
			Direction foundDirection = null;
			boolean fluidContactFlag = false;
			ITag.INamedTag<Fluid> fluidTag = null;
			
			//Find the direction that corresponds to where the lava or water is at.
			for(Direction direction : Direction.values())
			{
				if(worldIn.getFluidState(pos.offset(direction)).isTagged(FluidTags.WATER))
				{
					foundDirection = direction;
					fluidContactFlag = true;
					fluidTag = FluidTags.WATER;
					break;
				}
				else if(worldIn.getFluidState(pos.offset(direction)).isTagged(FluidTags.LAVA))
				{
					foundDirection = direction;
					fluidContactFlag = true;
					fluidTag = FluidTags.LAVA;
					break;
				}
			}
			
			if(fluidContactFlag)
			{
				if(fluidTag == FluidTags.WATER)
				{
					return generateBlocks(worldIn, pos, foundDirection, Blocks.END_STONE, VeBlocks.nephilite, Blocks.END_STONE);
				}
				else if(fluidTag == FluidTags.LAVA)
				{
					return generateBlocks(worldIn, pos, foundDirection, Blocks.END_STONE, VeBlocks.snowflake_obsidian, Blocks.END_STONE);
				}
			}
		}
		return true;
	}
	
	/*
	 * Generated the blocks from the fluids reacting with one another based off their position in the world.
	 */
	private boolean generateBlocks(World worldIn, BlockPos pos, Direction foundDirection, Block block1, Block block2, Block block3)
	{
		//If the lava is underneath the void liquid then replace it with first block.
		if(foundDirection == Direction.DOWN)
		{
			worldIn.setBlockState(pos.offset(foundDirection), ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, block1.getDefaultState()));
			this.triggerMixEffects(worldIn, pos);
			return true;
		}
		
		//If not check to see if its a source block and if so replace the void liquid with second block.
		else if(worldIn.getFluidState(pos).isSource())
		{
			worldIn.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, block2.getDefaultState()));
			this.triggerMixEffects(worldIn, pos);
			return true;
		}
		
		//If neither of these cases then place third block where the flowing void liquid is.
		else
		{
			worldIn.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, block3.getDefaultState()));
			this.triggerMixEffects(worldIn, pos);
			return true;
		}
	}
	
	/*
	 * Plays the smoke particles as the block is made.
	 */
	private void triggerMixEffects(IWorld worldIn, BlockPos pos)
	{
		Random random = new Random();
		
		worldIn.getWorld().playSound(null, pos, VeSoundEvents.BLOCK_VOID_HARDENS, SoundCategory.BLOCKS, random.nextFloat() * 0.2F + 1F, random.nextFloat() * 0.6F);
	}
	
	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
	{
		return false;
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if(entityIn instanceof LivingEntity && this.getFluid().isIn(VeFluidTags.VOID))
		{
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 2, true, true));
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 20, 5, true, true));
		}
	}
}
