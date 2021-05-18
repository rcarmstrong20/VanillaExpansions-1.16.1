package rcarmstrong20.vanilla_expansions.events;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.config.VeCropConfig;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;

public class VePlayerInteractEvent
{
    /**
     * Controls right-click crop harvesting and snapdragon potting.
     *
     * @param event Called when the player right-clicks a block.
     */
    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickBlock event)
    {
        BlockPos pos = event.getPos();
        World world = event.getWorld();
        BlockState state = event.getWorld().getBlockState(pos);
        ItemStack itemStack = event.getItemStack();
        PlayerEntity player = event.getPlayer();
        IntegerProperty ageSeven = BlockStateProperties.AGE_7;
        IntegerProperty ageThree = BlockStateProperties.AGE_3;
        IntegerProperty ageTwo = BlockStateProperties.AGE_2;
        boolean flag = VeCropConfig.VeBlockConfig.enableSmartHarvest.get();

        if (!event.getWorld().isClientSide())
        {
            if (itemStack.getItem().equals(VeBlocks.snapdragon.asItem()) && state.getBlock() == Blocks.FLOWER_POT)
            {
                itemStack.shrink(1);
                world.setBlock(pos, VeBlocks.pottedSnapdragon.defaultBlockState(), 3);
                player.swing(Hand.MAIN_HAND, true);
                event.setCanceled(true);
            }
            if (flag)
            {
                if (state.getBlock() instanceof CropsBlock && itemStack.getItem() != Items.BONE_MEAL)
                {
                    if (state.hasProperty(ageThree))
                    {
                        if (state.getValue(ageThree).equals(3))
                        {
                            resetCrop(state, world, pos, player, ageThree);
                            event.setResult(Result.ALLOW);
                            event.setCanceled(true);
                        }
                    }
                    else if (state.hasProperty(ageSeven))
                    {
                        if (state.getValue(ageSeven).equals(7))
                        {
                            resetCrop(state, world, pos, player, ageSeven);
                            event.setResult(Result.ALLOW);
                            event.setCanceled(true);
                        }
                    }
                }
                else if (state.getBlock() instanceof NetherWartBlock && state.hasProperty(ageThree))
                {
                    if (state.getValue(ageThree).equals(3))
                    {
                        resetCrop(state, world, pos, player, ageThree);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
                else if (state.hasProperty(ageTwo))
                {
                    if (state.getValue(ageTwo).equals(2))
                    {
                        resetCrop(state, world, pos, player, ageTwo);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
            }
            else
            {
                event.setResult(Result.DEFAULT);
            }
        }
    }

    /**
     * A helper method that harvests the passed in crop.
     *
     * @param state The state of the crop to harvest.
     * @param world The current world.
     * @param pos   The position for the crop to harvest.
     * @param age   The age property for this crop.
     */
    private static void resetCrop(BlockState state, World world, BlockPos pos, PlayerEntity player, IntegerProperty age)
    {
        Block.updateOrDestroy(state, Blocks.AIR.defaultBlockState(), world, pos, 1);
        world.setBlock(pos, state.setValue(age, 0), 2);
        player.swing(Hand.MAIN_HAND, true);
    }
}
