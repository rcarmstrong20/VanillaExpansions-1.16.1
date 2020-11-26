package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinPieces;

// @Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus =
// Mod.EventBusSubscriber.Bus.MOD)
public class VeStructurePieceTypes
{
    public static IStructurePieceType cabinPiece = register("cabin_piece", VeCabinPieces.VePiece::new);

    /**
     * @param name The name of the new piece.
     * @param type The structure piece factory for the new piece.
     * @return Registers a new structure piece.
     */
    private static IStructurePieceType register(String name, IStructurePieceType type)
    {
        System.out.println("Structure pieces registered.");
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(VanillaExpansions.MOD_ID, name), type);
    }
}
