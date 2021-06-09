package rndmaccess.vanilla_expansions.core;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.tile_entity.VEColoredCampfireTileEntity;

/**
 * A class for holding every tile entity type instance that vanilla expansions
 * has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VETileEntityTypes
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
            .create(ForgeRegistries.TILE_ENTITIES, VanillaExpansions.MOD_ID);

    public static TileEntityType<VEColoredCampfireTileEntity> coloredCampfire = register("colored_campfire",
            TileEntityType.Builder.of(VEColoredCampfireTileEntity::new, VEBlocks.whiteCampfire, VEBlocks.orangeCampfire,
                    VEBlocks.magentaCampfire, VEBlocks.lightBlueCampfire, VEBlocks.yellowCampfire,
                    VEBlocks.limeCampfire, VEBlocks.pinkCampfire, VEBlocks.grayCampfire, VEBlocks.lightGrayCampfire,
                    VEBlocks.cyanCampfire, VEBlocks.purpleCampfire, VEBlocks.blueCampfire, VEBlocks.brownCampfire,
                    VEBlocks.greenCampfire, VEBlocks.redCampfire, VEBlocks.blackCampfire));

    private static <T extends TileEntity> TileEntityType<T> register(String name, TileEntityType.Builder<T> builder)
    {
        TileEntityType<T> type = builder.build(null);
        TILE_ENTITY_TYPES.register(name, () -> type);
        return type;
    }
}
