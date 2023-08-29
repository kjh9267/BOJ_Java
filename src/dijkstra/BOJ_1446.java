package dijkstra;

// https://www.acmicpc.net/problem/1446

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1446 {

    private static final int LIMIT = 10_001;

    private static final int START = 0;

    private static final int INF = 200_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[LIMIT];

        for (int node = START; node < LIMIT; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int node = START; node < LIMIT - 1; node++) {
            graph[node].add(new Node(node + 1, 1));
        }

        for (int link = 0; link < N; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
        }

        System.out.println(dijkstra(graph, D));
    }

    private static class Node implements Comparable<Node> {
        int id;
        int cost;

        Node (int id, int cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    private static int dijkstra(List<Node>[] graph, int destination) {
        Queue<Node> pq = new LinkedList<>();
        pq.offer(new Node(START, 0));

        int[] dist = new int[LIMIT];
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

        return dist[destination];
    }
}
