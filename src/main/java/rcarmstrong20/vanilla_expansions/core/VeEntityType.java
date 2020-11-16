
package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeEntityType
{
    private static final List<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

    @SuppressWarnings("unused")
    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder)
    {
        EntityType<T> type = builder.build(VanillaExpansions.MOD_ID + ":" + name);
        type.setRegistryName(new ResourceLocation(VanillaExpansions.MOD_ID, name));
        ENTITY_TYPES.add(type);
        return type;
    }

    @SubscribeEvent
    public static void registerTypes(final RegistryEvent.Register<EntityType<?>> event)
    {
        ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
        ENTITY_TYPES.clear();

        VanillaExpansions.LOGGER.info("Entity types registered.");
    }
}