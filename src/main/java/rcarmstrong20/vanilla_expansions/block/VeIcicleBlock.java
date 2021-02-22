package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class VeIcicleBlock extends FallingBlock
{
    private static final VoxelShape ICICLE_SHAPE = Block.makeCuboidShape(4.0, 2.0, 4.0, 12.0, 16.0, 12.0);

    public VeIcicleBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return ICICLE_SHAPE;
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        if (worldIn.getBlockState(pos.up()).isSolid())
        {
            return;
        }
        else
        {
            super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        World world = context.getWorld();
        BlockPos pos = context.getPos();

        // Used to keep the player from placing icicle's without supporting blocks.
        if (world.getBlockState(pos.up()).isSolid())
        {
            return super.getStateForPlacement(context);
        }
        else
        {
            return world.getBlockState(pos);
        }
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
            BlockPos currentPos, BlockPos facingPos)
    {
        // In this case the icicle could be supported so do nothing.
        if (worldIn.getBlockState(currentPos.up()).isSolid())
        {
            return stateIn;
        }
        // In this case the the icicle can't fall but isn't supported so harvest it.
        else if (worldIn.getBlockState(currentPos.down()) != Blocks.AIR.getDefaultState())
        {
            return Blocks.AIR.getDefaultState();
        }
        // If neither of the previous are true then make the icicle fall.
        else
        {
            return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    /**
     * Called when a falling block hits the ground.
     */
    @Override
    public void onEndFalling(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState,
            FallingBlockEntity fallingBlock)
    {
        PlayerEntity player = worldIn.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.1, false);
        float blocksFallen = fallingBlock.fallTime / 3;
        float fallMult = blocksFallen / 10; // When the block has fallen more than 10 blocks the damage grows.

        // Damages the player when underneath the icicle.
        if (player != null)
        {
            player.attackEntityFrom(DamageSource.GENERIC, blocksFallen * fallMult);
        }

        Block.replaceBlock(this.getBlock().getDefaultState(), Blocks.AIR.getDefaultState(), worldIn, pos, 1);

        System.out.println(blocksFallen * fallMult);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.attackEntityFrom(DamageSource.GENERIC, 0.5F);
    }
}
