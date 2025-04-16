package part1;
import java.util.Arrays;

public class SimpleHashSet<V> {
    private final static int DEFAULT_STORE_SIZE = 16;
    private final static int INCREASE_STORE_SIZE = 8;
    private final static float DEFAULT_LOAD_FACTORY = 0.75f;
    private int pointer;
    private Bucket<V>[] buckets;

    static class Bucket<V> {
        private V value;

        Bucket(V value) {
            this.value = value;
        }

        public V setValue(V newValue) {
            V old = value;
            value = newValue;
            return old;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "value : "+value;
        }
    }

    private Bucket[] resize() {
        Bucket[] tempBuckets;
        int size = (buckets == null) ? 0 : buckets.length;
        if (size > 0 && pointer > (size * DEFAULT_LOAD_FACTORY)) {
            size = size + INCREASE_STORE_SIZE;
            tempBuckets = new Bucket[size];
            tempBuckets = Arrays.copyOf(buckets, size, null);
            buckets = tempBuckets;
            return buckets;
        } else {
            size = DEFAULT_STORE_SIZE;
        }
        tempBuckets = new Bucket[size];
        buckets = tempBuckets;
        return buckets;
    }

    private int getHash(V value) {
        int hash;
        return (value == null) ? 0 : (hash = value.hashCode()) ^ (hash >>> DEFAULT_STORE_SIZE);
    }

    public void add(V value) {
        Bucket<V>[] temp;
        int len, index;
        if ((temp = buckets) == null || (len = buckets.length) == 0) {
            len = (temp = resize()).length;
        }
        if (temp[index = (len - 1 & getHash(value))] == null) {
            ++pointer;
            temp[index] = new Bucket<>(value);
        } else {
            temp[index].setValue(value);
        }

        if (buckets != null && pointer > (DEFAULT_STORE_SIZE * DEFAULT_LOAD_FACTORY)) {
            resize();
        }
    }

    private int findIndexByValue(V value) {
        int len, index;
        if (buckets != null && (len = buckets.length) >= 0) {
            if (buckets[index = (len - 1 & getHash(value))] != null) {
                return index;
            }
        }
        return -1;
    }

    public boolean contains(V value) {
        return findIndexByValue(value) != -1;
    }

    public int size() {
        return pointer;
    }

    public void remove(V value) {
        if (buckets != null && pointer > 0) {
            int index = findIndexByValue(value);
            if (index != -1) {
                --pointer;
                buckets[index] = null;
            }
        }
    }

    public void showItems() {
        if (buckets != null && pointer > 0) {
            for (Bucket<V> bucket : buckets) {
                if (bucket != null) {
                    System.out.println(bucket.toString());
                }
            }
        }
    }
}
