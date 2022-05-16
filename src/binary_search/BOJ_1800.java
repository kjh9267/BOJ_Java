package binary_search;

// https://www.acmicpc.net/problem/1800

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1800 {

    private static final int IMPOSSIBLE = -1;

    private static final int START = 1;

    private static final int INF = 100_000_000;

    private static final int MAX = 1_000_001;

    private static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < P; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        int result = binarySearch(N, K);

        System.out.println(result);
    }

    private static class Node implements Comparable<Node> {
        int id;
        int cost;

        Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    private static int binarySearch(int N, int K) {
        int lo = -1;
        int hi = MAX;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;

            if (isPossible(mid, N, K)) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return hi == MAX ? IMPOSSIBLE: hi;
    }

    private static boolean isPossible(int targetCost, int N, int K) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(START, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[START] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.id]) {
                continue;
            }
            for (Node next: graph[cur.id]) {
                int cost = 0;

                if (next.cost > targetCost) {
                    cost = 1;
                }
                if (dist[next.id] <= dist[cur.id] + cost) {
                    continue;
                }
                dist[next.id] = dist[cur.id] + cost;
                pq.offer(new Node(next.id, dist[next.id]));
            }
        }

        return dist[N] <= K;
    }
}
