package binary_search;

// https://www.acmicpc.net/problem/9893

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_9893 {

    private static final int LIMIT = 100;

    private static final int INF = 3_500_000;

    private static final int START = 0;

    private static final int END = 1;

    private static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new List[N];

        for (int node = 0; node < N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost, 0, 0));
        }

        int result = binarySearch(N);
        System.out.println(result);
    }

    private static class Node implements Comparable<Node> {
        int id;
        int cost;
        int count;
        int prevId;

        Node(int id, int cost, int count, int prevId) {
            this.id = id;
            this.cost = cost;
            this.count = count;
            this.prevId = prevId;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    private static int binarySearch(int N) {
        int lo = 0;
        int hi = LIMIT;
        int result = 0;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            int dist = dijkstra(mid, N);

            if (dist != INF) {
                result = dist;
            }

            if (dist == INF) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }

        return result;
    }

    private static int dijkstra(int limit, int N) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(START, 0, 0, 0));

        int[][] dist = new int[N][N];
        for (int node = 0; node < N; node++) {
            Arrays.fill(dist[node], INF);
        }
        Arrays.fill(dist[START], 0);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next: graph[cur.id]) {
                if (dist[next.id][cur.id] <= dist[cur.id][cur.prevId] + next.cost) {
                    continue;
                }
                if (cur.count + 1 > limit) {
                    continue;
                }
                dist[next.id][cur.id] = dist[cur.id][cur.prevId] + next.cost;
                pq.offer(new Node(next.id, dist[next.id][cur.id], cur.count + 1, cur.id));
            }
        }

        return findMinimumValue(dist[END], N);
    }

    private static int findMinimumValue(int[] dist, int N) {
        int minimumValue = INF;

        for (int node = 0; node < N; node++) {
            minimumValue = min(minimumValue, dist[node]);
        }

        return minimumValue;
    }
}
