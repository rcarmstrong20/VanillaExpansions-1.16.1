package rcarmstrong20.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class VEBlockConfig
{
    public static BooleanValue enableSmartHarvest;
    public static IntValue spruceConePercent;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Block Config").push("block");

        enableSmartHarvest = server
                .comment("When true crops can be harvested and re-planted simply by right-clicking the crop.")
                .worldRestart().define("EnableSmartHarvest", true);
        spruceConePercent = server.comment("The chance that a spruce cone will drop from spruce leaves.").worldRestart()
                .defineInRange("SpruceConePercent", 5, 0, 100);

        server.pop();
    }
}
