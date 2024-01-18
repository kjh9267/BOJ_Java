package mcmf;

// https://www.acmicpc.net/problem/3640

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3640 {

    private static final int INF = 1_000_001;

    private static final int NOT_VISITED = -1;

    private static final int CAPACITY = 2;

    private static final int SOURCE = 2;

    private static final String EMPTY = "";

    private static final String NEW_LINE = "\n";

    private static int sink;

    private static int size;

    private static int total;

    private static List<Integer>[] graph;

    private static List<Integer>[] capacities;

    private static List<Integer>[] flows;

    private static List<Integer>[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            int V;
            int E;
            StringTokenizer st;

            try {
                st = new StringTokenizer(br.readLine());
                V = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
            }
            catch (Exception e) {
                break;
            }

            int[][] data = new int[E][3];

            for (int count = 0; count < E; count++) {
                st = new StringTokenizer(br.readLine());
                data[count][0] = Integer.parseInt(st.nextToken());
                data[count][1] = Integer.parseInt(st.nextToken());
                data[count][2] = Integer.parseInt(st.nextToken());
            }

            init(data, V);
            computeMCMF();

            sb.append(total)
                    .append(NEW_LINE);
        }
        System.out.print(sb);
    }

    private static void init(int[][] data, int V) {
        total = 0;
        sink = V * 2 + 1;
        size = V * 2 + 2;
        graph = new List[size];
        capacities = new List[size];
        flows = new List[size];
        costs = new List[size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
            capacities[node] = new ArrayList<>();
            flows[node] = new ArrayList<>();
            costs[node] = new ArrayList<>();
        }

        for (int node = 1; node <= V; node++) {
            graph[node * 2].add(node * 2 + 1);
            graph[node * 2 + 1].add(node * 2);
            capacities[node * 2].add(1);
            capacities[node * 2 + 1].add(0);
            flows[node * 2].add(0);
            flows[node * 2 + 1].add(0);
            costs[node * 2].add(0);
            costs[node * 2 + 1].add(0);
        }

        capacities[SOURCE].set(0, CAPACITY);
        capacities[V * 2].set(0, CAPACITY);

        for (int[] link: data) {
            int start = link[0];
            int end = link[1];
            int cost = link[2];

            graph[start * 2 + 1].add(end * 2);
            graph[end * 2].add(start * 2 + 1);
            capacities[start * 2 + 1].add(1);
            capacities[end * 2].add(0);
            flows[start * 2 + 1].add(0);
            flows[end * 2].add(0);
            costs[start * 2 + 1].add(cost);
            costs[end * 2].add(-cost);
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
        for (int node = sink; node != SOURCE; node = way[node]) {
            int prev = way[node];
            int index = graph[prev].indexOf(node);
            flows[prev].set(index, flows[prev].get(index) + 1);
            int prevIndex = graph[node].indexOf(prev);
            flows[node].set(prevIndex, flows[node].get(prevIndex) - 1);

            total += costs[prev].get(index);
        }
    }
}
