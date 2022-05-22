package dijkstra;

// https://www.acmicpc.net/problem/1162

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1162 {

    private static final long INF = 50_000_000_001L;

    private static final int START = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph[start].add(new Node(end, cost, 0));
            graph[end].add(new Node(start, cost, 0));
        }

        long minCost = dijkstra(graph, N, K);

        System.out.println(minCost);
    }

    private static class Node implements Comparable<Node> {
        int id;
        long cost;
        int linkCount;

        Node(int id, long cost, int linkCount) {
            this.id = id;
            this.cost = cost;
            this.linkCount = linkCount;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    private static long dijkstra(List<Node>[] graph, int N, int K) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(START, 0, 0));

        long[][] dist = new long[N + 1][K + 1];

        for (int node = 1; node <= N; node++) {
            Arrays.fill(dist[node], INF);
        }

        dist[START][0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.id][cur.linkCount] < cur.cost) {
                continue;
            }

            for (Node next: graph[cur.id]) {
                if (cur.linkCount + 1 <= K) {
                    offerNextNode(pq, dist, cur, next, 1, 0);
                }
                offerNextNode(pq, dist, cur, next, 0, next.cost);
            }
        }

        long minCost = INF;
        for (long cost: dist[N]) {
            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    private static void offerNextNode(Queue<Node> pq, long[][] dist, Node cur, Node next, int linkCount, long cost) {
        if (dist[next.id][cur.linkCount + linkCount] <= dist[cur.id][cur.linkCount] + cost) {
            return;

        }
        dist[next.id][cur.linkCount + linkCount] = dist[cur.id][cur.linkCount] + cost;
        pq.offer(new Node(next.id, dist[next.id][cur.linkCount + linkCount], cur.linkCount + linkCount));
    }
}
