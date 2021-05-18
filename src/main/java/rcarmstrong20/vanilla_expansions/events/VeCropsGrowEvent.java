package rcarmstrong20.vanilla_expansions.events;

import java.util.Random;

import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;

public class VeCropsGrowEvent
{
    @SubscribeEvent
    public void onCropsGrowPre(BlockEvent.CropGrowEvent.Pre event)
    {
        Random random = new Random();
        Block crop = event.getState().getBlock();
        Block soil = event.getWorld().getBlockState(event.getPos().below()).getBlock();

        if (VeBlockTags.fertileSoil.contains(soil) && (crop instanceof CropsBlock || crop instanceof BeetrootBlock))
        {
            float f = getGrowthChance(crop, event.getWorld(), event.getPos());

            event.setResult(random.nextInt((int) (25.0F / f) + 1) == 0 ? Result.ALLOW : Result.DENY);
        }
        else
        {
            event.setResult(Result.DEFAULT);
        }
    }

    private static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos)
    {
        float f = 1.0F;
        BlockPos blockpos = pos.below();

        for (int i = -1; i <= 1; ++i)
        {
            for (int j = -1; j <= 1; ++j)
            {
                float f1 = 0.0F;
                BlockState blockstate = worldIn.getBlockState(blockpos.offset(i, 0, j));
                if (blockstate.canSustainPlant(worldIn, blockpos.offset(i, 0, j), Direction.UP, (IPlantable) blockIn))
                {
                    f1 = 1.0F;
                    if (blockstate.is(VeBlockTags.fertileSoil))
                    {
                        pos.offset(i, 0, j);
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos northPos = pos.north();
        BlockPos southPos = pos.south();
        BlockPos westPos = pos.west();
        BlockPos eastpos = pos.east();
        boolean flag = blockIn.equals(worldIn.getBlockState(westPos).getBlock())
                || blockIn.equals(worldIn.getBlockState(eastpos).getBlock());
        boolean flag1 = blockIn.equals(worldIn.getBlockState(northPos).getBlock())
                || blockIn.equals(worldIn.getBlockState(southPos).getBlock());
        if (flag && flag1)
        {
            f /= 2.0F;
        }
        else
        {
            boolean flag2 = blockIn == worldIn.getBlockState(westPos.north()).getBlock()
                    || blockIn == worldIn.getBlockState(eastpos.north()).getBlock()
                    || blockIn == worldIn.getBlockState(eastpos.south()).getBlock()
                    || blockIn == worldIn.getBlockState(westPos.south()).getBlock();
            if (flag2)
            {
                f /= 2.0F;
            }
        }
        return f;
    }
}
