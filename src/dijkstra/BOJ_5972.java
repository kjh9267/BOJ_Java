package dijkstra;

// https://www.acmicpc.net/problem/5972

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5972 {

    private static final int START = 1;

    private static final int INF = 500_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        int result = dijkstra(graph, N);
        System.out.println(result);
    }

    private static class Node implements Comparable<Node> {
        int id;
        int cost;

        Node(int id , int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    private static int dijkstra(List<Node>[] graph, int N) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(START, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[START] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next: graph[cur.id]) {
                if (dist[next.id] <= dist[cur.id] + next.cost) {
                    continue;
                }
                dist[next.id] = dist[cur.id] + next.cost;
                pq.offer(new Node(next.id, dist[next.id]));
            }
        }

        return dist[N];
    }
}
