package tree;

class TreeNode<K, V> {
    K key;
    V value;
    TreeNode<K, V> left, right, parent;
    boolean color; // true = RED, false = BLACK

    TreeNode(K key, V value, TreeNode<K, V> parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
        this.color = true;
    }
}
