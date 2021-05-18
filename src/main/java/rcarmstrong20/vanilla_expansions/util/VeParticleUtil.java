package rcarmstrong20.vanilla_expansions.util;

import java.util.Random;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.SoundEvents;

public class VeParticleUtil
{
    /**
     * Spawn particles around the player.
     *
     * @param particle     The type of particle to spawn.
     * @param serverPlayer
     */
    public static void spawnTotemParticles(BasicParticleType particle, ServerPlayerEntity serverPlayer)
    {
        Random random = serverPlayer.getRandom();
        int max = random.nextInt(15) + 15;

        serverPlayer.playSound(SoundEvents.TOTEM_USE, 20000, 10000);

        for (int i = 0; i <= max; i++)
        {
            int count = random.nextInt(5) + 5;
            double x = serverPlayer.getRandomX(2.0);
            double y = serverPlayer.getRandomY();
            double z = serverPlayer.getRandomZ(2.0);

            serverPlayer.getServer().overworld().sendParticles(serverPlayer, particle, true, x, y, z, count, 0.0, 1.0,
                    0.0, 0.0);
        }
    }
}
