package rndmaccess.vanilla_expansions.client.renderer;

import net.minecraft.block.Block;
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

@OnlyIn(Dist.CLIENT)
public class VEColors
{
    private static final BlockColors BLOCK_COLORS = Minecraft.getInstance().getBlockColors();
    private static final ItemColors ITEM_COLORS = Minecraft.getInstance().getItemColors();

    /**
     * If the particle IS NOT tinted the tint index for each model must be 1.
     *
     * @param tintParticle Whether the particle should be tinted with the color.
     * @param blocks       The blocks to color.
     */
    public static void registerGrassColors(boolean tintParticle, Block... blocks)
    {
        int tint = tintParticle ? 0 : 1;

        // Use the grass color of the biome or the default grass color
        final IBlockColor grassColorHandler = (state, blockAccess, pos, tintIndex) ->
        {
            if (tintIndex == tint)
            {
                if (blockAccess != null && pos != null)
                {
                    return BiomeColors.getAverageGrassColor(blockAccess, pos);
                }
                return GrassColors.get(0.5D, 1.0D);
            }
            return -1;
        };

        BLOCK_COLORS.register(grassColorHandler, blocks);

        registerItemColorHandlers(blocks);
    }

    public static void registerWaterColors(Block... blocks)
    {
        // Use the water color of the biome or the default water color.
        final IBlockColor waterColorHandler = (state, blockAccess, pos, tintIndex) ->
        {
            if (blockAccess != null && pos != null && tintIndex == 1)
            {
                return BiomeColors.getAverageWaterColor(blockAccess, pos);
            }
            return -1;
        };

        BLOCK_COLORS.register(waterColorHandler, blocks);

        registerItemColorHandlers(blocks);
    }

    /**
     * A helper method that gets the colors for each block and registers the
     * appropriate colors automatically.
     *
     * @param blockColors
     * @param itemColors
     */
    private static void registerItemColorHandlers(Block... blocks)
    {
        // Use the Block's color for the ItemBlock
        final IItemColor itemBlockColorHandler = (stack, tintIndex) ->
        {
            final BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();

            return BLOCK_COLORS.getColor(state, null, null, tintIndex);
        };

        ITEM_COLORS.register(itemBlockColorHandler, blocks);
    }
}