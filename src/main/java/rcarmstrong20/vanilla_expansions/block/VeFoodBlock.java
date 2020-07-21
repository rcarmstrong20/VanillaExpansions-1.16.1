package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class VeFoodBlock extends CakeBlock
{
    private final int foodLevel;
    private final float foodSaturation;

    public VeFoodBlock(int foodLevel, float foodSaturation, Block.Properties properties)
    {
        super(properties);
        this.foodLevel = foodLevel;
        this.foodSaturation = foodSaturation;
    }

    /**
     * Called when the player right-clicks a block.
     */
    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
            Hand hand, BlockRayTraceResult BlockRayTrace)
    {
        if (world.isRemote)
        {
            ItemStack itemstack = player.getHeldItem(hand);

            if (this.eatBlock(world, pos, state, player) == ActionResultType.SUCCESS)
            {
                return ActionResultType.SUCCESS;
            } else if (itemstack.isEmpty())
            {
                return ActionResultType.CONSUME;
            }
        }
        return this.eatBlock(world, pos, state, player);
    }

    /**
     * If the block can be eaten add the appropriate hunger and when it has one
     * slice left remove it from the world.
     * 
     * @param world  This world.
     * @param pos    The block's position.
     * @param state  The state for this block.
     * @param player The player eating the block.
     * @return Whether the block was eaten ActionResultType.SUCCESS or
     *         ActionResultType.PASS.
     */
    private ActionResultType eatBlock(IWorld world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!player.canEat(false))
        {
            return ActionResultType.PASS;
        } else
        {
            player.getFoodStats().addStats(foodLevel, foodSaturation);
            int bites = state.get(BITES);

            if (bites < 6)
            {
                world.setBlockState(pos, state.with(BITES, Integer.valueOf(bites + 1)), 3);
            } else
            {
                world.removeBlock(pos, false);
            }
            return ActionResultType.SUCCESS;
        }
    }
}
