package rndmaccess.vanilla_expansions.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import rndmaccess.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Bus.MOD)
public class VEConfig
{
    public static final ForgeConfigSpec COMMON_CONFIG;

    public static BooleanValue enableSmartHarvest;
    public static IntValue spruceConePercent;

    public static BooleanValue blueberryBushFlag;
    public static BooleanValue cranberryBushFlag;
    public static BooleanValue witchsCradleFlag;
    public static BooleanValue hugePurpleMushroomFlag;
    public static BooleanValue purpleMushroomFlag;
    public static BooleanValue swampMudFlag;
    public static BooleanValue riverMudFlag;
    public static BooleanValue cattailFlag;
    public static BooleanValue smokyQuartzOreFlag;
    public static BooleanValue rubyOreFlag;
    public static BooleanValue darkMatterLakeFlag;
    public static BooleanValue snapdragonAndEnderGrassFlag;

    public static BooleanValue taigaCabinFlag;
    public static BooleanValue forestCabinFlag;
    public static BooleanValue crimsonCabinFlag;

    public static BooleanValue enableSaveTheBunnies;

    static
    {
        final ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();

        // Create the ore data configuration portion.
        common.comment("Ore Data Config").push("oreData");

        VEOreDataConfig.init(common);

        common.pop();

        // Create the block configuration portion.
        common.comment("Block Config").push("block");

        enableSmartHarvest = common.comment(
                "When true crops can be harvested and re-planted simply by right-clicking the crop. (Default: true)")
                .worldRestart().define("EnableSmartHarvest", true);
        spruceConePercent = common.comment(makeChanceComment("spruce cone", "spruce leaves", 5)).worldRestart()
                .defineInRange("SpruceConePercent", 5, 0, 100);

        common.pop();

        // Create the feature configuration portion.
        common.comment("Feature Config").push("feature");

        blueberryBushFlag = common.comment(makeSpawnComment("blueberry bushes", true)).worldRestart()
                .define("EnableBlueberryBushSpawns", true);
        cranberryBushFlag = common.comment(makeSpawnComment("cranberry bushes", true)).worldRestart()
                .define("EnableCranberryBushSpawns", true);
        witchsCradleFlag = common.comment(makeSpawnComment("witch's cradles", true)).worldRestart()
                .define("EnableWitchsCradleSpawns", true);
        hugePurpleMushroomFlag = common.comment(makeSpawnComment("big purple mushrooms", true)).worldRestart()
                .define("EnableBigPurpleMushroomSpawns", true);
        purpleMushroomFlag = common.comment(makeSpawnComment("purple mushrooms", true)).worldRestart()
                .define("EnablePurpleMushroomSpawns", true);
        swampMudFlag = common.comment(makeSpawnComment("swamp mud", true)).worldRestart().define("EnableSwampMudSpawns",
                true);
        riverMudFlag = common.comment(makeSpawnComment("river mud", true)).worldRestart().define("EnableRiverMudSpawns",
                true);
        cattailFlag = common.comment(makeSpawnComment("cattails", true)).worldRestart().define("EnableCattailSpawns",
                true);
        smokyQuartzOreFlag = common.comment(makeSpawnComment("nether smoky quartz ores", true)).worldRestart()
                .define("EnableNetherSmokyQuartzOreSpawns", true);
        rubyOreFlag = common.comment(makeSpawnComment("nether ruby ores", true)).worldRestart()
                .define("EnableNetherRubyOreSpawns", true);
        darkMatterLakeFlag = common.comment(makeSpawnComment("dark matter lakes", true)).worldRestart()
                .define("EnableDarkMatterLakeSpawns", true);
        snapdragonAndEnderGrassFlag = common.comment(makeSpawnComment("snapdragons and ender grass", true))
                .worldRestart().define("EnableSnapdragonAndEnderGrassSpawns", true);

        common.pop();

        // Create the structure configuration portion.
        common.comment("Structure Config").push("structure");

        taigaCabinFlag = common.comment(makeSpawnComment("taiga cabins", true)).worldRestart()
                .define("EnableTaigaCabinSpawns", true);
        forestCabinFlag = common.comment(makeSpawnComment("forest cabins", true)).worldRestart()
                .define("EnableForestCabinSpawns", true);
        crimsonCabinFlag = common.comment(makeSpawnComment("crimson cabins", true)).worldRestart()
                .define("EnableCrimsonCabinSpawns", true);

        common.pop();

        // Create the entity configuration portion.
        common.comment("Entity Config").push("entity");

        enableSaveTheBunnies = common.comment("Cancels bunny fall damage when true. (Default: true)").worldRestart()
                .define("EnableSaveTheBunnies", true);

        common.pop();

        COMMON_CONFIG = common.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path)
    {
        VanillaExpansions.LOGGER.info("Loading config: " + path);

        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
                .writingMode(WritingMode.REPLACE).build();

        VanillaExpansions.LOGGER.info("Built config: " + path);
        file.load();

        VanillaExpansions.LOGGER.info("Loaded config: " + path);
        config.setConfig(file);
    }

