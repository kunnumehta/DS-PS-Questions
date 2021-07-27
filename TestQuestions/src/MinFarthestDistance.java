import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinFarthestDistance {

    public static void main(String[] args) {
        List<List<Boolean>> input = new ArrayList<>();
        input.add(Arrays.asList(false, true, false));
        input.add(Arrays.asList(false, false, true));
        input.add(Arrays.asList(false, true, false));
        input.add(Arrays.asList(false, true, false));
        input.add(Arrays.asList(true, true, false));
        int result = getMinFarthestDistance(input);
        System.out.println(result);
    }

    public static int getMinFarthestDistance(List<List<Boolean>> isPresent) {
        int result = Integer.MAX_VALUE;
        int n = isPresent.size();
        List<List<Integer>> maxLeft = new ArrayList<>(n);
        List<List<Integer>> maxRight = new ArrayList<>(n);
        for(int i = 0; i<n; i++) {
            maxLeft.add(Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
            maxRight.add(Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        }
        for(int j = 0; j<isPresent.get(0).size();j++) {
            if (isPresent.get(0).get(j)) maxLeft.get(0).set(j, 0);
            for (int i = 1; i < n; i++) {
                if (isPresent.get(i).get(j)) {
                    maxLeft.get(i).set(j, 0);
                } else {
                    if(maxLeft.get(i-1).get(j) != Integer.MAX_VALUE)
                    maxLeft.get(i).set(j, maxLeft.get(i-1).get(j)+1);
                }
            }
            if (isPresent.get(n-1).get(j)) maxRight.get(n-1).set(j, 0);
            maxRight.get(n-1).set(j, Math.min(maxRight.get(n-1).get(j), maxLeft.get(n-1).get(j)));
            for (int i = n-2; i >=0; i--) {
                if (isPresent.get(i).get(j)) {
                    maxRight.get(i).set(j, Math.min(0, maxLeft.get(i).get(j)));
                } else {
                    if(maxRight.get(i+1).get(j) != Integer.MAX_VALUE)
                    maxRight.get(i).set(j, Math.min(maxRight.get(i + 1).get(j) + 1, maxLeft.get(i).get(j)));
                }
            }
        }
        for(int i =0; i<n;i++) {
            int currMax = Integer.MIN_VALUE;
            for(int j =0;j<isPresent.get(0).size();j++) {
                currMax = Math.max(currMax, maxRight.get(i).get(j));
            }
            result = Math.min(result, currMax);
        }
        System.out.println(result);
        return result;
    }
}
