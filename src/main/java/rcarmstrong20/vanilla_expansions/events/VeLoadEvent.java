package rcarmstrong20.vanilla_expansions.events;

import net.minecraft.world.World;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.core.VeStructure;

public class VeLoadEvent
{
    @SubscribeEvent
    public void onLoad(final WorldEvent.Load event)
    {
        if (event.getWorld() instanceof ServerWorld)
        {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            // Don't add structures to superflat worlds.
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator
                    && serverWorld.dimension().equals(World.OVERWORLD))
            {
                return;
            }

            addSpacing(serverWorld, VeStructure.overworldCabin, 15, 10, 724628428);
            addSpacing(serverWorld, VeStructure.netherCabin, 10, 5, 487422842);
        }
    }

    /**
     * A helper method for adding spacing to structures.
     *
     * @param serverWorld
     * @param structure          The structure to add.
     * @param maxChunkSeperation The minimum chunk distance between spawning
     *                           attempts.
     * @param minChunkSeperation The maximum chunk distance between spawning
     *                           attempts.
     * @param structureSeed      The seed that is used to make sure that the
     *                           structure is not spawned at the same position as
     *                           another of this type.
     */
    private static void addSpacing(ServerWorld serverWorld, Structure<?> structure, int maxChunkSeperation,
            int minChunkSeperation, int structureSeed)
    {
        serverWorld.getChunkSource().getGenerator().getSettings().structureConfig().put(structure,
                new StructureSeparationSettings(maxChunkSeperation, minChunkSeperation, structureSeed));
    }
}
