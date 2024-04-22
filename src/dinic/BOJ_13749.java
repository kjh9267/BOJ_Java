package dinic;

// https://www.acmicpc.net/problem/13749

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13749 {

    private static final String NEW_LINE = "\n";

    private static final int NOT_VISITED = -1;

    private static final int INF = 1_000_000_001;

    private static final int SOURCE = 2;

    private static final int SINK = 5;

    private static int N;

    private static int total;

    private static int size;

    private static boolean[][] graph;

    private static int[][] capacities;

    private static int[][] flows;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Link[] data = new Link[P];

        for (int index = 0; index < P; index++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int capacity = Integer.parseInt(st.nextToken());
            data[index] = new Link(start, end ,capacity);
        }

        init(data);

        sb.append(computeDinic())
                .append(NEW_LINE);

        for (int count = 0; count < K; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int capacity = Integer.parseInt(st.nextToken());
            connect(start * 2 + 1, end * 2, capacity);
            connect(end * 2 + 1, start * 2, capacity);
            sb.append(computeDinic())
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static class Link {
        int start;
        int end;
        int capacity;

        Link(int start, int end, int capacity) {
            this.start = start;
            this.end = end;
            this.capacity = capacity;
        }
    }

    private static void init(Link[] data) {
        size = N * 2 + 2;
        graph = new boolean[size][size];
        capacities = new int[size][size];
        flows = new int[size][size];

        for (int node = 1; node <= N; node++) {
            connect(node * 2, node * 2 + 1, INF);
        }

        for (Link link: data) {
            connect(link.start * 2 + 1, link.end * 2, link.capacity);
            connect(link.end * 2 + 1, link.start * 2, link.capacity);
        }
    }

    private static void connect(int start, int end, int capacity) {
        graph[start][end] = true;
        graph[end][start] = true;
        capacities[start][end] += capacity;
    }

    private static int computeDinic() {
        while (true) {
            int[] level = bfs();

            if (level[SINK] == NOT_VISITED) {
                break;
            }

            int[] work = new int[size];

            while (true) {
                int flowValue = dfs(SOURCE, INF, level, work);

                if (flowValue == 0) {
                    break;
                }

                total += flowValue;
            }
        }

        return total;
    }

    private static int[] bfs() {
        int[] level = new int[size];
        Arrays.fill(level, NOT_VISITED);
        level[SOURCE] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next = 0; next < size; next++) {
                if (!graph[cur][next]) {
                    continue;
                }
                if (level[next] != NOT_VISITED) {
                    continue;
                }
                if (capacities[cur][next] - flows[cur][next] <= 0) {
                    continue;
                }
                level[next] = level[cur] + 1;
                queue.offer(next);
            }
        }

        return level;
    }

    private static int dfs(int cur, int flowValue, int[] level, int[] work) {
        if (cur == SINK) {
            return flowValue;
        }

        for (int next = work[cur]; next < size; next++) {
            work[cur] = next;

            if (level[next] != level[cur] + 1) {
                continue;
            }
            if (capacities[cur][next] - flows[cur][next] <= 0) {
                continue;
            }
            int minFlowValue = Math.min(flowValue, capacities[cur][next] - flows[cur][next]);
            minFlowValue = dfs(next, minFlowValue, level, work);

            if (minFlowValue == 0) {
                continue;
            }
            flows[cur][next] += minFlowValue;
            flows[next][cur] -= minFlowValue;

            return minFlowValue;
        }

        return 0;
    }
}
