package mcmf;

// https://www.acmicpc.net/problem/12423

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_12423 {

    private static final String NEW_LINE = "\n";

    private static final int INF = 10_000_001;

    private static final int NOT_VISITED = -1;

    private static final int SOURCE = 0;

    private static final int SINK = 7;

    private static int size;

    private static List<Integer>[] graph;

    private static int[][] capacities;

    private static int[][] flows;

    private static int[][] costs;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int F = Integer.parseInt(st.nextToken());
            int[][] data = new int[3][3];

            for (int index = 0; index < 3; index++) {
                st = new StringTokenizer(br.readLine());
                data[index][0] = Integer.parseInt(st.nextToken());
                data[index][1] = Integer.parseInt(st.nextToken());
                data[index][2] = Integer.parseInt(st.nextToken());
            }

            init(data, A, B, C, D, E, F);
            int result = computeMCMF();
            sb.append(String.format("Case #%d: %d", test + 1, result))
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init(int[][] data, int A, int B, int C, int D, int E, int F) {
        size = 8;
        graph = new List[size];
        capacities = new int[size][size];
        flows = new int[size][size];
        costs = new int[size][size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        connect(SOURCE, 1, A, 0);
        connect(SOURCE, 2, B, 0);
        connect(SOURCE, 3, C, 0);
        connect(4, SINK, D, 0);
        connect(5, SINK, E, 0);
        connect(6, SINK, F, 0);

        for (int node1 = 0; node1 < 3; node1++) {
            for (int node2 = 0; node2 < 3; node2++) {
                connect(node1 + 1, node2 + 4, INF, -data[node1][node2]);
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

    private static int computeMCMF() {
        int totalCost = 0;

        while (true) {
            int[] way = spfa();

            if (way[SINK] == NOT_VISITED) {
                break;
            }

            totalCost += doFlow(way);
        }

        return -totalCost;
    }

    private static int[] spfa() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        int[] dist = new int[size];
        Arrays.fill(dist, INF);
        dist[SOURCE] = 0;

        int[] way = new int[size];
        Arrays.fill(way, NOT_VISITED);

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
        int flowValue = INF;
        int cost = 0;

        for (int node = SINK; node != SOURCE; node = way[node]) {
            int prev = way[node];
            flowValue = min(flowValue, capacities[prev][node] - flows[prev][node]);
        }

        for (int node = SINK; node != SOURCE; node = way[node]) {
            int prev = way[node];
            flows[prev][node] += flowValue;
            flows[node][prev] -= flowValue;
            cost += flowValue * costs[prev][node];
        }

        return cost;
    }
}
