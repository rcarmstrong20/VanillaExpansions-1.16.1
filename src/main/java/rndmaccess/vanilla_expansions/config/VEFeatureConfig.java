package rndmaccess.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VEFeatureConfig
{
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

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Feature Config").push("feature");

        blueberryBushFlag = server.comment(compileBooleanSpawnComment("blueberry bushes")).worldRestart()
                .define("EnableBlueberryBushSpawns", true);
        cranberryBushFlag = server.comment(compileBooleanSpawnComment("cranberry bushes")).worldRestart()
                .define("EnableCranberryBushSpawns", true);
        witchsCradleFlag = server.comment(compileBooleanSpawnComment("witch's cradles")).worldRestart()
                .define("EnableWitchsCradleSpawns", true);
        hugePurpleMushroomFlag = server.comment(compileBooleanSpawnComment("big purple mushrooms")).worldRestart()
                .define("EnableBigPurpleMushroomSpawns", true);
        purpleMushroomFlag = server.comment(compileBooleanSpawnComment("purple mushrooms")).worldRestart()
                .define("EnablePurpleMushroomSpawns", true);
        swampMudFlag = server.comment(compileBooleanSpawnComment("swamp mud")).worldRestart()
                .define("EnableSwampMudSpawns", true);
        riverMudFlag = server.comment(compileBooleanSpawnComment("river mud")).worldRestart()
                .define("EnableRiverMudSpawns", true);
        cattailFlag = server.comment(compileBooleanSpawnComment("cattails")).worldRestart()
                .define("EnableCattailSpawns", true);
        smokyQuartzOreFlag = server.comment(compileBooleanSpawnComment("nether smoky quartz ores")).worldRestart()
                .define("EnableNetherSmokyQuartzOreSpawns", true);
        rubyOreFlag = server.comment(compileBooleanSpawnComment("nether ruby ores")).worldRestart()
                .define("EnableNetherRubyOreSpawns", true);
        darkMatterLakeFlag = server.comment(compileBooleanSpawnComment("dark matter lakes")).worldRestart()
                .define("EnableDarkMatterLakeSpawns", true);
        snapdragonAndEnderGrassFlag = server.comment(compileBooleanSpawnComment("snapdragons and ender grass"))
                .worldRestart().define("EnableSnapdragonAndEnderGrassSpawns", true);

        server.pop();
    }

    public static String compileBooleanSpawnComment(String name)
    {
        return "When true " + name + " will spawn.";
    }
}
