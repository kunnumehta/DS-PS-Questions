import java.util.ArrayList;
import java.util.List;

//Give an array , split the array into 2 subsets,  sum(subset1) == sum(subset2)
//
//        Example 1
//        {3,5,4,2}
//        Answer {5,2} {3,4}
//
//        Example 2
//        {5,2,7}
//        Ans {7} {5,2}
public class TestQuestion {

    public List<Integer> subset1 = new ArrayList<>();
    public List<Integer> subset2 = new ArrayList<>();
    public TestQuestion() {}
    public static void main(String[] args) {
        TestQuestion testQuestion = new TestQuestion();
        int[] array = {3,3,2,2,4,4};
        int n = array.length;
        int sumTotal = 0;
        for (int i = 0; i<n;i++) {
            sumTotal+=array[i];
        }
        boolean[][] memo = new boolean[n][sumTotal/2 + 1];
        for(int i = 0; i<n;i++) {
            for(int j = 0 ; j<=sumTotal/2;j++) {
                memo[i][j] = false;
            }
        }
        if(sumTotal % 2 !=0) System.out.println("no solution present");
        else {
            boolean res = testQuestion.checkIfSumEqual(array,0,n,sumTotal/2, memo);
            if(!res) System.out.println("no solution present");
            else {
                testQuestion.printSubset(array);
                System.out.println("solution present");
            }
        }
    }

    public boolean checkIfSumEqual(int[] arr, int pos, int n, int sumOfOtherSubset,
                                   boolean[][] memo) {
        if(sumOfOtherSubset == 0) {
            return true;
        }
        if(pos == n) return false;
        if(memo[pos][sumOfOtherSubset]) return memo[pos][sumOfOtherSubset];
        boolean result = false;
        if(arr[pos] > sumOfOtherSubset) {
            result = checkIfSumEqual(arr, pos+1,n, sumOfOtherSubset, memo);
        } else {
            result = checkIfSumEqual(arr, pos+1,n, sumOfOtherSubset - arr[pos], memo);
            if(result) subset1.add(arr[pos]);
            else {
                result = result | checkIfSumEqual(arr, pos+1,n, sumOfOtherSubset, memo);
            }
        }
        memo[pos][sumOfOtherSubset] = result;
        return memo[pos][sumOfOtherSubset];
    }

    public void printSubset(int[] array) {
        for(Integer i : subset1){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 0; i < array.length;i++) {
            if(!subset1.contains(array[i])) subset2.add(array[i]);
        }
        for(Integer i : subset2){
            System.out.print(i + " ");
        }
    }
}
