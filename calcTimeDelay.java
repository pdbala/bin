import java.util.Scanner;

public class calcTimeDelay{

    static public int maxTime(int times[][], int N, int K) {
        int dist[] = new int[N + 1];
        for (int i = 1; i <= N; i++){
            dist[i]=999;
        }
        dist[K] = 0;

        for (int i = 1; i <= N; i++) {
            for (int edge[] : times) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] != 999 && dist[v] > dist[u] + w ) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        int maxTime = -1;
        for (int i = 1; i <= N; i++) {
            // System.out.println(dist[i]);
            if (dist[i] == 999) {
                return -1;
            }
            maxTime = Math.max(maxTime, dist[i]);
        }
    
        return maxTime;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of nodes (N): ");
        int N = sc.nextInt();

        System.out.print("Enter the number of edges (E): ");
        int E = sc.nextInt();

        int times[][] = new int[E][3];
        for (int i = 0; i < E; i++) {
            System.out.println("Enter edge " + (i + 1) + ":");
            System.out.print("Source (ui): ");
            times[i][0] = sc.nextInt();
            System.out.print("Target (vi): ");
            times[i][1] = sc.nextInt();
            System.out.print("Time (wi): ");
            times[i][2] = sc.nextInt();
        }

        System.out.print("Enter the source (K): ");
        int K = sc.nextInt();
        sc.close();

        int result = maxTime(times, N, K);        

        if (result == -1) {
            System.out.println("It is impossible for all nodes to receive the signal.");
        }
        else {
            System.out.println("Time for the signal to reach all nodes: " + result);
        }
    }
}
