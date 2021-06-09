package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.core.VEBlocks;
import rcarmstrong20.vanilla_expansions.core.VEItems;

public class VEThreeStageCropsBlock extends BeetrootBlock
{
    private static final VoxelShape[] ONION_SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D), Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D), Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D) };

    private static final VoxelShape[] GINGER_SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

    public VEThreeStageCropsBlock(Properties properties)
    {
        super(properties);
    }

    /**
     * Returns the bounding boxes for the blocks.
     */
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        if (this == VEBlocks.greenOnions || this == VEBlocks.garlic)
        {
            return ONION_SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
        }
        else
        {
            return GINGER_SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
        }
    }

    /**
     * Returns the item when using pick block.
     */
    @Override
    protected IItemProvider getBaseSeedId()
    {
        if (this == VEBlocks.greenOnions)
        {
            return VEItems.greenOnion;
        }
        else if (this == VEBlocks.garlic)
        {
            return VEItems.garlic;
        }
        else
        {
            return Items.WHEAT_SEEDS;
        }
    }
}
