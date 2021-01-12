package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VeEntityConfig
{
    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Entity Configuration").push("ve_entity_config");

        VeOverworldConfig.init(server);
        VeNetherConfig.init(server);

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
        public static BooleanValue enableSaveTheBunnies;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Overworld Configuration").push("ve_overworld_config");

            enableSaveTheBunnies = server.comment("Cancels bunny fall damage when true.")
                    .translation("ve_entity_config.ve_overworld_config.enable_save_the_bunnies").worldRestart()
                    .define("enable_save_the_bunnies", true);

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
        public static BooleanValue enableZombieVillagersSpawns;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Nether Configuration").push("ve_nether_config");

            enableZombieVillagersSpawns = server.comment("When enabled zombie villagers will spawn in the nether.")
                    .translation("ve_entity_config.ve_nether_config.enable_zombie_villager_spawns").worldRestart()
                    .define("enable_zombie_villager_spawns", true);

            server.pop();
        }
    }
}
