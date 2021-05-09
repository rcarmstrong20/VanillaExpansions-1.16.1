package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.VeCattailFeature;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeFeature
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
            VanillaExpansions.MOD_ID);

    public static final VeCattailFeature CATTAIL = (VeCattailFeature) register("cattail",
            new VeCattailFeature(ProbabilityConfig.CODEC));

    private static <C extends IFeatureConfig> Feature<?> register(String name, Feature<C> feature)
    {
        FEATURES.register(name, () -> feature);
        return feature;
    }
}
