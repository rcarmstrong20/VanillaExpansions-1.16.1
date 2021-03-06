package rndmaccess.vanilla_expansions.core;

import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.gen.feature.structure.VENetherCabinStructure;
import rndmaccess.vanilla_expansions.gen.feature.structure.VEOverworldCabinStructure;

/**
 * A class for holding every structure instance that vanilla expansions has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEStructures
{
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister
            .create(ForgeRegistries.STRUCTURE_FEATURES, VanillaExpansions.MOD_ID);

    public static Structure<VillageConfig> overworldCabin = register("overworld_cabin",
            new VEOverworldCabinStructure(VillageConfig.CODEC));
    public static Structure<VillageConfig> netherCabin = register("nether_cabin",
            new VENetherCabinStructure(VillageConfig.CODEC));

    /**
     * Add this structure to the registry list.
     *
     * @param <C>       The feature config for the structure.
     * @param name      The name of the structure.
     * @param structure An instance for this new structure.
     * @return The new Structure.
     */
    private static <C extends IFeatureConfig> Structure<C> register(String name, Structure<C> structure)
    {
        STRUCTURES.register(name, () -> structure);
        Structure.STRUCTURES_REGISTRY.put(VanillaExpansions.MOD_ID + ":" + name, structure);
        return structure;
    }
}
