package rndmaccess.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every block tag instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VEBlockTags
{
    public static ITag<Block> endBoneMealable = VEBlockTags.bind("end_bone_mealable");
    public static ITag<Block> endBoneMealPlants = VEBlockTags.bind("end_bone_meal_plants");
    public static ITag<Block> overworldBoxes = VEBlockTags.bind("overworld_boxes");
    public static ITag<Block> netherBoxes = VEBlockTags.bind("nether_boxes");
    public static ITag<Block> endBoxes = VEBlockTags.bind("end_boxes");
    public static ITag<Block> overworldBoxable = VEBlockTags.bind("overworld_boxable");
    public static ITag<Block> netherBoxable = VEBlockTags.bind("nether_boxable");
    public static ITag<Block> endBoxable = VEBlockTags.bind("end_boxable");
    public static ITag<Block> cattailLandSoil = VEBlockTags.bind("cattail_land_soil");
    public static ITag<Block> cattailWaterSoil = VEBlockTags.bind("cattail_water_soil");
    public static ITag<Block> endPlantable = VEBlockTags.bind("end_plantable");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Block> bind(String name)
    {
        return ForgeTagHandler.createOptionalTag(ForgeRegistries.BLOCKS,
                new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
