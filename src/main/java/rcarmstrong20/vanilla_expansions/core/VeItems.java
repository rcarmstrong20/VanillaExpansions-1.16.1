package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Suppliers;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Items;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.item.VeDrinkItem;
import rcarmstrong20.vanilla_expansions.item.VeFoods;
import rcarmstrong20.vanilla_expansions.item.VeGlassVialItem;
import rcarmstrong20.vanilla_expansions.item.VeMixedSeedPacketItem;
import rcarmstrong20.vanilla_expansions.item.VeSoupItem;
import rcarmstrong20.vanilla_expansions.item.VeTotemOfTheFortunateItem;

/**
 *
 * @author Ryan
 *
 *         A class for holding every item instance that vanilla expansions has.
 *
 *         Note: all item names must be lower case or forge will crash the game.
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeItems
{
    private static final Item.Properties VE_ITEMS = new Item.Properties().group(VanillaExpansions.VE_GROUP);
    private static final Item.Properties VE_16_STACK_ITEMS = new Item.Properties().maxStackSize(16)
            .group(VanillaExpansions.VE_GROUP);

    private static final List<Item> ITEMS = new ArrayList<>();

    public static Item ruby = register("ruby", new Item(VE_ITEMS));
    public static Item bokChoySeeds = register("bok_choy_seeds", new BlockNamedItem(VeBlocks.bokChoy, VE_ITEMS));
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
            new VeDrinkItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(16)
                    .group(VanillaExpansions.VE_GROUP).food(VeFoods.cranberrySauce)));
    public static Item noodles = register("noodles", new Item(VE_ITEMS));
    public static Item cookedNoodles = register("cooked_noodles", new Item(buildProperties(VeFoods.cookedNoodles)));
    public static Item noodleSoup = register("noodle_soup", new VeSoupItem(new Item.Properties()
            .containerItem(Items.BOWL).maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.noodleSoup)));
    public static Item pudding = register("pudding", new VeSoupItem(
            new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.pudding)));
    public static Item berryPudding = register("berry_pudding", new VeSoupItem(
            new Item.Properties().maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.berryPudding)));
    public static Item smokyQuartz = register("smoky_quartz", new Item(VE_ITEMS));
    public static Item darkMatterBucket = register("dark_matter_bucket", new BucketItem(
            Suppliers.ofInstance(VeFluids.darkMatter),
            new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(VanillaExpansions.VE_GROUP)));
    public static Item caramelApple = register("caramel_apple", new VeSoupItem(new Item.Properties().maxStackSize(1)
            .containerItem(Items.STICK).group(VanillaExpansions.VE_GROUP).food(VeFoods.caramelApple), Items.STICK));
    public static Item caramel = register("caramel", new Item(VE_ITEMS));
    public static Item spruceCone = register("spruce_cone", new Item(buildProperties(VeFoods.spruceCone)));
    public static Item forestsBounty = register("forests_bounty", new VeSoupItem(new Item.Properties()
            .containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP).maxStackSize(1).food(VeFoods.forestsBounty)));
    public static Item witchsCradleBranch = register("witchs_cradle_branch", new BlockNamedItem(VeBlocks.witchsCradle,
            new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.witchsCradleBranch)));
    public static Item witchsCradleSoup = register("witchs_cradle_soup",
            new VeSoupItem(new Item.Properties().containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP)
                    .maxStackSize(1).food(VeFoods.witchsCradleSoup)));
    public static Item glassVial = register("glass_vial", new VeGlassVialItem(VE_ITEMS));
    public static Item bloodVial = register("blood_vial",
            new VeDrinkItem(new Item.Properties().containerItem(VeItems.glassVial).group(VanillaExpansions.VE_GROUP)
                    .maxStackSize(16).food(VeFoods.bloodVile), VeItems.glassVial));
    public static Item mixedSeedPacket = register("mixed_seed_packet",
            new VeMixedSeedPacketItem(new Item.Properties().group(VanillaExpansions.VE_GROUP)));
    public static Item blankTotem = register("blank_totem", new Item(VE_16_STACK_ITEMS));
    public static Item totemOfTheGuardianI = register("totem_of_the_guardian_i", new Item(VE_16_STACK_ITEMS));
    public static Item totemOfTheGuardianII = register("totem_of_the_guardian_ii", new Item(VE_16_STACK_ITEMS));
    public static Item totemOfTheGuardianIII = register("totem_of_the_guardian_iii", new Item(VE_16_STACK_ITEMS));
    public static Item totemOfTheFortunateI = register("totem_of_the_fortunate_i",
            new VeTotemOfTheFortunateItem(VE_16_STACK_ITEMS, 0));
    public static Item totemOfTheFortunateII = register("totem_of_the_fortunate_ii",
            new VeTotemOfTheFortunateItem(VE_16_STACK_ITEMS, 1));
    public static Item totemOfTheFortunateIII = register("totem_of_the_fortunate_iii",
            new VeTotemOfTheFortunateItem(VE_16_STACK_ITEMS, 2));
    public static Item totemOfTheBruteI = register("totem_of_the_brute_i", new Item(VE_16_STACK_ITEMS));
    public static Item totemOfTheBruteII = register("totem_of_the_brute_ii", new Item(VE_16_STACK_ITEMS));
    public static Item totemOfTheBruteIII = register("totem_of_the_brute_iii", new Item(VE_16_STACK_ITEMS));
    public static Item whiteTorch = register("white_torch",
            new WallOrFloorItem(VeBlocks.whiteTorch, VeBlocks.whiteWallTorch, VE_ITEMS));
    public static Item orangeTorch = register("orange_torch",
            new WallOrFloorItem(VeBlocks.orangeTorch, VeBlocks.orangeWallTorch, VE_ITEMS));
    public static Item magentaTorch = register("magenta_torch",
            new WallOrFloorItem(VeBlocks.magentaTorch, VeBlocks.magentaWallTorch, VE_ITEMS));
    public static Item lightBlueTorch = register("light_blue_torch",
            new WallOrFloorItem(VeBlocks.lightBlueTorch, VeBlocks.lightBlueWallTorch, VE_ITEMS));
    public static Item yellowTorch = register("yellow_torch",
            new WallOrFloorItem(VeBlocks.yellowTorch, VeBlocks.yellowWallTorch, VE_ITEMS));
    public static Item limeTorch = register("lime_torch",
            new WallOrFloorItem(VeBlocks.limeTorch, VeBlocks.limeWallTorch, VE_ITEMS));
    public static Item pinkTorch = register("pink_torch",
            new WallOrFloorItem(VeBlocks.pinkTorch, VeBlocks.pinkWallTorch, VE_ITEMS));
    public static Item grayTorch = register("gray_torch",
            new WallOrFloorItem(VeBlocks.grayTorch, VeBlocks.grayWallTorch, VE_ITEMS));
    public static Item lightGrayTorch = register("light_gray_torch",
            new WallOrFloorItem(VeBlocks.lightGrayTorch, VeBlocks.lightGrayWallTorch, VE_ITEMS));
    public static Item cyanTorch = register("cyan_torch",
            new WallOrFloorItem(VeBlocks.cyanTorch, VeBlocks.cyanWallTorch, VE_ITEMS));
    public static Item purpleTorch = register("purple_torch",
            new WallOrFloorItem(VeBlocks.purpleTorch, VeBlocks.purpleWallTorch, VE_ITEMS));
    public static Item blueTorch = register("blue_torch",
            new WallOrFloorItem(VeBlocks.blueTorch, VeBlocks.blueWallTorch, VE_ITEMS));
    public static Item brownTorch = register("brown_torch",
            new WallOrFloorItem(VeBlocks.brownTorch, VeBlocks.brownWallTorch, VE_ITEMS));
    public static Item greenTorch = register("green_torch",
            new WallOrFloorItem(VeBlocks.greenTorch, VeBlocks.greenWallTorch, VE_ITEMS));
    public static Item redTorch = register("red_torch",
            new WallOrFloorItem(VeBlocks.redTorch, VeBlocks.redWallTorch, VE_ITEMS));
    public static Item blackTorch = register("black_torch",
            new WallOrFloorItem(VeBlocks.blackTorch, VeBlocks.blackWallTorch, VE_ITEMS));

    /**
     * A helper method that builds properties for a food item.
     *
     * @param foodIn The stats of this food.
     * @return The properties for this item.
     */
    private static Properties buildProperties(Food foodIn)
    {
        return new Item.Properties().group(VanillaExpansions.VE_GROUP).food(foodIn);
    }

    /**
     * @param name The name for the item.
     * @param item A new instance of the item class for this item.
     * @return A new item.
     */
    private static Item register(String name, Item item)
    {
        item.setRegistryName(VanillaExpansions.MOD_ID, name);
        ITEMS.add(item);
        return item;
    }

    /*
     * Register the Items to the game.
     */
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        ITEMS.forEach(item -> event.getRegistry().register(item));
        ITEMS.clear();

        VanillaExpansions.LOGGER.info("Items registered.");
    }
}
