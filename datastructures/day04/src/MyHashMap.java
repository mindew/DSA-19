import java.util.*;


public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per bucket before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per bucket before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {
        // TODO
        // hint: use key.hashCode() to calculate the key's hashCode using its built in hash function
        // then use % to choose which bucket to return.
        // hash function: object --> integer
        // hash table: hashcodes % table size

        int index = key == null ? 0 : Math.abs(key.hashCode()) % buckets.length;

        return buckets[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        // TODO
        // get the bucket of the corresponding key, see if key is already in
        // the bucket

        LinkedList<Entry> currentb = chooseBucket(key);

        for (int x = 0; x < currentb.size(); x++) {
            if (currentb.get(x).key.equals(key)) {
                // k==null : key.equals(k)
                return true;
            }
        }
        return false;
    }

    /**
     * return true if value is in map
     */
    @Override
    public boolean containsValue(Object value) {
        // iterate through every bucket and every element each bucket
        // use double nested loop
        // O(n^2)
        int bucketnum = buckets.length;

        for (int x = 0; x < bucketnum; x++) {
            for (int y = 0; y < buckets[x].size(); y++)
                if (buckets[x].get(y).value == value) {
                    // v==null : value.equals(k)
                    return true;
                }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        // returns the value of the input key or null if there's no mapping for the key
        // check if each element is equivalent to key
        LinkedList<Entry> currentb = chooseBucket(key);
        if (containsKey(key) != true)
            return null;

        for (int x = 0; x < currentb.size(); x++) {
            boolean iskey = currentb.get(x).key.equals(key);
            if (iskey != true)
                continue;
            else {
                return currentb.get(x).value;
            }
        }
        return null;
    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     *
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {
        // TODO: Complete this method
        // hint: use chooseBucket() to determine which bucket to place the pair in
        // hint: use rehash() to appropriately grow the hashmap if needed

        V resultval = null;
        if(containsKey(key)){
            resultval = remove(key);
        }
        LinkedList<Entry> currentb = chooseBucket(key);

        Entry newpair = new Entry(key, value);
        currentb.add(newpair);
        size = size + 1;
        // resizing factor: (new size) = (old size) * (resize factor)
        int bucketnum = buckets.length;

        if (((float)size) > (ALPHA * bucketnum)) {
            rehash(GROWTH_FACTOR);
        }

        return resultval;
    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     *
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {
        // TODO
        // hint: use chooseBucket() to determine which bucket the key would be
        // hint: use rehash() to appropriately grow the hashmap if needed
        V resultval = null;
        if(containsKey(key)){
            resultval = get(key);
        }
        else {
            return resultval;
        }
        LinkedList<Entry> currentb = chooseBucket(key);
        for (int x = 0; x < currentb.size(); x++) {
            boolean iskey = currentb.get(x).key.equals(key);
            if (iskey == true) {
                currentb.remove(x);
                size = size - 1;
            }
        }

        int bucketnum = buckets.length;
        if (size >= 16 && (((float)size) <= (BETA*bucketnum))) {
            rehash(SHRINK_FACTOR);
        }

        return resultval;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */
    private void rehash(double growthFactor) {
        LinkedList<Entry>[] oldb = buckets;
        double newbucketnum = buckets.length * growthFactor;
        size = 0;
        initBuckets((int)newbucketnum);

        for (int x = 0; x < oldb.length; x++) {
            for (int y = 0; y < oldb[x].size(); y++){
                K currentk = oldb[x].get(y).key;
                V currentv = oldb[x].get(y).value;
                put(currentk, currentv);
            }

        }


    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}
