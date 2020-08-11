package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class VeCabinConfig implements IFeatureConfig
{
    public static final Codec<VeCabinConfig> field_236627_a_ = VeCabinStructure.Type.field_236998_c_
            .fieldOf("portal_type").xmap(VeCabinConfig::new, (function) ->
            {
                return function.type;
            }).codec();

    public final VeCabinStructure.Type type;

    public VeCabinConfig(VeCabinStructure.Type type)
    {
        this.type = type;
    }
}