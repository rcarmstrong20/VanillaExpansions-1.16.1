package rndmaccess.vanilla_expansions.core;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.entity.hostile.VECharredRemnantEntity;
import rndmaccess.vanilla_expansions.entity.passive.VEEnderHorseEntity;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEEntityTypes
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES,
            VanillaExpansions.MOD_ID);

    public static EntityType<VEEnderHorseEntity> enderHorse = register("ender_horse", EntityType.Builder
            .of(VEEnderHorseEntity::new, EntityClassification.MONSTER).sized(1.3964844F, 1.6F).clientTrackingRange(10));
    public static EntityType<VECharredRemnantEntity> charredRemnant = register("charred_remnant", EntityType.Builder
            .of(VECharredRemnantEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));

    /**
     * Add this entity to the registry list.
     *
     * @param <C>           The type for the entity.
     * @param name          The name of the entity.
     * @param entityBuilder The builder for this entity.
     * @return The new entity.
     */
    private static <T extends MobEntity> EntityType<T> register(String name, EntityType.Builder<T> entityBuilder)
    {
        EntityType<T> entityType = entityBuilder.build(VanillaExpansions.MOD_ID + ":" + name);
        ENTITIES.register(name, () -> entityType);

        return entityType;
    }
}
