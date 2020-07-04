package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.List;

import com.mojang.serialization.Codec;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.VeConfig;

public class CabinStructure extends Structure<NoFeatureConfig>
{
	public CabinStructure(Codec<NoFeatureConfig> config)
	{
		super(config);
	}
	
	/**
	 * Get the start of the structure
	 * In vanilla the start is usually a static inner class of the structure class, follow this convention if you wish
	 *
	 * @return method reference to Start constructor
	 */
	@Override
	public Structure.IStartFactory<NoFeatureConfig> getStartFactory()
	{
		return CabinStructure.Start::new;
	}
	
	/**
	 * An 'id' for the structure, distinct from registry id
	 * Used for the Locate command (by forge only, vanilla uses its own system)
	 * Should probably be in the format 'modid:name'
	 *
	 * @return name of structure
	 */
	
	@Override
	public String getStructureName()
	{
		return VanillaExpansions.MOD_ID + ":cabin";
	}
	
	/**
	 * The structure start is responsible for creating the structure in memory, but not for placing the blocks themselves
	 */
	public static class Start extends StructureStart<NoFeatureConfig>
	{
		public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed)
		{
			super(structure, chunkX, chunkZ, boundingBox, references, seed);
		}
		
		/**
		 * For most structures this is the only method you will need to care about
		 * Not a lot needs to be done here, most of the work is done by structure pieces
		 * Examples of things vanilla does for different structures here include:
		 * - Getting configs from the chunk generator
		 * - Deciding the rotation of the structure
		 * - Getting height (rarely, most times height is determined in the piece)
		 */
		@Override
		public void func_230364_a_(ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig featureConfig)
		{
			ResourceLocation templateResource = null;
			if(isBiome(biome, VeConfig.Common.taigaCabinSpawnBiomes.get()))
			{
				templateResource = new ResourceLocation(VanillaExpansions.MOD_ID, "taiga_cabin");
			}
			else if(isBiome(biome, VeConfig.Common.birchForestCabinSpawnBiomes.get()))
			{
				templateResource = new ResourceLocation(VanillaExpansions.MOD_ID, "birch_forest_cabin");
			}
			else if (isBiome(biome, VeConfig.Common.forestCabinSpawnBiomes.get()))
			{
				templateResource = new ResourceLocation(VanillaExpansions.MOD_ID, "forest_cabin");
			}
			
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			CabinPiece piece = new CabinPiece(templateManager, templateResource, new BlockPos(chunkX * 16, 0, chunkZ * 16), rotation);
			this.components.add(piece);
			this.recalculateStructureSize();
		}
		
		/**
		 * Check to see if the current biome exists in the given list of biomes.
		 */
		private static boolean isBiome(Biome currentBiome, List<String> biomeNames)
		{
			for(int i = 0; i < biomeNames.size(); i++)
			{
				Biome biome = ForgeRegistries.BIOMES.getValue(new ResourceLocation(biomeNames.get(i)));
				
				if(currentBiome == biome)
				{
					return true;
				}
			}
			return false;
		}
	}
}
