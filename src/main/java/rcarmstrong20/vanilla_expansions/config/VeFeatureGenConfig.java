package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VeFeatureGenConfig
{
    public static BooleanValue enableBlueberryBushSpawns;
    public static BooleanValue enableCranberryBushSpawns;
    public static BooleanValue enableWitchsCradleSpawns;
    public static BooleanValue enableHugePurpleMushroomSpawns;
    public static BooleanValue enableCabinSpawns;

    public static BooleanValue enableVoidLakeSpawns;
    public static BooleanValue enableSnapdragonSpawns;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Feature Configuration").push("ve_feature_config");

        server.comment("Overworld Configuration").push("ve_overworld_config");

        enableBlueberryBushSpawns = server.comment(compileOverworldComment("blueberry bushes"))
                .translation("ve.configBushes.enableBlueberryBushSpawns").worldRestart()
                .define("enable_blueberry_bush_spawns", true);

        enableCranberryBushSpawns = server.comment(compileOverworldComment("cranberry bushes"))
                .translation("ve.configBushes.enableCranberryBushSpawns").worldRestart()
                .define("enable_cranberry_bush_spawns", true);

        enableWitchsCradleSpawns = server.comment(compileOverworldComment("witch's cradles"))
                .translation("ve.configBushes.enableWitchsCradleSpawns").worldRestart()
                .define("enable_witchs_cradle_spawns", true);

        enableHugePurpleMushroomSpawns = server.comment(compileOverworldComment("big purple mushrooms"))
                .translation("ve.configMushroom.enableBigPurpleMushroomSpawns").worldRestart()
                .define("enable_big_purple_mushroom_spawns", true);

        enableCabinSpawns = server.comment(compileOverworldComment("cabins"))
                .translation("ve.configStructure.enableCabinSpawns").worldRestart().define("enable_cabin_spawns", true);

        server.pop();

        server.comment("End Configuration").push("ve_end_config");

        enableVoidLakeSpawns = server.comment(compileEndComment("void lakes"))
                .translation("ve.configLake.enableVoidLakeSpawns").worldRestart()
                .define("enable_void_lake_spawns", true);

        enableSnapdragonSpawns = server.comment(compileEndComment("snapdragons"))
                .translation("ve.configLake.enableSnapdragonSpawns").worldRestart()
                .define("enable_snapdragon_spawns", true);

        server.pop(2);
    }

    private static String compileOverworldComment(String name)
    {
        return compileComment(name, "overworld");
    }

    private static String compileEndComment(String name)
    {
        return compileComment(name, "end");
    }

    private static String compileComment(String name, String worldName)
    {
        return "If true " + name + " will spawn in the " + worldName + ".";
    }
}
