package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class VeGlassOfDarknessBlock extends AbstractGlassBlock
{
    public VeGlassOfDarknessBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_200123_1_, IBlockReader p_200123_2_, BlockPos p_200123_3_)
    {
        return false;
    }

    /**
     * Checks whether this block should let light pass through.
     */
    @Override
    public int getLightBlock(BlockState state, IBlockReader world, BlockPos pos)
    {
        return world.getMaxLightLevel();
    }
}