package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class VeGlassBlock extends GlassBlock
{
    public VeGlassBlock(Block.Properties properties)
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
    /*
     * @Override public int getOpacity(BlockState state, IBlockReader worldIn,
     * BlockPos pos) { if (this == VeBlocks.glassOfDarkness) { return
     * worldIn.getMaxLightLevel(); } return state.propagatesSkylightDown(worldIn,
     * pos) ? 0 : 1; }
     */
}