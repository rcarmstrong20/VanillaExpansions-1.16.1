package rndmaccess.vanilla_expansions.gen.feature.structure;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.template.AlwaysTrueRuleTest;
import net.minecraft.world.gen.feature.template.RandomBlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleEntry;
import net.minecraft.world.gen.feature.template.RuleStructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.StructureProcessorList;
import net.minecraftforge.fml.common.Mod;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.core.VEBlocks;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEProcessorLists
{
    public static final StructureProcessorList CRIMSON_MOSSIFY_70_PERCENT = register("crimson_mossify_70_percent",
            ImmutableList.of(new RuleStructureProcessor(
                    ImmutableList.of(new RuleEntry(new RandomBlockMatchRuleTest(Blocks.NETHER_BRICKS, 0.7F),
                            AlwaysTrueRuleTest.INSTANCE, VEBlocks.crimsonChytridNetherBricks.defaultBlockState())))));

    private static StructureProcessorList register(String name, ImmutableList<StructureProcessor> processor)
    {
        ResourceLocation location = new ResourceLocation(VanillaExpansions.MOD_ID, name);
        StructureProcessorList structureprocessorlist = new StructureProcessorList(processor);
        return WorldGenRegistries.register(WorldGenRegistries.PROCESSOR_LIST, location, structureprocessorlist);
    }
}
