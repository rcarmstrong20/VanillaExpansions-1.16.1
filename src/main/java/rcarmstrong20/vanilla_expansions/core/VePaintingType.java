package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VePaintingType
{
    private static final List<PaintingType> PAINTING_TYPES = new ArrayList<>();

    public static PaintingType earth = register("earth", new PaintingType(16, 16));
    public static PaintingType fire = register("fire", new PaintingType(16, 16));
    public static PaintingType nether_wastes = register("nether_wastes", new PaintingType(16, 16));
    public static PaintingType starry_night = register("starry_night", new PaintingType(16, 16));
    public static PaintingType the_scream = register("the_scream", new PaintingType(16, 16));
    public static PaintingType water = register("water", new PaintingType(16, 16));
    public static PaintingType wind = register("wind", new PaintingType(16, 16));

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
