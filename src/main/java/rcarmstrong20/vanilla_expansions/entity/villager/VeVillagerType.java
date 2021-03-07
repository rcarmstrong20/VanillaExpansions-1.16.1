package rcarmstrong20.vanilla_expansions.entity.villager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.entity.villager.VillagerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every villager type instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VeVillagerType
{
    public static final Constructor<VillagerType> CONSTRUCTOR = ObfuscationReflectionHelper
            .findConstructor(VillagerType.class, String.class);

    public static VillagerType crimson = register("crimson");
    public static VillagerType warped = register("warped");

    /**
     * A helper method for automatically registering and adding every new villager
     * type to the byBiome map, which is used when spawning villagers in different
     * biomes.
     *
     * @param name The name of the new villager type.
     * @return A new registered villager type.
     */
    private static VillagerType register(String name)
    {
        String id = VanillaExpansions.MOD_ID;
        try
        {
            return Registry.register(Registry.VILLAGER_TYPE, new ResourceLocation(id, name),
                    CONSTRUCTOR.newInstance(id + ":" + name));
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        return VillagerType.PLAINS;
    }
}
