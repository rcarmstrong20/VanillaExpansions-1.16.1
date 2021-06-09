package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VEFeatureGenConfig
{
    public static BooleanValue enableBlueberryBushSpawns;
    public static BooleanValue enableCranberryBushSpawns;
    public static BooleanValue enableWitchsCradleSpawns;
    public static BooleanValue enableHugePurpleMushroomSpawns;
    public static BooleanValue enablePurpleMushroomSpawns;
    public static BooleanValue enableSwampMudSpawns;
    public static BooleanValue enableRiverMudSpawns;
    public static BooleanValue enableCattailSpawns;
    public static BooleanValue enableNetherSmokyQuartzOreSpawns;
    public static BooleanValue enableNetherRubyOreSpawns;
    public static BooleanValue enableDarkMatterLakeSpawns;
    public static BooleanValue enableSnapdragonAndEnderGrassSpawns;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Feature Config").push("feature");

        enableBlueberryBushSpawns = server.comment(compileBooleanSpawnComment("blueberry bushes")).worldRestart()
                .define("EnableBlueberryBushSpawns", true);
        enableCranberryBushSpawns = server.comment(compileBooleanSpawnComment("cranberry bushes")).worldRestart()
                .define("EnableCranberryBushSpawns", true);
        enableWitchsCradleSpawns = server.comment(compileBooleanSpawnComment("witch's cradles")).worldRestart()
                .define("EnableWitchsCradleSpawns", true);
        enableHugePurpleMushroomSpawns = server.comment(compileBooleanSpawnComment("big purple mushrooms"))
                .worldRestart().define("EnableBigPurpleMushroomSpawns", true);
        enablePurpleMushroomSpawns = server.comment(compileBooleanSpawnComment("purple mushrooms")).worldRestart()
                .define("EnablePurpleMushroomSpawns", true);
        enableSwampMudSpawns = server.comment(compileBooleanSpawnComment("swamp mud")).worldRestart()
                .define("EnableSwampMudSpawns", true);
        enableRiverMudSpawns = server.comment(compileBooleanSpawnComment("river mud")).worldRestart()
                .define("EnableRiverMudSpawns", true);
        enableCattailSpawns = server.comment(compileBooleanSpawnComment("cattails")).worldRestart()
                .define("EnableCattailSpawns", true);
        enableNetherSmokyQuartzOreSpawns = server.comment(compileBooleanSpawnComment("nether smoky quartz ores"))
                .worldRestart().define("EnableNetherSmokyQuartzOreSpawns", true);
        enableNetherRubyOreSpawns = server.comment(compileBooleanSpawnComment("nether ruby ores")).worldRestart()
                .define("EnableNetherRubyOreSpawns", true);
        enableDarkMatterLakeSpawns = server.comment(compileBooleanSpawnComment("dark matter lakes")).worldRestart()
                .define("EnableDarkMatterLakeSpawns", true);
        enableSnapdragonAndEnderGrassSpawns = server.comment(compileBooleanSpawnComment("snapdragons and ender grass"))
                .worldRestart().define("EnableSnapdragonAndEnderGrassSpawns", true);

        server.pop();
    }

    public static String compileBooleanSpawnComment(String name)
    {
        return "When true " + name + " will spawn.";
    }
}
