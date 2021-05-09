package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.tile_entity.VeColoredCampfireTileEntity;

/**
 * A class for holding every tile entity type instance that vanilla expansions
 * has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeTileEntityType
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
            .create(ForgeRegistries.TILE_ENTITIES, VanillaExpansions.MOD_ID);

    public static TileEntityType<VeColoredCampfireTileEntity> coloredCampfire = register("colored_campfire",
            TileEntityType.Builder.of(VeColoredCampfireTileEntity::new, VeBlocks.whiteCampfire, VeBlocks.orangeCampfire,
                    VeBlocks.magentaCampfire, VeBlocks.lightBlueCampfire, VeBlocks.yellowCampfire,
                    VeBlocks.limeCampfire, VeBlocks.pinkCampfire, VeBlocks.grayCampfire, VeBlocks.lightGrayCampfire,
                    VeBlocks.cyanCampfire, VeBlocks.purpleCampfire, VeBlocks.blueCampfire, VeBlocks.brownCampfire,
                    VeBlocks.greenCampfire, VeBlocks.redCampfire, VeBlocks.blackCampfire));

    private static <T extends TileEntity> TileEntityType<T> register(String name, TileEntityType.Builder<T> builder)
    {
        TileEntityType<T> type = builder.build(null);
        TILE_ENTITY_TYPES.register(name, () -> type);
        return type;
    }
}
