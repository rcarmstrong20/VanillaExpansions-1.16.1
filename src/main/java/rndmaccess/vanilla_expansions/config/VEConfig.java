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
    public static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SERVER_CONFIG;

    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CLIENT_CONFIG;

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
        // Create the ore data configuration portion.
        SERVER_BUILDER.comment("Ore Data Config").push("oreData");

        VEOreDataConfig.init(SERVER_BUILDER);

        SERVER_BUILDER.pop();

        // Create the block configuration portion.
        SERVER_BUILDER.comment("Block Config").push("block");

        enableSmartHarvest = SERVER_BUILDER
                .comment("When true crops can be harvested and re-planted simply by right-clicking the crop.")
                .worldRestart().define("EnableSmartHarvest", true);
        spruceConePercent = SERVER_BUILDER.comment("The chance that a spruce cone will drop from spruce leaves.")
                .worldRestart().defineInRange("SpruceConePercent", 5, 0, 100);

        SERVER_BUILDER.pop();

        // Create the feature configuration portion.
        SERVER_BUILDER.comment("Feature Config").push("feature");

        blueberryBushFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("blueberry bushes")).worldRestart()
                .define("EnableBlueberryBushSpawns", true);
        cranberryBushFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("cranberry bushes")).worldRestart()
                .define("EnableCranberryBushSpawns", true);
        witchsCradleFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("witch's cradles")).worldRestart()
                .define("EnableWitchsCradleSpawns", true);
        hugePurpleMushroomFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("big purple mushrooms"))
                .worldRestart().define("EnableBigPurpleMushroomSpawns", true);
        purpleMushroomFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("purple mushrooms")).worldRestart()
                .define("EnablePurpleMushroomSpawns", true);
        swampMudFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("swamp mud")).worldRestart()
                .define("EnableSwampMudSpawns", true);
        riverMudFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("river mud")).worldRestart()
                .define("EnableRiverMudSpawns", true);
        cattailFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("cattails")).worldRestart()
                .define("EnableCattailSpawns", true);
        smokyQuartzOreFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("nether smoky quartz ores"))
                .worldRestart().define("EnableNetherSmokyQuartzOreSpawns", true);
        rubyOreFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("nether ruby ores")).worldRestart()
                .define("EnableNetherRubyOreSpawns", true);
        darkMatterLakeFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("dark matter lakes")).worldRestart()
                .define("EnableDarkMatterLakeSpawns", true);
        snapdragonAndEnderGrassFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("snapdragons and ender grass"))
                .worldRestart().define("EnableSnapdragonAndEnderGrassSpawns", true);

        SERVER_BUILDER.pop();

        // Create the structure configuration portion.
        SERVER_BUILDER.comment("Structure Config").push("structure");

        taigaCabinFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("taiga cabins")).worldRestart()
                .define("EnableTaigaCabinSpawns", true);
        forestCabinFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("forest cabins")).worldRestart()
                .define("EnableForestCabinSpawns", true);
        crimsonCabinFlag = SERVER_BUILDER.comment(compileBooleanSpawnComment("crimson cabins")).worldRestart()
                .define("EnableCrimsonCabinSpawns", true);

        SERVER_BUILDER.pop();

        // Create the entity configuration portion.
        SERVER_BUILDER.comment("Entity Config").push("entity");

        enableSaveTheBunnies = SERVER_BUILDER.comment("Cancels bunny fall damage when true.").worldRestart()
                .define("EnableSaveTheBunnies", true);

        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
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

    public static String compileBooleanSpawnComment(String name)
    {
        return "When true " + name + " will spawn.";
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
                    .comment(compileVeinSizeComment("nether smoky quartz ore", SMOKY_QUARTZ_VEIN_SIZE_DEFAULT))
                    .worldRestart().defineInRange(SMOKY_QUARTZ_VEIN_SIZE_ID, SMOKY_QUARTZ_VEIN_SIZE_DEFAULT, 0, 17);

            blackstoneRubyOreVeinSize = server
                    .comment(compileVeinSizeComment("blackstone ruby ore", BLACKSTONE_RUBY_ORE_VEIN_SIZE_DEFAULT))
                    .worldRestart()
                    .defineInRange(BLACKSTONE_RUBY_ORE_VEIN_SIZE_ID, BLACKSTONE_RUBY_ORE_VEIN_SIZE_DEFAULT, 0, 17);

            server.pop();

            server.comment("Spread Config").push("oreSpread");

            netherSmokyQuartzOreSpread = server
                    .comment(compileSpreadComment("nether smoky quartz ore", SMOKY_QUARTZ_ORE_SPREAD_DEFAULT))
                    .worldRestart().defineInRange(SMOKY_QUARTZ_ORE_SPREAD_ID, SMOKY_QUARTZ_ORE_SPREAD_DEFAULT, 2, 20);

            blackstoneRubyOreSpread = server
                    .comment(compileSpreadComment("blackstone ruby ore", BLACKSTONE_RUBY_ORE_SPREAD_DEFAULT))
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
        private static String compileVeinSizeComment(String name, int defaultValue)
        {
            return "Sets vein size for " + name + ". (Default: " + defaultValue + ")";
        }

        /**
         *
         * @param name         The name of this ore.
         * @param defaultValue
         * @return A new string with the name and default value inserted.
         */
        private static String compileSpreadComment(String name, int defaultValue)
        {
            return "Sets how spread out the ores are when spawned for " + name + ". (Default: " + defaultValue + ")";
        }
    }
}
