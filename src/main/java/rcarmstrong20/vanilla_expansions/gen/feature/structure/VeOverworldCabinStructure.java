package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.structure.JigsawStructure;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class VeOverworldCabinStructure extends JigsawStructure
{
    public VeOverworldCabinStructure(Codec<VillageConfig> config)
    {
        super(config, 0, true, true);
    }

    /**
     * Get the start of the structure In vanilla the start is usually a static inner
     * class of the structure class, follow this convention if you wish.
     *
     * @return method reference to Start constructor.
     */
    @Override
    public Structure.IStartFactory<VillageConfig> getStartFactory()
    {
        return VeOverworldCabinStructure.Start::new;
    }

    @Override
    public Decoration getDecorationStage()
    {
        return Decoration.SURFACE_STRUCTURES;
    }

    /**
     * The structure start is responsible for creating the structure in memory, but
     * not for placing the blocks themselves
     */
    public static class Start extends StructureStart<VillageConfig>
    {
        public Start(Structure<VillageConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox,
                int references, long seed)
        {
            super(structure, chunkX, chunkZ, boundingBox, references, seed);
        }

        /**
         * For most structures this is the only method you will need to care about Not a
         * lot needs to be done here, most of the work is done by structure pieces
         * Examples of things vanilla does for different structures here include: -
         * Getting configs from the chunk generator - Deciding the rotation of the
         * structure - Getting height (rarely, most times height is determined in the
         * piece)
         */
        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistry, ChunkGenerator generator,
                TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, VillageConfig structureConfig)
        {
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            BlockPos blockPos = new BlockPos(x, 0, z);
            Rotation rotation = Rotation.randomRotation(rand);

            VeOverworldCabinPieces.init(templateManager, blockPos, rotation, this.components, structureConfig);

            // Move the piece up 1 and the bounding box down 1 to match align with most
            // land.
            this.components.forEach(piece -> piece.offset(0, 1, 0));
            this.components.forEach(piece -> piece.getBoundingBox().minY -= 1);

            this.recalculateStructureSize();
        }
    }
}
