package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class VeLadderBlock extends LadderBlock
{
    public VeLadderBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity)
    {
        return true;
    }
}
