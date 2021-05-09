package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every point of interest instance that vanilla expansions
 * has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VePointOfInterestTypes
{
    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister
            .create(ForgeRegistries.POI_TYPES, VanillaExpansions.MOD_ID);

    public static PointOfInterestType lumberjack = register("lumberjack", VeBlocks.woodcutter, 1, 1);

    private static PointOfInterestType register(String nameIn, Block block, int maxFreeTicketsIn, int validRangeIn)
    {
        return register(nameIn, new PointOfInterestType(nameIn, PointOfInterestType.getBlockStates(block),
                maxFreeTicketsIn, validRangeIn));
    }

    private static PointOfInterestType register(String name, PointOfInterestType pointOfInterest)
    {
        POI_TYPES.register(name, () -> pointOfInterest);
        return pointOfInterest;
    }
}