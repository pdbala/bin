import java.util.*;
import java.io.*;

class floyd {

    public static void floydWarshall(int graph[][]) {
        int[][] matrix = new int[4][4];
        int i, j, k;
        int n = times[][];
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                matrix[i][j] = graph[i][j];
            }
        }

        for (k = 0; k < 4; k++) {
            for (i = 0; i < 4; i++) {
                for (j = 0; j < 4; j++) {
                    if (matrix[i][k] + matrix[k][j] < matrix[i][j])
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                }
            }
            printMatrix(matrix);
            System.out.println("");
        }
    }

    public static void printMatrix(int matrix[][]) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (matrix[i][j] == 999)
                    System.out.print("999");
                else
                    System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int A[][] = {
                { 0, 3, 999, 7 },
                { 8, 0, 2, 999 },
                { 5, 999, 0, 1 },
                { 2, 999, 999, 0 } };
        floydWarshall(A);
    }

}

// import java.util.*;
// import java.io.*;

// class floyd {

//     public static void main(String[] args) {
//         int[][][] mat = new int[4][4][4];

//         Scanner sc = new Scanner(System.in);

//         for (int k = 0; k < 1; k++) {
//             for (int j = 0; j < 4; j++) {
//                 for (int i = 0; i < 4; i++) {
//                     int it = sc.nextInt();
//                     mat[k][j][i] = it;
//                 }
//             }
//         }

//         for (int k = 1; k < 4; k++) {
//             for (int j = 0; j < 4; j++) {
//                 for (int i = 0; i < 4; i++) {

//                     mat[k][j][i] = Math.min(mat[k - 1][j][i], mat[k - 1][j][k] + mat[k - 1][k][i]);
//                 }

//             }
//         }

//         for (int k = 0; k < 4; k++) {
//             for (int j = 0; j < 4; j++) {
//                 for (int i = 0; i < 4; i++) {
//                     System.out.print(mat[k][j][i] + " ");
//                 }
//                 System.out.println("");
//             }
//             System.out.println("");
//             System.out.println("");
//         }

//         sc.close();
//     }

// }
// Floyd Warshall Algorithm in Java
