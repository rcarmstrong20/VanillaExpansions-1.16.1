package rndmaccess.vanilla_expansions.core;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.inventory.container.VETransmutationTableContainer;
import rndmaccess.vanilla_expansions.inventory.container.VEWoodcutterContainer;

/**
 * A class for holding every container type instance that vanilla expansions
 * has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEContainerTypes
{
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
            .create(ForgeRegistries.CONTAINERS, VanillaExpansions.MOD_ID);

    public static ContainerType<VEWoodcutterContainer> woodcutter = register("woodcutter", VEWoodcutterContainer::new);
    public static ContainerType<VETransmutationTableContainer> transmutationTable = register("transmutation_table",
            VETransmutationTableContainer::new);

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
