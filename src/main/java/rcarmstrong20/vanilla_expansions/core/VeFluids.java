package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.fluid.VeDarkMatterFluid;

/**
 * A class for holding every fluid instance that vanilla expansions has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeFluids
{
    private static final List<Fluid> FLUIDS = new ArrayList<>();

    public static Fluid darkMatter = register("dark_matter", new VeDarkMatterFluid.Source());
    public static FlowingFluid flowingDarkMatter = register("flowing_dark_matter", new VeDarkMatterFluid.Flowing());

    /**
     * @param name  A name for the fluid.
     * @param fluid A new instance of flowing fluid.
     * @return A new fluid.
     */
    private static FlowingFluid register(String name, FlowingFluid fluid)
    {
        fluid.setRegistryName(VanillaExpansions.MOD_ID, name);
        FLUIDS.add(fluid);
        return fluid;
    }

    @SubscribeEvent
    public static void registerFluids(final RegistryEvent.Register<Fluid> event)
    {
        FLUIDS.forEach(fluid -> event.getRegistry().register(fluid));
        FLUIDS.clear();

        VanillaExpansions.LOGGER.info("Fluids registered.");
    }
}
