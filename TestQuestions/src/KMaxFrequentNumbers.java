import java.util.*;

public class KMaxFrequentNumbers {

    public static void main(String[] args) {
        int[] array = {3, 1, 2, 4, 4, 1, 1};
        int k = 2;
        Map<Integer, Integer> valueFrequencyMap = new HashMap<>();
        for (int j : array) {
            if (valueFrequencyMap.containsKey(j)) {
                valueFrequencyMap.put(j, valueFrequencyMap.get(j) + 1);
            } else {
                valueFrequencyMap.put(j, 1);
            }
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) ->
                a.getValue().equals(b.getValue()) ? Integer.compare(b.getKey(), a.getKey())
                        : Integer.compare(b.getValue(), a.getValue()));
        for (Map.Entry<Integer, Integer> entry : valueFrequencyMap.entrySet())
            queue.offer(entry);
        int i =0;
        while ( i < k) {
            System.out.println((Objects.requireNonNull(queue.poll())).getKey());
           i++;
        }
    }
}
