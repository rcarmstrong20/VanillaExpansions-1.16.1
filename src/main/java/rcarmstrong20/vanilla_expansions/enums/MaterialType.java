package rcarmstrong20.vanilla_expansions.enums;

import net.minecraft.block.material.Material;

public enum MaterialType
{
    ROCK(Material.ROCK), ANVIL(Material.ANVIL), CORAL(Material.CORAL), ICE(Material.ICE), IRON(Material.IRON),
    PACKED_ICE(Material.PACKED_ICE), PISTON(Material.PISTON), SHULKER(Material.SHULKER), BAMBOO(Material.BAMBOO),
    GOURD(Material.GOURD), CARPET(Material.CARPET), SNOW(Material.SNOW), WEB(Material.WEB), CLAY(Material.CLAY),
    EARTH(Material.EARTH), SAND(Material.SAND), WOOD(Material.WOOD), NETHER_WOOD(Material.NETHER_WOOD),
    WOOL(Material.WOOL), LEAVES(Material.LEAVES), SNOW_BLOCK(Material.SNOW_BLOCK), AIR(Material.AIR),
    STRUCTURE_VOID(Material.STRUCTURE_VOID), PORTAL(Material.PORTAL), PLANTS(Material.PLANTS),
    OCEAN_PLANT(Material.OCEAN_PLANT), TALL_PLANTS(Material.TALL_PLANTS), NETHER_PLANTS(Material.NETHER_PLANTS),
    SEA_GRASS(Material.SEA_GRASS), WATER(Material.WATER), BUBBLE_COLUMN(Material.BUBBLE_COLUMN), LAVA(Material.LAVA),
    FIRE(Material.FIRE), MISCELLANEOUS(Material.MISCELLANEOUS), REDSTONE_LIGHT(Material.REDSTONE_LIGHT),
    ORGANIC(Material.ORGANIC), SPONGE(Material.SPONGE), BAMBOO_SAPLING(Material.BAMBOO_SAPLING), TNT(Material.TNT),
    GLASS(Material.GLASS), CACTUS(Material.CACTUS), BARRIER(Material.BARRIER), DRAGON_EGG(Material.DRAGON_EGG),
    CAKE(Material.CAKE);

    private Material material;

    MaterialType(Material materialIn)
    {
        this.material = materialIn;
    }

    public Material getMaterial()
    {
        return this.material;
    }
}
