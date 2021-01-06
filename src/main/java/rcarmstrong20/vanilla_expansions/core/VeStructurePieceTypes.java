package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeNetherCabinPieces;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeOverworldCabinPieces;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructurePieceTypes
{
    public static IStructurePieceType overworldCabinPiece = register("overworld_cabin_piece",
            VeOverworldCabinPieces.VePiece::new);
    public static IStructurePieceType netherCabinPiece = register("nether_cabin_piece",
            VeNetherCabinPieces.VePiece::new);

    /**
     * A helper method for automatically registering every new structure piece.
     *
     * @param name           The name of this piece.
     * @param structurePiece An instance of a new structure piece.
     * @return A new registered structure piece.
     */
    private static IStructurePieceType register(String name, IStructurePieceType structurePiece)
    {
        Registry<IStructurePieceType> registry = Registry.STRUCTURE_PIECE;

        return Registry.register(registry, new ResourceLocation(VanillaExpansions.MOD_ID, name), structurePiece);
    }
}
