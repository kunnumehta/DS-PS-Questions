import java.util.*;
public class ValidateBinaryTree {

    public static void main(String[] args) {
       List<List<Integer>> nodes = new ArrayList<>();
       Integer[][] array = {{17,-1,-1},{13,-1,-1},{15,13,17},
                        {3,-1,-1},{7,-1,-1},{5,3,7},{10,5,15}};
        for(int i = 0; i<array.length; i++) {
            nodes.add(Arrays.asList(array[i]));
        }
       int[][] map = new int[18][3];
        for(int i=0; i<18;i++) {
            for(int j = 0 ; j<3; j++) {
               map[i][j] = -1;
            }
        }
        boolean flag = false;
       for(int i = 0; i<nodes.size();i++) {
           List<Integer> row = nodes.get(i);
           //System.out.println(row);
           int root = row.get(0);
           int lc = row.get(1);
           int rc = row.get(2);
           if (map[root][0] !=-1) {
               flag = true;
               break;
           } else {
               map[root][0] = 1;
               if (lc != -1 ) {
                   if (map[lc][1] == -1) map[lc][1] = 1;
                   else {flag = true;
                   break;}
               }
               if (rc != -1) {
                   if (map[rc][2] == -1) map[rc][2] = 1;
                   else {flag = true;
                   break;}
               }
           }
       }
       if (flag) System.out.println(-1);
       else {
           int res = -1;
           boolean flag2 = false;
           for (int i = 0; i < 18; i++) {
               if (map[i][0] == -1 && (map[i][1] != -1 || map[i][2] != -1)) {
                   res = -1;
                   flag2 = true;
               }
               if (map[i][1] != -1 && map[i][2] != -1) {
                   res = -1;
                   flag2 = true;
               }
               if (map[i][0] == 1 && map[i][1] == -1 && map[i][2] == -1) {
                   if (!flag2) {
                       flag2 = true;
                       res = i;
                   } else {
                       res = -1;
                   }
               }
           }
           System.out.println(res);
       }
    }
}
