package rcarmstrong20.vanilla_expansions.events;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import rcarmstrong20.vanilla_expansions.core.VeVillagerProfessions;
import rcarmstrong20.vanilla_expansions.entity.villager.VeVillagerTrades;
import rcarmstrong20.vanilla_expansions.entity.villager.VeVillagerType;

public class VeVillagerTradesEvent
{
    /**
     * This field is a mapping that represents which biome each villager type can
     * spawn in.
     */
    public static final Field BY_BIOME_FIELD = ObfuscationReflectionHelper.findField(VillagerType.class,
            "field_221180_h");

    @SuppressWarnings("unchecked") // Needed for BY_BIOME_FIELD.
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void addTrades(VillagerTradesEvent event)
    {
        addTrade(event, VeVillagerProfessions.lumberjack, VeVillagerTrades.lumberjackTrades);

        // Associate each villager type with the biomes after adding in the custom
        // trades.
        Map<RegistryKey<Biome>, VillagerType> byBiome;
        try
        {
            BY_BIOME_FIELD.setAccessible(true);

            byBiome = (Map<RegistryKey<Biome>, VillagerType>) BY_BIOME_FIELD.get(VillagerType.class);

            byBiome.put(Biomes.CRIMSON_FOREST, VeVillagerType.crimson);
            byBiome.put(Biomes.WARPED_FOREST, VeVillagerType.warped);

            BY_BIOME_FIELD.setAccessible(false);
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Adds a trade to the game for a specific profession.
     *
     * @param event
     * @param profession The profession to populate with trades.
     * @param trades     The trade map containing the tier and the associated trade.
     */
    private static void addTrade(VillagerTradesEvent event, VillagerProfession profession,
            ImmutableMap<Integer, List<ITrade>> trades)
    {
        for (int i = 1; i <= trades.size(); i++)
        {
            if (event.getType().equals(profession))
            {
                event.getTrades().put(i, trades.get(i));
            }
        }
    }
}
