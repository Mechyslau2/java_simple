package part1;

import java.util.Arrays;

public class SimpleHashSet<V> {
    private final static int DEFAULT_STORE_SIZE = 16;
    private final static int INCREASE_STORE_SIZE = 8;
    private final static float DEFAULT_LOAD_FACTORY = 0.75f;

    private int elementsCounter;
    private int filledBucketsCounter;
    private Bucket<V>[] buckets;

    private static class Bucket<V> {
        V value;
        Bucket<V> next;
        Bucket<V> prev;

        Bucket(V value, Bucket<V> next, Bucket<V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "" + value + "";
        }
    }

    private Bucket<V>[] resize() {
        Bucket<V>[] tempBuckets;
        int size = (buckets == null) ? 0 : buckets.length;
        if (size > 0 && filledBucketsCounter > (size * DEFAULT_LOAD_FACTORY)) {
            size = size + INCREASE_STORE_SIZE;
            tempBuckets = new Bucket[size];
            tempBuckets = Arrays.copyOf(buckets, size);

        } else {
            tempBuckets = new Bucket[DEFAULT_STORE_SIZE];
        }
        buckets = tempBuckets;
        return buckets;
    }

    private int getHash(V value) {
        int hash;
        return (value == null) ? 0 : (hash = value.hashCode()) ^ (hash >>> DEFAULT_STORE_SIZE);
    }

    public boolean add(V value) {
        boolean isAdded = false;
        if (value == null) {
            return isAdded;
        }
        Bucket<V>[] temp;
        Bucket<V> current;
        int len, index;
        if ((temp = buckets) == null || (len = buckets.length) == 0) {
            len = (temp = resize()).length;
        }
        if (temp[index = (len - 1 & getHash(value))] == null) {
            ++filledBucketsCounter;
            ++elementsCounter;
            current = temp[index] = new Bucket<V>(value, null, null);
            isAdded = true;
        } else {
            current = temp[index];
            V val;
            if (temp[index] != null) {
                for (int i = 0;; i++) {
                    if ((val = current.value) == value || val.equals(value)) {
                        break;
                    }
                    if (current.next == null) {
                        current.next = new Bucket<V>(value, null, current);
                        ++elementsCounter;
                        isAdded = true;
                        break;
                    }
                    current = current.next;
                }
            }
        }


        if (buckets != null && filledBucketsCounter > (buckets.length * DEFAULT_LOAD_FACTORY)) {
            resize();
        }
        return isAdded;
    }

    private int findIndexByValue(V value) {
        if (value == null) {
            return -1;
        } 
        int len, index;
        if (buckets != null && (len = buckets.length) >= 0) {
            if (buckets[index = (len - 1 & getHash(value))] != null) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(V value) {
        if (buckets != null && elementsCounter > 0) {
            Bucket<V> current;
            int index = findIndexByValue(value);
            if (index != -1 && (current = buckets[index]) != null) {
                while (current != null) {
                    V val;
                    if ((val = current.value) == value || value != null && val.equals(value)) {
                        return true;
                    }
                    current = current.next;
                }
            }
        }
        return false;
    }

    public int size() {
        return elementsCounter;
    }

    public boolean remove(V value) {
        if (buckets != null && elementsCounter > 0) {
            int index = findIndexByValue(value);
            Bucket<V> current;
            if (index != -1 && (current = buckets[index]) != null) {
                Bucket<V> prev;
                V val;
                if (current.next == null
                        && ((val = current.value) == value || value != null && val.equals(value))) {
                    buckets[index] = null;
                    --filledBucketsCounter;
                    --elementsCounter;
                    return true;
                } else {
                    while (current != null) {
                        if ((val = current.value) == value || value != null && val.equals(value)) {
                            if (current.prev != null) {
                                prev = current.prev;
                                prev.next = current.next;
                                current = null;
                                --elementsCounter;
                                return true;
                            }
                        }
                        current = current.next;
                    }
                }
            }
        }
        return false;
    }

    public void showItems() {
        Bucket<V> ref;
        if (buckets != null && elementsCounter > 0) {
            for (int i = 0; i < buckets.length; i++) {
                ref = buckets[i];
                if (ref != null) {
                    System.out.println("bucket " + (i + 1) + " values : ");
                }
                while (ref != null) {
                    System.out.print(ref.toString() + (ref.next == null ? ";\n" : ", "));
                    ref = ref.next;
                }

            }
        }
    }
}

