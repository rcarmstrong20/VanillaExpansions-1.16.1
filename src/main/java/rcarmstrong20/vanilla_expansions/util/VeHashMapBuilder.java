package rcarmstrong20.vanilla_expansions.util;

import java.util.HashMap;

public class VEHashMapBuilder<K, V>
{
    private HashMap<K, V> map;

    public VEHashMapBuilder()
    {
        this.map = new HashMap<>();
    }

    /**
     * Adds a new element to the hash map and returns this class allowing the map to
     * be built in a single line.
     *
     * @param key   The key.
     * @param value The value to map this key to.
     * @return This class with the new (key, value) pair inserted.
     */
    public VEHashMapBuilder<K, V> put(K key, V value)
    {
        map.put(key, value);
        return this;
    }

    /**
     * This method will build the hash map. Call this after adding every pair is
     * added.
     *
     * @return A map with the (key, value) pairs.
     */
    public HashMap<K, V> build()
    {
        return this.map;
    }
}
