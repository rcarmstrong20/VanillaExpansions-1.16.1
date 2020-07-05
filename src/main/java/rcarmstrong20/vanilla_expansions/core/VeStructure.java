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
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinPieces;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinStructure;

/**
 * Author: rcarmstrong20
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructure
{
	
	private static final List<Structure<?>> STRUCTURES = new ArrayList<>();
	
	public static final IStructurePieceType CABIN_PIECE = register("cabin", VeCabinPieces.VePiece::new);
	
	public static final Structure<NoFeatureConfig> CABIN = register("cabin", new VeCabinStructure(NoFeatureConfig.field_236558_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
	
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
	 * Create registers for the structure pieces and add them to the registry list.
	 */
	private static IStructurePieceType register(String name, IStructurePieceType type)
	{
		return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(VanillaExpansions.MOD_ID, name), type);
	}
	
	/**
	 * Register the Features to the game
	 */
	@SubscribeEvent
	public static void registerStructures(final RegistryEvent.Register<Structure<?>> event)
	{
		//Register the structures
		STRUCTURES.forEach(structure -> event.getRegistry().register(structure));
		STRUCTURES.clear();
		
		VanillaExpansions.LOGGER.info("Structures registered.");
	}
}
