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
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.VeBlockStateProperties;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.util.VeCollisionUtil;

public class VeTallPlushBlock extends VePlushBlock
{
    // Block Properties

    public static final IntegerProperty PLUSH_STACK_SIZE = VeBlockStateProperties.PLUSH_STACK_SIZE_1_3;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    // One Magma Cube Bounding Boxes

    protected static final VoxelShape ONE_MAGMA_CUBE_BODY_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 10.0D,
            13.0D);
    protected static final VoxelShape ONE_MAGMA_CUBE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(10.0D, 5.0D, 3.0D,
            12.0D, 7.0D, 2.5D);
    protected static final VoxelShape ONE_MAGMA_CUBE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(4.0D, 5.0D, 3.0D,
            6.0D, 7.0D, 2.5D);
    protected static final VoxelShape ONE_MAGMA_CUBE_NORTH_EYES_SHAPES = VoxelShapes
            .or(ONE_MAGMA_CUBE_NORTH_LEFT_EYE_SHAPE, ONE_MAGMA_CUBE_NORTH_RIGHT_EYE_SHAPE);
    protected static final VoxelShape ONE_MAGMA_CUBE_NORTH_SHAPE = VoxelShapes.or(ONE_MAGMA_CUBE_BODY_SHAPE,
            ONE_MAGMA_CUBE_NORTH_EYES_SHAPES);

    protected static final VoxelShape ONE_MAGMA_CUBE_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y,
            ONE_MAGMA_CUBE_NORTH_SHAPE);
    protected static final VoxelShape ONE_MAGMA_CUBE_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y,
            ONE_MAGMA_CUBE_NORTH_SHAPE);
    protected static final VoxelShape ONE_MAGMA_CUBE_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y,
            ONE_MAGMA_CUBE_NORTH_SHAPE);

    // Two Magma Cube Bounding Box

    protected static final VoxelShape TWO_MAGMA_CUBE_BODY_SHAPE = Block.makeCuboidShape(4.0D, 8.0D, 4.0D, 12.0D, 17.0D,
            12.0D);
    protected static final VoxelShape TWO_MAGMA_CUBE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 14.0D, 4.0D,
            10.5D, 15.5D, 3.5D);
    protected static final VoxelShape TWO_MAGMA_CUBE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(5.0D, 14.0D, 4.0D,
            6.5D, 15.5D, 3.5D);
    protected static final VoxelShape TWO_MAGMA_CUBE_NORTH_EYES_SHAPE = VoxelShapes
            .or(TWO_MAGMA_CUBE_NORTH_LEFT_EYE_SHAPE, TWO_MAGMA_CUBE_NORTH_RIGHT_EYE_SHAPE);
    protected static final VoxelShape TWO_MAGMA_CUBE_NORTH_SHAPE = VoxelShapes.or(TWO_MAGMA_CUBE_BODY_SHAPE,
            TWO_MAGMA_CUBE_NORTH_EYES_SHAPE, ONE_MAGMA_CUBE_NORTH_SHAPE);

    protected static final VoxelShape TWO_MAGMA_CUBE_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y,
            TWO_MAGMA_CUBE_NORTH_SHAPE);
    protected static final VoxelShape TWO_MAGMA_CUBE_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y,
            TWO_MAGMA_CUBE_NORTH_SHAPE);
    protected static final VoxelShape TWO_MAGMA_CUBE_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y,
            TWO_MAGMA_CUBE_NORTH_SHAPE);

    // Three Magma Cube Bounding Box

    protected static final VoxelShape THREE_MAGMA_CUBE_BODY_SHAPE = Block.makeCuboidShape(6.0D, 1.0D, 6.0D, 10.0D, 5.0D,
            10.0D);
    protected static final VoxelShape THREE_MAGMA_CUBE_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.5D, 3.0D, 6.0D,
            7.5D, 4.0D, 5.5D);
    protected static final VoxelShape THREE_MAGMA_CUBE_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 3.0D, 6.0D,
            9.5D, 4.0D, 5.5D);
    protected static final VoxelShape THREE_MAGMA_CUBE_NORTH_EYES_SHAPE = VoxelShapes
            .or(THREE_MAGMA_CUBE_NORTH_LEFT_EYE_SHAPE, THREE_MAGMA_CUBE_NORTH_RIGHT_EYE_SHAPE);
    protected static final VoxelShape THREE_MAGMA_CUBE_NORTH_SHAPE = VoxelShapes.or(THREE_MAGMA_CUBE_BODY_SHAPE,
            THREE_MAGMA_CUBE_NORTH_EYES_SHAPE);

    protected static final VoxelShape THREE_MAGMA_CUBE_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y,
            THREE_MAGMA_CUBE_NORTH_SHAPE);
    protected static final VoxelShape THREE_MAGMA_CUBE_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y,
            THREE_MAGMA_CUBE_NORTH_SHAPE);
    protected static final VoxelShape THREE_MAGMA_CUBE_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y,
            THREE_MAGMA_CUBE_NORTH_SHAPE);

    // One Slime Bounding Boxes

    protected static final VoxelShape ONE_SLIME_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(7.0D, 2.0D, 3.0D, 6.0D, 3.0D,
            2.5D);
    protected static final VoxelShape ONE_SLIME_NORTH_SHAPE = VoxelShapes.or(ONE_MAGMA_CUBE_NORTH_SHAPE,
            ONE_SLIME_NORTH_MOUTH_SHAPE);

    protected static final VoxelShape ONE_SLIME_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, ONE_SLIME_NORTH_SHAPE);
    protected static final VoxelShape ONE_SLIME_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, ONE_SLIME_NORTH_SHAPE);
    protected static final VoxelShape ONE_SLIME_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, ONE_SLIME_NORTH_SHAPE);

    // Two Slime Bounding Boxes

    protected static final VoxelShape TWO_SLIME_NORTH_MOUTH_EYE_SHAPE = Block.makeCuboidShape(6.5D, 11.5D, 4.0D, 7.5D,
            12.5D, 3.5D);
    protected static final VoxelShape TWO_SLIME_NORTH_SHAPE = VoxelShapes.or(TWO_MAGMA_CUBE_NORTH_SHAPE,
            TWO_SLIME_NORTH_MOUTH_EYE_SHAPE, ONE_SLIME_NORTH_SHAPE);

    protected static final VoxelShape TWO_SLIME_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, TWO_SLIME_NORTH_SHAPE);
    protected static final VoxelShape TWO_SLIME_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, TWO_SLIME_NORTH_SHAPE);
    protected static final VoxelShape TWO_SLIME_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, TWO_SLIME_NORTH_SHAPE);

    // Three Slime Bouding Boxes

    protected static final VoxelShape THREE_SLIME_NORTH_MOUTH_EYE_SHAPE = Block.makeCuboidShape(7.0D, 2.0D, 6.0D, 7.5D,
            2.5D, 5.5D);
    protected static final VoxelShape THREE_SLIME_NORTH_SHAPE = VoxelShapes.or(THREE_MAGMA_CUBE_NORTH_SHAPE,
            THREE_SLIME_NORTH_MOUTH_EYE_SHAPE);

    protected static final VoxelShape THREE_SLIME_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y,
            THREE_SLIME_NORTH_SHAPE);
    protected static final VoxelShape THREE_SLIME_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y,
            THREE_SLIME_NORTH_SHAPE);
    protected static final VoxelShape THREE_SLIME_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y,
            THREE_SLIME_NORTH_SHAPE);

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
     * Creates the bounding box for this block.
     */
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        Block block = this.getBlock();

        if (block == VeBlocks.slimePlush)
        {
            switch (state.get(HORIZONTAL_FACING))
            {
                case NORTH:
                    return getStackSizeShapes(state, ONE_SLIME_NORTH_SHAPE, TWO_SLIME_NORTH_SHAPE,
                            THREE_SLIME_NORTH_SHAPE);
                case SOUTH:
                    return getStackSizeShapes(state, ONE_SLIME_SOUTH_SHAPE, TWO_SLIME_SOUTH_SHAPE,
                            THREE_SLIME_SOUTH_SHAPE);
                case WEST:
                    return getStackSizeShapes(state, ONE_SLIME_WEST_SHAPE, TWO_SLIME_WEST_SHAPE,
                            THREE_SLIME_WEST_SHAPE);
                default:
                    return getStackSizeShapes(state, ONE_SLIME_EAST_SHAPE, TWO_SLIME_EAST_SHAPE,
                            THREE_SLIME_EAST_SHAPE);
            }
        }
        else if (block == VeBlocks.magmaCubePlush)
        {
            switch (state.get(HORIZONTAL_FACING))
            {
                case NORTH:
                    return getStackSizeShapes(state, ONE_MAGMA_CUBE_NORTH_SHAPE, TWO_MAGMA_CUBE_NORTH_SHAPE,
                            THREE_MAGMA_CUBE_NORTH_SHAPE);
                case SOUTH:
                    return getStackSizeShapes(state, ONE_MAGMA_CUBE_SOUTH_SHAPE, TWO_MAGMA_CUBE_SOUTH_SHAPE,
                            THREE_MAGMA_CUBE_SOUTH_SHAPE);
                case WEST:
                    return getStackSizeShapes(state, ONE_MAGMA_CUBE_WEST_SHAPE, TWO_MAGMA_CUBE_WEST_SHAPE,
                            THREE_MAGMA_CUBE_WEST_SHAPE);
                default:
                    return getStackSizeShapes(state, ONE_MAGMA_CUBE_EAST_SHAPE, TWO_MAGMA_CUBE_EAST_SHAPE,
                            THREE_MAGMA_CUBE_EAST_SHAPE);
            }
        }
        else
        {
            return VoxelShapes.fullCube();
        }
    }

    /**
     * @param state              The current state this block is in.
     * @param stackSizeShape1    The shape for stack size 1.
     * @param stackSizeShape2    The shape for stack size 2.
     * @param stackSizeShapeTop3 The shape for the top of stack size 3.
     * @return The appropriate bounding box for the given state.
     */
    private static VoxelShape getStackSizeShapes(BlockState state, VoxelShape stackSizeShape1,
            VoxelShape stackSizeShape2, VoxelShape stackSizeShapeTop3)
    {
        switch (state.get(PLUSH_STACK_SIZE))
        {
            case 1:
                return stackSizeShape1;
            case 2:
                return stackSizeShape2;
            default:
                return state.get(HALF) == DoubleBlockHalf.UPPER ? stackSizeShapeTop3 : stackSizeShape2;
        }
    }
}
