package part1;

public class SimpleLinkedList<V> {
    private Node<V> head;
    private Node<V> tail;
    private int size;

    private static class Node<V> {
        Node<V> next;
        Node<V> prev;
        V value;

        Node(V value, Node<V> prev, Node<V> next) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

        public String showItems() {
            return "value : " + value;
        }
    }

    public boolean add(V value) {
        if (value == null) {
            return false;
        }
        final Node<V> last = tail;
        Node<V> item = new Node<V>(value, last, null);
        if (head == null) {
            tail = head = item;
        } else {
            last.next = item;
            tail = item;
        }
        ++size;
        return true;
    }

    public boolean setLast(V value) {
        return add(value);
    }

    public boolean setFirst(V value) {
        if (value == null) {
            return false;
        }
        Node<V> first = head;
        Node<V> item = new Node<V>(value, null, null);
        head = item;
        if (first != null) {
            head.next = first;
        }
        ++size;
        return true;
    }

    private Node<V> getFirstNode() {
        return head;
    }

    public V getFirst() {
        Node<V> node;
        return (node = getFirstNode()) != null ? node.value : null;
    }

    private Node<V> getLastNode() {
        return tail;
    }

    public V getLast() {
        Node<V> node;
        return (node = getLastNode()) != null ? node.value : null;
    }

    public int size() {
        return size;
    }

    public V get(int index) {
        if (index >= 0 && index <= size && size > 0) {
            Node<V> current;
            int sizeDirection = size == 0 ? 0 : (size % 2 == 0 ? size / 2 : (size + 1) / 2);
            current = index == 0 ? getFirstNode() : (index == size ? getLastNode() : null);
            if (current != null) {
                return current.value;
            } else {
                int count;
                if (index < sizeDirection) {
                    current = head;
                    count = 1;
                    while (count <= index) {
                        if (count == index) {
                            return current.value;
                        }
                        count++;
                        current = current.next;
                    }
                } else {
                    count = size - 1;
                    current = tail;
                    while (count >= index) {
                        if (count == index) {
                            return current.value;
                        }
                        count--;
                        current = current.prev;
                    }
                }
            }
        }
        return null;
    }

    public boolean remove(V value) {
        if (size > 0) {
            Node<V> current = head;
            Node<V> prev;
            while (current != null) {
                if (current.value == value || value != null && value.equals(current.value)) {
                    if (current == tail && tail.prev != null) {
                        tail = tail.prev;
                    }
                    if (current.prev != null) {
                        prev = current.prev;
                        prev.next = current.next;
                        current = null;
                    } else {
                        head = head.next;
                    }
                    size--;
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    public boolean addAll(V[] items) {
        boolean added = false;
        if (items == null) {
            return added;
        }
        for (V value : items) {
           added = add(value);
        }
        return added;
    }

    public void showLinkedlistValues() {
        Node<V> item = head;
        while (item != null) {
            System.out.println(item.showItems());
            item = item.next;
        }

    }
}
