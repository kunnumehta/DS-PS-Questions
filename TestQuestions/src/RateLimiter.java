import java.util.*;

public class RateLimiter {

    private int tokens = 3;
    private RefillClass refillClass;
    private Long windowTime = 1000000000L;
    private Map<Integer,Set<Long>> timestamps;
    public static void main(String[] args) throws Exception{
        RateLimiter limiter = new RateLimiter();
        for(int i = 0; i<15;i++) {
            System.out.println(limiter.canMakeRequest(i%3));
        }
        Thread.sleep(1000);
        for(int i = 0; i<5;i++) {
            System.out.println(limiter.canMakeRequest(i%3));
        }
    }

    public RateLimiter() {
//        refillClass = new RefillClass();
//        Thread t1 = new Thread(refillClass);
//        t1.start();
        timestamps = new HashMap<>();
    }

    public boolean canMakeRequest(Integer userId){
        //System.out.println("size before " + timestamps.size());
        long timeNow = System.nanoTime();
        Set<Long>timesToBeRemoved = new TreeSet<>();
        Set<Long> userData = timestamps.getOrDefault(userId, new TreeSet<>());
        for(Long time : userData){
            if(timeNow - time >= windowTime){
                timesToBeRemoved.add(time);
            }
        }
        for(Long time : timesToBeRemoved) {
            timestamps.get(userId).remove(time);
        }
        //System.out.println("size after: " + timestamps.size());
        if(timestamps.getOrDefault(userId, new TreeSet<>()).size() >= tokens) return false;
        else {
            if(timestamps.containsKey(userId)) timestamps.get(userId).add(timeNow);
            else {
                Set<Long> list = new TreeSet<>();
                list.add(timeNow);
                timestamps.put(userId, list);
            }
        }
        return true;
    }

    public class RefillClass implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tokens = 3;
        }

    }
}
