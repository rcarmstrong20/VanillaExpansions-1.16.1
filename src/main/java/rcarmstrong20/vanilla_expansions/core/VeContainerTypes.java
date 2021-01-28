package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.inventory.container.VeWoodcutterContainer;

/**
 * A class for holding every container type instance that vanilla expansions
 * has.
 * 
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeContainerTypes
{
    public static final List<ContainerType<?>> CONTAINER_TYPES = new ArrayList<>();

    public static ContainerType<VeWoodcutterContainer> woodcutter = register("woodcutter", VeWoodcutterContainer::new);

    /**
     * @param <T>
     * @param name    A name for the container type.
     * @param factory A container factory for the container type.
     * @return A new container type.
     */
    private static <T extends Container> ContainerType<T> register(String name, ContainerType.IFactory<T> factory)
    {
        return register(new ResourceLocation(VanillaExpansions.MOD_ID, name), factory);
    }

    private static <T extends Container> ContainerType<T> register(ResourceLocation name,
            ContainerType.IFactory<T> factory)
    {
        ContainerType<T> type = new ContainerType<>(factory);
        type.setRegistryName(name);
        CONTAINER_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerTypes(final RegistryEvent.Register<ContainerType<?>> event)
    {
        CONTAINER_TYPES.forEach(type -> event.getRegistry().register(type));
        CONTAINER_TYPES.clear();
        VanillaExpansions.LOGGER.info("Container types registered.");
    }
}
