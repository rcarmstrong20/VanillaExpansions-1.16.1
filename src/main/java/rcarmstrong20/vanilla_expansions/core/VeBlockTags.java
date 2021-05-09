package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every block tag instance that vanilla expansions has.
 *
 * @author Ryan
 */
public class VeBlockTags
{
    public static ITag<Block> endBoneMealable = VeBlockTags.bind("end_bone_mealable");
    public static ITag<Block> endBoneMealPlants = VeBlockTags.bind("end_bone_meal_plants");
    public static ITag<Block> overworldPlantable = VeBlockTags.bind("overworld_plantable");
    public static ITag<Block> netherPlantable = VeBlockTags.bind("nether_plantable");
    public static ITag<Block> endPlantable = VeBlockTags.bind("end_plantable");
    public static ITag<Block> overworldPottable = VeBlockTags.bind("overworld_pottable");
    public static ITag<Block> netherPottable = VeBlockTags.bind("nether_pottable");
    public static ITag<Block> endPottable = VeBlockTags.bind("end_pottable");
    public static ITag<Block> singleCrops = VeBlockTags.bind("single_crops");
    public static ITag<Block> cattailLandSoil = VeBlockTags.bind("cattail_land_soil");
    public static ITag<Block> cattailWaterSoil = VeBlockTags.bind("cattail_water_soil");
    public static ITag<Block> fertileSoil = VeBlockTags.bind("fertile_soil");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Block> bind(String name)
    {
        return BlockTags.createOptional(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
