package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
    public static final List<TileEntityType<?>> TILE_ENTITY_TYPES = new ArrayList<>();

    public static TileEntityType<VeColoredCampfireTileEntity> colored_campfire = register("colored_campfire",
            TileEntityType.Builder.create(VeColoredCampfireTileEntity::new, VeBlocks.whiteCampfire,
                    VeBlocks.orangeCampfire, VeBlocks.magentaCampfire, VeBlocks.lightBlueCampfire,
                    VeBlocks.yellowCampfire, VeBlocks.limeCampfire, VeBlocks.pinkCampfire, VeBlocks.grayCampfire,
                    VeBlocks.lightGrayCampfire, VeBlocks.cyanCampfire, VeBlocks.purpleCampfire, VeBlocks.blueCampfire,
                    VeBlocks.brownCampfire, VeBlocks.greenCampfire, VeBlocks.redCampfire, VeBlocks.blackCampfire));

    /**
     * @param <T>     An instance of the TileEntity class.
     * @param name    The name of the tile entity type.
     * @param builder A new instance of TileEntityType.Builder<T> for the tile
     *                entity type.
     * @return A new tile entity type.
     */
    private static <T extends TileEntity> TileEntityType<T> register(String name, TileEntityType.Builder<T> builder)
    {
        return register(new ResourceLocation(VanillaExpansions.MOD_ID, name), builder);
    }

    private static <T extends TileEntity> TileEntityType<T> register(ResourceLocation location,
            TileEntityType.Builder<T> builder)
    {
        TileEntityType<T> type = builder.build(null);
        type.setRegistryName(location);
        TILE_ENTITY_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerTypes(final RegistryEvent.Register<TileEntityType<?>> event)
    {
        TILE_ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
        TILE_ENTITY_TYPES.clear();

        VanillaExpansions.LOGGER.info("Tile entity types registered.");
    }
}
