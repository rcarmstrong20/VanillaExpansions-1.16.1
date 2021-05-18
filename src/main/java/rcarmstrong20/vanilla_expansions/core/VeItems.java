package rcarmstrong20.vanilla_expansions.core;

import com.google.common.base.Suppliers;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Items;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.item.VeDrinkItem;
import rcarmstrong20.vanilla_expansions.item.VeFoods;
import rcarmstrong20.vanilla_expansions.item.VeGlassVialItem;
import rcarmstrong20.vanilla_expansions.item.VeMixedSeedPacketItem;
import rcarmstrong20.vanilla_expansions.item.VeSoupItem;
import rcarmstrong20.vanilla_expansions.item.VeTotemOfTheBruteItem;
import rcarmstrong20.vanilla_expansions.item.VeTotemOfTheFortunateItem;
import rcarmstrong20.vanilla_expansions.item.VeTotemOfTheGuardianItem;

/**
 *
 * A class for holding every item instance that vanilla expansions has.
 *
 * Note: all item names must be lower case or forge will crash the game.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            VanillaExpansions.MOD_ID);

    public static Item ruby = register("ruby", new Item(buildProperties()));
    public static Item bokChoySeeds = register("bok_choy_seeds",
            new BlockNamedItem(VeBlocks.bokChoy, buildProperties()));
    public static Item bokChoy = register("bok_choy", new Item(buildProperties(VeFoods.bokChoy)));
    public static Item garlic = register("garlic",
            new BlockNamedItem(VeBlocks.garlic, buildProperties(VeFoods.garlic)));
    public static Item greenOnion = register("green_onion",
            new BlockNamedItem(VeBlocks.greenOnions, buildProperties(VeFoods.greenOnion)));
    public static Item blueberries = register("blueberries",
            new BlockNamedItem(VeBlocks.blueberryBush, buildProperties(VeFoods.blueberries)));
    public static Item cranberries = register("cranberries",
            new BlockNamedItem(VeBlocks.cranberryBush, buildProperties(VeFoods.cranberries)));
    public static Item cranberrySauce = register("cranberry_sauce",
            new VeDrinkItem(buildProperties(VeFoods.cranberrySauce, 16)));
    public static Item noodles = register("noodles", new Item(buildProperties()));
    public static Item cookedNoodles = register("cooked_noodles", new Item(buildProperties(VeFoods.cookedNoodles)));
    public static Item noodleSoup = register("noodle_soup", new VeSoupItem(buildProperties(VeFoods.noodleSoup, 1)));
    public static Item pudding = register("pudding", new VeSoupItem(buildProperties(VeFoods.pudding, 1)));
    public static Item berryPudding = register("berry_pudding",
            new VeSoupItem(buildProperties(VeFoods.berryPudding, 1)));
    public static Item smokyQuartz = register("smoky_quartz", new Item(buildProperties()));
    public static Item darkMatterBucket = register("dark_matter_bucket",
            new BucketItem(Suppliers.ofInstance(VeFluids.darkMatter), buildProperties(1)));
    public static Item caramelApple = register("caramel_apple",
            new VeSoupItem(buildProperties(VeFoods.caramelApple, 1), Items.STICK));
    public static Item caramel = register("caramel", new Item(buildProperties()));
    public static Item spruceCone = register("spruce_cone", new Item(buildProperties(VeFoods.spruceCone)));
    public static Item forestsBounty = register("forests_bounty",
            new VeSoupItem(buildProperties(VeFoods.forestsBounty, 1)));
    public static Item witchsCradleBranch = register("witchs_cradle_branch",
            new BlockNamedItem(VeBlocks.witchsCradle, buildProperties(VeFoods.witchsCradleBranch)));
    public static Item witchsCradleSoup = register("witchs_cradle_soup",
            new VeSoupItem(buildProperties(VeFoods.witchsCradleSoup, 1)));
    public static Item glassVial = register("glass_vial", new VeGlassVialItem(buildProperties()));
    public static Item bloodVial = register("blood_vial",
            new VeDrinkItem(buildProperties(VeFoods.bloodVile, 1), VeItems.glassVial));
    public static Item mixedSeedPacket = register("mixed_seed_packet", new VeMixedSeedPacketItem(buildProperties()));
    public static Item totemOfTheGuardianI = register("totem_of_the_guardian_i",
            new VeTotemOfTheGuardianItem(buildProperties(16), 240));
    public static Item totemOfTheGuardianII = register("totem_of_the_guardian_ii",
            new VeTotemOfTheGuardianItem(buildProperties(16), 360));
    public static Item totemOfTheGuardianIII = register("totem_of_the_guardian_iii",
            new VeTotemOfTheGuardianItem(buildProperties(16), 480));
    public static Item totemOfTheGuardianIV = register("totem_of_the_guardian_iv",
            new VeTotemOfTheGuardianItem(buildProperties(16), 600));
    public static Item totemOfTheFortunateI = register("totem_of_the_fortunate_i",
            new VeTotemOfTheFortunateItem(buildProperties(16), 0));
    public static Item totemOfTheFortunateII = register("totem_of_the_fortunate_ii",
            new VeTotemOfTheFortunateItem(buildProperties(16), 1));
    public static Item totemOfTheFortunateIII = register("totem_of_the_fortunate_iii",
            new VeTotemOfTheFortunateItem(buildProperties(16), 2));
    public static Item totemOfTheFortunateIV = register("totem_of_the_fortunate_iv",
            new VeTotemOfTheFortunateItem(buildProperties(16), 3));
    public static Item totemOfTheBruteI = register("totem_of_the_brute_i",
            new VeTotemOfTheBruteItem(buildProperties(16), 0));
    public static Item totemOfTheBruteII = register("totem_of_the_brute_ii",
            new VeTotemOfTheBruteItem(buildProperties(16), 1));
    public static Item totemOfTheBruteIII = register("totem_of_the_brute_iii",
            new VeTotemOfTheBruteItem(buildProperties(16), 2));
    public static Item totemOfTheBruteIV = register("totem_of_the_brute_iv",
            new VeTotemOfTheBruteItem(buildProperties(16), 3));
    public static Item whiteTorch = register("white_torch",
            new WallOrFloorItem(VeBlocks.whiteTorch, VeBlocks.whiteWallTorch, buildProperties()));
    public static Item orangeTorch = register("orange_torch",
            new WallOrFloorItem(VeBlocks.orangeTorch, VeBlocks.orangeWallTorch, buildProperties()));
    public static Item magentaTorch = register("magenta_torch",
            new WallOrFloorItem(VeBlocks.magentaTorch, VeBlocks.magentaWallTorch, buildProperties()));
    public static Item lightBlueTorch = register("light_blue_torch",
            new WallOrFloorItem(VeBlocks.lightBlueTorch, VeBlocks.lightBlueWallTorch, buildProperties()));
    public static Item yellowTorch = register("yellow_torch",
            new WallOrFloorItem(VeBlocks.yellowTorch, VeBlocks.yellowWallTorch, buildProperties()));
    public static Item limeTorch = register("lime_torch",
            new WallOrFloorItem(VeBlocks.limeTorch, VeBlocks.limeWallTorch, buildProperties()));
    public static Item pinkTorch = register("pink_torch",
            new WallOrFloorItem(VeBlocks.pinkTorch, VeBlocks.pinkWallTorch, buildProperties()));
    public static Item grayTorch = register("gray_torch",
            new WallOrFloorItem(VeBlocks.grayTorch, VeBlocks.grayWallTorch, buildProperties()));
    public static Item lightGrayTorch = register("light_gray_torch",
            new WallOrFloorItem(VeBlocks.lightGrayTorch, VeBlocks.lightGrayWallTorch, buildProperties()));
    public static Item cyanTorch = register("cyan_torch",
            new WallOrFloorItem(VeBlocks.cyanTorch, VeBlocks.cyanWallTorch, buildProperties()));
    public static Item purpleTorch = register("purple_torch",
            new WallOrFloorItem(VeBlocks.purpleTorch, VeBlocks.purpleWallTorch, buildProperties()));
    public static Item blueTorch = register("blue_torch",
            new WallOrFloorItem(VeBlocks.blueTorch, VeBlocks.blueWallTorch, buildProperties()));
    public static Item brownTorch = register("brown_torch",
            new WallOrFloorItem(VeBlocks.brownTorch, VeBlocks.brownWallTorch, buildProperties()));
    public static Item greenTorch = register("green_torch",
            new WallOrFloorItem(VeBlocks.greenTorch, VeBlocks.greenWallTorch, buildProperties()));
    public static Item redTorch = register("red_torch",
            new WallOrFloorItem(VeBlocks.redTorch, VeBlocks.redWallTorch, buildProperties()));
    public static Item blackTorch = register("black_torch",
            new WallOrFloorItem(VeBlocks.blackTorch, VeBlocks.blackWallTorch, buildProperties()));
    public static Item mudBall = register("mud_ball", new Item(buildProperties()));
    public static Item mudBrick = register("mud_brick", new Item(buildProperties()));
    public static Item brownCattail = register("brown_cattail",
            new BlockNamedItem(VeBlocks.cattail, buildProperties()));
    public static Item greenCattail = register("green_cattail", new Item(buildProperties(VeFoods.greenCattail)));
    public static Item oceanEssence = register("ocean_essence", new Item(buildProperties()));
    public static Item rageEssence = register("rage_essence", new Item(buildProperties()));
    public static Item luckEssence = register("luck_essence", new Item(buildProperties()));

    /**
     * A helper method that builds default properties for a item.
     *
     * @return The properties for this item.
     */
    private static Properties buildProperties()
    {
        return new Item.Properties().tab(VanillaExpansions.VE_GROUP);
    }

    /**
     * A helper method that builds properties for a item with a custom max stack
     * size.
     *
     * @param maxStackSizeIn The max stack size for this item.
     * @return The properties for this item.
     */
    private static Properties buildProperties(int maxStackSizeIn)
    {
        return new Item.Properties().tab(VanillaExpansions.VE_GROUP).stacksTo(maxStackSizeIn);
    }

    /**
     * A helper method that builds properties for a food item.
     *
     * @param foodIn The stats of this food item.
     * @return The properties for this item.
     */
    private static Properties buildProperties(Food foodIn)
    {
        return new Item.Properties().tab(VanillaExpansions.VE_GROUP).food(foodIn);
    }

    /**
     * A helper method that builds properties for a food item with a custom stack
     * size.
     *
     * @param foodIn         The stats for this food item.
     * @param maxStackSizeIn The max stack size for this item.
     * @return The properties for this item.
     */
    private static Properties buildProperties(Food foodIn, int maxStackSizeIn)
    {
        return new Item.Properties().tab(VanillaExpansions.VE_GROUP).food(foodIn).stacksTo(maxStackSizeIn);
    }

    /**
     * @param name The name for the item.
     * @param item A new instance of the item class for this item.
     * @return A new item.
     */
    private static Item register(String name, Item item)
    {
        ITEMS.register(name, () -> item);
        return item;
    }
}
