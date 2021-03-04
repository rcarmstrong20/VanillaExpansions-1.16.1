package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every painting type instance that vanilla expansions has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VePaintingType
{
    public static final DeferredRegister<PaintingType> PAINTING_TYPES = DeferredRegister
            .create(ForgeRegistries.PAINTING_TYPES, VanillaExpansions.MOD_ID);

    public static PaintingType netherWastes = register("nether_wastes", new PaintingType(16, 16));
    public static PaintingType starryNight = register("starry_night", new PaintingType(32, 32));
    public static PaintingType theScream = register("the_scream", new PaintingType(16, 16));
    public static PaintingType rainsRustle = register("rains_rustle", new PaintingType(64, 48));

    private static PaintingType register(String name, PaintingType paintingType)
    {
        PAINTING_TYPES.register(name, () -> paintingType);
        return paintingType;
    }
}
