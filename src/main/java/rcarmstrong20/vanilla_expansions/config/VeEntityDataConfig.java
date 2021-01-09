package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class VeEntityDataConfig
{

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Entity Data Configuration").push("ve_entity_data_config");

        SpawnWeightConfig.init(server, client);
        MinimumSpawnSizeConfig.init(server, client);
        MaximumSpawnSizeConfig.init(server, client);

        server.pop();
    }

    public static class SpawnWeightConfig
    {
        public static IntValue zombieVillagerSpawnWeight;

        public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
        {
            server.comment("Spawn Weight Configuration").push("ve_spawn_weight");

            server.comment("Nether Configuration").push("ve_nether_config");

            zombieVillagerSpawnWeight = server.comment(compileSpawnWeightComment("villager zombie", "1"))
                    .translation("ve_entity_data_config.spawn_weight.zombie_villager").worldRestart()
                    .defineInRange("zombie_villager_spawn_weight", 1, 1, 100);

            server.pop(2);
        }
    }

    public static class MinimumSpawnSizeConfig
    {
        public static IntValue zombieVillagerMinSpawnSize;

        public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
        {
            server.comment("Minimum Spawn Size Configuration").push("ve_min_spawn_size");

            server.comment("Nether Configuration").push("ve_nether_config");

            zombieVillagerMinSpawnSize = server.comment(compileMinSpawnComment("villager zombie", "2"))
                    .translation("ve_entity_data_config.min_spawn_size.zombie_villager").worldRestart()
                    .defineInRange("zombie_villager_min_spawn_size", 2, 1, 200);

            server.pop(2);
        }
    }

    public static class MaximumSpawnSizeConfig
    {
        public static IntValue zombieVillagerMaxSpawnSize;

        public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
        {
            server.comment("Maximum Spawn Size Configuration").push("ve_max_spawn_size");

            server.comment("Nether Configuration").push("ve_nether_config");

            zombieVillagerMaxSpawnSize = server.comment(compileMaxSpawnComment("villager zombie", "4"))
                    .translation("ve_entity_data_config.max_spawn_size.zombie_villager").worldRestart()
                    .defineInRange("zombie_villager_max_spawn_size", 4, 1, 200);

            server.pop(2);
        }
    }

    private static String compileSpawnWeightComment(String name, String defaultValue)
    {
        return "Sets spawn weight for the " + name + " mob. (Default: " + defaultValue + ")";
    }

    private static String compileMinSpawnComment(String name, String defaultValue)
    {
        return "Sets spawn the minimum spawn size for the " + name
                + " mob. This must be less than the maximum spawn size or there will be bugs. (Default: " + defaultValue
                + ")";
    }

    private static String compileMaxSpawnComment(String name, String defaultValue)
    {
        return "Sets spawn the maximum spawn size for the " + name
                + " mob. This must be more than the minimum spawn size or there will be bugs. (Default: " + defaultValue
                + ")";
    }
}
