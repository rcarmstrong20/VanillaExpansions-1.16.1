package rcarmstrong20.vanilla_expansions.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.entity.villager.VillagerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 *
 * @author Ryan
 *
 *         A class for holding every villager type instance that vanilla
 *         expansions has.
 */
public class VeVillagerType
{
    public static VillagerType crimson = register("crimson");

    /**
     * A helper method for automatically registering every new villager types.
     *
     * @param name The name of the new villager type.
     * @return A new registered villager type.
     */
    private static VillagerType register(String name)
    {
        Constructor<VillagerType> constructor = ObfuscationReflectionHelper.findConstructor(VillagerType.class,
                String.class);
        String id = VanillaExpansions.MOD_ID;

        try
        {
            return Registry.register(Registry.VILLAGER_TYPE, new ResourceLocation(id, name),
                    constructor.newInstance(id + ":" + name));
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
            return VillagerType.PLAINS;
        }
    }
}
