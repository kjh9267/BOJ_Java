package mcmf;

// https://www.acmicpc.net/problem/11409

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11409 {

    private static final String NEW_LINE = "\n";

    private static final int INF = 4_000_001;

    private static final int NOT_VISITED = -1;

    private static final int SOURCE = 0;

    private static int sink;

    private static int size;

    private static int N;

    private static int M;

    private static List<Integer>[] graph;

    private static int[][] capacities;

    private static int[][] flows;

    private static int[][] costs;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<Work>[] data = new List[N];

        for (int index = 0; index < N; index++) {
            data[index] = new ArrayList<>();
        }

        for (int person = 0; person < N; person++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            for (int work = 0; work < count; work++) {
                int id = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                data[person].add(new Work(id, cost));
            }
        }

        init(data);
        int[] result = computeMCMF();

        sb.append(result[0])
                .append(NEW_LINE)
                .append(result[1]);

        System.out.println(sb);
    }

    private static class Work {
        int id;
        int cost;

        Work(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private static void init(List<Work>[] data) {
        sink = N + M + 1;
        size = N + M + 2;
        graph = new List[size];
        capacities = new int[size][size];
        flows = new int[size][size];
        costs = new int[size][size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int node = 1; node <= N; node++) {
            connect(SOURCE, node, 1, 0);
        }

        for (int node = N + 1; node <= N + M; node++) {
            connect(node, sink, 1, 0);
        }

        for (int person = 1; person <= N; person++) {
            for (int workIndex = 0; workIndex < data[person - 1].size(); workIndex++) {
                Work work = data[person - 1].get(workIndex);
                connect(person, N + work.id, 1, -work.cost);
            }
        }
    }

    private static void connect(int start, int end, int capacity, int cost) {
        graph[start].add(end);
        graph[end].add(start);
        capacities[start][end] = capacity;
        costs[start][end] = cost;
        costs[end][start] = -cost;
    }

    private static int[] computeMCMF() {
        int totalFlow = 0;
        int totalCost = 0;

        while (true) {
            int[] way = spfa();

            if (way[sink] == NOT_VISITED) {
                break;
            }

            totalFlow += 1;
            totalCost += doFlow(way);
        }

        return new int[] {totalFlow, -totalCost};
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
                    inQueue[next] = true;
                    queue.offer(next);
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
