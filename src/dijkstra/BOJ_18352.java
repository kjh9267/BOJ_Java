package dijkstra;

// https://www.acmicpc.net/problem/18352

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352 {

    private static final String NEW_LINE = "\n";

    private static final int INF = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, 1));
        }

        int[] dist = dijkstra(graph, N, X);
        String result = findTargetNodes(dist, K, N);
        System.out.print(result);
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
            return this.cost - other.cost;
        }
    }

    private static int[] dijkstra(List<Node>[] graph, int N, int X) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[X] = 0;

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

        return dist;
    }

    private static String findTargetNodes(int[] dist, int X, int N) {
        StringBuilder sb = new StringBuilder();
        List<Integer> result = new ArrayList<>();

        for (int node = 1; node <= N; node++) {
            if (dist[node] == X) {
                result.add(node);
            }
        }

        Collections.sort(result);

        for (int node: result) {
            sb.append(node)
                    .append(NEW_LINE);
        }

        if (result.isEmpty()) {
            return sb.append(-1)
                    .append(NEW_LINE)
                    .toString();
        }

        return sb.toString();
    }
}
