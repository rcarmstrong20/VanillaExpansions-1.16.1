package rcarmstrong20.vanilla_expansions.gen.feature.structure;

import com.mojang.serialization.Codec;

import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.structure.JigsawStructure;
import net.minecraft.world.gen.feature.structure.VillageConfig;

public class VEOverworldCabinStructure extends JigsawStructure
{
    public VEOverworldCabinStructure(Codec<VillageConfig> config)
    {
        super(config, 0, true, true);
    }

    @Override
    public Decoration step()
    {
        return Decoration.SURFACE_STRUCTURES;
    }
}
