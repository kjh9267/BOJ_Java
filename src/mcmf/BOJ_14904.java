package mcmf;

// https://www.acmicpc.net/problem/14904

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14904 {

    private static final int INF = 2_500_001;

    private static final int NOT_VISITED = -1;

    private static final int[][] DIR = {{1, 0}, {0, 1}};

    private static final int SOURCE = 0;

    private static int sink;

    private static int size;

    private static int N;

    private static int K;

    private static List<Integer>[] graph;

    private static int[][] capacities;

    private static int[][] flows;

    private static int[][] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        init(grid);
        int result = computeMCMF();

        System.out.println(result);
    }

    private static void init(int[][] grid) {
        sink = N * N * 2 + 2;
        size = N * N * 2 + 3;
        graph = new List[size];
        capacities = new int[size][size];
        flows = new int[size][size];
        costs = new int[size][size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        connect(SOURCE, 2, K, 0);
        connect(N * N * 2 + 1, sink, K, 0);

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                connect((y * N + x + 1) * 2, (y * N + x + 1) * 2 + 1, 1, -grid[y][x]);

                for (int[] dir: DIR) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];

                    if (nextX < 0 || nextX == N || nextY < 0 || nextY == N) {
                        continue;
                    }
                    connect((y * N + x + 1) * 2 + 1, (nextY * N + nextX + 1) * 2, K, 0);
                    connect((y * N + x + 1) * 2 + 1, (nextY * N + nextX + 1) * 2 + 1, K, 0);
                    connect((y * N + x + 1) * 2, (nextY * N + nextX + 1) * 2, K, 0);
                    connect((y * N + x + 1) * 2, (nextY * N + nextX + 1) * 2 + 1, K, 0);
                }
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

            if (way[sink] == NOT_VISITED) {
                break;
            }

            totalCost += doFlow(way);
        }

        return -totalCost;
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
