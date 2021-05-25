package rcarmstrong20.vanilla_expansions.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import rcarmstrong20.vanilla_expansions.core.VeItems;

public class VeTotemMaps
{
    public static final HashMap<Item, Integer> FORTUNATE_TOTEM_MAP = (new VeHashMapBuilder<Item, Integer>())
            .put(VeItems.coalTotemOfTheFortunate, 120).put(VeItems.ironTotemOfTheFortunate, 120)
            .put(VeItems.goldTotemOfTheFortunate, 120).put(VeItems.diamondTotemOfTheFortunate, 120).build();
    public static final Map<Item, Integer> BRUTE_TOTEM_MAP = (new VeHashMapBuilder<Item, Integer>())
            .put(VeItems.coalTotemOfTheFortunate, 60).put(VeItems.ironTotemOfTheFortunate, 60)
            .put(VeItems.goldTotemOfTheFortunate, 60).put(VeItems.diamondTotemOfTheFortunate, 60).build();
    public static final Map<Item, Integer> TOTEM_GUARDIAN_MAP = (new VeHashMapBuilder<Item, Integer>())
            .put(VeItems.coalTotemOfTheFortunate, 240).put(VeItems.ironTotemOfTheFortunate, 360)
            .put(VeItems.goldTotemOfTheFortunate, 480).put(VeItems.diamondTotemOfTheFortunate, 600).build();
}
