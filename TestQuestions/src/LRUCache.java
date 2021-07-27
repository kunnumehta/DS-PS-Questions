import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// key, value
// get(key)
// put(key, value)

//HashMap<String, Integer> cacheMap;


public class LRUCache {
    private final int MAX_QUEUE_SIZE = 10;

    private Deque<Integer> cacheQueue;
    private HashMap<String, Integer> cacheMap;
    private int capacity;

    public LRUCache() {
       cacheMap = new HashMap<>();
       cacheQueue = new LinkedList<>();
       capacity = MAX_QUEUE_SIZE;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        cacheQueue = new LinkedList<>();
    }

    public void addToCache(String key, Integer value) {
        if(cacheMap.containsKey(key)) {
            int entry = cacheMap.get(key);
            cacheQueue.remove(entry);
            cacheQueue.addFirst(entry);
        } else {
            cacheMap.put(key, value);
            if(cacheQueue.size() != capacity) {
                cacheQueue.addFirst(value);
            }
            else {
                int last = cacheQueue.getLast();
                for(Map.Entry<String, Integer> mapEntry : cacheMap.entrySet()) {
                    if(mapEntry.getValue() == last) {
                        cacheMap.remove(mapEntry.getKey());
                    }
                }
                cacheQueue.removeLast();
                cacheQueue.addFirst(value);
            }
        }
    }

    public int getFromCache(String key) {
        if(cacheMap.containsKey(key)) {
            int value = cacheMap.get(key);
            cacheQueue.remove(value);
            cacheQueue.addFirst(value);
            return value;
        }
        return -1;
    }

    public void display() {
        for(Map.Entry<String, Integer> mapEntry : cacheMap.entrySet()) {
            System.out.println("key: " + mapEntry.getKey() + " value: " + mapEntry.getValue());
        }
        System.out.println();
        for(Integer entry : cacheQueue) {
            System.out.println(entry);
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.addToCache("first", 1);
        lruCache.addToCache("second", 2);
        lruCache.display();
        System.out.println(lruCache.getFromCache("first"));
        lruCache.display();
        lruCache.addToCache("third", 3);
        lruCache.display();
        lruCache.addToCache("fourth", 4);
        lruCache.display();
    }

}
