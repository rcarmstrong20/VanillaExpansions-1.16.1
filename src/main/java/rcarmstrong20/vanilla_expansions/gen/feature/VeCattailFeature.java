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
import rcarmstrong20.vanilla_expansions.block.VeCattailBlock;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;

public class VeCattailFeature extends Feature<ProbabilityConfig>
{
    public VeCattailFeature(Codec<ProbabilityConfig> codec)
    {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos,
            ProbabilityConfig config)
    {
        boolean flag = false;
        int i = rand.nextInt(8) - rand.nextInt(8);
        int j = rand.nextInt(8) - rand.nextInt(8);
        int k = reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX() + i, pos.getZ() + j);
        BlockPos blockpos = new BlockPos(pos.getX() + i, k, pos.getZ() + j);
        if (reader.getBlockState(blockpos).isIn(Blocks.WATER))
        {
            BlockState state = VeBlocks.cattail.getDefaultState().with(VeCattailBlock.AGE, 3);
            if (state.isValidPosition(reader, blockpos))
            {
                BlockState stateTop = state.with(VeCattailBlock.HALF, DoubleBlockHalf.UPPER);
                BlockState stateBottom = state.with(VeCattailBlock.WATERLOGGED, true);
                BlockPos blockpos1 = blockpos.up();
                if (reader.getBlockState(blockpos1).isIn(Blocks.AIR)
                        && reader.getBlockState(blockpos).isIn(Blocks.WATER))
                {
                    reader.setBlockState(blockpos, stateBottom, 2);
                    reader.setBlockState(blockpos1, stateTop, 2);
                }

                flag = true;
            }
        }
        return flag;
    }
}
