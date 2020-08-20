package p465_LRU;

public class Test {
    public static void main(String[] args) {
        MyCache<String, Integer> myCache = new MyCache<>(3);
        myCache.set("A", 1);
        myCache.set("B", 2);
        myCache.set("C", 3);
        myCache.get("A");
        myCache.set("D", 4);

    }
}
