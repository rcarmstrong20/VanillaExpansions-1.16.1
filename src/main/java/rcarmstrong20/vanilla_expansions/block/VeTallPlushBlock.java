package rcarmstrong20.vanilla_expansions.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.VeBlockStateProperties;

/**
 * A base class designed to help create tall plush blocks.
 *
 * @author Ryan
 *
 */
public class VeTallPlushBlock extends VePlushBlock
{
    public static final IntegerProperty PLUSH_STACK_SIZE = VeBlockStateProperties.PLUSH_STACK_SIZE_1_3;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public VeTallPlushBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(
                this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(PLUSH_STACK_SIZE, 1));
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack)
    {
        BlockPos posUp = pos.up();
        FluidState fluidState = world.getFluidState(posUp);
        boolean flag = fluidState.isTagged(FluidTags.WATER) && fluidState.getLevel() == 8;

        if (state.get(PLUSH_STACK_SIZE) == 3)
        {
            world.setBlockState(posUp, state.with(HALF, DoubleBlockHalf.UPPER).with(WATERLOGGED, flag), 3);
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState blockstate = context.getWorld().getBlockState(context.getPos());
        if (blockstate.getBlock() == this)
        {
            return blockstate.with(PLUSH_STACK_SIZE,
                    Integer.valueOf(Math.min(3, blockstate.get(PLUSH_STACK_SIZE) + 1)));
        }
        else
        {
            FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
            boolean flag = fluidstate.isTagged(FluidTags.WATER) && fluidstate.getLevel() == 8;

            return super.getStateForPlacement(context).with(WATERLOGGED, Boolean.valueOf(flag));
        }
    }

    @Override
    public boolean isReplaceable(BlockState state, BlockItemUseContext useContext)
    {
        BlockPos pos = useContext.getPos().up();
        BlockState worldState = useContext.getWorld().getBlockState(pos);

        if (useContext.getItem().getItem() == this.asItem())
        {
            return state.get(PLUSH_STACK_SIZE) < 3
                    && (VanillaExpansions.isAir(worldState) || VanillaExpansions.isLiquid(worldState)) ? true : false;
        }
        return false;
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (state.get(PLUSH_STACK_SIZE) == 3)
        {
            if (state.get(HALF) == DoubleBlockHalf.UPPER)
            {
                world.destroyBlock(pos.down(), false);
            }
            else if (state.get(HALF) == DoubleBlockHalf.LOWER)
            {
                world.destroyBlock(pos.up(), false);
            }
        }
        world.destroyBlock(pos, Boolean.valueOf(!player.isCreative()));
    }

    /**
     * Creates a list of properties that this block can have.
     */
    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder)
    {
        builder.add(HORIZONTAL_FACING, WATERLOGGED, PLUSH_STACK_SIZE, HALF);
    }

    /**
     * @param state                The current state this block is in.
     * @param stackSizeShapeBottom The shape for stack size 1.
     * @param stackSizeShapeMiddle The shape for stack size 2.
     * @param stackSizeShapeTop    The shape for the top of stack size 3.
     * @return The appropriate bounding box for the given state.
     */
    protected VoxelShape getStackSizeShapes(BlockState state, VoxelShape stackSizeShapeBottom,
            VoxelShape stackSizeShapeMiddle, VoxelShape stackSizeShapeTop)
    {
        switch (state.get(PLUSH_STACK_SIZE))
        {
            case 1:
                return stackSizeShapeBottom;
            case 2:
                return stackSizeShapeMiddle;
            default:
                return state.get(HALF) == DoubleBlockHalf.UPPER ? stackSizeShapeTop : stackSizeShapeMiddle;
        }
    }
}
