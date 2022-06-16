package minimum_spanning_tree;

// https://www.acmicpc.net/problem/16398

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16398 {

    private static int N;

    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Queue<Link> pq = new PriorityQueue<>();

        for (int start = 0; start < N; start++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int end = 0; end < N; end++) {
                int cost = Integer.parseInt(st.nextToken());
                pq.offer(new Link(start, end, cost));
            }
        }

        parents = new int[N];
        Arrays.fill(parents, -1);

        long minimumCost = minimumSpanningTree(pq);

        System.out.println(minimumCost);
    }

    private static long minimumSpanningTree(Queue<Link> pq) {
        long minimumCost = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Link link = pq.poll();

            if (merge(link.start, link.end)) {
                count += 1;
                minimumCost += link.cost;

                if (count == N - 1) {
                    return minimumCost;
                }
            }
        }

        return minimumCost;
    }

    private static class Link implements Comparable<Link> {
        int start;
        int end;
        int cost;

        Link(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Link other) {
            return Integer.compare(this.cost, other.cost);
        }
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

    private static int find(int x) {
        if (parents[x] < 0) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
}
