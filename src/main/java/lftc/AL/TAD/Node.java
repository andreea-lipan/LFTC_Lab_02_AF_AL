package lftc.AL.TAD;

public class Node<K, V> {
    public K key;
    public V value;
    Node<K, V> next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}