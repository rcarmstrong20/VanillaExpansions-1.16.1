package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.Random;

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
import net.minecraft.world.gen.feature.structure.JigsawStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class VeNetherCabinStructure extends JigsawStructure
{
    public static final int y = new Random().nextInt(25);

    public VeNetherCabinStructure(Codec<VillageConfig> codec)
    {
        super(codec, 0, true, false);
    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider biomeProvider, long p_230363_3_,
            SharedSeedRandom sharedSeedRandom, int p_230363_6_, int p_230363_7_, Biome biome, ChunkPos pos,
            VillageConfig config)
    {
        return sharedSeedRandom.nextInt(5) >= 2;
    }

    @Override
    public IStartFactory<VillageConfig> getStartFactory()
    {
        return VeNetherCabinStructure.Start::new;
    }

    @Override
    public Decoration getDecorationStage()
    {
        return Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<VillageConfig>
    {
        public Start(Structure<VillageConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox,
                int references, long seed)
        {
            super(structure, chunkX, chunkZ, boundingBox, references, seed);
        }

        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistry, ChunkGenerator generator,
                TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, VillageConfig structureConfig)
        {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos blockPos = new BlockPos(x, 0, z);
            Rotation rotation = Rotation.randomRotation(this.rand);

            VeNetherCabinPieces.init(templateManager, blockPos, rotation, this.components, structureConfig);

            this.components.forEach(piece -> piece.offset(0, 1, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);

            this.recalculateStructureSize();
        }
    }
}
