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
import rcarmstrong20.vanilla_expansions.item.VeGlassVialItem;
import rcarmstrong20.vanilla_expansions.item.VeMixedSeedPacketItem;
import rcarmstrong20.vanilla_expansions.item.VePickaxeItem;
import rcarmstrong20.vanilla_expansions.item.VeShovelItem;
import rcarmstrong20.vanilla_expansions.item.VeSoupItem;
import rcarmstrong20.vanilla_expansions.item.VeSwordItem;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeItems
{
    // Item Property Presets

    public static final Item.Properties VE_ITEMS = new Item.Properties().group(VanillaExpansions.VE_GROUP);
    public static final Item.Properties VE_SINGLE_ITEMS = new Item.Properties().maxStackSize(1)
            .group(VanillaExpansions.VE_GROUP);
    private static final List<Item> ITEMS = new ArrayList<>();

    // Vanilla Expansions Items
    public static Item ruby = register("ruby", new Item(VE_ITEMS));
    public static Item ruby_axe = register("ruby_axe", new AxeItem(VeItemTier.RUBY, 5.0F, -2.8F,
            new Item.Properties().addToolType(ToolType.AXE, 4).group(VanillaExpansions.VE_GROUP)));
    public static Item ruby_pickaxe = register("ruby_pickaxe", new VePickaxeItem(VeItemTier.RUBY, 1,
            new Item.Properties().addToolType(ToolType.PICKAXE, 4).group(VanillaExpansions.VE_GROUP)));
    public static Item ruby_shovel = register("ruby_shovel", new VeShovelItem(VeItemTier.RUBY, 1.5F,
            new Item.Properties().addToolType(ToolType.SHOVEL, 4).group(VanillaExpansions.VE_GROUP)));
    public static Item ruby_sword = register("ruby_sword", new VeSwordItem(VeItemTier.RUBY, 3, VE_SINGLE_ITEMS));
    public static Item ruby_hoe = register("ruby_hoe", new HoeItem(VeItemTier.RUBY, -4, 1, VE_SINGLE_ITEMS));
    public static Item ruby_helmet = register("ruby_helmet",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
    public static Item ruby_chestplate = register("ruby_chestplate",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
    public static Item ruby_leggings = register("ruby_leggings",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
    public static Item ruby_boots = register("ruby_boots",
            new ArmorItem(VeArmorMaterial.RUBY, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
    public static Item ruby_horse_armor = register("ruby_horse_armor", 12, "ruby");
    public static Item emerald_axe = register("emerald_axe", new AxeItem(VeItemTier.EMERALD, 6.0F, -3.0F,
            new Item.Properties().addToolType(ToolType.AXE, 2).group(VanillaExpansions.VE_GROUP)));
    public static Item emerald_pickaxe = register("emerald_pickaxe", new VePickaxeItem(VeItemTier.EMERALD, 1,
            new Item.Properties().addToolType(ToolType.PICKAXE, 2).group(VanillaExpansions.VE_GROUP)));
    public static Item emerald_shovel = register("emerald_shovel", new VeShovelItem(VeItemTier.EMERALD, 2,
            new Item.Properties().addToolType(ToolType.SHOVEL, 2).group(VanillaExpansions.VE_GROUP)));
    public static Item emerald_sword = register("emerald_sword",
            new VeSwordItem(VeItemTier.EMERALD, 4, VE_SINGLE_ITEMS));
    public static Item emerald_hoe = register("emerald_hoe", new HoeItem(VeItemTier.EMERALD, -2, 0, VE_SINGLE_ITEMS));
    public static Item emerald_helmet = register("emerald_helmet",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.HEAD, VE_SINGLE_ITEMS));
    public static Item emerald_chestplate = register("emerald_chestplate",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.CHEST, VE_SINGLE_ITEMS));
    public static Item emerald_leggings = register("emerald_leggings",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.LEGS, VE_SINGLE_ITEMS));
    public static Item emerald_boots = register("emerald_boots",
            new ArmorItem(VeArmorMaterial.EMERALD, EquipmentSlotType.FEET, VE_SINGLE_ITEMS));
    public static Item emerald_horse_armor = register("emerald_horse_armor", 8, "emerald");
    public static Item bok_choy_seeds = register("bok_choy_seeds", new BlockNamedItem(VeBlocks.bok_choy, VE_ITEMS));
    public static Item bok_choy = register("bok_choy", new Item(VE_ITEMS));
    public static Item garlic = register("garlic", new BlockNamedItem(VeBlocks.garlic, VE_ITEMS));
    public static Item green_onion = register("green_onion", new BlockNamedItem(VeBlocks.green_onions, VE_ITEMS));
    public static Item quinoa = register("quinoa", new BlockNamedItem(VeBlocks.quinoa, VE_ITEMS));
    public static Item blueberries = register("blueberries", new BlockNamedItem(VeBlocks.blueberry_bush,
            new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.BLUEBERRIES)));
    public static Item cranberries = register("cranberries", new BlockNamedItem(VeBlocks.cranberry_bush,
            new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRIES)));
    public static Item cranberry_sauce = register("cranberry_sauce",
            new VeDrinkItem(new Item.Properties().containerItem(Items.GLASS_BOTTLE).maxStackSize(16)
                    .group(VanillaExpansions.VE_GROUP).food(VeFoods.CRANBERRY_SAUCE)));
    public static Item noodles = register("noodles", new Item(VE_ITEMS));
    public static Item cooked_noodles = register("cooked_noodles", new Item(VE_ITEMS));
    public static Item noodle_soup = register("noodle_soup", new VeSoupItem(new Item.Properties()
            .containerItem(Items.BOWL).maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.NOODLE_SOUP)));
    public static Item noodle_bowl = register("noodle_bowl", new Item(VE_ITEMS));
    public static Item quinoa_cerceal = register("quinoa_cerceal", new SoupItem(new Item.Properties().maxStackSize(1)
            .containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP).food(VeFoods.QUINOA_CERCEAL)));
    public static Item smoky_quartz = register("smoky_quartz", new Item(VE_ITEMS));
    public static Item dark_matter_bucket = register("dark_matter_bucket",
            new BucketItem(Suppliers.ofInstance(VeFluids.dark_matter), new Item.Properties().containerItem(Items.BUCKET)
                    .maxStackSize(1).group(VanillaExpansions.VE_GROUP).food(VeFoods.VOID_BUCKET)));
    public static Item caramel_apple = register("caramel_apple", new VeSoupItem(new Item.Properties().maxStackSize(1)
            .containerItem(Items.STICK).group(VanillaExpansions.VE_GROUP).food(VeFoods.CARAMEL_APPLE)));
    public static Item caramel = register("caramel", new Item(VE_ITEMS));
    public static Item spruce_cone = register("spruce_cone",
            new Item(new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.SPRUCE_CONE)));
    public static Item forests_bounty = register("forests_bounty", new SoupItem(new Item.Properties()
            .containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP).maxStackSize(1).food(VeFoods.FORESTS_BOUNTY)));
    public static Item witchs_cradle_branch = register("witchs_cradle_branch",
            new BlockNamedItem(VeBlocks.witchs_cradle,
                    new Item.Properties().group(VanillaExpansions.VE_GROUP).food(VeFoods.WITCHS_CRADLE_BRANCH)));
    public static Item witchs_cradle_soup = register("witchs_cradle_soup",
            new VeSoupItem(new Item.Properties().containerItem(Items.BOWL).group(VanillaExpansions.VE_GROUP)
                    .maxStackSize(1).food(VeFoods.WITCHS_CRADLE_SOUP)));
    public static Item glass_vial = register("glass_vial", new VeGlassVialItem(VE_ITEMS));
    public static Item blood_vial = register("blood_vial",
            new VeDrinkItem(new Item.Properties().containerItem(VeItems.glass_vial).group(VanillaExpansions.VE_GROUP)
                    .maxStackSize(16).food(VeFoods.BLOOD_VIAL)));
    public static Item mixed_seed_packet = register("mixed_seed_packet",
            new VeMixedSeedPacketItem(new Item.Properties().group(VanillaExpansions.VE_GROUP)));

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
