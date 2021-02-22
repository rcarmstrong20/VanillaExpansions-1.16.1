package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.block.VeIcicleBlock;
import rcarmstrong20.vanilla_expansions.block.VeBabyCowPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBabyMooshroomPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBatPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBeePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBerryBushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBlazePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeCatPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeCaveSpiderPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeChickenPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeColoredCampfireBlock;
import rcarmstrong20.vanilla_expansions.block.VeCowPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeCreeperPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeEnderGrassBlock;
import rcarmstrong20.vanilla_expansions.block.VeEndermanPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeEndermitePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeFlowingDarkMatterBlock;
import rcarmstrong20.vanilla_expansions.block.VeGhastPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeGlassBlock;
import rcarmstrong20.vanilla_expansions.block.VeGuardianPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeHorsePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeMagmaCubePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeMooshroomPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeMushroomBlock;
import rcarmstrong20.vanilla_expansions.block.VeOreBlock;
import rcarmstrong20.vanilla_expansions.block.VePersianCatPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VePigPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VePlanterBoxBlock;
import rcarmstrong20.vanilla_expansions.block.VePottedSnapdragonBlock;
import rcarmstrong20.vanilla_expansions.block.VePufferfishPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VePurpleHugeMushroomBlock;
import rcarmstrong20.vanilla_expansions.block.VeRabbitPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeRoastedChickenBlock;
import rcarmstrong20.vanilla_expansions.block.VeSevenStageCropBlock;
import rcarmstrong20.vanilla_expansions.block.VeSheepPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeSilverfishPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeSkeletonPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeSlimePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeSnapdragonBlock;
import rcarmstrong20.vanilla_expansions.block.VeSpiderPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeSquidPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeThreeStageCropsBlock;
import rcarmstrong20.vanilla_expansions.block.VeTransmutationTableBlock;
import rcarmstrong20.vanilla_expansions.block.VeVillagerPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeWitchPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeWolfPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeWoodcutterBlock;
import rcarmstrong20.vanilla_expansions.block.VeZombieDemonPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeZombiePigmanPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeZombiePlushBlock;

