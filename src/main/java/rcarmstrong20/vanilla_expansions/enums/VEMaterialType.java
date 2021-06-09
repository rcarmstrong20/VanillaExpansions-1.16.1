package rcarmstrong20.vanilla_expansions.enums;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public enum VEMaterialType
{
    BAMBOO(Material.BAMBOO, SoundType.BAMBOO), BAMBOO_SAPLING(Material.BAMBOO_SAPLING, SoundType.BAMBOO_SAPLING),
    BUILDABLE_GLASS(Material.BUILDABLE_GLASS, SoundType.GLASS), CACTUS(Material.CACTUS, SoundType.WOOL),
    CAKE(Material.CAKE, SoundType.WOOL), CLAY(Material.CLAY, SoundType.GRAVEL),
    CLOTH_DECORATION(Material.CLOTH_DECORATION, SoundType.WOOL), CORAL(Material.CORAL, SoundType.STONE),
    DECORATION(Material.DECORATION, SoundType.STONE), DIRT(Material.DIRT, SoundType.GRAVEL),
    EGG(Material.EGG, SoundType.STONE), EXPLOSIVE(Material.EXPLOSIVE, SoundType.GRASS),
    FIRE(Material.FIRE, SoundType.WOOL), GLASS(Material.GLASS, SoundType.GLASS), GRASS(Material.GRASS, SoundType.GRASS),
    HEAVY_METAL(Material.HEAVY_METAL, SoundType.ANVIL), ICE(Material.ICE, SoundType.GLASS),
    ICE_SOLID(Material.ICE_SOLID, SoundType.GLASS), LEAVES(Material.LEAVES, SoundType.GRASS),
    METAL(Material.METAL, SoundType.METAL), NETHER_WOOD(Material.NETHER_WOOD, SoundType.WOOD),
    PLANT(Material.PLANT, SoundType.GRASS),
    REPLACEABLE_FIREPROOF_PLANT(Material.REPLACEABLE_FIREPROOF_PLANT, SoundType.ROOTS),
    REPLACEABLE_PLANT(Material.REPLACEABLE_PLANT, SoundType.GRASS),
    REPLACEABLE_WATER_PLANT(Material.REPLACEABLE_WATER_PLANT, SoundType.WET_GRASS),
    SAND(Material.SAND, SoundType.GRAVEL), SHULKER_SHELL(Material.SHULKER_SHELL, SoundType.STONE),
    SNOW(Material.SNOW, SoundType.SNOW), SPONGE(Material.SPONGE, SoundType.GRASS),
    STONE(Material.STONE, SoundType.STONE), TOP_SNOW(Material.TOP_SNOW, SoundType.SNOW),
    VEGETABLE(Material.VEGETABLE, SoundType.WOOD), WATER_PLANT(Material.WATER_PLANT, SoundType.WET_GRASS),
    WEB(Material.WEB, SoundType.STONE), WOOD(Material.WOOD, SoundType.WOOD), WOOL(Material.WOOL, SoundType.WOOL);

    private Material material;
    private SoundType sound;

    VEMaterialType(Material materialIn, SoundType soundIn)
    {
        this.material = materialIn;
        this.sound = soundIn;
    }

    public Material getMaterial()
    {
        return this.material;
    }

    public SoundType getSound()
    {
        return this.sound;
    }
}
