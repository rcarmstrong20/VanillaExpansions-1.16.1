package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every painting type instance that vanilla expansions has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VePaintingType
{
    private static final List<PaintingType> PAINTING_TYPES = new ArrayList<>();

    public static PaintingType netherWastes = register("nether_wastes", new PaintingType(16, 16));
    public static PaintingType starryNight = register("starry_night", new PaintingType(32, 32));
    public static PaintingType theScream = register("the_scream", new PaintingType(16, 16));
    public static PaintingType rainsRustle = register("rains_rustle", new PaintingType(64, 48));

    public static PaintingType register(String name, PaintingType paintingType)
    {
        paintingType.setRegistryName(VanillaExpansions.MOD_ID, name);
        PAINTING_TYPES.add(paintingType);
        return paintingType;
    }

    @SubscribeEvent
    public static void registerPaintingTypes(final RegistryEvent.Register<PaintingType> event)
    {
        PAINTING_TYPES.forEach(paintingType -> event.getRegistry().register(paintingType));
        PAINTING_TYPES.clear();

        VanillaExpansions.LOGGER.info("Painting types registered.");
    }
}
