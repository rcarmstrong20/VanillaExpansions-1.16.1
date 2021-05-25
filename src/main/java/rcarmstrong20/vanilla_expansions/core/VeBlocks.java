package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.GlassBlock;
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
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.block.VeBabyCowPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBabyMooshroomPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBatPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBeePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBerryBushBlock;
import rcarmstrong20.vanilla_expansions.block.VeBlazePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeCatPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeCattailBlock;
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
import rcarmstrong20.vanilla_expansions.block.VeGlassOfDarknessBlock;
import rcarmstrong20.vanilla_expansions.block.VeGuardianPlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeHorsePlushBlock;
import rcarmstrong20.vanilla_expansions.block.VeIcicleBlock;
import rcarmstrong20.vanilla_expansions.block.VeLadderBlock;
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
import rcarmstrong20.vanilla_expansions.enums.MaterialType;

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
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            VanillaExpansions.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            VanillaExpansions.MOD_ID);

    public static Block batPlush = register("bat_plush", true, new VeBatPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_GRAY, SoundType.WOOL).strength(0.2F)));
    public static Block blazePlush = register("blaze_plush", true, new VeBlazePlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_YELLOW, SoundType.WOOL).strength(0.2F)));
    public static Block caveSpiderPlush = register("cave_spider_plush", true, new VeCaveSpiderPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_CYAN, SoundType.WOOL).strength(0.2F)));
    public static Block chickenPlush = register("chicken_plush", true, new VeChickenPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.WOOL, SoundType.WOOL).strength(0.2F)));
    public static Block cowPlush = register("cow_plush", true, new VeCowPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_BROWN, SoundType.WOOL).strength(0.2F)));
    public static Block babyCowPlush = register("baby_cow_plush", true,
            new VeBabyCowPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block creeperPlush = register("creeper_plush", true, new VeCreeperPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_GREEN, SoundType.WOOL).strength(0.2F)));
    public static Block endermanPlush = register("enderman_plush", true, new VeEndermanPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_BLACK, SoundType.WOOL).strength(0.2F)));
    public static Block endermitePlush = register("endermite_plush", true, new VeEndermitePlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_PURPLE, SoundType.WOOL).strength(0.2F)));
    public static Block ghastPlush = register("ghast_plush", true,
            new VeGhastPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block guardianPlush = register("guardian_plush", true,
            new VeGuardianPlushBlock(AbstractBlock.Properties.copy(VeBlocks.caveSpiderPlush)));
    public static Block whiteHorsePlush = register("white_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block grayHorsePlush = register("gray_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.copy(VeBlocks.batPlush)));
    public static Block lightGrayHorsePlush = register("light_gray_horse_plush", true, new VeHorsePlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_LIGHT_GRAY, SoundType.WOOL).strength(0.2F)));
    public static Block brownHorsePlush = register("brown_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block blackHorsePlush = register("black_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush)));
    public static Block purpleHorsePlush = register("purple_horse_plush", true,
            new VeHorsePlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermitePlush)));
    public static Block magmaCubePlush = register("magma_cube_plush", true, new VeMagmaCubePlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_RED, SoundType.WOOL).strength(0.2F)));
    public static Block redMooshroomPlush = register("red_mooshroom_plush", true,
            new VeMooshroomPlushBlock(AbstractBlock.Properties.copy(VeBlocks.magmaCubePlush)));
    public static Block babyRedMooshroomPlush = register("baby_red_mooshroom_plush", true,
            new VeBabyMooshroomPlushBlock(AbstractBlock.Properties.copy(VeBlocks.magmaCubePlush)));
    public static Block brownMooshroomPlush = register("brown_mooshroom_plush", true,
            new VeMooshroomPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block babyBrownMooshroomPlush = register("baby_brown_mooshroom_plush", true,
            new VeBabyMooshroomPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block ocelotPlush = register("ocelot_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.blazePlush)));
    public static Block tabbyCatPlush = register("tabby_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block tuxedoCatPlush = register("tuxedo_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush)));
    public static Block redCatPlush = register("red_cat_plush", true, new VeCatPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_ORANGE, SoundType.WOOL).strength(0.2F)));
    public static Block siameseCatPlush = register("siamese_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block britishShorthairCatPlush = register("british_shorthair_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.lightGrayHorsePlush)));
    public static Block calicoCatPlush = register("calico_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block persianCatPlush = register("persian_cat_plush", true,
            new VePersianCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.blazePlush)));
    public static Block ragdollCatPlush = register("ragdoll_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block whiteCatPlush = register("white_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block jellieCatPlush = register("jellie_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block blackCatPlush = register("black_cat_plush", true,
            new VeCatPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush)));
    public static Block pigPlush = register("pig_plush", true, new VePigPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_PINK, SoundType.WOOL).strength(0.2F)));
    public static Block brownRabbitPlush = register("brown_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block whiteRabbitPlush = register("white_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block blackRabbitPlush = register("black_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush)));
    public static Block whiteSplotchedRabbitPlush = register("white_splotched_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block goldRabbitPlush = register("gold_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.copy(VeBlocks.blazePlush)));
    public static Block toastRabbitPlush = register("toast_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block saltRabbitPlush = register("salt_rabbit_plush", true,
            new VeRabbitPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block whiteSheepPlush = register("white_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block orangeSheepPlush = register("orange_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.redCatPlush)));
    public static Block magentaSheepPlush = register("magenta_sheep_plush", true, new VeSheepPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_MAGENTA, SoundType.WOOL).strength(0.2F)));
    public static Block lightBlueSheepPlush = register("light_blue_sheep_plush", true, new VeSheepPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_LIGHT_BLUE, SoundType.WOOL).strength(0.2F)));
    public static Block yellowSheepPlush = register("yellow_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.blazePlush)));
    public static Block limeSheepPlush = register("lime_sheep_plush", true, new VeSheepPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_LIGHT_GREEN, SoundType.WOOL).strength(0.2F)));
    public static Block pinkSheepPlush = register("pink_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.pigPlush)));
    public static Block graySheepPlush = register("gray_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.batPlush)));
    public static Block lightGraySheepPlush = register("light_gray_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.lightGrayHorsePlush)));
    public static Block cyanSheepPlush = register("cyan_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.caveSpiderPlush)));
    public static Block purpleSheepPlush = register("purple_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermitePlush)));
    public static Block blueSheepPlush = register("blue_sheep_plush", true, new VeSheepPlushBlock(
            buildProperties(MaterialType.WOOL, MaterialColor.COLOR_BLUE, SoundType.WOOL).strength(0.2F)));
    public static Block brownSheepPlush = register("brown_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush)));
    public static Block greenSheepPlush = register("green_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.creeperPlush)));
    public static Block redSheepPlush = register("red_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.magmaCubePlush)));
    public static Block blackSheepPlush = register("black_sheep_plush", true,
            new VeSheepPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush)));
    public static Block silverfishPlush = register("silverfish_plush", true,
            new VeSilverfishPlushBlock(AbstractBlock.Properties.copy(VeBlocks.lightGrayHorsePlush)));
    public static Block skeletonPlush = register("skeleton_plush", true,
            new VeSkeletonPlushBlock(AbstractBlock.Properties.copy(VeBlocks.lightGrayHorsePlush)));
    public static Block slimePlush = register("slime_plush", true,
            new VeSlimePlushBlock(AbstractBlock.Properties.copy(VeBlocks.limeSheepPlush)));
    public static Block spiderPlush = register("spider_plush", true,
            new VeSpiderPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush)));
    public static Block squidPlush = register("squid_plush", true,
            new VeSquidPlushBlock(AbstractBlock.Properties.copy(VeBlocks.blueSheepPlush)));
    public static Block beePlush = register("bee_plush", true,
            new VeBeePlushBlock(AbstractBlock.Properties.copy(VeBlocks.blazePlush)));
    public static Block plainsVillagerPlush = register("plains_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush), 0));
    public static Block desertVillagerPlush = register("desert_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.redCatPlush), 1));
    public static Block jungleVillagerPlush = register("jungle_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.blazePlush), 2));
    public static Block savannaVillagerPlush = register("savanna_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush), 3));
    public static Block snowVillagerPlush = register("snow_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush), 4));
    public static Block swampVillagerPlush = register("swamp_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush), 5));
    public static Block taigaVillagerPlush = register("taiga_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.cowPlush), 6));
    public static Block crimsonVillagerPlush = register("crimson_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush), 7));
    public static Block warpedVillagerPlush = register("warped_villager_plush", true,
            new VeVillagerPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush), 7));
    public static Block witchPlush = register("witch_plush", true,
            new VeWitchPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermitePlush)));
    public static Block wolfPlush = register("wolf_plush", true,
            new VeWolfPlushBlock(AbstractBlock.Properties.copy(VeBlocks.chickenPlush)));
    public static Block zombiePlush = register("zombie_plush", true,
            new VeZombiePlushBlock(AbstractBlock.Properties.copy(VeBlocks.creeperPlush)));
    public static Block zombieDemonPlush = register("zombie_demon_plush", true,
            new VeZombieDemonPlushBlock(AbstractBlock.Properties.copy(VeBlocks.endermanPlush)));
    public static Block zombiePigmanPlush = register("zombie_pigman_plush", true,
            new VeZombiePigmanPlushBlock(AbstractBlock.Properties.copy(VeBlocks.pigPlush)));
    public static Block pufferfishPlush = register("pufferfish_plush", true,
            new VePufferfishPlushBlock(AbstractBlock.Properties.copy(VeBlocks.blazePlush)));
    public static Block rubyOre = register("blackstone_ruby_ore", true,
            new VeOreBlock(buildProperties(MaterialType.STONE, MaterialColor.COLOR_BLACK).strength(2.0F, 6.0F)));
    public static Block rubyBlock = register("ruby_block", true,
            new Block(buildProperties(MaterialType.METAL, MaterialColor.COLOR_RED).strength(5.0F, 6.0F)));
    public static Block smokyQuartzOre = register("nether_smoky_quartz_ore", true, new VeOreBlock(
            buildProperties(MaterialType.STONE, MaterialColor.NETHER, SoundType.NETHER_ORE).strength(3.0F, 3.0F)));
    public static Block smokyQuartzBlock = register("smoky_quartz_block", true,
            new Block(buildProperties(MaterialType.STONE, MaterialColor.COLOR_BLACK).strength(0.8F)));
    public static Block chiseledSmokyQuartzBlock = register("chiseled_smoky_quartz_block", true,
            new Block(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzBricks = register("smoky_quartz_bricks", true,
            new Block(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzPillar = register("smoky_quartz_pillar", true,
            new RotatedPillarBlock(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzStairs = register("smoky_quartz_stairs", true,
            new StairsBlock(() -> VeBlocks.smokyQuartzBlock.defaultBlockState(),
                    AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzSlab = register("smoky_quartz_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smokyQuartzWall = register("smoky_quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartz = register("smooth_smoky_quartz", true,
            new Block(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartzStairs = register("smooth_smoky_quartz_stairs", true,
            new StairsBlock(() -> VeBlocks.smokyQuartzBlock.defaultBlockState(),
                    AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartzSlab = register("smooth_smoky_quartz_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block smoothSmokyQuartzWall = register("smooth_smoky_quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.smokyQuartzBlock)));
    public static Block crackedStoneBrickStairs = register("cracked_stone_brick_stairs", true,
            new StairsBlock(() -> Blocks.CRACKED_STONE_BRICKS.defaultBlockState(),
                    AbstractBlock.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static Block crackedStoneBrickSlab = register("cracked_stone_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static Block crackedStoneBrickWall = register("cracked_stone_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.CRACKED_STONE_BRICKS)));
    public static Block quartzWall = register("quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.QUARTZ_BLOCK)));
    public static Block smoothQuartzWall = register("smooth_quartz_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.SMOOTH_QUARTZ)));
    public static Block stoneWall = register("stone_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block redGlass = register("red_glass", true, new GlassBlock(
            buildProperties(MaterialType.GLASS).strength(0.3F).noOcclusion().isValidSpawn(VeBlocks::never)));
    public static Block redGlassPane = register("red_glass_pane", true,
            new PaneBlock(AbstractBlock.Properties.copy(VeBlocks.redGlass)));
    public static Block blueberryBush = register("blueberry_bush", false,
            new VeBerryBushBlock(AbstractBlock.Properties.copy(Blocks.WHEAT)));
    public static Block cranberryBush = register("cranberry_bush", false,
            new VeBerryBushBlock(AbstractBlock.Properties.copy(Blocks.WHEAT)));
    public static Block bokChoy = register("bok_choy", false,
            new VeSevenStageCropBlock(AbstractBlock.Properties.copy(Blocks.WHEAT)));
    public static Block garlic = register("garlic", false,
            new VeThreeStageCropsBlock(AbstractBlock.Properties.copy(Blocks.WHEAT)));
    public static Block greenOnions = register("green_onions", false,
            new VeThreeStageCropsBlock(AbstractBlock.Properties.copy(Blocks.WHEAT)));
    public static Block stonePlanterBox = register("stone_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block mossyCobblestonePlanterBox = register("mossy_cobblestone_planter_box", true,
            new VePlanterBoxBlock(Block.Properties.copy(Blocks.MOSSY_COBBLESTONE)));
    public static Block cobblestonePlanterBox = register("cobblestone_planter_box", true,
            new VePlanterBoxBlock(Block.Properties.copy(Blocks.COBBLESTONE)));
    public static Block oakPlanterBox = register("oak_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static Block sprucePlanterBox = register("spruce_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static Block birchPlanterBox = register("birch_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static Block junglePlanterBox = register("jungle_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static Block acaciaPlanterBox = register("acacia_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static Block darkOakPlanterBox = register("dark_oak_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static Block netherrackPlanterBox = register("netherrack_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.NETHER_BRICKS)));
    public static Block warpedPlanterBox = register("warped_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.WARPED_PLANKS)));
    public static Block crimsonPlanterBox = register("crimson_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static Block obsidianPlanterBox = register("obsidian_planter_box", true,
            new VePlanterBoxBlock(AbstractBlock.Properties.copy(Blocks.END_STONE)));
    public static Block oakWall = register("oak_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static Block spruceWall = register("spruce_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static Block birchWall = register("birch_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static Block jungleWall = register("jungle_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static Block acaciaWall = register("acacia_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static Block darkOakWall = register("dark_oak_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static Block warpedWall = register("warped_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.WARPED_PLANKS)));
    public static Block crimsonWall = register("crimson_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static Block strippedOakWall = register("stripped_oak_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));
    public static Block strippedSpruceWall = register("stripped_spruce_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static Block strippedBirchWall = register("stripped_birch_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static Block strippedJungleWall = register("stripped_jungle_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static Block strippedAcaciaWall = register("stripped_acacia_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static Block strippedDarkOakWall = register("stripped_dark_oak_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static Block strippedWarpedWall = register("stripped_warped_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.WARPED_PLANKS)));
    public static Block strippedCrimsonWall = register("stripped_crimson_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static Block spruceLadder = register("spruce_ladder", true,
            new VeLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
    public static Block birchLadder = register("birch_ladder", true,
            new VeLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
    public static Block jungleLadder = register("jungle_ladder", true,
            new VeLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
    public static Block acaciaLadder = register("acacia_ladder", true,
            new VeLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
    public static Block darkOakLadder = register("dark_oak_ladder", true,
            new VeLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
    public static Block warpedLadder = register("warped_ladder", true,
            new VeLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
    public static Block crimsonLadder = register("crimson_ladder", true,
            new VeLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)));
    public static Block ironLadder = register("iron_ladder", true,
            new VeLadderBlock(buildProperties(MaterialType.HEAVY_METAL, SoundType.METAL).strength(0.4F, 5.0F)
                    .sound(SoundType.METAL).noOcclusion()));
    public static Block dirtSlab = register("dirt_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(Blocks.DIRT)));
    public static Block dirtStairs = register("dirt_stairs", true,
            new StairsBlock(() -> Blocks.DIRT.defaultBlockState(), AbstractBlock.Properties.copy(Blocks.DIRT)));
    public static Block coarseDirtSlab = register("coarse_dirt_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(Blocks.COARSE_DIRT)));
    public static Block coarseDirtStairs = register("coarse_dirt_stairs", true, new StairsBlock(
            () -> Blocks.COARSE_DIRT.defaultBlockState(), AbstractBlock.Properties.copy(Blocks.COARSE_DIRT)));
    public static Block snowBricks = register("snow_bricks", true,
            new Block(buildProperties(MaterialType.SNOW, SoundType.SNOW).strength(0.6F)));
    public static Block snowBrickStairs = register("snow_brick_stairs", true, new StairsBlock(
            () -> VeBlocks.snowBricks.defaultBlockState(), AbstractBlock.Properties.copy(VeBlocks.snowBricks)));
    public static Block snowBrickSlab = register("snow_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.snowBricks)));
    public static Block snowBrickWall = register("snow_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.snowBricks)));
    public static Block packedSnowBlock = register("packed_snow_block", true,
            new Block(buildProperties(MaterialType.SNOW, SoundType.SNOW).strength(0.4F).friction(0.98F)));
    public static Block packedSnowStairs = register("packed_snow_stairs", true,
            new StairsBlock(() -> VeBlocks.packedSnowBlock.defaultBlockState(),
                    AbstractBlock.Properties.copy(VeBlocks.packedSnowBlock)));
    public static Block packedSnowSlab = register("packed_snow_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.packedSnowBlock)));
    public static Block glassOfDarkness = register("glass_of_darkness", true,
            new VeGlassOfDarknessBlock(AbstractBlock.Properties.copy(VeBlocks.redGlass)));
    public static Block purpleMushroom = register("purple_mushroom", true,
            new VeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM)));
    public static Block purpleMushroomBlock = register("purple_mushroom_block", true,
            new VePurpleHugeMushroomBlock(AbstractBlock.Properties.copy(Blocks.RED_MUSHROOM_BLOCK)));
    public static Block woodcutter = register("woodcutter", true,
            new VeWoodcutterBlock(AbstractBlock.Properties.copy(Blocks.STONECUTTER)));
    public static Block whiteCampfire = register("white_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.whiteSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block orangeCampfire = register("orange_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.orangeSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block magentaCampfire = register("magenta_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.magentaSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block lightBlueCampfire = register("light_blue_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.lightBlueSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block yellowCampfire = register("yellow_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.yellowSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block limeCampfire = register("lime_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.limeSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block pinkCampfire = register("pink_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.pinkSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block grayCampfire = register("gray_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.graySpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block lightGrayCampfire = register("light_gray_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.lightGraySpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block cyanCampfire = register("cyan_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.cyanSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block purpleCampfire = register("purple_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.purpleSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block blueCampfire = register("blue_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.blueSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block brownCampfire = register("brown_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.brownSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block greenCampfire = register("green_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.greenSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block redCampfire = register("red_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.redSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block blackCampfire = register("black_campfire", true,
            new VeColoredCampfireBlock(VeParticleTypes.blackSpark, AbstractBlock.Properties.copy(Blocks.CAMPFIRE)));
    public static Block whiteLantern = register("white_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block orangeLantern = register("orange_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block magentaLantern = register("magenta_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block lightBlueLantern = register("light_blue_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block yellowLantern = register("yellow_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block limeLantern = register("lime_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block pinkLantern = register("pink_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block grayLantern = register("gray_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block lightGrayLantern = register("light_gray_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block cyanLantern = register("cyan_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block purpleLantern = register("purple_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block blueLantern = register("blue_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block brownLantern = register("brown_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block greenLantern = register("green_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block redLantern = register("red_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block blackLantern = register("black_lantern", true,
            new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN)));
    public static Block whiteTorch = register("white_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.whiteFlame));
    public static Block orangeTorch = register("orange_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.orangeFlame));
    public static Block magentaTorch = register("magenta_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.magentaFlame));
    public static Block lightBlueTorch = register("light_blue_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.lightBlueFlame));
    public static Block yellowTorch = register("yellow_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.yellowFlame));
    public static Block limeTorch = register("lime_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.limeFlame));
    public static Block pinkTorch = register("pink_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.pinkFlame));
    public static Block grayTorch = register("gray_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.grayFlame));
    public static Block lightGrayTorch = register("light_gray_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.lightGrayFlame));
    public static Block cyanTorch = register("cyan_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.cyanFlame));
    public static Block purpleTorch = register("purple_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.purpleFlame));
    public static Block blueTorch = register("blue_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.blueFlame));
    public static Block brownTorch = register("brown_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.brownFlame));
    public static Block greenTorch = register("green_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.greenFlame));
    public static Block redTorch = register("red_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.redFlame));
    public static Block blackTorch = register("black_torch", false,
            new TorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH), VeParticleTypes.blackFlame));
    public static Block whiteWallTorch = register("white_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.whiteTorch), VeParticleTypes.whiteFlame));
    public static Block orangeWallTorch = register("orange_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.orangeTorch), VeParticleTypes.orangeFlame));
    public static Block magentaWallTorch = register("magenta_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.magentaTorch), VeParticleTypes.magentaFlame));
    public static Block lightBlueWallTorch = register("light_blue_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.lightBlueTorch), VeParticleTypes.lightBlueFlame));
    public static Block yellowWallTorch = register("yellow_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.yellowTorch), VeParticleTypes.yellowFlame));
    public static Block limeWallTorch = register("lime_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.limeTorch), VeParticleTypes.limeFlame));
    public static Block pinkWallTorch = register("pink_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.pinkTorch), VeParticleTypes.pinkFlame));
    public static Block grayWallTorch = register("gray_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.grayTorch), VeParticleTypes.grayFlame));
    public static Block lightGrayWallTorch = register("light_gray_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.lightGrayTorch), VeParticleTypes.lightGrayFlame));
    public static Block cyanWallTorch = register("cyan_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.cyanTorch), VeParticleTypes.cyanFlame));
    public static Block purpleWallTorch = register("purple_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.purpleTorch), VeParticleTypes.purpleFlame));
    public static Block blueWallTorch = register("blue_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.blueTorch), VeParticleTypes.blueFlame));
    public static Block brownWallTorch = register("brown_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.brownTorch), VeParticleTypes.brownFlame));
    public static Block greenWallTorch = register("green_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.greenTorch), VeParticleTypes.greenFlame));
    public static Block redWallTorch = register("red_wall_torch", false, new WallTorchBlock(
            buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak().lightLevel((state) ->
            {
                return 14;
            }).lootFrom(() -> VeBlocks.redTorch), VeParticleTypes.redFlame));
    public static Block blackWallTorch = register("black_wall_torch", false,
            new WallTorchBlock(buildProperties(MaterialType.DECORATION, SoundType.WOOD).noCollission().instabreak()
                    .lootFrom(() -> VeBlocks.blackTorch).lightLevel((state) ->
                    {
                        return 14;
                    }), VeParticleTypes.blackFlame));
    public static Block witchsCradle = register("witchs_cradle", false,
            new VeBerryBushBlock(AbstractBlock.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
    public static Block darkMatter = register("dark_matter", false,
            new VeFlowingDarkMatterBlock(() -> VeFluids.flowingDarkMatter, AbstractBlock.Properties
                    .of(Material.WATER, MaterialColor.COLOR_BLACK).noCollission().strength(100.0F).noDrops()));
    public static Block bambooFence = register("bamboo_fence", true,
            new FenceBlock(buildProperties(MaterialType.BAMBOO, MaterialColor.PLANT).strength(1.0F).noOcclusion()));
    public static Block bambooFenceGate = register("bamboo_fence_gate", true,
            new FenceGateBlock(AbstractBlock.Properties.copy(VeBlocks.bambooFence)));
    public static Block bauxite = register("bauxite", true,
            new Block(buildProperties(MaterialType.STONE, MaterialColor.PODZOL).strength(0.3F)));
    public static Block bauxiteSlab = register("bauxite_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.bauxite)));
    public static Block bauxiteStairs = register("bauxite_stairs", true, new StairsBlock(
            () -> VeBlocks.bauxite.defaultBlockState(), AbstractBlock.Properties.copy(VeBlocks.bauxite)));
    public static Block bauxiteWall = register("bauxite_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.bauxite)));
    public static Block sodalite = register("sodalite", true,
            new Block(buildProperties(MaterialType.STONE, MaterialColor.COLOR_BLACK).strength(0.4F)));
    public static Block sodaliteSlab = register("sodalite_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block sodaliteStairs = register("sodalite_stairs", true, new StairsBlock(
            () -> VeBlocks.sodalite.defaultBlockState(), AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block sodaliteWall = register("sodalite_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block sodaliteBricks = register("sodalite_bricks", true,
            new Block(AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block chiseledSodaliteBricks = register("chiseled_sodalite_bricks", true,
            new Block(AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block sodaliteBrickSlab = register("sodalite_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block sodaliteBrickStairs = register("sodalite_brick_stairs", true, new StairsBlock(
            () -> VeBlocks.sodalite.defaultBlockState(), AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block sodaliteBrickWall = register("sodalite_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.sodalite)));
    public static Block snowSlab = register("snow_slab", true,
            new SlabBlock(buildProperties(MaterialType.SNOW, SoundType.SNOW).strength(0.2F)));
    public static Block snowStairs = register("snow_stairs", true, new StairsBlock(
            () -> Blocks.SNOW_BLOCK.defaultBlockState(), AbstractBlock.Properties.copy(VeBlocks.snowSlab)));
    public static Block snowWall = register("snow_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.snowSlab)));
    public static Block warpedChytridNetherBricks = register("warped_chytrid_nether_bricks", true,
            new Block(AbstractBlock.Properties.copy(Blocks.NETHER_BRICKS)));
    public static Block warpedChytridNetherBrickStairs = register("warped_chytrid_nether_brick_stairs", true,
            new StairsBlock(() -> VeBlocks.warpedChytridNetherBricks.defaultBlockState(),
                    AbstractBlock.Properties.copy(VeBlocks.warpedChytridNetherBricks)));
    public static Block warpedChytridNetherBrickSlab = register("warped_chytrid_nether_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.warpedChytridNetherBricks)));
    public static Block warpedChytridNetherBrickWall = register("warped_chytrid_nether_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.warpedChytridNetherBricks)));
    public static Block warpedChytridNetherrack = register("warped_chytrid_netherrack", true,
            new Block(AbstractBlock.Properties.copy(Blocks.NETHERRACK)));
    public static Block crimsonChytridNetherBricks = register("crimson_chytrid_nether_bricks", true,
            new Block(AbstractBlock.Properties.copy(Blocks.NETHER_BRICKS)));
    public static Block crimsonChytridNetherBrickStairs = register("crimson_chytrid_nether_brick_stairs", true,
            new StairsBlock(() -> VeBlocks.crimsonChytridNetherBricks.defaultBlockState(),
                    AbstractBlock.Properties.copy(VeBlocks.crimsonChytridNetherBricks)));
    public static Block crimsonChytridNetherBrickSlab = register("crimson_chytrid_nether_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.crimsonChytridNetherBricks)));
    public static Block crimsonChytridNetherBrickWall = register("crimson_chytrid_nether_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.crimsonChytridNetherBricks)));
    public static Block crimsonChytridNetherrack = register("crimson_chytrid_netherrack", true,
            new Block(AbstractBlock.Properties.copy(Blocks.NETHERRACK)));
    public static Block snapdragon = register("snapdragon", true,
            new VeSnapdragonBlock(Effects.LUCK, 8, AbstractBlock.Properties.copy(Blocks.POPPY).lightLevel((state) ->
            {
                return 7;
            })));
    public static Block pottedSnapdragon = register("potted_snapdragon", false, new VePottedSnapdragonBlock(
            VeBlocks.snapdragon, AbstractBlock.Properties.copy(Blocks.POTTED_POPPY).lightLevel((state) ->
            {
                return 7;
            })));
    public static Block enderGrass = register("ender_grass", true,
            new VeEnderGrassBlock(Block.Properties.copy(Blocks.GRASS).lightLevel((state) ->
            {
                return 7;
            })));
    public static Block transmutationTable = register("transmutation_table", true,
            new VeTransmutationTableBlock(buildProperties(MaterialType.STONE).strength(1.5F, 6.0F).lightLevel((state) ->
            {
                return 7;
            })));
    public static Block icicle = register("icicle", true,
            new VeIcicleBlock(buildProperties(MaterialType.ICE, SoundType.GLASS).strength(0.2F).noCollission()));
    public static Block mud = register("mud", true, new Block(buildProperties(MaterialType.DIRT).strength(0.5F)));
    public static Block mudBricks = register("mud_bricks", true,
            new Block(buildProperties(MaterialType.DIRT).strength(1.0F, 2.0F)));
    public static Block mudBrickSlab = register("mud_brick_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(VeBlocks.mudBricks)));
    public static Block mudBrickStairs = register("mud_brick_stairs", true, new StairsBlock(
            () -> VeBlocks.bauxite.defaultBlockState(), AbstractBlock.Properties.copy(VeBlocks.mudBricks)));
    public static Block mudBrickWall = register("mud_brick_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(VeBlocks.mudBricks)));
    public static Block cattail = register("cattail", false,
            new VeCattailBlock(buildProperties(MaterialType.PLANT).noCollission().noOcclusion()));
    public static Block chocolateCake = register("chocolate_cake", true,
            new CakeBlock(AbstractBlock.Properties.copy(Blocks.CAKE)), 1);
    public static Block redVelvetCake = register("red_velvet_cake", true,
            new CakeBlock(AbstractBlock.Properties.copy(Blocks.CAKE)), 1);
    public static Block marble = register("marble", true, new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block marbleSlab = register("marble_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block marbleStairs = register("marble_stairs", true,
            new StairsBlock(() -> VeBlocks.marble.defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block marbleWall = register("marble_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block polished_marble = register("polished_marble", true,
            new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block polishedMarbleSlab = register("polished_marble_slab", true,
            new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block polishedMarbleStairs = register("polished_marble_stairs", true,
            new StairsBlock(() -> VeBlocks.marble.defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)));
    public static Block polishedMarbleWall = register("polished_marble_wall", true,
            new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)));

    private static boolean never(BlockState stateIn, IBlockReader readerIn, BlockPos posIn, EntityType<?> entityTypeIn)
    {
        return false;
    }

    /**
     * Build a property set with default sound and a custom material and map color.
     *
     * @param materialIn      The material of this block.
     * @param materialColorIn The map color of this block.
     * @return A base property set for this block.
     */
    private static AbstractBlock.Properties buildProperties(MaterialType materialIn, MaterialColor materialColorIn)
    {
        return buildProperties(materialIn, materialColorIn, null);
    }

    /**
     * Build a property set with default sound, and material, and a custom map
     * color.
     *
     * @param materialIn The material of this block.
     * @return A base property set for this block.
     */
    private static AbstractBlock.Properties buildProperties(MaterialType materialIn)
    {
        return buildProperties(materialIn, materialIn.getMaterial().getColor(), null);
    }

    /**
     * Build a property set with a custom sound, and material, and a default map
     * color.
     *
     * @param materialIn The material of this block.
     * @param soundIn    The sound of this block.
     * @return A base property set for this block.
     */
    private static AbstractBlock.Properties buildProperties(MaterialType materialIn, SoundType soundIn)
    {
        return buildProperties(materialIn, materialIn.getMaterial().getColor(), soundIn);
    }

    /**
     * Sets the appropriate harvest tool for the block according to its material.
     *
     * @param materialIn      The material of this block.
     * @param materialColorIn The map color of this block.
     * @param soundIn         The sound of this block.
     * @return A base property set for this block.
     */
    private static AbstractBlock.Properties buildProperties(MaterialType materialIn, MaterialColor materialColorIn,
            SoundType soundIn)
    {
        AbstractBlock.Properties properties = null;

        if (soundIn == null)
        {
            properties = AbstractBlock.Properties.of(materialIn.getMaterial(), materialColorIn)
                    .sound(materialIn.getSound());
        }
        else
        {
            properties = AbstractBlock.Properties.of(materialIn.getMaterial(), materialColorIn).sound(soundIn);
        }

        switch (materialIn)
        {
            case HEAVY_METAL:
                setPickaxeHarvestTool(properties, true);
                break;
            case CORAL:
                setPickaxeHarvestTool(properties, true);
                break;
            case VEGETABLE:
                properties.harvestTool(ToolType.AXE);
                break;
            case ICE:
                setPickaxeHarvestTool(properties, true);
                break;
            case METAL:
                setPickaxeHarvestTool(properties, true);
                break;
            case ICE_SOLID:
                setPickaxeHarvestTool(properties, true);
                break;
            case STONE:
                setPickaxeHarvestTool(properties, true);
                break;
            case SHULKER_SHELL:
                setPickaxeHarvestTool(properties, false);
                break;
            case CLAY:
                setShovelHarvestTool(properties, false);
                break;
            case DIRT:
                setShovelHarvestTool(properties, false);
                break;
            case LEAVES:
                properties.harvestTool(ToolType.HOE);
                break;
            case NETHER_WOOD:
                properties.harvestTool(ToolType.AXE);
                break;
            case SAND:
                setShovelHarvestTool(properties, false);
                break;
            case SNOW:
                setShovelHarvestTool(properties, true);
                break;
            case TOP_SNOW:
                setShovelHarvestTool(properties, true);
                break;
            case WOOD:
                properties.harvestTool(ToolType.AXE);
                break;
            case GLASS:
                setPickaxeHarvestTool(properties, false);
            default:
                break;
        }
        return properties;
    }

    private static void setPickaxeHarvestTool(AbstractBlock.Properties properties, boolean needsPick)
    {
        setHarvestTool(properties, ToolType.PICKAXE, needsPick);
    }

    private static void setShovelHarvestTool(AbstractBlock.Properties properties, boolean needsShovel)
    {
        setHarvestTool(properties, ToolType.SHOVEL, needsShovel);
    }

    private static void setHarvestTool(AbstractBlock.Properties properties, ToolType tool, boolean needsTool)
    {
        if (needsTool)
        {
            properties.harvestTool(tool).requiresCorrectToolForDrops();
        }
        else
        {
            properties.harvestTool(tool);
        }
    }

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
                new Item.Properties().tab(VanillaExpansions.VE_GROUP).stacksTo(maxStackSize));
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
        return register(name, hasItem, block, new Item.Properties().tab(VanillaExpansions.VE_GROUP));
    }

    /**
     * Helper method for adding all blocks and block items to the registry list.
     */
    private static Block register(String name, boolean hasItem, Block block, Properties properties)
    {
        if (hasItem)
        {
            BlockItem item = new BlockItem(block, properties);
            ITEMS.register(name, () -> item);
        }
        BLOCKS.register(name, () -> block);

        return block;
    }
}
