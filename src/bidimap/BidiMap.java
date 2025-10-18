package bidimap;

public interface BidiMap<K, V> {
    V put(K key, V value);
    K getKey(V value);
    V getValue(K key);
    V removeByKey(K key);
    K removeByValue(V value);
}