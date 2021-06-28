package rndmaccess.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VEStructureConfig
{
    public static BooleanValue taigaCabinFlag;
    public static BooleanValue forestCabinFlag;
    public static BooleanValue crimsonCabinFlag;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Structure Config").push("structure");

        taigaCabinFlag = server.comment(compileBooleanSpawnComment("taiga cabins")).worldRestart()
                .define("EnableTaigaCabinSpawns", true);
        forestCabinFlag = server.comment(compileBooleanSpawnComment("forest cabins")).worldRestart()
                .define("EnableForestCabinSpawns", true);
        crimsonCabinFlag = server.comment(compileBooleanSpawnComment("crimson cabins")).worldRestart()
                .define("EnableCrimsonCabinSpawns", true);

        server.pop();
    }

    public static String compileBooleanSpawnComment(String name)
    {
        return "When true " + name + " will spawn.";
    }
}
