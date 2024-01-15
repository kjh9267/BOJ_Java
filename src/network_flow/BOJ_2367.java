package network_flow;

// https://www.acmicpc.net/problem/2367

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2367 {

    private static final int INF = 10_001;

    private static final int SOURCE = 0;

    private static final int NOT_VISITED = -1;

    private static int sink;

    private static int size;

    private static int total;

    private static boolean[][] graph;

    private static int[][] flow;

    private static int[][] capacities;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        sink = (N + D) * 2 + 2;
        size = (N + D) * 2 + 3;
        graph = new boolean[size][size];
        capacities = new int[size][size];
        flow = new int[size][size];

        for (int cur = 1; cur <= N + D; cur++) {
            for (int next = 1; next <= N + D; next++) {
                if (cur == next) {
                    continue;
                }
                graph[cur * 2 + 1][next * 2] = true;
                graph[next * 2 + 1][cur * 2] = true;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int food = N + 1; food <= N + D; food++) {
            graph[food * 2 + 1][sink] = true;
            graph[sink][food * 2 + 1] = true;
            graph[food * 2][food * 2 + 1] = true;
            graph[food * 2 + 1][food * 2] = true;
            capacities[food * 2 + 1][sink] = INF;
            capacities[food * 2][food * 2 + 1] = Integer.parseInt(st.nextToken());
        }

        for (int person = 1; person <= N; person++) {
            st = new StringTokenizer(br.readLine());
            int Z = Integer.parseInt(st.nextToken());
            graph[SOURCE][person * 2] = true;
            graph[person * 2][SOURCE] = true;
            graph[person * 2][person * 2 + 1] = true;
            graph[person * 2 + 1][person * 2] = true;
            capacities[SOURCE][person * 2] = INF;
            capacities[person * 2][person * 2 + 1] = K;

            for (int count = 0; count < Z; count++) {
                int food = Integer.parseInt(st.nextToken()) + N;
                graph[person * 2 + 1][food * 2] = true;
                graph[food * 2][person * 2 + 1] = true;
                graph[food * 2 + 1][person * 2] = true;
                graph[person * 2][food * 2 + 1] = true;
                capacities[person * 2 + 1][food * 2] = 1;
            }
        }

        networkFlow();
        System.out.println(total);
    }

    private static void networkFlow() {
        while (true) {
            int[] way = bfs();

            if (way[sink] == -1) {
                break;
            }

            doFlow(way);
        }
    }

    private static int[] bfs() {
        int[] way = new int[size];
        Arrays.fill(way, NOT_VISITED);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next = 0; next < size; next++) {
                if (!graph[cur][next]) {
                    continue;
                }
                if (way[next] != NOT_VISITED) {
                    continue;
                }
                if (capacities[cur][next] - flow[cur][next] <= 0) {
                    continue;
                }
                way[next] = cur;
                queue.offer(next);
                if (next == sink) {
                    return way;
                }
            }
        }

        return way;
    }

    private static void doFlow(int[] way) {
        for (int node = sink; node != SOURCE; node = way[node]) {
            flow[way[node]][node] += 1;
            flow[node][way[node]] -= 1;
        }

        total += 1;
    }
}