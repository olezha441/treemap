package tree;

import java.util.*;

public class MyTreeMap<K, V> {
    private TreeNode<K, V> root;
    private int size = 0;
    private Comparator<? super K> comparator;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public MyTreeMap() {}

    public MyTreeMap(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    private int compare(K k1, K k2) {
        return (comparator == null)
                ? ((Comparable<? super K>) k1).compareTo(k2)
                : comparator.compare(k1, k2);
    }

    public V put(K key, V value) {
        if (key == null) throw new NullPointerException("null key not supported");

        if (root == null) {
            root = new TreeNode<>(key, value, null);
            root.color = BLACK;
            size++;
            return null;
        }

        TreeNode<K, V> parent = null;
        TreeNode<K, V> x = root;
        int cmp = 0;
        while (x != null) {
            parent = x;
            cmp = compare(key, x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {
                V old = x.value;
                x.value = value;
                return old;
            }
        }

        TreeNode<K, V> newNode = new TreeNode<>(key, value, parent);
        if (cmp < 0) parent.left = newNode;
        else parent.right = newNode;

        fixAfterInsert(newNode);
        size++;
        return null;
    }

    private void fixAfterInsert(TreeNode<K, V> x) {
        while (x != null && x != root && x.parent.color == RED) {
            TreeNode<K, V> p = x.parent;
            TreeNode<K, V> gp = p.parent;

            if (gp == null) break;
            if (p == gp.left) {
                TreeNode<K, V> uncle = gp.right;
                if (uncle != null && uncle.color == RED) {
                    p.color = BLACK;
                    uncle.color = BLACK;
                    gp.color = RED;
                    x = gp;
                } else {
                    if (x == p.right) {
                        x = p;
                        rotateLeft(x);
                    }
                    p.color = BLACK;
                    gp.color = RED;
                    rotateRight(gp);
                }
            } else {
                TreeNode<K, V> uncle = gp.left;
                if (uncle != null && uncle.color == RED) {
                    p.color = BLACK;
                    uncle.color = BLACK;
                    gp.color = RED;
                    x = gp;
                } else {
                    if (x == p.left) {
                        x = p;
                        rotateRight(x);
                    }
                    p.color = BLACK;
                    gp.color = RED;
                    rotateLeft(gp);
                }
            }
        }
        root.color = BLACK;
    }

    private void rotateLeft(TreeNode<K, V> p) {
        if (p == null) return;
        TreeNode<K, V> r = p.right;
        p.right = r.left;
        if (r.left != null) r.left.parent = p;
        r.parent = p.parent;
        if (p.parent == null) root = r;
        else if (p.parent.left == p) p.parent.left = r;
        else p.parent.right = r;
        r.left = p;
        p.parent = r;
    }

    private void rotateRight(TreeNode<K, V> p) {
        if (p == null) return;
        TreeNode<K, V> l = p.left;
        p.left = l.right;
        if (l.right != null) l.right.parent = p;
        l.parent = p.parent;
        if (p.parent == null) root = l;
        else if (p.parent.right == p) p.parent.right = l;
        else p.parent.left = l;
        l.right = p;
        p.parent = l;
    }

    public V get(K key) {
        TreeNode<K, V> node = root;
        while (node != null) {
            int cmp = compare(key, node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    public int size() {
        return size;
    }
}