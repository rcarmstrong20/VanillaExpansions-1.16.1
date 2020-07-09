package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.PlantBlockHelper;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;

public class VePlantingPotBlock extends Block implements IWaterLoggable
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape TOP_OUTSIDE_SHAPE = Block.makeCuboidShape(2.0, 8.0, 2.0, 14.0, 18.0, 14.0);
	private static final VoxelShape TOP_INSIDE_SHAPE = Block.makeCuboidShape(3.0, 16.0, 3.0, 13.0, 18.0, 13.0);
	private static final VoxelShape PILLAR_1_SHAPE = Block.makeCuboidShape(4.0, 1.0, 4.0, 6.0, 8.0, 6.0);
	private static final VoxelShape PILLAR_2_SHAPE = Block.makeCuboidShape(12.0, 1.0, 12.0, 10.0, 8.0, 10.0);
	private static final VoxelShape PILLAR_3_SHAPE = Block.makeCuboidShape(6.0, 1.0, 12.0, 4.0, 8.0, 10.0);
	private static final VoxelShape PILLAR_4_SHAPE = Block.makeCuboidShape(12.0, 1.0, 6.0, 10.0, 8.0, 4.0);
	private static final VoxelShape LIQUID_SHAPE = Block.makeCuboidShape(10.0, 1.0, 10.0, 6.0, 8.0, 6.0);
	private static final VoxelShape BASE_SHAPE = Block.makeCuboidShape(2.0, 0.0, 2.0, 14.0, 1.0, 14.0);
	
	protected static final VoxelShape TOP_SHAPE = VoxelShapes.combineAndSimplify(TOP_OUTSIDE_SHAPE, TOP_INSIDE_SHAPE, IBooleanFunction.ONLY_FIRST);
	
	private static final VoxelShape PILLARS_SHAPE = VoxelShapes.or(PILLAR_1_SHAPE, PILLAR_2_SHAPE, PILLAR_3_SHAPE, PILLAR_4_SHAPE);
	private static final VoxelShape PLANTING_POT_SHAPE = VoxelShapes.or(TOP_SHAPE, PILLARS_SHAPE, LIQUID_SHAPE, BASE_SHAPE);
	
	public VePlantingPotBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return PLANTING_POT_SHAPE;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
		BlockPos blockpos = context.getPos();
		FluidState ifluidstate = context.getWorld().getFluidState(blockpos);
		return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		if (stateIn.get(WATERLOGGED))
		{
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}
		return stateIn;
	}
	
	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
	}
	
	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable)
	{
		//PlantType plantType = plantable.getPlantType(world, pos);
		Block plantBlock = plantable.getPlant(world, pos).getBlock();
		if(VeBlockTags.NETHER_PLANTING_POTS.func_230235_a_(this.getBlock()) && VeBlockTags.NETHER_PLANTS.func_230235_a_(plantBlock))
		{
			return true;
		}
		else if(VeBlockTags.OVERWORLD_PLANTING_POTS.func_230235_a_(this.getBlock()) && VeBlockTags.OVERWORLD_PLANTS.func_230235_a_(plantBlock))
		{
			return true;
		}
		else if(VeBlockTags.END_PLANTING_POTS.func_230235_a_(this.getBlock()) && VeBlockTags.END_PLANTS.func_230235_a_(plantBlock))
		{
			return true;
		}
		return false;
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder)
	{
		builder.add(WATERLOGGED);
	}
}
