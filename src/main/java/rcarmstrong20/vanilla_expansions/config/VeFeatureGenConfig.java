package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VeFeatureGenConfig
{
    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Feature Configuration").push("ve_feature_config");

        VeFeatureGenConfig.VeOverworldConfig.init(server);
        VeFeatureGenConfig.VeNetherConfig.init(server);
        VeFeatureGenConfig.VeEndConfig.init(server);

        server.pop();
    }

    /**
     *
     * @author Ryan
     *
     *         A sub-class that holds all the configurations for the overworld
     *         dimension.
     */
    public static class VeOverworldConfig
    {
        public static BooleanValue enableBlueberryBushSpawns;
        public static BooleanValue enableCranberryBushSpawns;
        public static BooleanValue enableWitchsCradleSpawns;
        public static BooleanValue enableHugePurpleMushroomSpawns;
        public static BooleanValue enableTaigaCabinSpawns;
        public static BooleanValue enableForestCabinSpawns;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Overworld Configuration").push("ve_overworld_config");

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

            server.pop();
        }
    }

    /**
     *
     * @author Ryan
     *
     *         A sub-class that holds all the configurations for the nether
     *         dimension.
     */
    public static class VeNetherConfig
    {
        public static BooleanValue enableNetherSmokyQuartzOreSpawns;
        public static BooleanValue enableNetherRubyOreSpawns;
        public static BooleanValue enableCrimsonCabinSpawns;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Nether Configuration").push("ve_nether_config");

            enableNetherSmokyQuartzOreSpawns = server.comment(compileBooleanSpawnComment("nether smoky quartz ores"))
                    .translation("ve.configNetherOre.enableNetherSmokyQuartzOreSpawns").worldRestart()
                    .define("enable_nether_smoky_quartz_ore_spawns", true);

            enableNetherRubyOreSpawns = server.comment(compileBooleanSpawnComment("nether ruby ores"))
                    .translation("ve.configNetherOre.enableNetherRubyOreSpawns").worldRestart()
                    .define("enable_nether_ruby_ore_spawns", true);

            enableCrimsonCabinSpawns = server.comment(compileBooleanSpawnComment("crimson cabins"))
                    .translation("ve.configStructure.enableCrimsonCabinSpawns").worldRestart()
                    .define("enable_crimson_cabin_spawns", true);

            server.pop();
        }
    }

    /**
     *
     * @author Ryan
     *
     *         A sub-class that holds all the configurations for the end dimension.
     */
    public static class VeEndConfig
    {
        public static BooleanValue enableDarkMatterLakeSpawns;
        public static BooleanValue enableSnapdragonAndEnderGrassSpawns;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("End Configuration").push("ve_end_config");

            enableDarkMatterLakeSpawns = server.comment(compileBooleanSpawnComment("dark matter lakes"))
                    .translation("ve.configLake.enableDarkMatterLakeSpawns").worldRestart()
                    .define("enable_dark_matter_lake_spawns", true);

            enableSnapdragonAndEnderGrassSpawns = server
                    .comment(compileBooleanSpawnComment("snapdragons and ender grass"))
                    .translation("ve.configLake.enableSnapdragonAndEnderGrassSpawns").worldRestart()
                    .define("enable_snapdragon_and_ender_grass_spawns", true);

            server.pop();
        }
    }

    public static String compileBooleanSpawnComment(String name)
    {
        return "If true " + name + " will spawn.";
    }
}
