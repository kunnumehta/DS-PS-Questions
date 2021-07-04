public class GoogleXSquares {

    public static void main(String[] args) {
        char[][] input = {{'x', 'x','x', ' ', ' '},
                {' ', ' ',' ', ' ', ' '},
                {'x', ' ',' ', ' ', 'x'},
                {' ', ' ',' ', ' ', 'x'}};
        int[][] matrix = new int[input.length][input[0].length];
        System.out.println(countNumberOfSquares(matrix, input));
    }

    public static int countNumberOfSquares(int[][] matrix, char[][] input) {
        int count = 0;
        for (int i = 0;i<input.length;i++) {
            if (input[i][0] == 'x') matrix[i][0] = 0;
            else matrix[i][0] = 1;
        }
        for (int i = 0;i<input[0].length;i++) {
            if (input[0][i] == 'x') matrix[0][i] = 0;
            else matrix[0][i] = 1;
        }

        for (int i =1; i<input.length; i++) {
            for (int j = 1; j< input[0].length; j++) {
                if (input[i][j] == 'x') matrix[i][j] = 0;
                else {
                    if (matrix[i][j-1] == 0 || matrix[i-1][j] == 0 || matrix[i-1][j-1] == 0) matrix[i][j] = 1;
                    else matrix[i][j] = matrix[i-1][j-1] + 1;
                }
            }
        }
        for (int i = 0;i<matrix.length;i++) {
            for(int j = 0; j<matrix[0].length;j++) {
                count+=matrix[i][j];
            }
        }
        return count;
    }
}
