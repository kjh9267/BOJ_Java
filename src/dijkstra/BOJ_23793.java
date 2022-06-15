package dijkstra;

// https://www.acmicpc.net/problem/23793

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_23793 {

    private static final int INF = Integer.MAX_VALUE;

    private static final String SPACE = " ";

    private static final int IMPOSSIBLE = -1;

    private static List<Node>[] graph;

    private static int N;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int mid = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] startDist = dijkstra(start, 0);
        int[] midDist = dijkstra(mid, 0);
        int[] exceptDist = dijkstra(start, mid);

        if (startDist[mid] == INF || midDist[end] == INF) {
            sb.append(IMPOSSIBLE);
        }
        else {
            sb.append(startDist[mid] + midDist[end]);
        }

        sb.append(SPACE);

        if (exceptDist[end] == INF) {
                sb.append(IMPOSSIBLE);
        }
        else {
            sb.append(exceptDist[end]);
        }

        System.out.println(sb);
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

    private static int[] dijkstra(int start, int except) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.id] < cur.cost) {
                continue;
            }

            for (Node next: graph[cur.id]) {
                if (next.id == except) {
                    continue;
                }
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
