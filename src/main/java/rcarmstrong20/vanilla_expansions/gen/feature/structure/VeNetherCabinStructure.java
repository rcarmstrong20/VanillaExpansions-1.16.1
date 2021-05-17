package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.structure.JigsawStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

public class VeNetherCabinStructure extends JigsawStructure
{
    public VeNetherCabinStructure(Codec<VillageConfig> config)
    {
        super(config, 33, false, false);
    }

    @Override
    public Decoration step()
    {
        return Decoration.SURFACE_STRUCTURES;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator cunkGenerator, BiomeProvider provider, long p_230363_3_,
            SharedSeedRandom sharedSeedRandom, int p_230363_6_, int p_230363_7_, Biome biome, ChunkPos pos,
            VillageConfig cabinConfig)
    {
        return sharedSeedRandom.nextInt(5) >= 2;
    }
}
