package dijkstra;

// https://www.acmicpc.net/problem/9370

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class BOJ_9370 {

    private static final int INF = 50_000_001;

    private static final String SPACE = " ";

    private static final String NEW_LINE = "\n";

    private static List<Node>[] graph;

    private static List<Integer> targets;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            graph = new List[n + 1];

            for (int node = 1; node <= n; node++) {
                graph[node] = new ArrayList<>();
            }

            for (int link = 0; link < m; link++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph[a].add(new Node(b, d));
                graph[b].add(new Node(a, d));
            }

            targets = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int target = Integer.parseInt(br.readLine());
                targets.add(target);
            }

            int[] startDist = dijkstra(s, n);
            int[] gDist = dijkstra(g, n);
            int[] hDist = dijkstra(h, n);

            List<Integer> destinations = findDestinations(startDist, gDist, hDist, g, h);

            String destinationString = destinations.stream()
                    .sorted()
                    .map(String::valueOf)
                    .collect(joining(SPACE));

            sb.append(destinationString)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
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
    private static int[] dijkstra(int start, int n) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.id]) {
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

    private static List<Integer> findDestinations(int[] startDist, int[] gDist, int[] hDist, int g, int h) {
        List<Integer> destinations = new ArrayList<>();

        for (int target: targets) {
            int dist = Math.min(startDist[g] + gDist[h] + hDist[target], startDist[h] + hDist[g] + gDist[target]);
            if (dist == startDist[target]) {
                destinations.add(target);
            }
        }

        return destinations;
    }
}
