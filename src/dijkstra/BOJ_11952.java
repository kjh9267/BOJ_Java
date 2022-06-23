package dijkstra;

// https://www.acmicpc.net/problem/11952

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11952 {

    private static final int NOT_VISITED = -1;

    private static final int START = 1;

    private static final long INF = 20_000_000_100L;

    private static int N;

    private static List<Integer>[] graph;

    private static Set<Integer> zombieCities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int costOfSafeCities = Integer.parseInt(st.nextToken());
        int costOfDangerousCities = Integer.parseInt(st.nextToken());

        zombieCities = new HashSet<>();
        
        for (int index = 0; index < K; index++) {
            int cityId = Integer.parseInt(br.readLine());
            zombieCities.add(cityId);
        }

        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        int[] costs = fillCostsOfDangerousCities(S, costOfDangerousCities);
        costs = fillCostsOfSafeCities(costs, costOfSafeCities);

        long minimumCost = dijkstra(costs);

        System.out.println(minimumCost);
    }

    private static class City {
        int id;
        int distFromZombie;

        City(int id, int distFromZombie) {
            this.id = id;
            this.distFromZombie = distFromZombie;
        }
    }

    private static int[] fillCostsOfDangerousCities(int S, int q) {
        Queue<City> queue = new LinkedList<>();

        for (int zombieCity: zombieCities) {
            queue.offer(new City(zombieCity, 0));
        }

        int[] costs = new int[N + 1];
        Arrays.fill(costs, NOT_VISITED);

        for (City node: queue) {
            costs[node.id] = 0;
        }

        while (!queue.isEmpty()) {
            City cur = queue.poll();

            if (cur.distFromZombie == S) {
                return costs;
            }

            for (int next: graph[cur.id]) {
                if (costs[next] != NOT_VISITED) {
                    continue;
                }
                queue.offer(new City(next, cur.distFromZombie + 1));
                costs[next] = q;
            }
        }

        return costs;
    }

    private static int[] fillCostsOfSafeCities(int[] costs, int p) {
        for (int city = 1; city <= N; city++) {
            if (costs[city] != NOT_VISITED) {
                continue;
            }
            costs[city] = p;
        }

        return costs;
    }

    private static long dijkstra(int[] costs) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(START, 0));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[START] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.id] < cur.cost) {
                continue;
            }

            for (int next: graph[cur.id]) {
                if (zombieCities.contains(next)) {
                    continue;
                }
                if (dist[next] <= dist[cur.id] + costs[next]) {
                    continue;
                }
                dist[next] = dist[cur.id] + costs[next];
                pq.offer(new Node(next, dist[next]));
            }
        }

        return dist[N] - costs[N];
    }

    private static class Node implements Comparable<Node> {
        int id;
        long cost;

        Node(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }
}
