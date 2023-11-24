import java.util.*;

public class AsmtProb_BnB {

    static int[][] costMatrix;
    static boolean available[];
    static Node leaf;
    static boolean assigned[];

    static class Node {
        int worker;
        int job;
        int cost;
        int sumtillnow;
        Node parent;
        Node(int a, int b, int c, int e, Node d) {
            worker = a;
            job = b;
            cost = c;
            sumtillnow = e;
            parent = d;
        }
    }

    static class sortPQ implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return a.sumtillnow - b.sumtillnow;
        }
    }

    static int calcLB(int i, int n, int worker) {
        if (worker == n)
            return 0;
        int LB = 0;
        available[i] = false;
        for (int j = worker; j < n; j++) {
            int min = Integer.MAX_VALUE;
            for (int k = 0; k < n; k++) {
                if (available[k]) {
                    if (costMatrix[j][k] < min) {
                        min = costMatrix[j][k];
                    }
                }
            }
            LB += min;
        }
        available[i] = true;
        return LB;
    }

    static void assign() {
        int n = available.length;
        PriorityQueue<Node> pq = new PriorityQueue<>(new sortPQ());
        pq.add(new Node(-1, -1, 0, 0, null));
        while (pq.size() > 0) {
            Node curr = pq.poll();
            if (curr.worker != -1 && assigned[curr.worker]) {
                continue;
            }
            if (curr.job != -1) {
                available[curr.job] = false;
                assigned[curr.worker] = true;
            }
            int worker = curr.worker + 1;
            if (worker == n) {
                leaf = curr;
                return;
            }
            for (int i = 0; i < n; i++) {
                if (available[i]) {
                    pq.add(new Node(worker, i, costMatrix[worker][i], curr.sumtillnow +
                            costMatrix[worker][i] + calcLB(i, n, worker + 1), curr));
                }
            }
        }
    }

    static void display() {
        ArrayList<Node> list = new ArrayList<Node>();
        int cost = 0;
        while (leaf.parent != null) {
            cost += leaf.cost;
            list.add(leaf);
            leaf = leaf.parent;
        }
        Collections.reverse(list);
        System.out.println("\nMinimum cost is: " + cost);
        System.out.println("\nAssignmnets are as follows : ");
        for (Node i : list) {
            System.out.println("Worker " + i.worker + " is assigned the job " + i.job);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of jobs: ");
        int n = sc.nextInt();
        
        costMatrix = new int[n][n];
        available = new boolean[n];
        assigned = new boolean[n];

        System.out.println("Enter the cost matrix: ");
        for (int i = 0; i < n; i++) {
            available[i] = true;
            assigned[i] = false;
            for (int j = 0; j < n; j++) {
                costMatrix[i][j] = sc.nextInt();
            }
        }
        assign();
        
        display();

    }
}