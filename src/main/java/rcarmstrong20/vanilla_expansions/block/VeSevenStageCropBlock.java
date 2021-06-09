package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Items;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.IItemProvider;
import rcarmstrong20.vanilla_expansions.core.VEBlocks;
import rcarmstrong20.vanilla_expansions.core.VEItems;

public class VESevenStageCropBlock extends CropsBlock
{
    public VESevenStageCropBlock(Block.Properties properties)
    {
        super(properties);
    }

    /**
     * Called when the player pick-block's this crop.
     */
    @Override
    protected IItemProvider getBaseSeedId()
    {
        Block block = this.getBlock();

        if (block.equals(VEBlocks.bokChoy))
        {
            return VEItems.bokChoySeeds;
        }
        return Items.WHEAT_SEEDS;
    }

    /**
     * Creates a list of properties that this block can have.
     */
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        builder.add(this.getAgeProperty());
    }
}
