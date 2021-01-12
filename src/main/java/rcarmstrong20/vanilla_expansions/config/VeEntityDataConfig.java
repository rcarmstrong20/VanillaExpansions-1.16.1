package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class VeEntityDataConfig
{

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Entity Data Configuration").push("ve_entity_data_config");

        SpawnWeightConfig.init(server);
        MinSpawnSizeConfig.init(server);
        MaxSpawnSizeConfig.init(server);

        server.pop();
    }

    public static class SpawnWeightConfig
    {
        public static IntValue netherZombieVillagerSpawnWeight;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Spawn Weight Configuration").push("ve_spawn_weight");

            netherZombieVillagerSpawnWeight = server.comment(compileSpawnWeightComment("nether zombie villager", "1"))
                    .translation("ve_entity_data_config.spawn_weight.nether_zombie_villager_spawn_weight")
                    .worldRestart().defineInRange("nether_zombie_villager_spawn_weight", 1, 1, 100);

            server.pop();
        }

        /**
         *
         * @param name         The name of this mob.
         * @param defaultValue
         * @return A new string with the name and default value.
         */
        private static String compileSpawnWeightComment(String name, String defaultValue)
        {
            return "Sets spawn weight for the " + name + " mob. (Default: " + defaultValue + ")";
        }
    }

    public static class MinSpawnSizeConfig
    {
        public static IntValue netherZombieVillagerMinSpawnSize;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Min Spawn Count Config").push("ve_min_spawn_count");

            netherZombieVillagerMinSpawnSize = server.comment(compileMinSpawnComment("nether zombie villager", "2"))
                    .translation("ve_entity_data.ve_min_spawn_size.nether_zombie_villager_min_spawn_count")
                    .worldRestart().defineInRange("nether_zombie_villager_min_spawn_count", 2, 1, 200);

            server.pop();
        }

        /**
         *
         * @param name         The name of this mob.
         * @param defaultValue
         * @return A new string with the name and default value.
         */
        private static String compileMinSpawnComment(String name, String defaultValue)
        {
            return "The minimum number of " + name + "s that can spawn at once. (Default: " + defaultValue + ")";
        }
    }

    public static class MaxSpawnSizeConfig
    {
        public static IntValue netherZombieVillagerMaxSpawnSize;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Max Spawn Count Config").push("ve_max_spawn_count");

            netherZombieVillagerMaxSpawnSize = server.comment(compileMaxSpawnComment("nether zombie villager", "4"))
                    .translation("ve_entity_data_config.max_spawn_count.zombie_villager_nether_max_spawn_count")
                    .worldRestart().defineInRange("nether_zombie_villager_max_spawn_count", 4, 1, 200);

            server.pop();
        }

        /**
         *
         * @param name         The name of this mob.
         * @param defaultValue
         * @return A new string with the name and default value.
         */
        private static String compileMaxSpawnComment(String name, String defaultValue)
        {
            return "The maximum number of " + name + "s that can spawn at once. (Default: " + defaultValue + ")";
        }
    }
}
