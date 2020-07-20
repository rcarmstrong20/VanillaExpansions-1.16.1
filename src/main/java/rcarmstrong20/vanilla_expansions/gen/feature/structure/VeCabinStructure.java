package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.mojang.serialization.Codec;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

public class VeCabinStructure extends Structure<VeCabinFeatureConfig>
{
	public VeCabinStructure(Codec<VeCabinFeatureConfig> config)
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
	public Structure.IStartFactory<VeCabinFeatureConfig> getStartFactory()
	{
		return VeCabinStructure.Start::new;
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
	
	public static enum Location implements IStringSerializable
	{
		TAIGA("taiga_cabin"),
		BIRCH_FOREST("birch_forest_cabin"),
		FOREST("forest_cabin");
		
		public static final Codec<VeCabinStructure.Location> field_236342_h_ = IStringSerializable.func_233023_a_(VeCabinStructure.Location::values, VeCabinStructure.Location::func_236346_a_);
		private static final Map<String, VeCabinStructure.Location> field_236343_i_ = Arrays.stream(values()).collect(Collectors.toMap(VeCabinStructure.Location::func_176610_l, (p_236345_0_) ->
		{
			return p_236345_0_;
		}));
		
		private final String name;
		
		private Location(String name)
		{
			this.name = name;
		}
		
		public static VeCabinStructure.Location func_236346_a_(String locationName)
		{
			return field_236343_i_.get(locationName);
		}
		
		@Override
		public String func_176610_l()
		{
			return this.name;
		}
	}
	
	/**
	 * The structure start is responsible for creating the structure in memory, but not for placing the blocks themselves
	 */
	public static class Start extends StructureStart<VeCabinFeatureConfig>
	{
		public Start(Structure<VeCabinFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int references, long seed)
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
		public void func_230364_a_(ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, VeCabinFeatureConfig featureConfig)
		{
			String id = VanillaExpansions.MOD_ID;
			ResourceLocation templateLocation;
			
			if(featureConfig.location == VeCabinStructure.Location.TAIGA)
			{
				templateLocation = new ResourceLocation(id, VeCabinStructure.Location.TAIGA.func_176610_l());
			}
			else if(featureConfig.location == VeCabinStructure.Location.BIRCH_FOREST)
			{
				templateLocation = new ResourceLocation(id, VeCabinStructure.Location.BIRCH_FOREST.func_176610_l());
			}
			else
			{
				templateLocation = new ResourceLocation(id, VeCabinStructure.Location.FOREST.func_176610_l());
			}
			
			Rotation rotation = Rotation.randomRotation(this.rand);
			BlockPos blockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
			VeCabinPieces.init(templateManager, templateLocation, blockPos, rotation, this.components);
			this.recalculateStructureSize();
		}
	}
}
