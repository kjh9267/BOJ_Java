package mcmf;

// https://www.acmicpc.net/problem/11407

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_11407 {

    private static final int INF = 10_000_001;

    private static final int NOT_VISITED = -1;

    private static final int SOURCE = 0;

    private static int sink;

    private static int size;

    private static int totalFlow;

    private static int totalCost;

    private static List<Integer>[] graph;

    private static List<Integer>[] capacities;

    private static List<Integer>[] flows;

    private static List<Integer>[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] peopleData = new int[N];
        int[] storeData = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int person = 0; person < N; person++) {
            peopleData[person] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int store = 0; store < M; store++) {
            storeData[store] = Integer.parseInt(st.nextToken());
        }

        int[][] capacityData = new int[M][N];

        for (int store = 0; store < M; store++) {
            st = new StringTokenizer(br.readLine());
            for (int person = 0; person < N; person++) {
                capacityData[store][person] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] costData = new int[M][N];

        for (int store = 0; store < M; store++) {
            st = new StringTokenizer(br.readLine());
            for (int person = 0; person < N; person++) {
                costData[store][person] = Integer.parseInt(st.nextToken());
            }
        }

        init(peopleData, storeData, capacityData, costData, N, M);
        computeMCMF();

        System.out.println(totalFlow);
        System.out.println(totalCost);
    }

    private static void init(int[] peopleData, int[] storeData, int[][] capacityData, int[][] costData, int N, int M) {
        sink = (N + M) * 2 + 2;
        size = (N + M) * 2 + 3;
        graph = new List[size];
        capacities = new List[size];
        flows = new List[size];
        costs = new List[size];

        for (int node = SOURCE; node < size; node++) {
            graph[node] = new ArrayList<>();
            capacities[node] = new ArrayList<>();
            flows[node] = new ArrayList<>();
            costs[node] = new ArrayList<>();
        }

        for (int node = 1; node <= M; node++) {
            graph[SOURCE].add(node * 2);
            graph[node * 2].add(SOURCE);
            capacities[SOURCE].add(INF);
            capacities[node * 2].add(0);
            flows[SOURCE].add(0);
            flows[node * 2].add(0);
            costs[SOURCE].add(0);
            costs[node * 2].add(0);

            graph[node * 2].add(node * 2 + 1);
            graph[node * 2 + 1].add(node * 2);
            capacities[node * 2].add(storeData[node - 1]);
            capacities[node * 2 + 1].add(0);
            flows[node * 2].add(0);
            flows[node * 2 + 1].add(0);
            costs[node * 2].add(0);
            costs[node * 2 + 1].add(0);
        }

        for (int node = M + 1; node <= M + N; node++) {
            graph[node * 2 + 1].add(sink);
            graph[sink].add(node * 2 + 1);
            capacities[node * 2 + 1].add(INF);
            capacities[sink].add(0);
            flows[node * 2 + 1].add(0);
            flows[sink].add(0);
            costs[node * 2 + 1].add(0);
            costs[sink].add(0);

            graph[node * 2].add(node * 2 + 1);
            graph[node * 2 + 1].add(node * 2);
            capacities[node * 2].add(peopleData[node - M - 1]);
            capacities[node * 2 + 1].add(0);
            flows[node * 2].add(0);
            flows[node * 2 + 1].add(0);
            costs[node * 2].add(0);
            costs[node * 2 + 1].add(0);
        }

        for (int store = 1; store <= M; store++) {
            for (int person = M + 1; person <= N + M; person++) {
                int cost = costData[store - 1][person - M - 1];
                int capacity = capacityData[store - 1][person - M - 1];

                graph[store * 2 + 1].add(person * 2);
                graph[person * 2].add(store * 2 + 1);
                capacities[store * 2 + 1].add(capacity);
                capacities[person * 2].add(0);
                flows[store * 2 + 1].add(0);
                flows[person * 2].add(0);
                costs[store * 2 + 1].add(cost);
                costs[person * 2].add(-cost);
            }
        }
    }

    private static void computeMCMF() {
        while (true) {
            int[] way = spfa();

            if (way[sink] == NOT_VISITED) {
                break;
            }

            doFlow(way);
        }
    }

    private static int[] spfa() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        int[] dist = new int[size];
        Arrays.fill(dist, INF);
        dist[SOURCE] = 0;

        boolean[] inQueue = new boolean[size];
        inQueue[SOURCE] = true;

        int[] way = new int[size];
        Arrays.fill(way, NOT_VISITED);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            inQueue[cur] = false;

            for (int index = 0; index < graph[cur].size(); index++) {
                int next = graph[cur].get(index);

                if (capacities[cur].get(index) - flows[cur].get(index) <= 0) {
                    continue;
                }
                if (dist[next] <= dist[cur] + costs[cur].get(index)) {
                    continue;
                }
                dist[next] = dist[cur] + costs[cur].get(index);
                way[next] = cur;
                if (!inQueue[next]) {
                    queue.add(next);
                    inQueue[next] = true;
                }
            }
        }

        return way;
    }

    private static void doFlow(int[] way) {
        int flowValue = INF;

        for (int node = sink; node != SOURCE; node = way[node]) {
            int prev = way[node];
            int index = graph[prev].indexOf(node);
            int capacity = capacities[prev].get(index);
            int flow = flows[prev].get(index);
            flowValue = min(flowValue, capacity - flow);
        }

        totalFlow += flowValue;

        for (int node = sink; node != SOURCE; node = way[node]) {
            int prev = way[node];
            int index = graph[prev].indexOf(node);
            flows[prev].set(index, flows[prev].get(index) + flowValue);
            int prevIndex = graph[node].indexOf(prev);
            flows[node].set(prevIndex, flows[node].get(prevIndex) - flowValue);

            totalCost += costs[prev].get(index) * flowValue;
        }
    }
}
