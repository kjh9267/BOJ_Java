package minimum_spanning_tree;

// https://www.acmicpc.net/problem/21924

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21924 {

    private static final int IMPOSSIBLE = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<Link> pq = new PriorityQueue<>();
        int[] parents = new int[N + 1];

        Arrays.fill(parents, -1);

        long total = 0;
        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Link(start, end, cost));
            total += cost;
        }

        long minimumCost = findMinimumSpanningTreeCost(pq, parents, N);

        if (minimumCost == IMPOSSIBLE) {
            System.out.println(IMPOSSIBLE);
        }
        else {
            System.out.println(total - minimumCost);
        }
    }

    private static class Link implements Comparable<Link> {
        int start;
        int end;
        int cost;

        Link (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Link other) {
            return this.cost - other.cost;
        }
    }

    private static int find(int x, int[] parents) {
        if (parents[x] < 0) {
            return x;
        }
        return parents[x] = find(parents[x], parents);
    }

    private static boolean merge(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

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

    private static long findMinimumSpanningTreeCost(Queue<Link> pq, int[] parents, int N) {
        int count = 0;
        long minimumCost = 0;

        while (!pq.isEmpty()) {
            Link link = pq.poll();

            if (merge(link.start, link.end, parents)) {
                count += 1;
                minimumCost += link.cost;

                if (count == N - 1) {
                    return minimumCost;
                }
            }
        }

        return IMPOSSIBLE;
    }
}
