package bidimap;

import java.util.*;

public class BidiMapImpl<K, V> implements BidiMap<K, V> {
    private final Map<K, V> forward = new HashMap<>();
    private final Map<V, K> backward = new HashMap<>();

    @Override
    public V put(K key, V value) {
        if (forward.containsKey(key)) backward.remove(forward.get(key));
        if (backward.containsKey(value)) forward.remove(backward.get(value));
        forward.put(key, value);
        backward.put(value, key);
        return value;
    }

    @Override
    public K getKey(V value) {
        return backward.get(value);
    }

    @Override
    public V getValue(K key) {
        return forward.get(key);
    }

    @Override
    public V removeByKey(K key) {
        V val = forward.remove(key);
        if (val != null) backward.remove(val);
        return val;
    }

    @Override
    public K removeByValue(V value) {
        K key = backward.remove(value);
        if (key != null) forward.remove(key);
        return key;
    }
}
