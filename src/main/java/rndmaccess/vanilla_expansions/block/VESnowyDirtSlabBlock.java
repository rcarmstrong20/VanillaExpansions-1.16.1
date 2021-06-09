package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import rndmaccess.vanilla_expansions.block.util.VESlabBlockUtil;

public class VESnowyDirtSlabBlock extends VESoilSlabBlock
{
    public static final BooleanProperty SNOWY = BlockStateProperties.SNOWY;

    public VESnowyDirtSlabBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(SNOWY, false));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, IWorld world, BlockPos pos,
            BlockPos pos2)
    {
        boolean flag = state2.is(Blocks.SNOW_BLOCK) || state2.is(Blocks.SNOW);

        if (direction != Direction.UP || VESlabBlockUtil.isBottom(state))
        {
            return state;
        }
        else
        {
            return state.setValue(SNOWY, Boolean.valueOf(flag));
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        World level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        BlockState stateAt = level.getBlockState(clickedPos);
        BlockState stateAbove = level.getBlockState(clickedPos.above());
        boolean flag = stateAbove.is(Blocks.SNOW_BLOCK) || stateAbove.is(Blocks.SNOW);

        if (stateAt.is(this))
        {
            return stateAt.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, false).setValue(SNOWY, flag);
        }
        else
        {
            Fluid fluid = level.getFluidState(clickedPos).getType();
            BlockState grassSlab = this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED,
                    fluid.equals(Fluids.WATER));
            Direction direction = context.getClickedFace();

            return direction != Direction.DOWN
                    && (direction == Direction.UP || !(context.getClickLocation().y - clickedPos.getY() > 0.5D))
                            ? grassSlab
                            : grassSlab.setValue(TYPE, SlabType.TOP).setValue(SNOWY, flag);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(SNOWY);
    }
}