    private static String makeSpawnComment(String name, boolean defaultValue)
    {
        return "When true " + name + " will spawn. (Default: " + defaultValue + ")";
    }

    private static String makeChanceComment(String itemName, String blockName, int defaultValue)
    {
        return "The chance that a " + itemName + " will drop from " + blockName + ". (Default: " + defaultValue + ")";
    }

    public static class VEOreDataConfig
    {
        public static final String SMOKY_QUARTZ_VEIN_SIZE_ID = "NetherSmokyQuartzOreVeinSize";
        public static final String BLACKSTONE_RUBY_ORE_VEIN_SIZE_ID = "BlackstoneRubyOreVeinSize";
        public static final String SMOKY_QUARTZ_ORE_SPREAD_ID = "NetherSmokyQuartzOreSpread";
        public static final String BLACKSTONE_RUBY_ORE_ORE_SPREAD_ID = "BlackstoneRubyOreSpread";

        public static final int SMOKY_QUARTZ_VEIN_SIZE_DEFAULT = 14;
        public static final int BLACKSTONE_RUBY_ORE_VEIN_SIZE_DEFAULT = 5;
        public static final int SMOKY_QUARTZ_ORE_SPREAD_DEFAULT = 16;
        public static final int BLACKSTONE_RUBY_ORE_SPREAD_DEFAULT = 16;

        public static IntValue netherSmokyQuartzOreVeinSize;
        public static IntValue blackstoneRubyOreVeinSize;
        public static IntValue netherSmokyQuartzOreSpread;
        public static IntValue blackstoneRubyOreSpread;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Vein Size Config").push("veinSize");

            netherSmokyQuartzOreVeinSize = server
                    .comment(makeVeinSizeComment("nether smoky quartz ore", SMOKY_QUARTZ_VEIN_SIZE_DEFAULT))
                    .worldRestart().defineInRange(SMOKY_QUARTZ_VEIN_SIZE_ID, SMOKY_QUARTZ_VEIN_SIZE_DEFAULT, 0, 17);

            blackstoneRubyOreVeinSize = server
                    .comment(makeVeinSizeComment("blackstone ruby ore", BLACKSTONE_RUBY_ORE_VEIN_SIZE_DEFAULT))
                    .worldRestart()
                    .defineInRange(BLACKSTONE_RUBY_ORE_VEIN_SIZE_ID, BLACKSTONE_RUBY_ORE_VEIN_SIZE_DEFAULT, 0, 17);

            server.pop();

            server.comment("Spread Config").push("oreSpread");

            netherSmokyQuartzOreSpread = server
                    .comment(makeSpreadComment("nether smoky quartz ore", SMOKY_QUARTZ_ORE_SPREAD_DEFAULT))
                    .worldRestart().defineInRange(SMOKY_QUARTZ_ORE_SPREAD_ID, SMOKY_QUARTZ_ORE_SPREAD_DEFAULT, 2, 20);

            blackstoneRubyOreSpread = server
                    .comment(makeSpreadComment("blackstone ruby ore", BLACKSTONE_RUBY_ORE_SPREAD_DEFAULT))
                    .worldRestart()
                    .defineInRange(BLACKSTONE_RUBY_ORE_ORE_SPREAD_ID, BLACKSTONE_RUBY_ORE_SPREAD_DEFAULT, 2, 20);

            server.pop();
        }

        /**
         *
         * @param name         The name of this ore.
         * @param defaultValue
         * @return A new string with the name and default value inserted.
         */
        private static String makeVeinSizeComment(String name, int defaultValue)
        {
            return "Sets vein size for " + name + ". (Default: " + defaultValue + ")";
        }

        /**
         *
         * @param name         The name of this ore.
         * @param defaultValue
         * @return A new string with the name and default value inserted.
         */
        private static String makeSpreadComment(String name, int defaultValue)
        {
            return "Sets how spread out the ores are when spawned for " + name + ". (Default: " + defaultValue + ")";
        }
    }
}
