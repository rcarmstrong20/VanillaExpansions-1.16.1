package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
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
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(PLUSH_STACK_SIZE, 1).setValue(WATERLOGGED, false));
    }

    public void placeTop(World world, BlockPos pos, BlockState state)
    {
        FluidState fluidstate = world.getFluidState(pos);
        boolean flag = fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8;

        if (state.getValue(PLUSH_STACK_SIZE).equals(2))
        {
            world.setBlock(pos, state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(PLUSH_STACK_SIZE, 3)
                    .setValue(WATERLOGGED, flag), 3);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockPos pos = context.getClickedPos();
        World world = context.getLevel();
        BlockState blockstate = world.getBlockState(pos);

        if (blockstate.getBlock() == this)
        {
            placeTop(world, pos.above(), blockstate);

            return blockstate.setValue(PLUSH_STACK_SIZE, Math.min(3, blockstate.getValue(PLUSH_STACK_SIZE) + 1));
        }
        else
        {
            return super.getStateForPlacement(context);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState stateIn, BlockItemUseContext useContext)
    {
        BlockPos pos = useContext.getClickedPos().above();
        BlockState state = useContext.getLevel().getBlockState(pos);
        int size = stateIn.getValue(PLUSH_STACK_SIZE);

        if (useContext.getItemInHand().getItem() == this.asItem())
        {
            return size < 2 || size < 3 && state.getMaterial().isReplaceable() ? true : false;
        }
        return false;
    }

    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (state.getValue(PLUSH_STACK_SIZE) == 3)
        {
            if (state.getValue(HALF) == DoubleBlockHalf.UPPER)
            {
                this.breakOtherBlock(world, pos.below(), player);
            }
            else if (state.getValue(HALF) == DoubleBlockHalf.LOWER)
            {
                this.breakOtherBlock(world, pos.above(), player);
            }
        }
        world.destroyBlock(pos, Boolean.valueOf(!player.isCreative()));
    }

    /**
     * A helper method that breaks the half that the player has not broken.
     *
     * @param world
     * @param otherPos The pos for the other half.
     * @param player   The player breaking the block.
     */
    protected void breakOtherBlock(World world, BlockPos otherPos, PlayerEntity player)
    {
        if (world.getFluidState(otherPos).is(FluidTags.WATER))
        {
            world.setBlock(otherPos, Blocks.WATER.defaultBlockState(), 35);
        }
        else
        {
            world.setBlock(otherPos, Blocks.AIR.defaultBlockState(), 35);
        }

        world.levelEvent(player, 2001, otherPos, Block.getId(world.getBlockState(otherPos)));
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        builder.add(FACING, WATERLOGGED, PLUSH_STACK_SIZE, HALF);
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
        switch (state.getValue(PLUSH_STACK_SIZE))
        {
            case 1:
                return stackSizeShapeBottom;
            case 2:
                return stackSizeShapeMiddle;
            default:
                return state.getValue(HALF) == DoubleBlockHalf.UPPER ? stackSizeShapeTop : stackSizeShapeMiddle;
        }
    }
}
