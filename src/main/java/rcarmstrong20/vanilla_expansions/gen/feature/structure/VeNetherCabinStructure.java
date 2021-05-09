package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class VeNetherCabinStructure extends Structure<VeCabinConfig>
{
    public VeNetherCabinStructure(Codec<VeCabinConfig> codec)
    {
        super(codec);
    }

    @Override
    public IStartFactory<VeCabinConfig> getStartFactory()
    {
        return VeNetherCabinStructure.Start::new;
    }

    @Override
    public String getFeatureName()
    {
        return "ve:nether_cabin";
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator cunkGenerator, BiomeProvider provider, long p_230363_3_,
            SharedSeedRandom sharedSeedRandom, int p_230363_6_, int p_230363_7_, Biome biome, ChunkPos pos,
            VeCabinConfig cabinConfig)
    {
        return sharedSeedRandom.nextInt(5) >= 2;
    }

    @Override
    public Decoration step()
    {
        return Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<VeCabinConfig>
    {
        public Start(Structure<VeCabinConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox,
                int references, long seed)
        {
            super(structure, chunkX, chunkZ, boundingBox, references, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistry, ChunkGenerator generator,
                TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, VeCabinConfig cabinConfig)
        {
            BlockPos blockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            Rotation rotation = Rotation.getRandom(this.random);

            VeNetherCabinPieces.init(templateManager, blockPos, rotation, this.pieces, cabinConfig);
            this.calculateBoundingBox();
        }
    }
}
