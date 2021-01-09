package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 *
 * @author Ryan
 *
 *         A class for holding every point of interest instance that vanilla
 *         expansions has.
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VePointOfInterestTypes
{
    private static final List<PointOfInterestType> POI_TYPES = new ArrayList<>();

    public static PointOfInterestType lumberjack = register("lumberjack", VeBlocks.woodcutter, 1, 1);

    private static PointOfInterestType register(String nameIn, Block blockIn, int maxFreeTicketsIn, int validRangeIn)
    {
        return register(nameIn, new PointOfInterestType(nameIn, PointOfInterestType.getAllStates(blockIn),
                maxFreeTicketsIn, validRangeIn));
    }

    private static PointOfInterestType register(String name, PointOfInterestType pointOfInterest)
    {
        pointOfInterest.setRegistryName(VanillaExpansions.MOD_ID, name);
        POI_TYPES.add(pointOfInterest);
        return pointOfInterest;
    }

    @SubscribeEvent
    public static void registerPointOfInterestTypes(final RegistryEvent.Register<PointOfInterestType> event)
    {
        POI_TYPES.forEach(poi -> event.getRegistry().register(poi));
        POI_TYPES.clear();

        VanillaExpansions.LOGGER.info("Point of Interests registered.");
    }
}