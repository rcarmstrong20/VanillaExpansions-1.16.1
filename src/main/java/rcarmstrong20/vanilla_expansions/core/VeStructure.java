package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinFeatureConfig;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinStructure;

/**
 * Author: rcarmstrong20
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructure
{
	private static final List<Structure<?>> STRUCTURES = new ArrayList<>();
	
	public static final Structure<VeCabinFeatureConfig> CABIN = register("cabin", new VeCabinStructure(VeCabinFeatureConfig.field_236627_a_), GenerationStage.Decoration.SURFACE_STRUCTURES);
	
	/**
	 * @param <F>
	 * @param name       The name for the structure.
	 * @param structure  A new instance of the Structure<config> class.
	 * @param decoration The decoration category for this structure.
	 * @return           A new structure.
	 */
	private static <F extends Structure<?>> F register(String name, F structure, GenerationStage.Decoration decoration)
	{
		structure.setRegistryName(VanillaExpansions.MOD_ID, name);
		STRUCTURES.add(structure);
		return structure;
	}
	
	/**
	 * Register the Structures to the game
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
