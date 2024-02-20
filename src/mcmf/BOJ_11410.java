package mcmf;

// https://www.acmicpc.net/problem/11410

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_11410 {

    private static final int SOURCE = 0;

    private static final int NOT_VISITED = -1;

    private static final int INF = 10_001;

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
        int P = Integer.parseInt(st.nextToken());
        int[][] people = new int[N - 1][N - 1];
        int[][] money = new int[N - 1][N - 1];

        for (int start = 0; start < N - 1; start++) {
            st = new StringTokenizer(br.readLine());
            for (int end = start; end < N - 1; end++) {
                people[start][end - start] = Integer.parseInt(st.nextToken());
            }
        }

        for (int start = 0; start < N - 1; start++) {
            st = new StringTokenizer(br.readLine());
            for (int end = start; end < N - 1; end++) {
                money[start][end - start] = Integer.parseInt(st.nextToken());
            }
        }

        init(people, money, N, P);
        int result = computeMCMF();
        System.out.println(-result);
    }

    private static void init(int[][] people, int[][] money, int N, int P) {
        sink = N * 2 + 2;
        size = N * 2 + 3;
        graph = new List[size];
        capacities = new int[size][size];
        flows = new int[size][size];
        costs = new int[size][size];

        for (int node = SOURCE; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        connect(SOURCE, 2, P, 0);
        connect(N * 2 + 1, sink, P, 0);

        for (int node = 1; node < N; node++) {
            connect(node * 2 + 1, (node + 1) * 2, P, 0);
        }

        for (int node = 1; node <= N; node++) {
            connect(node * 2, node * 2 + 1, P, 0);
        }

        for (int start = 1; start < N; start++) {
            int end = start;
            for (int index = start; index < N; index++) {
                end += 1;
                connect(start * 2 + 1, end * 2 + 1, people[start - 1][index - start], money[start - 1][index - start]);
            }
        }
    }

    private static void connect(int start, int end, int capacity, int cost) {
        graph[start].add(end);
        graph[end].add(start);
        capacities[start][end] = capacity;
        costs[start][end] = -cost;
        costs[end][start] = cost;
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

        return totalCost;
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

        for (int node = sink; node != SOURCE; node = way[node]) {
            int prev = way[node];
            flowValue = min(flowValue, capacities[prev][node] - flows[prev][node]);
        }

        for (int node = sink; node != SOURCE; node = way[node]) {
            int prev = way[node];
            flows[prev][node] += flowValue;
            flows[node][prev] -= flowValue;
            cost += costs[prev][node] * flowValue;
        }

        return cost;
    }
}
