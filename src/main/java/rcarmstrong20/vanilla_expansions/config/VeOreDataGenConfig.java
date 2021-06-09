package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class VEOreDataGenConfig
{
    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Ore Data Config").push("oreData");

        VEVeinSizeConfig.init(server);
        VESpreadConfig.init(server);

        server.pop();
    }

    public static class VEVeinSizeConfig
    {
        public static IntValue netherSmokyQuartzOreVeinSize;
        public static IntValue blackstoneRubyOreVeinSize;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Vein Size Config").push("veinSize");

            netherSmokyQuartzOreVeinSize = server.comment(compileVeinSizeComment("nether smoky quartz ore", "14"))
                    .worldRestart().defineInRange("NetherSmokyQuartzOreVeinSize", 14, 0, 17);
            blackstoneRubyOreVeinSize = server.comment(compileVeinSizeComment("blackstone ruby ore", "5"))
                    .worldRestart().defineInRange("BlackstoneRubyOreVeinSize", 5, 0, 17);

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

    public static class VESpreadConfig
    {
        public static IntValue netherSmokyQuartzOreSpread;
        public static IntValue blackstoneRubyOreSpread;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Spread Config").push("oreSpread");

            netherSmokyQuartzOreSpread = server.comment(compileSpreadComment("nether smoky quartz ore", "16"))
                    .worldRestart().defineInRange("NetherSmokyQuartzOreSpread", 16, 2, 20);
            blackstoneRubyOreSpread = server.comment(compileSpreadComment("blackstone ruby ore", "16")).worldRestart()
                    .defineInRange("BlackstoneRubyOreSpread", 16, 2, 20);

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
