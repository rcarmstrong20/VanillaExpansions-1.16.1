package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IPlantable;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;

public class VeCattailBlock extends DoublePlantBlock
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public VeCattailBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(
                this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(WATERLOGGED, false));
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader world, BlockPos pos)
    {
        return canSupportCattail(world, pos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
    {
        if (state.get(HALF) != DoubleBlockHalf.UPPER)
        {
            return canSupportCattail(world, pos);
        }
        else
        {
            BlockState blockstate = world.getBlockState(pos.down());
            if (state.getBlock() != this)
            {
                return canSupportCattail(world, pos);
            }
            return blockstate.isIn(this) && blockstate.get(HALF) == DoubleBlockHalf.LOWER;
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
            IPlantable plantable)
    {
        return canSupportCattail(world, pos);
    }

    /**
     * A helper method used for checking that the plant placement is valid.
     *
     * @param world The current world.
     * @param pos   The plants position.
     * @return True if the soil can support the plant.
     */
    private boolean canSupportCattail(IBlockReader world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.down()).getBlock();

        return VeBlockTags.cattailLandSoil.contains(block) || VeBlockTags.cattailWaterSoil.contains(block);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockPos blockpos = context.getPos();
        FluidState ifluidstate = context.getWorld().getFluidState(blockpos);
        Block block = context.getWorld().getBlockState(blockpos.down()).getBlock();
        BlockState state = context.getWorld().getBlockState(blockpos.up());

        if (VeBlockTags.cattailLandSoil.contains(block))
        {
            return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
        }
        else if (ifluidstate.isTagged(FluidTags.WATER) && isAir(state))
        {
            return this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
        }
        else
        {
            return null;
        }
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : Fluids.EMPTY.getDefaultState();
    }

    /**
     * Creates a list of properties that this block can have.
     */
    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(WATERLOGGED);
    }
}
