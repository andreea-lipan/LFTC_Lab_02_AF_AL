package lftc.AL.TAD;

import java.util.Objects;

public class AtomHashMap {
    private final int capacity = 15;
    public final Node<String, Integer>[] table = new Node[capacity];

    private int hash(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); ++i) {
            sum += key.charAt(i);
        }
        return sum % capacity;
    }

    private int findNextEmptyPoz() {
        for (int i = 0; i < capacity; ++i) {
            if (table[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public Integer put(String key) {
        int index = hash(key);
        Node<String, Integer> node = table[index];
        if (node == null) {
            table[index] = new Node<>(key, index);
            return index;
        } else {
            // does it already exist?
            Node<String, Integer> copynode = node;
            while (copynode != null) {
                if (Objects.equals(copynode.key, key)) {
                    return copynode.value;
                }
                copynode = copynode.next;
            }

            // if not add it
            int indexEmpty = findNextEmptyPoz();
            if (indexEmpty == -1) {
                throw new RuntimeException("No more space in the table");
            }
            Node<String, Integer> newNode = new Node<>(key, indexEmpty);
            table[indexEmpty] = newNode;
            node.next = newNode;
            return indexEmpty;
        }
    }

}

