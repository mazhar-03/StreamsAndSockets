package task22;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LinkedList<T> implements Serializable {
    private Node head;

    public void add(T newElement) {
        if (head == null) {
            head = new Node(newElement);
            return;
        }

        Node cursor = head;
        while (cursor.next != null) {
            cursor = cursor.next;
        }

        cursor.next = new Node(newElement);
    }

    public void forEach(Consumer<T> consumer) {
        getNodesAsStream()
                .map(node -> node.data)
                .forEach(consumer);
    }

    private Stream<Node> getNodesAsStream() {
        return Stream.iterate(head, Objects::nonNull, node -> node.next);
    }

    public java.time.Duration getLifespan() {
        var youngest = getNodesAsStream()
                .map(node -> node.creationTime)
                .min(Comparator.naturalOrder())
                .orElse(LocalDateTime.MIN);

        var oldest = getNodesAsStream()
                .map(node -> node.creationTime)
                .max(Comparator.naturalOrder())
                .orElse(LocalDateTime.MIN);

        return java.time.Duration.between(youngest, oldest);
    }

    private class Node implements Serializable {
        T data;
        Node next = null;

        // Transient field for creationTime
        private transient LocalDateTime creationTime = LocalDateTime.now();

        public Node(T data) {
            this.data = data;
        }

        // Custom deserialization to reset creationTime
        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            this.creationTime = LocalDateTime.now(); // Reset the creation time
        }
    }
}
