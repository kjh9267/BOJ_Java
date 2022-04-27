package minimum_spanning_tree;

// https://www.acmicpc.net/problem/6497

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6497 {

    private static final String NEW_LINE = "\n";

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            if (isEnd(M, N)) {
                break;
            }

            int total = 0;

            Queue<Node> pq = new PriorityQueue<>();
            parents = new int[M];

            Arrays.fill(parents, -1);

            for (int path = 0; path < N; path++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                total += z;

                pq.offer(new Node(x, y, z));
            }

            int minimumCost = mst(pq, M);
            sb.append(total - minimumCost)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static class Node implements Comparable<Node> {
        int current;
        int next;
        int cost;

        Node(int current, int next, int cost) {
            this.current = current;
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private static boolean isEnd(int M, int N) {
        return M == 0 && N == 0;
    }

    private static int mst(Queue<Node> pq, int M) {
        int minimumCost = 0;
        int linkCount = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (merge(node.current, node.next)) {
                minimumCost += node.cost;
                linkCount += 1;
                if (linkCount == M - 1) {
                    break;
                }
            }
        }

        return minimumCost;
    }

    private static int find(int x) {
        if (parents[x] < 0) {
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    private static boolean merge(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return false;
        }
        if (parents[x] < parents[y]) {
            parents[x] += parents[y];
            parents[y] = x;
        }
        else {
            parents[y] += parents[x];
            parents[x] = y;
        }

        return true;
    }
}
