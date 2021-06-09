package rndmaccess.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VEStructureGenConfig
{
    public static BooleanValue enableTaigaCabinSpawns;
    public static BooleanValue enableForestCabinSpawns;
    public static BooleanValue enableCrimsonCabinSpawns;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Structure Config").push("structure");

        enableTaigaCabinSpawns = server.comment(compileBooleanSpawnComment("taiga cabins")).worldRestart()
                .define("EnableTaigaCabinSpawns", true);
        enableForestCabinSpawns = server.comment(compileBooleanSpawnComment("forest cabins")).worldRestart()
                .define("EnableForestCabinSpawns", true);
        enableCrimsonCabinSpawns = server.comment(compileBooleanSpawnComment("crimson cabins")).worldRestart()
                .define("EnableCrimsonCabinSpawns", true);

        server.pop();
    }

    public static String compileBooleanSpawnComment(String name)
    {
        return "When true " + name + " will spawn.";
    }
}
