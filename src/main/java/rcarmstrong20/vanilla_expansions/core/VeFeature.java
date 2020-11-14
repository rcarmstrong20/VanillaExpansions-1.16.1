package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeFeature
{
    private static final List<Feature<?>> FEATURES = new ArrayList<>();

    /**
     * Set the registry name for the features and add them to the registry list.
     *
     * @param <C>
     * @param name    A name for the feature.
     * @param feature A new instance of the class feature.
     * @return A new feature.
     */
    @SuppressWarnings("unused")
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