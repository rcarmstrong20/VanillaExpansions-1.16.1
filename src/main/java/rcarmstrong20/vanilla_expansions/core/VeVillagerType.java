package rcarmstrong20.vanilla_expansions.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.entity.villager.VillagerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding all the villager instances.
 *
 * @author Ryan
 *
 */
public class VeVillagerType
{
    public static VillagerType crimson = register("crimson");

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
            return null;
        }
    }
}
