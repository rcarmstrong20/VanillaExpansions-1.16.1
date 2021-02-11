package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;
import rcarmstrong20.vanilla_expansions.util.VeShapeUtil;

public class VePlanterBoxBlock extends Block
{
    public static final BooleanProperty NORTH = SixWayBlock.NORTH;
    public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
    public static final BooleanProperty WEST = SixWayBlock.WEST;
    public static final BooleanProperty EAST = SixWayBlock.EAST;

    private static final VoxelShape INSIDE_SINGLE_SHAPE = Block.makeCuboidShape(3.0, 15.0, 3.0, 13.0, 16.0, 13.0);
    private static final VoxelShape PLANTER_SINGLE_BOX_SHAPE = VeShapeUtil.cutShape(VoxelShapes.fullCube(),
            INSIDE_SINGLE_SHAPE);

    private static final VoxelShape INSIDE_MIDDLE_SHAPE = Block.makeCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape PLANTER_MIDDLE_BOX_SHAPE = VeShapeUtil.cutShape(VoxelShapes.fullCube(),
            INSIDE_MIDDLE_SHAPE);

    private static final VoxelShape INSIDE_NORTH_SHAPE = Block.makeCuboidShape(3.0, 15.0, 0.0, 13.0, 16.0, 13.0);
    private static final VoxelShape PLANTER_NORTH_BOX_SHAPE = VeShapeUtil.cutShape(VoxelShapes.fullCube(),
            INSIDE_NORTH_SHAPE);
    private static final VoxelShape PLANTER_SOUTH_BOX_SHAPE = VeShapeUtil.rotate180(Axis.Y, PLANTER_NORTH_BOX_SHAPE);
    private static final VoxelShape PLANTER_WEST_BOX_SHAPE = VeShapeUtil.rotate270(Axis.Y, PLANTER_NORTH_BOX_SHAPE);
    private static final VoxelShape PLANTER_EAST_BOX_SHAPE = VeShapeUtil.rotate90(Axis.Y, PLANTER_NORTH_BOX_SHAPE);

    private static final VoxelShape INSIDE_NORTH_SOUTH_SHAPE = Block.makeCuboidShape(3.0, 15.0, 0.0, 13.0, 16.0, 16.0);
    private static final VoxelShape PLANTER_NORTH_SOUTH_BOX_SHAPE = VeShapeUtil.cutShape(VoxelShapes.fullCube(),
            INSIDE_NORTH_SOUTH_SHAPE);
    private static final VoxelShape PLANTER_EAST_WEST_BOX_SHAPE = VeShapeUtil.rotate90(Axis.Y,
            PLANTER_NORTH_SOUTH_BOX_SHAPE);

    private static final VoxelShape INSIDE_NORTH_EAST_SHAPE = Block.makeCuboidShape(3.0, 15.0, 0.0, 16.0, 16.0, 13.0);
    private static final VoxelShape PLANTER_NORTH_EAST_BOX_SHAPE = VeShapeUtil.cutShape(VoxelShapes.fullCube(),
            INSIDE_NORTH_EAST_SHAPE);

    private static final VoxelShape PLANTER_SOUTH_WEST_BOX_SHAPE = VeShapeUtil.rotate180(Axis.Y,
            PLANTER_NORTH_EAST_BOX_SHAPE);
    private static final VoxelShape PLANTER_NORTH_WEST_BOX_SHAPE = VeShapeUtil.rotate270(Axis.Y,
            PLANTER_NORTH_EAST_BOX_SHAPE);
    private static final VoxelShape PLANTER_SOUTH_EAST_BOX_SHAPE = VeShapeUtil.rotate90(Axis.Y,
            PLANTER_NORTH_EAST_BOX_SHAPE);

