package rndmaccess.vanilla_expansions.core;

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
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.item.VEDrinkItem;
import rndmaccess.vanilla_expansions.item.VEFoods;
import rndmaccess.vanilla_expansions.item.VEGlassVialItem;
import rndmaccess.vanilla_expansions.item.VEMixedSeedPacketItem;
import rndmaccess.vanilla_expansions.item.VESoupItem;
import rndmaccess.vanilla_expansions.item.VETotemOfTheBruteItem;
import rndmaccess.vanilla_expansions.item.VETotemOfTheFortunateItem;
import rndmaccess.vanilla_expansions.item.VETotemOfTheGuardianItem;
import rndmaccess.vanilla_expansions.item.VETotemOfTheMinerItem;
import rndmaccess.vanilla_expansions.item.VETotemOfThePhantomItem;

/**
 *
 * A class for holding every item instance that vanilla expansions has.
 *
 * Note: All item instance names must have lower case letters or forge will
 * think the items belong to vanilla Minecraft and will give a warning.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            VanillaExpansions.MOD_ID);

    public static Item ruby = register("ruby", new Item(buildProperties()));
    public static Item bokChoySeeds = register("bok_choy_seeds",
            new BlockNamedItem(VEBlocks.bokChoy, buildProperties()));
    public static Item bokChoy = register("bok_choy", new Item(buildProperties(VEFoods.bokChoy)));
    public static Item garlic = register("garlic",
            new BlockNamedItem(VEBlocks.garlic, buildProperties(VEFoods.garlic)));
    public static Item greenOnion = register("green_onion",
            new BlockNamedItem(VEBlocks.greenOnions, buildProperties(VEFoods.greenOnion)));
    public static Item blueberries = register("blueberries",
            new BlockNamedItem(VEBlocks.blueberryBush, buildProperties(VEFoods.blueberries)));
    public static Item cranberries = register("cranberries",
            new BlockNamedItem(VEBlocks.cranberryBush, buildProperties(VEFoods.cranberries)));
    public static Item cranberrySauce = register("cranberry_sauce",
            new VEDrinkItem(buildProperties(VEFoods.cranberrySauce, 16)));
    public static Item noodles = register("noodles", new Item(buildProperties()));
    public static Item cookedNoodles = register("cooked_noodles", new Item(buildProperties(VEFoods.cookedNoodles)));
    public static Item noodleSoup = register("noodle_soup", new VESoupItem(buildProperties(VEFoods.noodleSoup, 1)));
    public static Item pudding = register("pudding", new VESoupItem(buildProperties(VEFoods.pudding, 1)));
    public static Item berryPudding = register("berry_pudding",
            new VESoupItem(buildProperties(VEFoods.berryPudding, 1)));
    public static Item smokyQuartz = register("smoky_quartz", new Item(buildProperties()));
    public static Item darkMatterBucket = register("dark_matter_bucket",
            new BucketItem(Suppliers.ofInstance(VEFluids.darkMatter), buildProperties(1)));
    public static Item caramelApple = register("caramel_apple",
            new VESoupItem(buildProperties(VEFoods.caramelApple, 1), Items.STICK));
    public static Item caramel = register("caramel", new Item(buildProperties()));
    public static Item spruceCone = register("spruce_cone", new Item(buildProperties(VEFoods.spruceCone)));
    public static Item forestsBounty = register("forests_bounty",
            new VESoupItem(buildProperties(VEFoods.forestsBounty, 1)));
    public static Item witchsCradleBranch = register("witchs_cradle_branch",
            new BlockNamedItem(VEBlocks.witchsCradle, buildProperties(VEFoods.witchsCradleBranch)));
    public static Item witchsCradleSoup = register("witchs_cradle_soup",
            new VESoupItem(buildProperties(VEFoods.witchsCradleSoup, 1)));
    public static Item glassVial = register("glass_vial", new VEGlassVialItem(buildProperties()));
    public static Item bloodVial = register("blood_vial",
            new VEDrinkItem(buildProperties(VEFoods.bloodVile, 1), VEItems.glassVial));
    public static Item mixedSeedPacket = register("mixed_seed_packet", new VEMixedSeedPacketItem(buildProperties()));
    public static Item whiteTorch = register("white_torch",
            new WallOrFloorItem(VEBlocks.whiteTorch, VEBlocks.whiteWallTorch, buildProperties()));
    public static Item orangeTorch = register("orange_torch",
            new WallOrFloorItem(VEBlocks.orangeTorch, VEBlocks.orangeWallTorch, buildProperties()));
    public static Item magentaTorch = register("magenta_torch",
            new WallOrFloorItem(VEBlocks.magentaTorch, VEBlocks.magentaWallTorch, buildProperties()));
    public static Item lightBlueTorch = register("light_blue_torch",
            new WallOrFloorItem(VEBlocks.lightBlueTorch, VEBlocks.lightBlueWallTorch, buildProperties()));
    public static Item yellowTorch = register("yellow_torch",
            new WallOrFloorItem(VEBlocks.yellowTorch, VEBlocks.yellowWallTorch, buildProperties()));
    public static Item limeTorch = register("lime_torch",
            new WallOrFloorItem(VEBlocks.limeTorch, VEBlocks.limeWallTorch, buildProperties()));
    public static Item pinkTorch = register("pink_torch",
            new WallOrFloorItem(VEBlocks.pinkTorch, VEBlocks.pinkWallTorch, buildProperties()));
    public static Item grayTorch = register("gray_torch",
            new WallOrFloorItem(VEBlocks.grayTorch, VEBlocks.grayWallTorch, buildProperties()));
    public static Item lightGrayTorch = register("light_gray_torch",
            new WallOrFloorItem(VEBlocks.lightGrayTorch, VEBlocks.lightGrayWallTorch, buildProperties()));
    public static Item cyanTorch = register("cyan_torch",
            new WallOrFloorItem(VEBlocks.cyanTorch, VEBlocks.cyanWallTorch, buildProperties()));
    public static Item purpleTorch = register("purple_torch",
            new WallOrFloorItem(VEBlocks.purpleTorch, VEBlocks.purpleWallTorch, buildProperties()));
    public static Item blueTorch = register("blue_torch",
            new WallOrFloorItem(VEBlocks.blueTorch, VEBlocks.blueWallTorch, buildProperties()));
    public static Item brownTorch = register("brown_torch",
            new WallOrFloorItem(VEBlocks.brownTorch, VEBlocks.brownWallTorch, buildProperties()));
    public static Item greenTorch = register("green_torch",
            new WallOrFloorItem(VEBlocks.greenTorch, VEBlocks.greenWallTorch, buildProperties()));
    public static Item redTorch = register("red_torch",
            new WallOrFloorItem(VEBlocks.redTorch, VEBlocks.redWallTorch, buildProperties()));
    public static Item blackTorch = register("black_torch",
            new WallOrFloorItem(VEBlocks.blackTorch, VEBlocks.blackWallTorch, buildProperties()));
    public static Item coalTotemOfTheGuardian = register("coal_totem_of_the_guardian",
            new VETotemOfTheGuardianItem(buildProperties(16), 120));
    public static Item ironTotemOfTheGuardian = register("iron_totem_of_the_guardian",
            new VETotemOfTheGuardianItem(buildProperties(16), 240));
    public static Item goldTotemOfTheGuardian = register("gold_totem_of_the_guardian",
            new VETotemOfTheGuardianItem(buildProperties(16), 360));
    public static Item diamondTotemOfTheGuardian = register("diamond_totem_of_the_guardian",
            new VETotemOfTheGuardianItem(buildProperties(16), 480));
    public static Item coalTotemOfTheFortunate = register("coal_totem_of_the_fortunate",
            new VETotemOfTheFortunateItem(buildProperties(16), 0));
    public static Item ironTotemOfTheFortunate = register("iron_totem_of_the_fortunate",
            new VETotemOfTheFortunateItem(buildProperties(16), 1));
    public static Item goldTotemOfTheFortunate = register("gold_totem_of_the_fortunate",
            new VETotemOfTheFortunateItem(buildProperties(16), 2));
    public static Item diamondTotemOfTheFortunate = register("diamond_totem_of_the_fortunate",
            new VETotemOfTheFortunateItem(buildProperties(16), 3));
    public static Item coalTotemOfTheBrute = register("coal_totem_of_the_brute",
            new VETotemOfTheBruteItem(buildProperties(16), 0));
    public static Item ironTotemOfTheBrute = register("iron_totem_of_the_brute",
            new VETotemOfTheBruteItem(buildProperties(16), 1));
    public static Item goldTotemOfTheBrute = register("gold_totem_of_the_brute",
            new VETotemOfTheBruteItem(buildProperties(16), 2));
    public static Item diamondTotemOfTheBrute = register("diamond_totem_of_the_brute",
            new VETotemOfTheBruteItem(buildProperties(16), 3));
    public static Item coalTotemOfTheMiner = register("coal_totem_of_the_miner",
            new VETotemOfTheMinerItem(buildProperties(16), 0));
    public static Item ironTotemOfTheMiner = register("iron_totem_of_the_miner",
            new VETotemOfTheMinerItem(buildProperties(16), 1));
    public static Item goldTotemOfTheMiner = register("gold_totem_of_the_miner",
            new VETotemOfTheMinerItem(buildProperties(16), 2));
    public static Item diamondTotemOfTheMiner = register("diamond_totem_of_the_miner",
            new VETotemOfTheMinerItem(buildProperties(16), 3));
    public static Item coalTotemOfThePhantom = register("coal_totem_of_the_phantom",
            new VETotemOfThePhantomItem(buildProperties(16), 0));
    public static Item ironTotemOfThePhantom = register("iron_totem_of_the_phantom",
            new VETotemOfThePhantomItem(buildProperties(16), 1));
    public static Item goldTotemOfThePhantom = register("gold_totem_of_the_phantom",
            new VETotemOfThePhantomItem(buildProperties(16), 2));
    public static Item diamondTotemOfThePhantom = register("diamond_totem_of_the_phantom",
            new VETotemOfThePhantomItem(buildProperties(16), 3));
    public static Item mudBall = register("mud_ball", new Item(buildProperties()));
    public static Item mudBrick = register("mud_brick", new Item(buildProperties()));
    public static Item brownCattail = register("brown_cattail",
            new BlockNamedItem(VEBlocks.cattail, buildProperties()));
    public static Item greenCattail = register("green_cattail", new Item(buildProperties(VEFoods.greenCattail)));
    public static Item oceanEssence = register("ocean_essence", new Item(buildProperties()));
    public static Item rageEssence = register("rage_essence", new Item(buildProperties()));
    public static Item luckEssence = register("luck_essence", new Item(buildProperties()));
    public static Item stoneEssence = register("stone_essence", new Item(buildProperties()));
    public static Item midnightEssence = register("midnight_essence", new Item(buildProperties()));

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
