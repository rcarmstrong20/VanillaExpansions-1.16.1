package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class VeCabinFeatureConfig implements IFeatureConfig
{
	public static final Codec<VeCabinFeatureConfig> field_236627_a_ = VeCabinStructure.Location.field_236342_h_.fieldOf("cabin_type").xmap(VeCabinFeatureConfig::new, (p_236629_0_) ->
	{
		return p_236629_0_.location;
	}).codec();
	public final VeCabinStructure.Location location;
	
	public VeCabinFeatureConfig(VeCabinStructure.Location location)
	{
		this.location = location;
	}
}
