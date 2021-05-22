package rcarmstrong20.vanilla_expansions.events;

import java.util.Hashtable;

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
import rcarmstrong20.vanilla_expansions.config.VeCropConfig;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeItems;
import rcarmstrong20.vanilla_expansions.util.VeTimeUtil;

public class VePlayerInteractEvent
{
    public static final Hashtable<Item, Integer> TOTEM_FORTUNATE_TABLE = complileTotemTable(
            VeItems.totemOfTheFortunateI, VeItems.totemOfTheFortunateII, VeItems.totemOfTheFortunateIII,
            VeItems.totemOfTheFortunateIV);

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

    @SubscribeEvent
    public void onRightClickBlock(final PlayerInteractEvent.RightClickItem event)
    {
        Item item = event.getItemStack().getItem();
        PlayerEntity player = event.getPlayer();

        if (!player.getCooldowns().isOnCooldown(item) && TOTEM_FORTUNATE_TABLE.containsKey(item))
        {
            for (Item totem : TOTEM_FORTUNATE_TABLE.keySet())
            {
                player.getCooldowns().addCooldown(totem, VeTimeUtil.convertSecsToTicks(120));
            }
        }
    }

    /**
     * A helper method that constructs a hash table with 4 items.
     */
    private static Hashtable<Item, Integer> complileTotemTable(Item item0, Item item1, Item item2, Item item3)
    {
        Hashtable<Item, Integer> hashtable = new Hashtable<>(4);

        hashtable.put(item0, 0);
        hashtable.put(item1, 0);
        hashtable.put(item2, 0);
        hashtable.put(item3, 0);

        return hashtable;
    }
}