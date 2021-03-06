package rndmaccess.vanilla_expansions.events;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
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
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.config.VEConfig;
import rndmaccess.vanilla_expansions.core.VEBlocks;
import rndmaccess.vanilla_expansions.util.VETimeUtil;

public class VEPlayerInteractEvent
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
        BlockState worldState = world.getBlockState(pos);
        Block worldBlock = worldState.getBlock();
        ItemStack heldStack = event.getItemStack();
        Item heldItem = heldStack.getItem();
        PlayerEntity player = event.getPlayer();
        boolean smartHarvestFlag = VEConfig.enableSmartHarvest.get();

        if (!event.getWorld().isClientSide())
        {
            if (heldItem.equals(VEBlocks.snapdragon.asItem()) && worldState.getBlock() == Blocks.FLOWER_POT)
            {
                heldStack.shrink(1);
                world.setBlock(pos, VEBlocks.pottedSnapdragon.defaultBlockState(), 3);
                player.swing(Hand.MAIN_HAND, true);
                event.setCanceled(true);
            }
            if (smartHarvestFlag)
            {
                IntegerProperty ageSeven = BlockStateProperties.AGE_7;
                IntegerProperty ageThree = BlockStateProperties.AGE_3;
                IntegerProperty ageTwo = BlockStateProperties.AGE_2;

                if (worldBlock instanceof CropsBlock && heldItem != Items.BONE_MEAL)
                {
                    if (hasMaxAge(worldState, ageThree, 3))
                    {
                        resetCrop(worldState, world, pos, player, ageThree);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                    else if (hasMaxAge(worldState, ageSeven, 7))
                    {
                        resetCrop(worldState, world, pos, player, ageSeven);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
                else if (worldBlock instanceof NetherWartBlock)
                {
                    if (hasMaxAge(worldState, ageThree, 3))
                    {
                        resetCrop(worldState, world, pos, player, ageThree);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
                else if (hasMaxAge(worldState, ageTwo, 2))
                {
                    resetCrop(worldState, world, pos, player, ageTwo);
                    event.setResult(Result.ALLOW);
                    event.setCanceled(true);
                }
            }
            else
            {
                event.setResult(Result.DEFAULT);
            }
        }
    }

    private static boolean hasMaxAge(BlockState state, IntegerProperty ageProperty, int maxAge)
    {
        return state.hasProperty(ageProperty) && state.getValue(ageProperty).equals(maxAge);
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

    @SubscribeEvent
    public void onRightClickItem(final PlayerInteractEvent.RightClickItem event)
    {
        Item item = event.getItemStack().getItem();
        PlayerEntity player = event.getPlayer();

        addTotemCooldown(player, item, VanillaExpansions.FORTUNATE_TOTEM_MAP);
        addTotemCooldown(player, item, VanillaExpansions.BRUTE_TOTEM_MAP);
        addTotemCooldown(player, item, VanillaExpansions.GUARDIAN_TOTEM_MAP);
        addTotemCooldown(player, item, VanillaExpansions.MINER_TOTEM_MAP);
        addTotemCooldown(player, item, VanillaExpansions.PHANTOM_TOTEM_MAP);
    }

    private static void addTotemCooldown(PlayerEntity player, Item item, HashMap<Item, Integer> totemCooldownMap)
    {
        if (!player.getCooldowns().isOnCooldown(item) && totemCooldownMap.containsKey(item))
        {
            for (Entry<Item, Integer> totem : totemCooldownMap.entrySet())
            {
                player.getCooldowns().addCooldown(totem.getKey(), VETimeUtil.convertSecsToTicks(totem.getValue()));
            }
        }
    }
}
