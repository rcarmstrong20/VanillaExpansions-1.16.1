package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.world.gen.feature.structure.JigsawStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeCabinStructure extends JigsawStructure
{
    public VeCabinStructure(Codec<VillageConfig> config)
    {
        super(config, 0, false, false);
    }

    /**
     * Get the start of the structure In vanilla the start is usually a static inner
     * class of the structure class, follow this convention if you wish.
     *
     * @return method reference to Start constructor.
     */
    /*
     * @Override public Structure.IStartFactory<VillageConfig> getStartFactory() {
     * return VeCabinStructure.Start::new; } BastionRemantsStructure /** The name
     * used for the locate command.
     *
     * @return The name for the structure.
     */
    @Override
    public String getStructureName()
    {
        return VanillaExpansions.MOD_ID + ":cabin";
    }

    /**
     * The structure start is responsible for creating the structure in memory, but
     * not for placing the blocks themselves
     */
    /*
     * public static class Start extends StructureStart<VillageConfig> { public
     * Start(Structure<VillageConfig> structure, int chunkX, int chunkZ,
     * MutableBoundingBox boundingBox, int references, long seed) { super(structure,
     * chunkX, chunkZ, boundingBox, references, seed); }
     * 
     * /** For most structures this is the only method you will need to care about
     * Not a lot needs to be done here, most of the work is done by structure pieces
     * Examples of things vanilla does for different structures here include: -
     * Getting configs from the chunk generator - Deciding the rotation of the
     * structure - Getting height (rarely, most times height is determined in the
     * piece)
     */
    /*
     * @Override public void func_230364_a_(DynamicRegistries dynamicRegistry,
     * ChunkGenerator generator, TemplateManager templateManager, int chunkX, int
     * chunkZ, Biome biome, VillageConfig structureConfig) { Rotation rotation =
     * Rotation.randomRotation(this.rand); BlockPos blockPos = new BlockPos(chunkX *
     * 16, 0, chunkZ * 16); VeCabinPieces.init(templateManager,
     * structureConfig.startPool, blockPos, rotation, this.components);
     * this.recalculateStructureSize(); }
     */
}
