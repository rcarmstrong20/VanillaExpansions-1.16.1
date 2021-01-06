package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 *
 * @author Ryan
 *
 *         A class for holding every block tag instance that vanilla expansions
 *         has.
 */
public class VeBlockTags
{
    public static ITag<Block> endBoneMealable = VeBlockTags.makeWrapperTag("end_bone_mealable");
    public static ITag<Block> endBoneMealPlants = VeBlockTags.makeWrapperTag("end_bone_meal_plants");
    public static ITag<Block> overworldPlantable = VeBlockTags.makeWrapperTag("overworld_plantable");
    public static ITag<Block> netherPlantable = VeBlockTags.makeWrapperTag("nether_plantable");
    public static ITag<Block> endPlantable = VeBlockTags.makeWrapperTag("end_plantable");
    public static ITag<Block> overworldPottable = VeBlockTags.makeWrapperTag("overworld_pottable");
    public static ITag<Block> netherPottable = VeBlockTags.makeWrapperTag("nether_pottable");
    public static ITag<Block> endPottable = VeBlockTags.makeWrapperTag("end_pottable");
    public static ITag<Block> singleCrops = VeBlockTags.makeWrapperTag("single_crops");

    /**
     * @param name The name of the tag.
     * @return The tag from the location or an empty tag if none exists.
     */
    private static ITag<Block> makeWrapperTag(String name)
    {
        return BlockTags.getCollection().get(new ResourceLocation(VanillaExpansions.MOD_ID, name));
    }
}
