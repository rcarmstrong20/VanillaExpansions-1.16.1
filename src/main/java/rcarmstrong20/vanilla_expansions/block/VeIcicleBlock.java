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
    private static final VoxelShape ICICLE_SHAPE = Block.box(4.0, 1.0, 4.0, 12.0, 16.0, 12.0);

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
    public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
    {
        if (worldIn.getBlockState(pos.above()).isCollisionShapeFullBlock(worldIn, pos))
        {
            return;
        }
        else
        {
            super.onPlace(state, worldIn, pos, oldState, isMoving);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();

        // Used to keep the player from placing icicle's without supporting blocks.
        if (world.getBlockState(pos.above()).isCollisionShapeFullBlock(world, pos))
        {
            return super.getStateForPlacement(context);
        }
        else
        {
            return null;
        }
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
            BlockPos currentPos, BlockPos facingPos)
    {
        // In this case the icicle could be supported so do nothing.
        if (worldIn.getBlockState(currentPos.above()).isCollisionShapeFullBlock(worldIn, currentPos))
        {
            return stateIn;
        }
        // In this case the the icicle can't fall but isn't supported so harvest it.
        else if (worldIn.getBlockState(currentPos.below()) != Blocks.AIR.defaultBlockState())
        {
            return Blocks.AIR.defaultBlockState();
        }
        // If neither of the previous are true then make the icicle fall.
        else
        {
            return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    /**
     * Called when a falling block hits the ground.
     */
    @Override
    public void onLand(World worldIn, BlockPos pos, BlockState fallingState, BlockState hitState,
            FallingBlockEntity fallingBlock)
    {
        PlayerEntity player = worldIn.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 1.1, false);
        float blocksFallen = fallingBlock.time / 3.0F;
        float fallMult = blocksFallen / 10.0F; // When the block has fallen more than 10 blocks the damage grows.

        // Damages the player when underneath the icicle.
        if (player != null)
        {
            player.hurt(DamageSource.GENERIC, blocksFallen * fallMult);
        }

        worldIn.destroyBlock(pos, false);
    }

    @Override
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        entityIn.hurt(DamageSource.GENERIC, 0.5F);
    }
}
