package rcarmstrong20.vanilla_expansions.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import rcarmstrong20.vanilla_expansions.block.VECattailBlock;
import rcarmstrong20.vanilla_expansions.core.VEBlocks;

public class VECattailFeature extends Feature<ProbabilityConfig>
{
    public VECattailFeature(Codec<ProbabilityConfig> codec)
    {
        super(codec);
    }

    // SeaGrassFeature

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos,
            ProbabilityConfig config)
    {
        boolean flag = false;
        int i = rand.nextInt(8) - rand.nextInt(8);
        int j = rand.nextInt(8) - rand.nextInt(8);
        int k = reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
        BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
        if (reader.getBlockState(blockpos).is(Blocks.WATER))
        {
            BlockState state = VEBlocks.cattail.defaultBlockState().setValue(VECattailBlock.AGE, 3);
            if (state.canSurvive(reader, blockpos))
            {
                BlockState stateTop = state.setValue(VECattailBlock.HALF, DoubleBlockHalf.UPPER);
                BlockState stateBottom = state.setValue(VECattailBlock.WATERLOGGED, true);
                BlockPos blockpos1 = blockpos.above();
                if (reader.getBlockState(blockpos1).is(Blocks.AIR) && reader.getBlockState(blockpos).is(Blocks.WATER))
                {
                    reader.setBlock(blockpos, stateBottom, 2);
                    reader.setBlock(blockpos1, stateTop, 2);
                }

                flag = true;
            }
        }
        return flag;
    }
}