    private static final VoxelShape INSIDE_NORTH_WEST_EAST_SHAPE = Block.makeCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0,
            13.0);
    private static final VoxelShape PLANTER_NORTH_WEST_EAST_BOX_SHAPE = VeShapeUtil.cutShape(VoxelShapes.fullCube(),
            INSIDE_NORTH_WEST_EAST_SHAPE);

    private static final VoxelShape PLANTER_SOUTH_WEST_EAST_BOX_SHAPE = VeShapeUtil.rotate180(Axis.Y,
            PLANTER_NORTH_WEST_EAST_BOX_SHAPE);

    private static final VoxelShape PLANTER_NORTH_SOUTH_WEST_BOX_SHAPE = VeShapeUtil.rotate270(Axis.Y, // FINISHED
            PLANTER_NORTH_WEST_EAST_BOX_SHAPE);

    private static final VoxelShape PLANTER_NORTH_SOUTH_EAST_BOX_SHAPE = VeShapeUtil.rotate90(Axis.Y, // 1
            PLANTER_NORTH_WEST_EAST_BOX_SHAPE);

    public VePlanterBoxBlock(Properties properties)
    {
        super(properties);
    }

    /**
     * Creates the bounding box for this block.
     */
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        boolean north = state.get(NORTH);
        boolean south = state.get(SOUTH);
        boolean west = state.get(WEST);
        boolean east = state.get(EAST);

        if (north && south && west && east)
        {
            return PLANTER_MIDDLE_BOX_SHAPE;
        }
        else if (north)
        {
            if (east)
            {
                if (south)
                {
                    return PLANTER_NORTH_SOUTH_EAST_BOX_SHAPE;
                }
                else if (west)
                {
                    return PLANTER_NORTH_WEST_EAST_BOX_SHAPE;
                }
                else
                {
                    return PLANTER_NORTH_EAST_BOX_SHAPE;
                }
            }
            else if (west)
            {
                if (south)
                {
                    return PLANTER_NORTH_SOUTH_WEST_BOX_SHAPE;
                }
                else if (east)
                {
                    return PLANTER_NORTH_WEST_EAST_BOX_SHAPE;
                }
                else
                {
                    return PLANTER_NORTH_WEST_BOX_SHAPE;
                }
            }
            else if (south)
            {
                return PLANTER_NORTH_SOUTH_BOX_SHAPE;
            }
            else
            {
                return PLANTER_NORTH_BOX_SHAPE;
            }
        }
        else if (south)
        {
            if (east)
            {
                if (west)
                {
                    return PLANTER_SOUTH_WEST_EAST_BOX_SHAPE;
                }
                else
                {
                    return PLANTER_SOUTH_EAST_BOX_SHAPE;
                }
            }
            else if (west)
            {
                return PLANTER_SOUTH_WEST_BOX_SHAPE;
            }
            else
            {
                return PLANTER_SOUTH_BOX_SHAPE;
            }
        }
        else if (west)
        {
            if (east)
            {
                return PLANTER_EAST_WEST_BOX_SHAPE;
            }
            else
            {
                return PLANTER_WEST_BOX_SHAPE;
            }
        }
        else if (east)
        {
            return PLANTER_EAST_BOX_SHAPE;
        }
        return PLANTER_SINGLE_BOX_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockPos blockpos = context.getPos();
        World world = context.getWorld();

        return this.getDefaultState().with(NORTH, world.getBlockState(blockpos.north()).getBlock() == this)
                .with(SOUTH, world.getBlockState(blockpos.south()).getBlock() == this)
                .with(WEST, world.getBlockState(blockpos.west()).getBlock() == this)
                .with(EAST, world.getBlockState(blockpos.east()).getBlock() == this);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
            BlockPos currentPos, BlockPos facingPos)
    {
        return stateIn.with(NORTH, worldIn.getBlockState(currentPos.north()).getBlock() == this)
                .with(SOUTH, worldIn.getBlockState(currentPos.south()).getBlock() == this)
                .with(WEST, worldIn.getBlockState(currentPos.west()).getBlock() == this)
                .with(EAST, worldIn.getBlockState(currentPos.east()).getBlock() == this);
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
    private boolean isValid(Block soilBlock, Block plantBlock)
    {
        return VeBlockTags.netherPlantable.contains(this.getBlock()) && VeBlockTags.netherPottable.contains(plantBlock)
                || VeBlockTags.overworldPlantable.contains(this.getBlock())
                        && VeBlockTags.overworldPottable.contains(plantBlock)
                || VeBlockTags.endPlantable.contains(this.getBlock()) && VeBlockTags.endPottable.contains(plantBlock);
    }

    /**
     * Creates a list of properties that this block can have.
     */
    @Override
    protected void fillStateContainer(Builder<Block, BlockState> builder)
    {
        builder.add(NORTH, SOUTH, WEST, EAST);
    }
}
