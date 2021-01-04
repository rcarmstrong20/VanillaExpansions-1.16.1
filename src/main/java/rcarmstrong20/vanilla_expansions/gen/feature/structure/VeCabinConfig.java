package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class VeCabinConfig implements IFeatureConfig
{
    public static Codec<VeCabinConfig> cabinConfig = RecordCodecBuilder.create((config) ->
    {
        return config.group(
                ResourceLocation.CODEC.fieldOf("template_location").forGetter(VeCabinConfig::getTemplateLocation))
                .apply(config, VeCabinConfig::new);
    });

    private final ResourceLocation templateLocation;

    public VeCabinConfig(ResourceLocation templateLocation)
    {
        this.templateLocation = templateLocation;
    }

    public ResourceLocation getTemplateLocation()
    {
        return templateLocation;
    }
}
