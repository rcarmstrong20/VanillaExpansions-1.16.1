package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BigRedMushroomFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * Author: rcarmstrong20
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeFeature
{
	private static final List<Feature<?>> FEATURES = new ArrayList<>();
	
	public static final Feature<BigMushroomFeatureConfig> HUGE_PURPLE_MUSHROOM = register("huge_purple_mushroom", new BigRedMushroomFeature(BigMushroomFeatureConfig.field_236528_a_));
	public static final StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> CABIN = VeStructure.CABIN.func_236391_a_(NoFeatureConfig.field_236559_b_);
	
	/**
	 * Set the registry name for the features and add them to the registry list.
	 */
	private static <C extends IFeatureConfig> Feature<C> register(String name, Feature<C> feature)
	{
		feature.setRegistryName(VanillaExpansions.MOD_ID, name);
		FEATURES.add(feature);
		return feature;
	}
	
	/**
	 * Register the Features to the game
	 */
	@SubscribeEvent
	public static void registerFeaturesAndStructures(final RegistryEvent.Register<Feature<?>> event)
	{
		FEATURES.forEach(feature -> event.getRegistry().register(feature));
		FEATURES.clear();
		
		VanillaExpansions.LOGGER.info("Features registered.");
	}
}