/**
 * A class for holding every block instance that vanilla expansions has.
 *
 * Note: all block names must be lower case or forge will crash the game.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeBlocks
{
    private static final List<Block> BLOCKS = new ArrayList<>();
    private static final List<Item> ITEMS = new ArrayList<>();

    public static Block batPlush = register("bat_plush", true,
            new VeBatPlushBlock(AbstractBlock.Properties.from(Blocks.GRAY_WOOL)));
    public static Block blazePlush = register("blaze_plush", true,
            new VeBlazePlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static Block caveSpiderPlush = register("cave_spider_plush", true,
            new VeCaveSpiderPlushBlock(AbstractBlock.Properties.from(Blocks.CYAN_WOOL)));
    public static Block chickenPlush = register("chicken_plush", true,
            new VeChickenPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block cowPlush = register("cow_plush", true,
            new VeCowPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block babyCowPlush = register("baby_cow_plush", true,
            new VeBabyCowPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block creeperPlush = register("creeper_plush", true,
            new VeCreeperPlushBlock(AbstractBlock.Properties.from(Blocks.GREEN_WOOL)));
    public static Block endermanPlush = register("enderman_plush", true,
            new VeEndermanPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block endermitePlush = register("endermite_plush", true,
            new VeEndermitePlushBlock(AbstractBlock.Properties.from(Blocks.PURPLE_WOOL)));
    public static Block ghastPlush = register("ghast_plush", true,
            new VeGhastPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block guardianPlush = register("guardian_plush", true,
            new VeGuardianPlushBlock(AbstractBlock.Properties.from(Blocks.CYAN_WOOL)));
    public static Block whiteHorsePlush = register("white_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block grayHorsePlush = register("gray_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.from(Blocks.GRAY_WOOL)));
    public static Block lightGrayHorsePlush = register("light_gray_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_WOOL)));
    public static Block brownHorsePlush = register("brown_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block blackHorsePlush = register("black_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block purpleHorsePlush = register("purple_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.from(Blocks.PURPLE_WOOL)));
    public static Block magmaCubePlush = register("magma_cube_plush", true,
            new VeMagmaCubePlushBlock(AbstractBlock.Properties.from(Blocks.RED_WOOL)));
    public static Block redMooshroomPlush = register("red_mooshroom_plush", true,
            new VeMooshroomPlushBlock(AbstractBlock.Properties.from(Blocks.RED_WOOL)));
    public static Block babyRedMooshroomPlush = register("baby_red_mooshroom_plush", true,
            new VeBabyMooshroomPlushBlock(AbstractBlock.Properties.from(Blocks.RED_WOOL)));
    public static Block brownMooshroomPlush = register("brown_mooshroom_plush", true,
            new VeMooshroomPlushBlock(AbstractBlock.Properties.from(Blocks.RED_WOOL)));
    public static Block babyBrownMooshroomPlush = register("baby_brown_mooshroom_plush", true,
            new VeBabyMooshroomPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block ocelotPlush = register("ocelot_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static Block tabbyCatPlush = register("tabby_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block tuxedoCatPlush = register("tuxedo_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block redCatPlush = register("red_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.ORANGE_WOOL)));
    public static Block siameseCatPlush = register("siamese_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block britishShorthairCatPlush = register("british_shorthair_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_WOOL)));
    public static Block calicoCatPlush = register("calico_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block persianCatPlush = register("persian_cat_plush", true,
            new VePersianCatPlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static Block ragdollCatPlush = register("ragdoll_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block whiteCatPlush = register("white_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block jellieCatPlush = register("jellie_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block blackCatPlush = register("black_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block pigPlush = register("pig_plush", true,
            new VePigPlushBlock(AbstractBlock.Properties.from(Blocks.PINK_WOOL)));
    public static Block brownRabbitPlush = register("brown_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block whiteRabbitPlush = register("white_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block blackRabbitPlush = register("black_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block whiteSplotchedRabbitPlush = register("white_splotched_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block goldRabbitPlush = register("gold_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static Block toastRabbitPlush = register("toast_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block saltRabbitPlush = register("salt_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block whiteSheepPlush = register("white_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block orangeSheepPlush = register("orange_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.ORANGE_WOOL)));
    public static Block magentaSheepPlush = register("magenta_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.MAGENTA_WOOL)));
    public static Block lightBlueSheepPlush = register("light_blue_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.LIGHT_BLUE_WOOL)));
    public static Block yellowSheepPlush = register("yellow_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static Block limeSheepPlush = register("lime_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.LIME_WOOL)));
    public static Block pinkSheepPlush = register("pink_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.PINK_WOOL)));
    public static Block graySheepPlush = register("gray_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.GRAY_WOOL)));
    public static Block lightGraySheepPlush = register("light_gray_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_WOOL)));
    public static Block cyanSheepPlush = register("cyan_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.CYAN_WOOL)));
    public static Block purpleSheepPlush = register("purple_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.PURPLE_WOOL)));
    public static Block blueSheepPlush = register("blue_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.BLUE_WOOL)));
    public static Block brownSheepPlush = register("brown_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL)));
    public static Block greenSheepPlush = register("green_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.GREEN_WOOL)));
    public static Block redSheepPlush = register("red_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.RED_WOOL)));
    public static Block blackSheepPlush = register("black_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block silverfishPlush = register("silverfish_plush", true,
            new VeSilverfishPlushBlock(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_WOOL)));
    public static Block skeletonPlush = register("skeleton_plush", true,
            new VeSkeletonPlushBlock(AbstractBlock.Properties.from(Blocks.LIGHT_GRAY_WOOL)));
    public static Block slimePlush = register("slime_plush", true,
            new VeSlimePlushBlock(AbstractBlock.Properties.from(Blocks.LIME_WOOL)));
    public static Block spiderPlush = register("spider_plush", true,
            new VeSpiderPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block squidPlush = register("squid_plush", true,
            new VeSquidPlushBlock(AbstractBlock.Properties.from(Blocks.BLUE_WOOL)));
    public static Block beePlush = register("bee_plush", true,
            new VeBeePlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static Block plainsVillagerPlush = register("plains_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL), 0));
    public static Block desertVillagerPlush = register("desert_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.ORANGE_WOOL), 1));
    public static Block jungleVillagerPlush = register("jungle_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL), 2));
    public static Block savannaVillagerPlush = register("savanna_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL), 3));
    public static Block snowVillagerPlush = register("snow_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL), 4));
    public static Block swampVillagerPlush = register("swamp_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL), 5));
    public static Block taigaVillagerPlush = register("taiga_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.BROWN_WOOL), 6));
    public static Block crimsonVillagerPlush = register("crimson_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL), 7));
    public static Block warpedVillagerPlush = register("warped_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL), 7));
    public static Block witchPlush = register("witch_plush", true,
            new VeWitchPlushBlock(AbstractBlock.Properties.from(Blocks.PURPLE_WOOL)));
    public static Block wolfPlush = register("wolf_plush", true,
            new VeWolfPlushBlock(AbstractBlock.Properties.from(Blocks.WHITE_WOOL)));
    public static Block zombiePlush = register("zombie_plush", true,
            new VeZombiePlushBlock(AbstractBlock.Properties.from(Blocks.GREEN_WOOL)));
    public static Block zombieDemonPlush = register("zombie_demon_plush", true,
            new VeZombieDemonPlushBlock(AbstractBlock.Properties.from(Blocks.BLACK_WOOL)));
    public static Block zombiePigmanPlush = register("zombie_pigman_plush", true,
            new VeZombiePigmanPlushBlock(AbstractBlock.Properties.from(Blocks.PINK_WOOL)));
    public static Block pufferfishPlush = register("pufferfish_plush", true,
            new VePufferfishPlushBlock(AbstractBlock.Properties.from(Blocks.YELLOW_WOOL)));
    public static Block rubyOre = register("nether_ruby_ore", true,
            new VeOreBlock(AbstractBlock.Properties.from(Blocks.DIAMOND_ORE)));
    public static Block rubyBlock = register("ruby_block", true, new Block(AbstractBlock.Properties
            .create(Material.IRON, MaterialColor.RED).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.STONE)));
    public static Block smokyQuartzOre = register("nether_smoky_quartz_ore", true,
            new VeOreBlock(AbstractBlock.Properties.from(Blocks.NETHER_QUARTZ_ORE)));
    public static Block smokyQuartzBlock = register("smoky_quartz_block", true,
            new Block(AbstractBlock.Properties.from(Blocks.QUARTZ_BLOCK)));
    public static Block chiseledSmokyQuartzBlock = register("chiseled_smoky_quartz_block", true,
            new Block(AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzPillar = register("smoky_quartz_pillar", true,
            new RotatedPillarBlock(AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzStairs = register("smoky_quartz_stairs", true,
            new StairsBlock(() -> VeBlocks.smokyQuartzBlock.getDefaultState(),
                    AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzSlab = register("smoky_quartz_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzWall = register("smoky_quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartz = register("smooth_smoky_quartz", true,
            new Block(AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartzStairs = register("smooth_smoky_quartz_stairs", true,
            new StairsBlock(() -> VeBlocks.smokyQuartzBlock.getDefaultState(),
                    AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartzSlab = register("smooth_smoky_quartz_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartzWall = register("smooth_smoky_quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.from(VeBlocks.smokyQuartzBlock)));
    public static Block crackedStoneBrickStairs = register("cracked_stone_brick_stairs", true,
            new StairsBlock(() -> Blocks.CRACKED_STONE_BRICKS.getDefaultState(),
                    AbstractBlock.Properties.from(Blocks.CRACKED_STONE_BRICKS)));
    public static Block crackedStoneBrickSlab = register("cracked_stone_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(Blocks.CRACKED_STONE_BRICKS)));
    public static Block crackedStoneBrickWall = register("cracked_stone_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.CRACKED_STONE_BRICKS)));
    public static Block quartzWall = register("quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.QUARTZ_BLOCK)));
    public static Block smoothQuartzWall = register("smooth_quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.SMOOTH_QUARTZ)));
    public static Block stoneWall = register("stone_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)));
    public static Block redGlass = register("red_glass", true,
            new VeGlassBlock(AbstractBlock.Properties.from(Blocks.GLASS)));
    public static Block redGlassPane = register("red_glass_pane", true,
            new PaneBlock(AbstractBlock.Properties.from(Blocks.GLASS_PANE)));
    public static Block blueberryBush = register("blueberry_bush", false,
            new VeBerryBushBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static Block cranberryBush = register("cranberry_bush", false,
            new VeBerryBushBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static Block bokChoy = register("bok_choy", false,
            new VeSevenStageCropBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static Block garlic = register("garlic", false,
            new VeThreeStageCropsBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static Block greenOnions = register("green_onions", false,
            new VeThreeStageCropsBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static Block quinoa = register("quinoa", false,
            new VeSevenStageCropBlock(AbstractBlock.Properties.from(Blocks.WHEAT)));
    public static Block stonePlanterBox = register("stone_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)));
    public static Block mossyCobblestonePlanterBox = register("mossy_cobblestone_planter_box", true,
            new VePlanterBoxBlock(Block.Properties.from(Blocks.MOSSY_STONE_BRICKS)));
    public static Block cobblestonePlanterBox = register("cobblestone_planter_box", true,
            new VePlanterBoxBlock(Block.Properties.from(Blocks.CRACKED_STONE_BRICKS)));
    public static Block oakPlanterBox = register("oak_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static Block sprucePlanterBox = register("spruce_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.SPRUCE_PLANKS)));
    public static Block birchPlanterBox = register("birch_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.BIRCH_PLANKS)));
    public static Block junglePlanterBox = register("jungle_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_PLANKS)));
    public static Block acaciaPlanterBox = register("acacia_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.ACACIA_PLANKS)));
    public static Block darkOakPlanterBox = register("dark_oak_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_PLANKS)));
    public static Block netherrackPlanterBox = register("netherrack_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.NETHER_BRICKS)));
    public static Block warpedPlanterBox = register("warped_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.WARPED_PLANKS)));
    public static Block crimsonPlanterBox = register("crimson_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_PLANKS)));
    public static Block obsidianPlanterBox = register("obsidian_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.from(Blocks.END_STONE)));
    public static Block oakWall = register("oak_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static Block spruceWall = register("spruce_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.SPRUCE_PLANKS)));
    public static Block birchWall = register("birch_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.BIRCH_PLANKS)));
    public static Block jungleWall = register("jungle_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_PLANKS)));
    public static Block acaciaWall = register("acacia_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.ACACIA_PLANKS)));
    public static Block darkOakWall = register("dark_oak_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_PLANKS)));
    public static Block warpedWall = register("warped_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.WARPED_PLANKS)));
    public static Block crimsonWall = register("crimson_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_PLANKS)));
    public static Block strippedOakWall = register("stripped_oak_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static Block strippedSpruceWall = register("stripped_spruce_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.SPRUCE_PLANKS)));
    public static Block strippedBirchWall = register("stripped_birch_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.BIRCH_PLANKS)));
    public static Block strippedJungleWall = register("stripped_jungle_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.JUNGLE_PLANKS)));
    public static Block strippedAcaciaWall = register("stripped_acacia_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.ACACIA_PLANKS)));
    public static Block strippedDarkOakWall = register("stripped_dark_oak_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.DARK_OAK_PLANKS)));
    public static Block strippedWarpedWall = register("stripped_warped_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.WARPED_PLANKS)));
    public static Block strippedCrimsonWall = register("stripped_crimson_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.CRIMSON_PLANKS)));
    public static Block spruceLadder = register("spruce_ladder", true,
            new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static Block birchLadder = register("birch_ladder", true,
            new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static Block jungleLadder = register("jungle_ladder", true,
            new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static Block acaciaLadder = register("acacia_ladder", true,
            new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static Block darkOakLadder = register("dark_oak_ladder", true,
            new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static Block warpedLadder = register("warped_ladder", true,
            new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static Block crimsonLadder = register("crimson_ladder", true,
            new LadderBlock(AbstractBlock.Properties.from(Blocks.LADDER)));
    public static Block ironLadder = register("iron_ladder", true,
            new LadderBlock(AbstractBlock.Properties.create(Material.ANVIL, MaterialColor.IRON)
                    .hardnessAndResistance(0.4F, 5.0F).sound(SoundType.METAL).notSolid()));
    public static Block dirtSlab = register("dirt_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(Blocks.DIRT)));
    public static Block dirtStairs = register("dirt_stairs", true,
            new StairsBlock(() -> Blocks.DIRT.getDefaultState(), AbstractBlock.Properties.from(Blocks.DIRT)));
    public static Block coarseDirtSlab = register("coarse_dirt_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(Blocks.COARSE_DIRT)));
    public static Block coarseDirtStairs = register("coarse_dirt_stairs", true, new StairsBlock(
            () -> Blocks.COARSE_DIRT.getDefaultState(), AbstractBlock.Properties.from(Blocks.COARSE_DIRT)));
    public static Block snowBricks = register("snow_bricks", true,
            new Block(AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.SNOW)
                    .hardnessAndResistance(0.6F).harvestTool(ToolType.SHOVEL).sound(SoundType.SNOW)));
    public static Block snowBrickStairs = register("snow_brick_stairs", true, new StairsBlock(
            () -> VeBlocks.snowBricks.getDefaultState(), AbstractBlock.Properties.from(VeBlocks.snowBricks)));
    public static Block snowBrickSlab = register("snow_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.snowBricks)));
    public static Block snowBrickWall = register("snow_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.from(VeBlocks.snowBricks)));
    public static Block packedSnowBlock = register("packed_snow_block", true,
            new Block(
                    AbstractBlock.Properties.create(Material.SNOW_BLOCK, MaterialColor.SNOW).hardnessAndResistance(0.4F)
                            .harvestTool(ToolType.SHOVEL).sound(SoundType.SNOW).slipperiness(0.98F)));
    public static Block packedSnowStairs = register("packed_snow_stairs", true, new StairsBlock(
            () -> VeBlocks.packedSnowBlock.getDefaultState(), AbstractBlock.Properties.from(VeBlocks.packedSnowBlock)));
    public static Block packedSnowSlab = register("packed_snow_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.packedSnowBlock)));
    public static Block roasted_chicken = register("roasted_chicken", true,
            new VeRoastedChickenBlock(3, 0.5F, AbstractBlock.Properties.create(Material.CAKE, MaterialColor.BROWN)
                    .hardnessAndResistance(0.5F).sound(SoundType.SNOW)),
            1);
    public static Block glassOfDarkness = register("glass_of_darkness", true,
            new VeGlassBlock(AbstractBlock.Properties.from(Blocks.GLASS)));
    public static Block purpleMushroom = register("purple_mushroom", true,
            new VeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM)));
    public static Block purpleMushroomBlock = register("purple_mushroom_block", true,
            new VePurpleHugeMushroomBlock(AbstractBlock.Properties.from(Blocks.RED_MUSHROOM_BLOCK)));
    public static Block woodcutter = register("woodcutter", true,
            new VeWoodcutterBlock(AbstractBlock.Properties.from(Blocks.STONECUTTER)));
    public static Block whiteCampfire = register("white_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.whiteSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block orangeCampfire = register("orange_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.orangeSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block magentaCampfire = register("magenta_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.magentaSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block lightBlueCampfire = register("light_blue_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.lightBlueSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block yellowCampfire = register("yellow_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.yellowSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block limeCampfire = register("lime_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.limeSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block pinkCampfire = register("pink_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.pinkSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block grayCampfire = register("gray_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.graySpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block lightGrayCampfire = register("light_gray_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.lightGraySpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block cyanCampfire = register("cyan_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.cyanSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block purpleCampfire = register("purple_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.purpleSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block blueCampfire = register("blue_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.blueSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block brownCampfire = register("brown_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.brownSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block greenCampfire = register("green_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.greenSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block redCampfire = register("red_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.redSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block blackCampfire = register("black_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.blackSpark, AbstractBlock.Properties.from(Blocks.CAMPFIRE)));
    public static Block whiteLantern = register("white_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block orangeLantern = register("orange_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block magentaLantern = register("magenta_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block lightBlueLantern = register("light_blue_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block yellowLantern = register("yellow_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block limeLantern = register("lime_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block pinkLantern = register("pink_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block grayLantern = register("gray_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block lightGrayLantern = register("light_gray_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block cyanLantern = register("cyan_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block purpleLantern = register("purple_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block blueLantern = register("blue_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block brownLantern = register("brown_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block greenLantern = register("green_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block redLantern = register("red_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block blackLantern = register("black_lantern", true,
            new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
    public static Block whiteTorch = register("white_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.whiteFlame));
    public static Block orangeTorch = register("orange_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.orangeFlame));
    public static Block magentaTorch = register("magenta_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.magentaFlame));
    public static Block lightBlueTorch = register("light_blue_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.lightBlueFlame));
    public static Block yellowTorch = register("yellow_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.yellowFlame));
    public static Block limeTorch = register("lime_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.limeFlame));
    public static Block pinkTorch = register("pink_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.pinkFlame));
    public static Block grayTorch = register("gray_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.grayFlame));
    public static Block lightGrayTorch = register("light_gray_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.lightGrayFlame));
    public static Block cyanTorch = register("cyan_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.cyanFlame));
    public static Block purpleTorch = register("purple_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.purpleFlame));
    public static Block blueTorch = register("blue_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.blueFlame));
    public static Block brownTorch = register("brown_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.brownFlame));
    public static Block greenTorch = register("green_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.greenFlame));
    public static Block redTorch = register("red_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.redFlame));
    public static Block blackTorch = register("black_torch", false,
            new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), VeParticleTypes.blackFlame));
    public static Block whiteWallTorch = register("white_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.whiteTorch), VeParticleTypes.whiteFlame));
    public static Block orangeWallTorch = register("orange_wall_torch", false,
            new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
                    .zeroHardnessAndResistance().setLightLevel((state) ->
                    {
                        return 14;
                    }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.orangeTorch), VeParticleTypes.orangeFlame));
    public static Block magentaWallTorch = register("magenta_wall_torch", false,
            new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
                    .zeroHardnessAndResistance().setLightLevel((state) ->
                    {
                        return 14;
                    }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.magentaTorch), VeParticleTypes.magentaFlame));
    public static Block lightBlueWallTorch = register("light_blue_wall_torch", false,
            new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
                    .zeroHardnessAndResistance().setLightLevel((state) ->
                    {
                        return 14;
                    }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.lightBlueTorch), VeParticleTypes.lightBlueFlame));
    public static Block yellowWallTorch = register("yellow_wall_torch", false,
            new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
                    .zeroHardnessAndResistance().setLightLevel((state) ->
                    {
                        return 14;
                    }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.yellowTorch), VeParticleTypes.yellowFlame));
    public static Block limeWallTorch = register("lime_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.limeTorch), VeParticleTypes.limeFlame));
    public static Block pinkWallTorch = register("pink_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.pinkTorch), VeParticleTypes.pinkFlame));
    public static Block grayWallTorch = register("gray_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.grayTorch), VeParticleTypes.grayFlame));
    public static Block lightGrayWallTorch = register("light_gray_wall_torch", false,
            new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
                    .zeroHardnessAndResistance().setLightLevel((state) ->
                    {
                        return 14;
                    }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.lightGrayTorch), VeParticleTypes.lightGrayFlame));
    public static Block cyanWallTorch = register("cyan_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.cyanTorch), VeParticleTypes.cyanFlame));
    public static Block purpleWallTorch = register("purple_wall_torch", false,
            new WallTorchBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement()
                    .zeroHardnessAndResistance().setLightLevel((state) ->
                    {
                        return 14;
                    }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.purpleTorch), VeParticleTypes.purpleFlame));
    public static Block blueWallTorch = register("blue_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.blueTorch), VeParticleTypes.blueFlame));
    public static Block brownWallTorch = register("brown_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.brownTorch), VeParticleTypes.brownFlame));
    public static Block greenWallTorch = register("green_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.greenTorch), VeParticleTypes.greenFlame));
    public static Block redWallTorch = register("red_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.redTorch), VeParticleTypes.redFlame));
    public static Block blackWallTorch = register("black_wall_torch", false, new WallTorchBlock(AbstractBlock.Properties
            .create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) ->
            {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(() -> VeBlocks.blackTorch), VeParticleTypes.blackFlame));
    public static Block witchsCradle = register("witchs_cradle", false,
            new VeBerryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
    public static Block darkMatter = register("dark_matter", false,
            new VeFlowingDarkMatterBlock(() -> VeFluids.flowingDarkMatter,
                    AbstractBlock.Properties.create(Material.WATER, MaterialColor.BLACK).doesNotBlockMovement()
                            .hardnessAndResistance(100.0F).noDrops()));
    public static Block bambooFence = register("bamboo_fence", true,
            new FenceBlock(AbstractBlock.Properties.from(Blocks.BAMBOO)));
    public static Block bambooFenceGate = register("bamboo_fence_gate", true,
            new FenceGateBlock(AbstractBlock.Properties.from(Blocks.BAMBOO)));
    public static Block bambooWall = register("bamboo_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.BAMBOO)));
    public static Block snowflakeObsidian = register("snowflake_obsidian", true,
            new Block(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
    public static Block nephilite = register("nephilite", true, new Block(
            AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.3F).sound(SoundType.STONE)));
    public static Block nephiliteSlab = register("nephilite_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block nephiliteStairs = register("nephilite_stairs", true, new StairsBlock(
            () -> VeBlocks.nephilite.getDefaultState(), AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block nephiliteWall = register("nephilite_wall", true,
            new WallBlock(AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block nephiliteBricks = register("nephilite_bricks", true,
            new Block(AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block nephiliteBrickSlab = register("nephilite_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block nephiliteBrickStairs = register("nephilite_brick_stairs", true, new StairsBlock(
            () -> VeBlocks.nephilite.getDefaultState(), AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block nephiliteBrickWall = register("nephilite_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block chiseledNephiliteBricks = register("chiseled_nephilite_bricks", true,
            new Block(AbstractBlock.Properties.from(VeBlocks.nephilite)));
    public static Block snowSlab = register("snow_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(Blocks.SNOW_BLOCK).harvestTool(ToolType.SHOVEL)));
    public static Block snowStairs = register("snow_stairs", true,
            new StairsBlock(() -> Blocks.SNOW_BLOCK.getDefaultState(),
                    AbstractBlock.Properties.from(Blocks.SNOW_BLOCK).harvestTool(ToolType.SHOVEL)));
    public static Block snowWall = register("snow_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.SNOW_BLOCK).harvestTool(ToolType.SHOVEL)));
    public static Block warpedChytridNetherBricks = register("warped_chytrid_nether_bricks", true,
            new Block(AbstractBlock.Properties.from(Blocks.NETHER_BRICKS)));
    public static Block warpedChytridNetherBrickStairs = register("warped_chytrid_nether_brick_stairs", true,
            new StairsBlock(() -> VeBlocks.warpedChytridNetherBricks.getDefaultState(),
                    AbstractBlock.Properties.from(VeBlocks.warpedChytridNetherBricks)));
    public static Block warpedChytridNetherBrickSlab = register("warped_chytrid_nether_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.warpedChytridNetherBricks)));
    public static Block warpedChytridNetherBrickWall = register("warped_chytrid_nether_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.from(VeBlocks.warpedChytridNetherBricks)));
    public static Block warpedChytridNetherrack = register("warped_chytrid_netherrack", true,
            new Block(AbstractBlock.Properties.from(Blocks.NETHERRACK)));
    public static Block crimsonChytridNetherBricks = register("crimson_chytrid_nether_bricks", true,
            new Block(AbstractBlock.Properties.from(Blocks.NETHER_BRICKS)));
    public static Block crimsonChytridNetherBrickStairs = register("crimson_chytrid_nether_brick_stairs", true,
            new StairsBlock(() -> VeBlocks.crimsonChytridNetherBricks.getDefaultState(),
                    AbstractBlock.Properties.from(VeBlocks.crimsonChytridNetherBricks)));
    public static Block crimsonChytridNetherBrickSlab = register("crimson_chytrid_nether_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(VeBlocks.crimsonChytridNetherBricks)));
    public static Block crimsonChytridNetherBrickWall = register("crimson_chytrid_nether_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.from(VeBlocks.crimsonChytridNetherBricks)));
    public static Block crimsonChytridNetherrack = register("crimson_chytrid_netherrack", true,
            new Block(AbstractBlock.Properties.from(Blocks.NETHERRACK)));
    public static Block snapdragon = register("snapdragon", true, new VeSnapdragonBlock(Effects.LUCK, 8,
            AbstractBlock.Properties.from(Blocks.POPPY).setLightLevel((state) -> 4)));
    public static Block pottedSnapdragon = register("potted_snapdragon", false, new VePottedSnapdragonBlock(
            VeBlocks.snapdragon, AbstractBlock.Properties.from(Blocks.POTTED_POPPY).setLightLevel((state) -> 4)));
    public static Block enderGrass = register("ender_grass", true,
            new VeEnderGrassBlock(Block.Properties.from(Blocks.GRASS).setLightLevel((state) -> 4)));
    public static Block smoothEndStone = register("smooth_end_stone", true,
            new Block(AbstractBlock.Properties.from(Blocks.END_STONE)));
    public static Block smoothEndStoneSlab = register("smooth_end_stone_slab", true,
            new SlabBlock(AbstractBlock.Properties.from(Blocks.END_STONE)));
    public static Block smoothEndStoneStairs = register("smooth_end_stone_stairs", true,
            new StairsBlock(() -> Blocks.END_STONE.getDefaultState(), AbstractBlock.Properties.from(Blocks.END_STONE)));
    public static Block smoothEndStoneWall = register("smooth_end_stone_wall", true,
            new WallBlock(AbstractBlock.Properties.from(Blocks.END_STONE)));
    public static Block transmutationTable = register("transmutation_table", true,
            new VeTransmutationTableBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool()
                    .hardnessAndResistance(1.5F, 6.0F).setLightLevel((state) ->
                    {
                        return 7;
                    }).sound(SoundType.STONE)));
    public static Block icicle = register("icicle", true,
            new VeIcicleBlock(AbstractBlock.Properties.from(Blocks.BLUE_ICE).doesNotBlockMovement()));

    /**
     * Used to register a block with a item that has a custom stack size.
     *
     * @param name         The name of the block and block item.
     * @param hasItem      If this block has an item.
     * @param block        A new block object.
     * @param maxStackSize The block item max stack size.
     * @return The main registry method.
     */
    private static Block register(String name, boolean hasItem, Block block, int maxStackSize)
    {
        return register(name, hasItem, block,
                new Item.Properties().group(VanillaExpansions.VE_GROUP).maxStackSize(maxStackSize));
    }

    /**
     * Used to register a block with default item properties.
     *
     * @param name    The name of the block.
     * @param hasItem If this block has an item.
     * @param block   A new block object.
     * @return The main registry method.
     */
    private static Block register(String name, boolean hasItem, Block block)
    {
        return register(name, hasItem, block, new Item.Properties().group(VanillaExpansions.VE_GROUP));
    }

    /**
     * Helper method for registering all default blocks and block items.
     */
    private static Block register(String name, boolean hasItem, Block block, Properties properties)
    {
        String id = VanillaExpansions.MOD_ID;

        if (hasItem)
        {
            BlockItem item = new BlockItem(block, properties);
            item.setRegistryName(id, name);
            ITEMS.add(item);
        }

        block.setRegistryName(id, name);
        BLOCKS.add(block);

        return block;
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event)
    {
        BLOCKS.forEach(block -> event.getRegistry().register(block));
        BLOCKS.clear();

        VanillaExpansions.LOGGER.info("Blocks registered.");
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event)
    {
        ITEMS.forEach(item -> event.getRegistry().register(item));
        ITEMS.clear();

        VanillaExpansions.LOGGER.info("Block Items registered.");
    }
}
