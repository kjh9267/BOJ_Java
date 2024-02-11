package dijkstra;

// https://www.acmicpc.net/problem/6248

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_6248 {

    private static final int INF = 100_001;

    private static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        int[] dist = dijkstra(N, X);
        int maxValue = 0;

        for (int node = 1; node <= N; node++) {
            if (dist[node] == INF) {
                continue;
            }
            maxValue = Math.max(maxValue, dist[node]);
        }

        System.out.println(maxValue * 2);
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
            return this.cost - other.cost;
        }
    }

    private static int[] dijkstra(int N, int X) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.id] < cur.cost) {
                continue;
            }

            for (Node next: graph[cur.id]) {
                if (dist[next.id] <= dist[cur.id] + next.cost) {
                    continue;
                }
                dist[next.id] = dist[cur.id] + next.cost;
                pq.offer(new Node(next.id, dist[next.id]));
            }
        }

        return dist;
    }
}
