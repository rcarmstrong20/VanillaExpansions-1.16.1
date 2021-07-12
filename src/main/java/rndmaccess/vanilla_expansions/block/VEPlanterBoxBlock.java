package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import rndmaccess.vanilla_expansions.core.VEBlockTags;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VEPlanterBoxBlock extends Block
{
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;

    private static final VoxelShape INSIDE_SINGLE_SHAPE = Block.box(3.0, 15.0, 3.0, 13.0, 16.0, 13.0);
    private static final VoxelShape SINGLE_BOX_SHAPE = VEBoxUtil.cutBox(VoxelShapes.block(), INSIDE_SINGLE_SHAPE);

    private static final VoxelShape INSIDE_MIDDLE_SHAPE = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape MIDDLE_BOX_SHAPE = VEBoxUtil.cutBox(VoxelShapes.block(), INSIDE_MIDDLE_SHAPE);

    private static final VoxelShape INSIDE_NORTH_SHAPE = Block.box(3.0, 15.0, 0.0, 13.0, 16.0, 13.0);
    private static final VoxelShape NORTH_BOX_SHAPE = VEBoxUtil.cutBox(VoxelShapes.block(), INSIDE_NORTH_SHAPE);
    private static final VoxelShape SOUTH_BOX_SHAPE = VEBoxUtil.rotate180(NORTH_BOX_SHAPE);
    private static final VoxelShape WEST_BOX_SHAPE = VEBoxUtil.rotate270(NORTH_BOX_SHAPE);
    private static final VoxelShape EAST_BOX_SHAPE = VEBoxUtil.rotate90(NORTH_BOX_SHAPE);

    private static final VoxelShape INSIDE_NORTH_SOUTH_SHAPE = Block.box(3.0, 15.0, 0.0, 13.0, 16.0, 16.0);
    private static final VoxelShape NORTH_SOUTH_BOX_SHAPE = VEBoxUtil.cutBox(VoxelShapes.block(),
            INSIDE_NORTH_SOUTH_SHAPE);
    private static final VoxelShape EAST_WEST_BOX_SHAPE = VEBoxUtil.rotate90(NORTH_SOUTH_BOX_SHAPE);

    private static final VoxelShape INSIDE_NORTH_EAST_SHAPE = Block.box(3.0, 15.0, 0.0, 16.0, 16.0, 13.0);
    private static final VoxelShape NORTH_EAST_BOX_SHAPE = VEBoxUtil.cutBox(VoxelShapes.block(),
            INSIDE_NORTH_EAST_SHAPE);

    private static final VoxelShape SOUTH_WEST_BOX_SHAPE = VEBoxUtil.rotate180(NORTH_EAST_BOX_SHAPE);
    private static final VoxelShape NORTH_WEST_BOX_SHAPE = VEBoxUtil.rotate270(NORTH_EAST_BOX_SHAPE);
    private static final VoxelShape SOUTH_EAST_BOX_SHAPE = VEBoxUtil.rotate90(NORTH_EAST_BOX_SHAPE);

    private static final VoxelShape INSIDE_NORTH_WEST_EAST_SHAPE = Block.box(0.0, 15.0, 0.0, 16.0, 16.0, 13.0);
    private static final VoxelShape NORTH_WEST_EAST_BOX_SHAPE = VEBoxUtil.cutBox(VoxelShapes.block(),
            INSIDE_NORTH_WEST_EAST_SHAPE);

    private static final VoxelShape SOUTH_WEST_EAST_BOX_SHAPE = VEBoxUtil.rotate180(NORTH_WEST_EAST_BOX_SHAPE);

    private static final VoxelShape NORTH_SOUTH_WEST_BOX_SHAPE = VEBoxUtil.rotate270(NORTH_WEST_EAST_BOX_SHAPE);

    private static final VoxelShape NORTH_SOUTH_EAST_BOX_SHAPE = VEBoxUtil.rotate90(NORTH_WEST_EAST_BOX_SHAPE);

    public VEPlanterBoxBlock(Properties properties)
    {
        super(properties);

        this.registerDefaultState(this.defaultBlockState().setValue(NORTH, false).setValue(SOUTH, false)
                .setValue(WEST, false).setValue(EAST, false));
    }

    /**
     * Creates the bounding box for this block.
     */
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        boolean north = state.getValue(NORTH);
        boolean south = state.getValue(SOUTH);
        boolean west = state.getValue(WEST);
        boolean east = state.getValue(EAST);

        if (north && south && west && east)
        {
            return MIDDLE_BOX_SHAPE;
        }
        else if (north)
        {
            if (east)
            {
                if (south)
                {
                    return NORTH_SOUTH_EAST_BOX_SHAPE;
                }
                else if (west)
                {
                    return NORTH_WEST_EAST_BOX_SHAPE;
                }
                else
                {
                    return NORTH_EAST_BOX_SHAPE;
                }
            }
            else if (west)
            {
                if (south)
                {
                    return NORTH_SOUTH_WEST_BOX_SHAPE;
                }
                else if (east)
                {
                    return NORTH_WEST_EAST_BOX_SHAPE;
                }
                else
                {
                    return NORTH_WEST_BOX_SHAPE;
                }
            }
            else if (south)
            {
                return NORTH_SOUTH_BOX_SHAPE;
            }
            else
            {
                return NORTH_BOX_SHAPE;
            }
        }
        else if (south)
        {
            if (east)
            {
                if (west)
                {
                    return SOUTH_WEST_EAST_BOX_SHAPE;
                }
                else
                {
                    return SOUTH_EAST_BOX_SHAPE;
                }
            }
            else if (west)
            {
                return SOUTH_WEST_BOX_SHAPE;
            }
            else
            {
                return SOUTH_BOX_SHAPE;
            }
        }
        else if (west)
        {
            if (east)
            {
                return EAST_WEST_BOX_SHAPE;
            }
            else
            {
                return WEST_BOX_SHAPE;
            }
        }
        else if (east)
        {
            return EAST_BOX_SHAPE;
        }
        return SINGLE_BOX_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockPos blockpos = context.getClickedPos();
        World world = context.getLevel();

        return this.defaultBlockState().setValue(NORTH, world.getBlockState(blockpos.north()).getBlock() == this)
                .setValue(SOUTH, world.getBlockState(blockpos.south()).getBlock() == this)
                .setValue(WEST, world.getBlockState(blockpos.west()).getBlock() == this)
                .setValue(EAST, world.getBlockState(blockpos.east()).getBlock() == this);
    }

    /**
     * When a structure is rotated with this block in it. This method decides what
     * state each block should be in.
     */
    @Override
    public BlockState rotate(BlockState state, Rotation rotation)
    {
        BlockState rotatedState = this.defaultBlockState();
        boolean north = state.getValue(NORTH);
        boolean south = state.getValue(SOUTH);
        boolean west = state.getValue(WEST);
        boolean east = state.getValue(EAST);
        boolean hasNone = north && south && west && east;
        boolean hasAll = !north && !south && !west && !east;

        // If the block is surrounded by edges or has no edges. Then we don't have
        // to rotate it.
        if (!(hasAll || hasNone))
        {
            switch (rotation)
            {
                case CLOCKWISE_180:
                    if (south)
                    {
                        rotatedState = setStateValueTrue(rotatedState, NORTH);
                    }
                    if (north)
                    {
                        rotatedState = setStateValueTrue(rotatedState, SOUTH);
                    }
                    if (east)
                    {
                        rotatedState = setStateValueTrue(rotatedState, WEST);
                    }
                    if (west)
                    {
                        rotatedState = setStateValueTrue(rotatedState, EAST);
                    }
                    return rotatedState;
                case CLOCKWISE_90:
                    if (south)
                    {
                        rotatedState = setStateValueTrue(rotatedState, WEST);
                    }
                    if (north)
                    {
                        rotatedState = setStateValueTrue(rotatedState, EAST);
                    }
                    if (east)
                    {
                        rotatedState = setStateValueTrue(rotatedState, SOUTH);
                    }
                    if (west)
                    {
                        rotatedState = setStateValueTrue(rotatedState, NORTH);
                    }
                    return rotatedState;
                case COUNTERCLOCKWISE_90:
                    if (south)
                    {
                        rotatedState = setStateValueTrue(rotatedState, EAST);
                    }
                    if (north)
                    {
                        rotatedState = setStateValueTrue(rotatedState, WEST);
                    }
                    if (east)
                    {
                        rotatedState = setStateValueTrue(rotatedState, NORTH);
                    }
                    if (west)
                    {
                        rotatedState = setStateValueTrue(rotatedState, SOUTH);
                    }
                    return rotatedState;
                default:
                    return state;
            }
        }
        return state;
    }

    /**
     * @param state    The state to change.
     * @param property The boolean property to change to true.
     * @return The state with the property set to true.
     */
    private static BlockState setStateValueTrue(BlockState state, BooleanProperty property)
    {
        return state.setValue(property, true);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
            BlockPos currentPos, BlockPos facingPos)
    {
        return stateIn.setValue(NORTH, worldIn.getBlockState(currentPos.north()).getBlock() == this)
                .setValue(SOUTH, worldIn.getBlockState(currentPos.south()).getBlock() == this)
                .setValue(WEST, worldIn.getBlockState(currentPos.west()).getBlock() == this)
                .setValue(EAST, worldIn.getBlockState(currentPos.east()).getBlock() == this);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
            IPlantable plantable)
    {
        Block plantBlock = plantable.getPlant(world, pos).getBlock();
        return isValid(this.getBlock(), plantBlock);
    }

    /**
     * A helper method used for checking that the plant placement is valid.
     */
    private static boolean isValid(Block soilBlock, Block plantBlock)
    {
        return VEBlockTags.netherBoxes.contains(soilBlock) && VEBlockTags.netherBoxable.contains(plantBlock)
                || VEBlockTags.overworldBoxes.contains(soilBlock) && VEBlockTags.overworldBoxable.contains(plantBlock)
                || VEBlockTags.endBoxes.contains(soilBlock) && VEBlockTags.endBoxable.contains(plantBlock);
    }

    @Override
    public boolean isFertile(BlockState state, IBlockReader world, BlockPos pos)
    {
        return true;
    }

    /**
     * Creates a list of properties that this block can have.
     */
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        builder.add(NORTH, SOUTH, WEST, EAST);
    }
}
