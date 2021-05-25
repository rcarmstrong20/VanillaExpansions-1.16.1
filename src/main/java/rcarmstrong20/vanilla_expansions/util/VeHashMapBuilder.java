package rcarmstrong20.vanilla_expansions.util;

import java.util.HashMap;

public class VeHashMapBuilder<K, V>
{
    private HashMap<K, V> map;

    public VeHashMapBuilder()
    {
        this.map = new HashMap<>();
    }

    protected VeHashMapBuilder<K, V> put(K key, V value)
    {
        this.map.put(key, value);

        return this;
    }

    protected HashMap<K, V> build()
    {
        return this.map;
    }
}
