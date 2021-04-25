package rcarmstrong20.vanilla_expansions.block;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;

public class VeCattailBlock extends BushBlock implements IGrowable
{
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_3;

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
        World world = context.getWorld();
        FluidState fluidstate = world.getFluidState(blockpos);
        FluidState topFluidstate = world.getFluidState(blockpos.up());
        Block block = world.getBlockState(blockpos.down()).getBlock();

        if (VeBlockTags.cattailLandSoil.contains(block))
        {
            return this.getDefaultState().with(WATERLOGGED, isWater(fluidstate));
        }
        else if (fluidstate.isTagged(FluidTags.WATER) && !topFluidstate.isTagged(FluidTags.WATER))
        {
            return this.getDefaultState().with(WATERLOGGED, isWater(fluidstate));
        }
        else
        {
            return null;
        }
    }

    /**
     * @param fluidState
     * @return true if this block is filled with water.
     */
    protected boolean isWater(FluidState fluidState)
    {
        return Boolean.valueOf(fluidState.getFluid() == Fluids.WATER);
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
        builder.add(HALF, WATERLOGGED, AGE);
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
    {
        return canCattailGrow(worldIn, state, pos);
    }

    /**
     * A helper method that returns true if the cattail has room to grow.
     *
     * @param worldIn
     * @param state   The cattail.
     * @param pos     The cattails position.
     * @return Whether or not this cattail can grow.
     */
    public boolean canCattailGrow(IBlockReader worldIn, BlockState state, BlockPos pos)
    {
        BlockState stateTop = worldIn.getBlockState(pos.up());

        return !isMaxAge(state)
                && (isAir(stateTop) || (stateTop.hasProperty(HALF) && stateTop.get(HALF).equals(DoubleBlockHalf.UPPER)))
                && stateTop.getBlock() != Blocks.WATER;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
    {
        return false;
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state)
    {}

    /**
     * A helper method used to place cattails.
     *
     * @param worldIn
     * @param pos     The position of the plant.
     * @param i       The age for this plant.
     * @param half    The half property for the cattail, upper or lower.
     */
    protected void placeCattail(World worldIn, BlockPos pos, int i, DoubleBlockHalf half)
    {
        FluidState fluid = worldIn.getFluidState(pos);

        worldIn.setBlockState(pos, this.withAge(i).with(HALF, half).with(WATERLOGGED, isWater(fluid)), 2);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random)
    {
        if (!worldIn.isAreaLoaded(pos, 1))
            return;

        if (canCattailGrow(worldIn, state, pos) && worldIn.getLightSubtracted(pos, 0) >= 9)
        {
            int i = this.getAge(state);
            if (i < this.getMaxAge())
            {
                float f = getGrowthChance(this, worldIn, pos);
                if (ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0))
                {
                    if (Block.hasSolidSideOnTop(worldIn, pos.down()))
                    {
                        this.placeCattail(worldIn, pos, i + 1, DoubleBlockHalf.LOWER);
                        this.placeCattail(worldIn, pos.up(), i + 1, DoubleBlockHalf.UPPER);
                    }
                    else
                    {
                        pos = pos.down();
                    }
                    ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    /**
     * Cattails will grow faster in water and slower on land.
     *
     * @param blockIn The plant.
     * @param worldIn
     * @param pos     The position for the plant.
     * @return The growth chance.
     */
    protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos)
    {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.add(i, 0, j), Direction.UP, (IPlantable) blockIn))
                {
                    f1 = 1.0F;
                    if (worldIn.getFluidState(pos.add(i, 0, j)).isTagged(FluidTags.WATER))
                    {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock()
                || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock()
                || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock()
                    || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock()
                    || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock()
                    || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2)
            {
                f /= 2.0F;
            }
        }

        return f;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!worldIn.isRemote && state.get(HALF) == DoubleBlockHalf.UPPER)
        {
            worldIn.destroyBlock(pos.down(), !player.isCreative());
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public AbstractBlock.OffsetType getOffsetType()
    {
        return AbstractBlock.OffsetType.XZ;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public long getPositionRandom(BlockState state, BlockPos pos)
    {
        return MathHelper.getCoordinateRandom(pos.getX(),
                pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
    }

    @Override
    public boolean ticksRandomly(BlockState state)
    {
        return !this.isMaxAge(state);
    }

    protected int getAge(BlockState state)
    {
        return state.get(AGE);
    }

    protected int getMaxAge()
    {
        return 3;
    }

    public BlockState withAge(int age)
    {
        return this.getDefaultState().with(AGE, Integer.valueOf(age));
    }

    protected boolean isMaxAge(BlockState state)
    {
        return state.get(AGE) >= this.getMaxAge();
    }
}
