package dijkstra;

// https://www.acmicpc.net/problem/1916

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1916 {

    private static final long INF = 100_000_100_001L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Bus>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int path = 0; path < M; path++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Bus(end, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        long minCost = dijkstra(startNode, endNode, graph, N);
        System.out.println(minCost);
    }

    private static class Bus implements Comparable<Bus> {
        int node;
        long cost;

        Bus(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    private static long dijkstra(int startNode, int endNode, List<Bus>[] graph, int N) {
        PriorityQueue<Bus> pq = new PriorityQueue<>();

        pq.offer(new Bus(startNode, 0));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        dist[startNode] = 0;

        while (!pq.isEmpty()) {
            Bus cur = pq.poll();

            if (dist[cur.node] < cur.cost) {
                continue;
            }

            for (Bus bus: graph[cur.node]) {
                if (dist[bus.node] <= dist[cur.node] + bus.cost) {
                    continue;
                }
                dist[bus.node] = dist[cur.node] + bus.cost;
                pq.offer(new Bus(bus.node, dist[bus.node]));
            }
        }

        return dist[endNode];
    }
}
