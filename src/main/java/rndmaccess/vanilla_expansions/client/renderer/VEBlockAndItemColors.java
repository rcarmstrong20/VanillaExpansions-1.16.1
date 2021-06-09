package rndmaccess.vanilla_expansions.client.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rndmaccess.vanilla_expansions.core.VEBlocks;

@OnlyIn(Dist.CLIENT)
public class VEBlockAndItemColors
{
    public static void registerColorHandlers()
    {
        final BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        final ItemColors itemColors = Minecraft.getInstance().getItemColors();

        registerBlockColorHandlers(blockColors);
        registerItemColorHandlers(blockColors, itemColors);
    }

    private static void registerBlockColorHandlers(final BlockColors blockColors)
    {
        // Use the grass color of the biome or the default grass color
        final IBlockColor grassColorHandler = (state, blockAccess, pos, tintIndex) ->
        {
            if (tintIndex == 1)
            {
                if (blockAccess != null && pos != null)
                {
                    return BiomeColors.getAverageGrassColor(blockAccess, pos);
                }
                return GrassColors.get(0.5D, 1.0D);
            }
            return -1;
        };

        // Use the water color of the biome or the default water color.
        @SuppressWarnings("unused")
        final IBlockColor waterColorHandler = (state, blockAccess, pos, tintIndex) ->
        {
            if (blockAccess != null && pos != null && tintIndex == 1)
            {
                return BiomeColors.getAverageWaterColor(blockAccess, pos);
            }
            return -1;
        };

        blockColors.register(grassColorHandler, VEBlocks.endermanPlush, VEBlocks.grassSlab);
    }

    private static void registerItemColorHandlers(final BlockColors blockColors, final ItemColors itemColors)
    {
        // Use the Block's color for the ItemBlock
        final IItemColor itemBlockColorHandler = (stack, tintIndex) ->
        {
            final BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();

            return blockColors.getColor(state, null, null, tintIndex);
        };

        itemColors.register(itemBlockColorHandler, VEBlocks.endermanPlush, VEBlocks.grassSlab);
    }
}