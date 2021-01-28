package rcarmstrong20.vanilla_expansions.core;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;

/**
 * A class for holding and creating every villager trade instance that vanilla
 * expansions has.
 * 
 * @author Ryan
 */
public class VeVillagerTrades
{
    public static ImmutableMap<Integer, List<ITrade>> lumberjackTrades = ImmutableMap.of(1,
            Arrays.asList(itemForEmerald(new ItemStack(Items.APPLE), 16, 2),
                    itemForEmerald(new ItemStack(VeItems.spruceCone), 10, 2)),
            2,
            Arrays.asList(new BasicTrade(2, new ItemStack(Items.GOLDEN_AXE), 6, 10, 0.02F),
                    new BasicTrade(3, new ItemStack(Items.IRON_AXE), 3, 15, 0.02F)),
            3,
            Arrays.asList(
                    itemAndEmeraldToItem(new ItemStack(Blocks.OAK_LOG, 5), new ItemStack(Items.CHARCOAL, 5), 5, 15),
                    itemAndEmeraldToItem(new ItemStack(Blocks.BIRCH_LOG, 5), new ItemStack(Items.CHARCOAL, 5), 5, 15),
                    itemAndEmeraldToItem(new ItemStack(Blocks.JUNGLE_LOG, 5), new ItemStack(Items.CHARCOAL, 5), 5, 15),
                    itemAndEmeraldToItem(new ItemStack(Blocks.ACACIA_LOG, 5), new ItemStack(Items.CHARCOAL, 5), 5, 15),
                    itemAndEmeraldToItem(new ItemStack(Blocks.DARK_OAK_LOG, 5), new ItemStack(Items.CHARCOAL, 5), 5,
                            15)),
            4,
            Arrays.asList(new BasicTrade(1, new ItemStack(Blocks.OAK_SAPLING, 3), 5, 20, 0.05F),
                    new BasicTrade(1, new ItemStack(Blocks.BIRCH_SAPLING, 3), 5, 20, 0.05F),
                    new BasicTrade(1, new ItemStack(Blocks.JUNGLE_SAPLING, 5), 5, 20, 0.05F),
                    new BasicTrade(1, new ItemStack(Blocks.ACACIA_SAPLING, 5), 5, 20, 0.05F),
                    new BasicTrade(1, new ItemStack(Blocks.DARK_OAK_SAPLING, 10), 5, 20, 0.05F)),
            5, Arrays.asList(master(1, new ItemStack(Blocks.NOTE_BLOCK), 10, 0.05F),
                    master(5, new ItemStack(Blocks.JUKEBOX), 5, 0.05F)));

    /**
     * Create villager trades that convert an item to an emerald.
     *
     * @param price     The item to trade.
     * @param maxTrades The number of times the player can trade before it locks.
     * @param xp        The amount of xp this villager receives for trading.
     * @return A new villager trade.
     */
    private static VillagerTrades.ITrade itemForEmerald(ItemStack price, int maxTrades, int xp)
    {
        return new BasicTrade(price, new ItemStack(Items.EMERALD), maxTrades, xp, 0.05F);
    }

    /**
     * Create villager trades that convert a material and emerald to make a new
     * item. Example, (wood and emerald) -> coal.
     *
     * @param price     The resource to trade.
     * @param forSale   The item that is being sold.
     * @param maxTrades The number of times the player can trade before it locks.
     * @param xp        The amount of xp this villager receives for trading.
     * @return A new villager trade.
     */
    private static VillagerTrades.ITrade itemAndEmeraldToItem(ItemStack price, ItemStack forSale, int maxTrades, int xp)
    {
        return new BasicTrade(price, new ItemStack(Items.EMERALD), forSale, maxTrades, xp, 0.05F);
    }

    /**
     * Create villager trades for the master level.
     *
     * @param emeralds  Number of emeralds this item goes for.
     * @param forSale   The item that is being sold.
     * @param maxTrades The number of times the player can trade before it locks.
     * @param priceMult The price multiplier for this trade.
     * @return A new villager trade.
     */
    private static VillagerTrades.ITrade master(int emeralds, ItemStack forSale, int maxTrades, float priceMult)
    {
        return new BasicTrade(emeralds, forSale, maxTrades, 0, priceMult);
    }
}