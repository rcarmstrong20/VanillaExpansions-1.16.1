package rndmaccess.vanilla_expansions.core;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.gen.feature.VECattailFeature;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEFeature
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
            VanillaExpansions.MOD_ID);

    public static final VECattailFeature CATTAIL = (VECattailFeature) register("cattail",
            new VECattailFeature(ProbabilityConfig.CODEC));

    private static <C extends IFeatureConfig> Feature<?> register(String name, Feature<C> feature)
    {
        FEATURES.register(name, () -> feature);
        return feature;
    }
}
