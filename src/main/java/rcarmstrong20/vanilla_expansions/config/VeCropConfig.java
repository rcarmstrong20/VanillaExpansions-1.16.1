package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VeCropConfig
{
    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Vanilla Expansions Crops Configuration").push("ve_crop_config");

        VeHarvestConfig.init(server);

        server.pop();
    }

    public static class VeHarvestConfig
    {
        public static BooleanValue enableSmartHarvest;

        public static void init(ForgeConfigSpec.Builder server)
        {
            server.comment("Crop Harvest Configuration").push("ve_harvest");

            enableSmartHarvest = server
                    .comment("When true crops can be harvested and re-planted simply by right-clicking the crop.")
                    .translation("ve_crop_config.ve_harvest.enable_smart_harvest").worldRestart()
                    .define("enable_smart_harvest", true);

            server.pop();
        }
    }
}
