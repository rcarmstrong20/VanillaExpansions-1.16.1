package rndmaccess.vanilla_expansions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class VEEntityConfig
{
    public static BooleanValue enableSaveTheBunnies;

    public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client)
    {
        server.comment("Entity Config").push("entity");

        enableSaveTheBunnies = server.comment("Cancels bunny fall damage when true.").worldRestart()
                .define("EnableSaveTheBunnies", true);

        server.pop();
    }
}
