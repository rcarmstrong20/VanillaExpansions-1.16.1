package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class VeCropConfig
{
    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Block Configuration").push("ve_block_config");

        VeBlockConfig.init(server);

        server.pop();
    }

    public static class VeBlockConfig
    {
        public static BooleanValue enableSmartHarvest;
        public static IntValue spruceConePercent;

        public static void init(ForgeConfigSpec.Builder server)
        {
            enableSmartHarvest = server
                    .comment("When true crops can be harvested and re-planted simply by right-clicking the crop.")
                    .translation("ve_block_config.enable_smart_harvest").worldRestart()
                    .define("enable_smart_harvest", true);

            spruceConePercent = server.comment("The chance that a spruce cone will drop from spruce leaves.")
                    .translation("ve_block_config.spruce_cone_percent").defineInRange("spruce_cone_percent", 5, 0, 100);
        }
    }
}
