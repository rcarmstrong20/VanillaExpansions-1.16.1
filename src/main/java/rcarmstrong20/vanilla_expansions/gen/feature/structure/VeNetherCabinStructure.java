package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
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
    public Decoration getDecorationStage()
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
        public void func_230364_a_(DynamicRegistries dynamicRegistry, ChunkGenerator generator,
                TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, VeCabinConfig cabinConfig)
        {
            BlockPos blockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
            Rotation rotation = Rotation.randomRotation(this.rand);

            VeNetherCabinPieces.init(templateManager, blockPos, rotation, this.components, cabinConfig);
            this.recalculateStructureSize();
        }
    }
}
