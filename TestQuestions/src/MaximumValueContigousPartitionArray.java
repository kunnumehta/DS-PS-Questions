import java.util.Arrays;

public class MaximumValueContigousPartitionArray {

    public static void main(String[] args) {
        int array[] = {1,15,7,9,2,5,10};
        //int res[] = new int[array.length];
        int dp[] = new int[array.length];
        Arrays.fill(dp, -1);
        int k  = 3;
        int result = maxSum(array, 0, k, dp);
        System.out.println(result);
    }

    public static int maxSum(int[] array, int index, int k, int[] dp) {
        if (index >= array.length) return 0;
        if (dp[index] != -1) return dp[index];
        int sum = -1;
        int maxValue = -1;
        for (int i = 0; i < k && index + i < array.length; i++) {

            maxValue = Math.max(array[index + i], maxValue);
           // res[index] = maxValue;
            int temp = maxValue*(i+1) + maxSum(array, index + i +1, k, dp);
            sum = Math.max(sum,  temp);
        }
        dp[index] = sum;
        return sum;
    }
}
