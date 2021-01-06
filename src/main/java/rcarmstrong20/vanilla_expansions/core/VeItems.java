package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Suppliers;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SoupItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.enums.VeArmorMaterial;
import rcarmstrong20.vanilla_expansions.enums.VeItemTier;
import rcarmstrong20.vanilla_expansions.item.VeDrinkItem;
import rcarmstrong20.vanilla_expansions.item.VeFoods;
import rcarmstrong20.vanilla_expansions.item.VeGlassVialItem;
import rcarmstrong20.vanilla_expansions.item.VeMixedSeedPacketItem;
import rcarmstrong20.vanilla_expansions.item.VePickaxeItem;
import rcarmstrong20.vanilla_expansions.item.VeShovelItem;
import rcarmstrong20.vanilla_expansions.item.VeSoupItem;
import rcarmstrong20.vanilla_expansions.item.VeSwordItem;
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
    // Item Property Presets

    public static final Item.Properties VE_ITEMS = new Item.Properties().group(VanillaExpansions.VE_GROUP);
    public static final Item.Properties VE_SINGLE_ITEMS = new Item.Properties().maxStackSize(1)
            .group(VanillaExpansions.VE_GROUP);
    public static final Item.Properties VE_16_STACK_ITEMS = new Item.Properties().maxStackSize(16)
            .group(VanillaExpansions.VE_GROUP);
    public static final Item.Properties VE_RARE_16_STACK_ITEMS = new Item.Properties().maxStackSize(16)
            .group(VanillaExpansions.VE_GROUP);

    private static final List<Item> ITEMS = new ArrayList<>();

    // Vanilla Expansions Items
    public static Item ruby = register("ruby", new Item(VE_ITEMS));
    public static Item rubyAxe = register("ruby_axe", new AxeItem(VeItemTier.RUBY, 5.0F, -2.8F,
            new Item.Properties().addToolType(ToolType.AXE, 4).group(VanillaExpansions.VE_GROUP)));
    public static Item rubyPickaxe = register("ruby_pickaxe", new VePickaxeItem(VeItemTier.RUBY, 1,
            new Item.Properties().addToolType(ToolType.PICKAXE, 4).group(VanillaExpansions.VE_GROUP)));
    public static Item rubyShovel = register("ruby_shovel", new VeShovelItem(VeItemTier.RUBY, 1.5F,
            new Item.Properties().addToolType(ToolType.SHOVEL, 4).group(VanillaExpansions.VE_GROUP)));
    public static Item rubySword = register("ruby_sword", new VeSwordItem(VeItemTier.RUBY, 3, VE_SINGLE_ITEMS));
    public static Item rubyHoe = register("ruby_hoe", new HoeItem(VeItemTier.RUBY, -4, 1, VE_SINGLE_ITEMS));
    public static Item rubyHelmet = register("ruby_helmet",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
    public static Item rubyChestplate = register("ruby_chestplate",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
    public static Item rubyLeggings = register("ruby_leggings",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
    public static Item rubyBoots = register("ruby_boots",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
    public static Item rubyHorseArmor = register("ruby_horse_armor", 12, "ruby");
    public static Item emeraldAxe = register("emerald_axe", new AxeItem(VeItemTier.EMERALD, 6.0F, -3.0F,
            new Item.Properties().addToolType(ToolType.AXE, 2).group(VanillaExpansions.VE_GROUP)));
    public static Item emeraldPickaxe = register("emerald_pickaxe", new VePickaxeItem(VeItemTier.EMERALD, 1,
            new Item.Properties().addToolType(ToolType.PICKAXE, 2).group(VanillaExpansions.VE_GROUP)));
    public static Item emeraldShovel = register("emerald_shovel", new VeShovelItem(VeItemTier.EMERALD, 2,
            new Item.Properties().addToolType(ToolType.SHOVEL, 2).group(VanillaExpansions.VE_GROUP)));
    public static Item emeraldSword = register("emerald_sword",
            new VeSwordItem(VeItemTier.EMERALD, 4, VE_SINGLE_ITEMS));
    public static Item emeraldHoe = register("emerald_hoe", new HoeItem(VeItemTier.EMERALD, -2, 0, VE_SINGLE_ITEMS));
    public static Item emeraldHelmet = register("emerald_helmet",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
    public static Item emeraldChestplate = register("emerald_chestplate",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
    public static Item emeraldLeggings = register("emerald_leggings",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
    public static Item emeraldBoots = register("emerald_boots",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
    public static Item emeraldHorseArmor = register("emerald_horse_armor", 8, "emerald");
    public static Item bokChoySeeds = register("bok_choy_seeds", new BlockNamedItem(VeBlocks.bokChoy, VE_ITEMS));
    public static Item bokChoy = register("bok_choy", new Item(VE_ITEMS));
    public static Item garlic = register("garlic", new BlockNamedItem(VeBlocks.garlic, VE_ITEMS));
    public static Item greenOnion = register("green_onion", new BlockNamedItem(VeBlocks.greenOnions, VE_ITEMS));
    public static Item quinoa = register("quinoa", new BlockNamedItem(VeBlocks.quinoa, VE_ITEMS));
    public static Item blueberries = register("blueberries", new BlockNamedItem(VeBlocks.blueberryBush,
            new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.BLUEBERRIES)));
    public static Item cranberries = register("cranberries", new BlockNamedItem(VeBlocks.cranberryBush,
            new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRIES)));
    public static Item cranberrySauce = register("cranberry_sauce",
            new VeDrinkItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(16)
                    .group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRY_SAUCE)));
    public static Item noodles = register("noodles", new Item(VE_ITEMS));
    public static Item cookedNoodles = register("cooked_noodles", new Item(VE_ITEMS));
    public static Item noodleSoup = register("noodle_soup", new VeSoupItem(new Item.Properties()
            .containerItem(Items.BOWL).maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.NOODLE_SOUP)));
    public static Item noodleBowl = register("noodle_bowl", new Item(VE_ITEMS));
    public static Item quinoaCerceal = register("quinoa_cerceal", new SoupItem(new Item.Properties().maxStackSize(1)
            .containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP).food(VeFoods.QUINOA_CERCEAL)));
    public static Item smokyQuartz = register("smoky_quartz", new Item(VE_ITEMS));
    public static Item darkMatterBucket = register("dark_matter_bucket",
            new BucketItem(Suppliers.ofInstance(VeFluids.darkMatter), new Item.Properties().containerItem(Items.BUCKET)
                    .maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.DARK_MATTER_BUCKET)));
    public static Item caramelApple = register("caramel_apple", new VeSoupItem(new Item.Properties().maxStackSize(1)
            .containerItem(Items.STICK).group(VanillaExpansions.VE_GROUP).food(VeFoods.CARAMEL_APPLE)));
    public static Item caramel = register("caramel", new Item(VE_ITEMS));
    public static Item spruceCone = register("spruce_cone",
            new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.SPRUCE_CONE)));
    public static Item forestsBounty = register("forests_bounty", new SoupItem(new Item.Properties()
            .containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP).maxStackSize(1).food(VeFoods.FORESTS_BOUNTY)));
    public static Item witchsCradleBranch = register("witchs_cradle_branch", new BlockNamedItem(VeBlocks.witchsCradle,
            new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.WITCHS_CRADLE_BRANCH)));
    public static Item witchsCradleSoup = register("witchs_cradle_soup",
            new VeSoupItem(new Item.Properties().containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP)
                    .maxStackSize(1).food(VeFoods.WITCHS_CRADLE_SOUP)));
    public static Item glassVial = register("glass_vial", new VeGlassVialItem(VE_ITEMS));
    public static Item bloodVial = register("blood_vial",
            new VeDrinkItem(new Item.Properties().containerItem(VeItems.glassVial).group(VanillaExpansions.VE_GROUP)
                    .maxStackSize(16).food(VeFoods.BLOOD_VIAL)));
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

    /**
     * @param name          The horse armor name for the item.
     * @param strength      The strength level for the horse armor.
     * @param armorMaterial The armor material name.
     * @return A new horse armor item.
     */
    private static Item register(String name, int strength, String armorMaterial)
    {
        Item item = new HorseArmorItem(strength, new ResourceLocation(VanillaExpansions.MOD_ID,
                "textures/entity/horse/armor/horse_armor_" + armorMaterial + ".png"), VE_SINGLE_ITEMS);
        item.setRegistryName(VanillaExpansions.MOD_ID, name);
        ITEMS.add(item);
        return item;
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
     * Register the Items to the game
     */
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        ITEMS.forEach(item -> event.getRegistry().register(item));
        ITEMS.clear();

        VanillaExpansions.LOGGER.info("Items registered.");
    }
}
