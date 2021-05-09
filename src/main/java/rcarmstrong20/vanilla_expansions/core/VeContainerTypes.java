package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.inventory.container.VeTransmutationTableContainer;
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
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
            .create(ForgeRegistries.CONTAINERS, VanillaExpansions.MOD_ID);

    public static ContainerType<VeWoodcutterContainer> woodcutter = register("woodcutter", VeWoodcutterContainer::new);
    public static ContainerType<VeTransmutationTableContainer> transmutationTable = register("transmutation_table",
            VeTransmutationTableContainer::new);

    /**
     * @param <T>
     * @param name    A name for the container type.
     * @param factory A container factory for the container type.
     * @return A new container type.
     */
    private static <T extends Container> ContainerType<T> register(String name, ContainerType.IFactory<T> factory)
    {
        ContainerType<T> container = new ContainerType<>(factory);
        CONTAINERS.register(name, () -> container);
        return container;
    }
}
