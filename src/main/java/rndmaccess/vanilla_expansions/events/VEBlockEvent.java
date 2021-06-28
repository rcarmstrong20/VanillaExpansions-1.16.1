package rndmaccess.vanilla_expansions.events;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rndmaccess.vanilla_expansions.config.VEBlockConfig;
import rndmaccess.vanilla_expansions.core.VEItems;

public class VEBlockEvent
{
    @SubscribeEvent
    public void onBlockBroken(BlockEvent.BreakEvent event)
    {
        Block block = event.getState().getBlock();
        PlayerEntity player = event.getPlayer();
        ItemStack stack = player.getItemInHand(player.getUsedItemHand());
        IWorld world = event.getWorld();
        BlockPos pos = event.getPos();

        if (!player.isCreative() && block == Blocks.SPRUCE_LEAVES && stack.getItem() != Items.SHEARS)
        {
            Random random = new Random();
            double chance = VEBlockConfig.spruceConePercent.get() / 100.0;
            float chanceChosen = random.nextFloat();

            // 5% chance to drop by default
            if (chanceChosen <= chance)
            {
                Block.popResource((World) world, pos, new ItemStack(VEItems.spruceCone, 1));
            }
            else
            {
                if (EnchantmentHelper.getEnchantments(stack).get(Enchantments.BLOCK_FORTUNE) != null
                        && chanceChosen <= chance * 4.0)
                {
                    Block.popResource((World) world, pos, new ItemStack(VEItems.spruceCone, 2));
                }
            }
        }
    }
}
