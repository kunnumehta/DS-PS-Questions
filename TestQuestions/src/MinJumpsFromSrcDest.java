import java.util.Arrays;

public class MinJumpsFromSrcDest {

    private static int[] deltax = {-1,-1,1,1};
    private static int[] deltay = {-1,1, -1, 1};
    public static void main(String[] args) {
        int n = 4;
        int sx = 1,sy=1,dx=2,dy=4;
        boolean visited[][] = new boolean[n+1][n+1];
        boolean array[] = new boolean[n+1];
        Arrays.fill(array, false);
        Arrays.fill(visited, array);
        int res = findMinCost(sx,sy,dx,dy,n,-1, visited);
        for(int i = 0;i<=n;i++) {
            for(int j = 0;j<=n;j++) {
                System.out.print(visited[i][j]);
            }
            System.out.println();
        }
        if(res == 1000) System.out.println(-1);
        else System.out.println(res);
    }

    public static int findMinCost(int sx, int sy, int dx, int dy, int n, int dir, boolean[][] visited) {

        if(sx > n || sy > n || sx <=0 || sy <=0) return 1000;
        if(sx == dx && sy == dy) return 0;
        if(visited[sx][sy]) return 0;
        visited[sx][sy] = true;
        int minMalue = 1000;
        int res;
        for(int i = 1; i<=4;i++) {
           int sx1 = sx + deltax[i-1];
           int sy1 = sy + deltay[i-1];
            res = findMinCost(sx1, sy1, dx, dy, n, i, visited);
            if(dir != i) res = res+1;
            minMalue = Math.min(minMalue, res);
            if(res == 0) break;
        }
        return minMalue;
    }
}
