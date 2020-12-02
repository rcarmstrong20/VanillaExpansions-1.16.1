package rcarmstrong20.vanilla_expansions.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.gen.feature.structure.VeCabinPieces;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeStructurePieceTypes
{
    public static final Map<String, IStructurePieceType> STRUCTURE_PIECES = new HashMap<>();

    public static IStructurePieceType cabinPiece = register("cabin_piece", VeCabinPieces.VePiece::new);

    public static void register()
    {
        Registry<IStructurePieceType> registry = Registry.STRUCTURE_PIECE;

        STRUCTURE_PIECES.forEach((name, structurePiece) -> Registry.register(registry,
                new ResourceLocation(VanillaExpansions.MOD_ID, name), structurePiece));
        STRUCTURE_PIECES.clear();

        VanillaExpansions.LOGGER.info("Structure pieces registered.");
    }

    /**
     * @param name           The name of this piece.
     * @param structurePiece An instance of a new structure piece.
     * @return The instance of this piece.
     */
    private static IStructurePieceType register(String name, IStructurePieceType structurePiece)
    {
        if (!STRUCTURE_PIECES.containsKey(name))
        {
            STRUCTURE_PIECES.put(name, structurePiece);
        }
        return structurePiece;
    }
}
