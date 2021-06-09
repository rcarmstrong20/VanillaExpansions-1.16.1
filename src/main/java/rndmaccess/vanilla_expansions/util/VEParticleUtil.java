package rndmaccess.vanilla_expansions.util;

import java.util.Random;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.SoundEvents;

public class VEParticleUtil
{
    /**
     * Spawn particles around the player.
     *
     * @param particle The type of particle to spawn.
     * @param player
     */
    public static void spawnTotemParticles(BasicParticleType particle, ServerPlayerEntity player)
    {
        Random random = player.getRandom();
        int max = random.nextInt(15) + 15;

        player.playSound(SoundEvents.TOTEM_USE, 20000, 10000);

        for (int i = 0; i <= max; i++)
        {
            int count = random.nextInt(5) + 5;
            double x = player.getRandomX(2.0);
            double y = player.getRandomY();
            double z = player.getRandomZ(2.0);

            player.getServer().overworld().sendParticles(player, particle, true, x, y, z, count, 0.0, 1.0, 0.0, 0.0);
        }
    }
}
