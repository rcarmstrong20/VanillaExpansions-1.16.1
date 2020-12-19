package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VeFeatureGenConfig
{
    // Overworld
    public static BooleanValue enableBlueberryBushSpawns;
    public static BooleanValue enableCranberryBushSpawns;
    public static BooleanValue enableWitchsCradleSpawns;
    public static BooleanValue enableHugePurpleMushroomSpawns;
    public static BooleanValue enableTaigaCabinSpawns;
    public static BooleanValue enableForestCabinSpawns;

    // Nether
    public static BooleanValue enableNetherSmokyQuartzOreSpawns;
    public static BooleanValue enableNetherRubyOreSpawns;
    public static BooleanValue enableCrimsonCabinSpawns;

    // End
    public static BooleanValue enableVoidLakeSpawns;
    public static BooleanValue enableSnapdragonAndEnderGrassSpawns;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Feature Configuration").push("ve_feature_config"); // Enter feature config

        server.comment("Overworld Configuration").push("ve_overworld_config"); // Enter overworld sub-category

        enableBlueberryBushSpawns = server.comment(compileBooleanSpawnComment("blueberry bushes"))
                .translation("ve.configBushes.enableBlueberryBushSpawns").worldRestart()
                .define("enable_blueberry_bush_spawns", true);

        enableCranberryBushSpawns = server.comment(compileBooleanSpawnComment("cranberry bushes"))
                .translation("ve.configBushes.enableCranberryBushSpawns").worldRestart()
                .define("enable_cranberry_bush_spawns", true);

        enableWitchsCradleSpawns = server.comment(compileBooleanSpawnComment("witch's cradles"))
                .translation("ve.configBushes.enableWitchsCradleSpawns").worldRestart()
                .define("enable_witchs_cradle_spawns", true);

        enableHugePurpleMushroomSpawns = server.comment(compileBooleanSpawnComment("big purple mushrooms"))
                .translation("ve.configMushroom.enableBigPurpleMushroomSpawns").worldRestart()
                .define("enable_big_purple_mushroom_spawns", true);

        enableTaigaCabinSpawns = server.comment(compileBooleanSpawnComment("taiga cabins"))
                .translation("ve.configStructure.enableTaigaCabinSpawns").worldRestart()
                .define("enable_taiga_cabin_spawns", true);

        enableForestCabinSpawns = server.comment(compileBooleanSpawnComment("forest cabins"))
                .translation("ve.configStructure.enableForestCabinSpawns").worldRestart()
                .define("enable_forest_cabin_spawns", true);

        server.pop(); // Exit overworld sub-category

        server.comment("Nether Configuration").push("ve_nether_config"); // Enter nether sub-category

        enableNetherSmokyQuartzOreSpawns = server.comment(compileBooleanSpawnComment("nether smoky quartz ores"))
                .translation("ve.configNetherOre.enableNetherSmokyQuartzOreSpawns").worldRestart()
                .define("enable_nether_smoky_quartz_ore_spawns", true);

        enableNetherRubyOreSpawns = server.comment(compileBooleanSpawnComment("nether ruby ores"))
                .translation("ve.configNetherOre.enableNetherRubyOreSpawns").worldRestart()
                .define("enable_nether_ruby_ore_spawns", true);

        enableCrimsonCabinSpawns = server.comment(compileBooleanSpawnComment("crimson cabins"))
                .translation("ve.configStructure.enableCrimsonCabinSpawns").worldRestart()
                .define("enable_crimson_cabin_spawns", true);

        server.pop(); // Exit nether sub-category

        server.comment("End Configuration").push("ve_end_config"); // Enter end sub-category

        enableVoidLakeSpawns = server.comment(compileBooleanSpawnComment("void lakes"))
                .translation("ve.configLake.enableVoidLakeSpawns").worldRestart()
                .define("enable_void_lake_spawns", true);

        enableSnapdragonAndEnderGrassSpawns = server.comment(compileBooleanSpawnComment("snapdragons and ender grass"))
                .translation("ve.configLake.enableSnapdragonAndEnderGrassSpawns").worldRestart()
                .define("enable_snapdragon_and_ender_grass_spawns", true);

        server.pop(2); // Exit end and feature config category's
    }

    public static String compileBooleanSpawnComment(String name)
    {
        return "If true " + name + " will spawn.";
    }
}
