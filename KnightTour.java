import java.util.Scanner;

public class KnightTour {
    static int N;

    static boolean isSafe(int x, int y, int sol[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
    }

    static void printSolution(int sol[][]) {
        for (int i = 0; i < N; i++) {
             for (int j = 0; j < N; j++)System.out.print("----");
              System.out.println();
            for (int j = 0; j < N; j++){
                System.out.printf("%3d",sol[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    static boolean solveKT() {
        int sol[][] = new int[N][N];

        for (int x = 0; x < N; x++)
            for (int y = 0; y < N; y++)
                sol[x][y] = -1;

        int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        // Start from 0 index
        sol[0][0] = 0;
        // Explore all
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
            return false;
        } 
        else
            printSolution(sol);

        return true;
    }

    static boolean solveKTUtil(int x, int y, int movei,int sol[][], int xMove[],int yMove[]) {
        int k, next_x, next_y;
        if (movei == N * N)
            return true;

        for (k = 0; k < 8; k++) {
            next_x = x + xMove[k];
            next_y = y + yMove[k];
            if (isSafe(next_x, next_y, sol)) {
                sol[next_x][next_y] = movei;
                if (solveKTUtil(next_x, next_y, movei + 1,sol, xMove, yMove))
                    {
                    return true;
                    }
                else
                    sol[next_x][next_y] = -1; // Backtracking
            }
        }

        return false;
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the chessboard: ");
        N = scanner.nextInt();
        scanner.close();

        if (N <= 0) {
            System.out.println("Invalid chessboard size.");
            return;
        }

        solveKT();
    }
}
