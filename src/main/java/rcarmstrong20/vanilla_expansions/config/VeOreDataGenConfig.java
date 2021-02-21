package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class VeOreDataGenConfig
{
    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Ore Data Configuration").push("ve_ore_data_config");

        VeVeinSizeConfig.init(server);
        VeSpreadConfig.init(server);

        server.pop();
    }

    public static class VeVeinSizeConfig
    {
        public static IntValue netherSmokyQuartzOreVeinSize;
        public static IntValue netherRubyOreVeinSize;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Vein Size Configuration").push("ve_vein_size");

            netherSmokyQuartzOreVeinSize = server.comment(compileVeinSizeComment("nether smoky quartz ore", "14"))
                    .translation("ve_ore_data.ve_vein_size.nether_smoky_quartz_ore_vein_size").worldRestart()
                    .defineInRange("nether_smoky_quartz_ore_vein_size", 14, 0, 17);

            netherRubyOreVeinSize = server.comment(compileVeinSizeComment("nether ruby ore", "3"))
                    .translation("ve_ore_data.ve_vein_size.nether_ruby_ore_vein_size").worldRestart()
                    .defineInRange("nether_ruby_ore_vein_size", 3, 0, 17);

            server.pop();
        }

        /**
         *
         * @param name         The name of this ore.
         * @param defaultValue
         * @return A new string with the name and default value inserted.
         */
        private static String compileVeinSizeComment(String name, String defaultValue)
        {
            return "Sets vein size for " + name + ". (Default: " + defaultValue + ")";
        }
    }

    public static class VeSpreadConfig
    {
        public static IntValue netherSmokyQuartzOreSpread;
        public static IntValue netherRubyOreSpread;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Spread Configuration").push("ve_ore_spread");

            netherSmokyQuartzOreSpread = server.comment(compileSpreadComment("nether smoky quartz ore", "16"))
                    .translation("ve_ore_data.ve_ore_spread.nether_smoky_quartz_ore_spread").worldRestart()
                    .defineInRange("nether_smoky_quartz_ore_spread", 16, 2, 20);

            netherRubyOreSpread = server.comment(compileSpreadComment("nether ruby ore", "16"))
                    .translation("ve_ore_data.ve_ore_spread.nether_ruby_ore_spread").worldRestart()
                    .defineInRange("nether_ruby_ore_spread", 16, 2, 20);

            server.pop();
        }

        /**
         *
         * @param name         The name of this ore.
         * @param defaultValue
         * @return A new string with the name and default value inserted.
         */
        private static String compileSpreadComment(String name, String defaultValue)
        {
            return "Sets how spread out the ores are when spawned for " + name + ". (Default: " + defaultValue + ")";
        }
    }
}
