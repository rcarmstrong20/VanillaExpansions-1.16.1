package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class VeOreDataGenConfig
{
    public static IntValue netherSmokyQuartzOreVeinSize;
    public static IntValue netherRubyOreVeinSize;
    public static IntValue netherSmokyQuartzOreSpread;
    public static IntValue netherRubyOreSpread;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Ore Data Configuration").push("ve_ore_data_config");

        server.comment("Vein Size Configuration").push("ve_vein_size");

        netherSmokyQuartzOreVeinSize = server.comment(compileVeinSizeComment("nether smoky quartz ore", "14"))
                .translation("ve.configNetherOre.netherSmokyQuartzOreVeinSize").worldRestart()
                .defineInRange("nether_smoky_quartz_ore_vein_size", 14, 0, 17);

        netherRubyOreVeinSize = server.comment(compileVeinSizeComment("nether ruby ore", "3"))
                .translation("ve.configNetherOre.netherRubyOreVeinSize").worldRestart()
                .defineInRange("nether_ruby_ore_vein_size", 3, 0, 17);

        server.pop();

        server.comment("Spread Configuration").push("ve_ore_spread");

        netherSmokyQuartzOreSpread = server.comment(compileSpreadComment("nether smoky quartz ore", "16"))
                .translation("ve.configNetherOre.netherSmokyQuartzOreVeinSize").worldRestart()
                .defineInRange("nether_smoky_quartz_ore_vein_size", 16, 2, 20);

        netherRubyOreSpread = server.comment(compileSpreadComment("nether ruby ore", "7"))
                .translation("ve.configNetherOre.netherRubyOreVeinSize").worldRestart()
                .defineInRange("nether_ruby_ore_vein_size", 7, 2, 20);

        server.pop(2);
    }

    private static String compileVeinSizeComment(String name, String defaultValue)
    {
        return "Sets vein size for " + name + ". (Default: " + defaultValue + ")";
    }

    private static String compileSpreadComment(String name, String defaultValue)
    {
        return "Sets how spread out the ore blocks are when spawned for " + name + ". (Default: " + defaultValue + ")";
    }
}
