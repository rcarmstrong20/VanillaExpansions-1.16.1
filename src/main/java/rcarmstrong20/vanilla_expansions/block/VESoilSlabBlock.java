package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import rcarmstrong20.vanilla_expansions.block.util.VESlabBlockUtil;

public class VESoilSlabBlock extends SlabBlock
{
    public VESoilSlabBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
            IPlantable plantable)
    {
        // Block plantBlock = plantable.getPlant(world, pos).getBlock();

        return !VESlabBlockUtil.isBottom(state);
    }
}
