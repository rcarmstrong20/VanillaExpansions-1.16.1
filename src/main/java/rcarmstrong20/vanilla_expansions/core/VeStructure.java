package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.CabinPiece;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.CabinStructure;

public class VeStructure
{
	
	private static final List<Structure<?>> STRUCTURES = new ArrayList<>();
	private static final List<IStructurePieceType> STRUCTURE_PIECES = new ArrayList<>();
	
	public static final IStructurePieceType CABIN_PIECE = register("cabin", CabinPiece::new);
	
	public static final Structure<NoFeatureConfig> CABIN = register("cabin", new CabinStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
	
	/**
	 * Create registers for the structure pieces and add them to the registry list.
	 */
	private static IStructurePieceType register(String name, IStructurePieceType structurePieceType)
	{
		IStructurePieceType pieceRegistry = Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(VanillaExpansions.MOD_ID, name), structurePieceType);
		STRUCTURE_PIECES.add(pieceRegistry);
		return pieceRegistry;
	}
	
	/**
	 * Set the registry name for the structures and add them to the registry list.
	 */
	private static <F extends Structure<?>> F register(String name, F structure, GenerationStage.Decoration decoration)
	{
		structure.setRegistryName(VanillaExpansions.MOD_ID, name);
		STRUCTURES.add(structure);
		return structure;
	}
	
	/**
	 * Register the Features to the game
	 */
	@SubscribeEvent
	public static void registerStructures(final RegistryEvent.Register<Structure<?>> event)
	{
		//Register the cabin pieces
		STRUCTURE_PIECES.forEach(structurePiece -> STRUCTURE_PIECES.iterator());
		STRUCTURE_PIECES.clear();
		
		//Register the structures
		STRUCTURES.forEach(structure -> event.getRegistry().register(structure));
		STRUCTURES.clear();
		
		VanillaExpansions.LOGGER.info("Structures registered.");
	}
}
