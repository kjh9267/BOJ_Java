package mcmf;

// https://www.acmicpc.net/problem/2311

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2311 {

    private static final int NOT_VISITED = -1;

    private static final int INF = 500_000_001;

    private static final int SOURCE = 2;

    private static int sink;

    private static int size;

    private static List<Integer>[] graph;

    private static int[][] capacities;

    private static int[][] flows;

    private static int[][] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Link[] links = new Link[M];

        for (int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            links[index] = new Link(start, end, cost);
        }

        init(links, N);
        int result = computeMCMF();

        System.out.println(result);
    }

    private static class Link {
        int start;
        int end;
        int cost;

        Link (int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    private static void init(Link[] links, int N) {
        sink = N * 2 + 1;
        size = N * 2 + 2;
        graph = new List[size];
        capacities = new int[size][size];
        flows = new int[size][size];
        costs = new int[size][size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int node = 1; node <= N; node++) {
            connect(node * 2, node * 2 + 1, INF, 0);
        }

        for (Link link: links) {
            connect(link.start * 2 + 1, link.end * 2, 1, link.cost);
            connect(link.end * 2 + 1, link.start * 2, 1, link.cost);
        }
    }

    private static void connect(int start, int end, int capacity, int cost) {
        graph[start].add(end);
        graph[end].add(start);
        capacities[start][end] = capacity;
        costs[start][end] = cost;
        costs[end][start] = -cost;
    }

    private static int computeMCMF() {
        int totalCost = 0;

        for (int count = 0; count < 2; count++) {
            int[] way = spfa();

            if (way[sink] == NOT_VISITED) {
                break;
            }

            totalCost += doFlow(way);
        }

        return totalCost;
    }

    private static int[] spfa() {
        int[] way = new int[size];
        Arrays.fill(way, NOT_VISITED);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        int[] dist = new int[size];
        Arrays.fill(dist, INF);
        dist[SOURCE] = 0;

        boolean[] inQueue = new boolean[size];
        inQueue[SOURCE] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            inQueue[cur] = false;

            for (int next: graph[cur]) {
                if (capacities[cur][next] - flows[cur][next] <= 0) {
                    continue;
                }
                if (dist[next] <= dist[cur] + costs[cur][next]) {
                    continue;
                }
                dist[next] = dist[cur] + costs[cur][next];
                way[next] = cur;
                if (!inQueue[next]) {
                    queue.offer(next);
                    inQueue[next] = true;
                }
            }
        }

        return way;
    }

    private static int doFlow(int[] way) {
        int cost = 0;

        for (int node = sink; node != SOURCE; node = way[node]) {
            int prev = way[node];
            flows[prev][node] += 1;
            flows[node][prev] -= 1;
            cost += costs[prev][node];
        }

        return cost;
    }
}
