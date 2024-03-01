package dinic;

// https://www.acmicpc.net/problem/17506

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_10976 {

    private static final String NEW_LINE = "\n";

    private static final int INF = 201;

    private static final int NOT_VISITED = -1;

    private static final int SOURCE = 1;

    private static int sink;

    private static int size;

    private static List<Integer>[] graph;

    private static int[][] capacities;

    private static int[][] flows;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] data = new int[M][2];

            for (int index = 0; index < M; index++) {
                st = new StringTokenizer(br.readLine());
                data[index][0] = Integer.parseInt(st.nextToken());
                data[index][1] = Integer.parseInt(st.nextToken());
            }

            init(data, N);
            int totalFlow = computeDinic();
            sb.append(totalFlow)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init(int[][] data, int N) {
        sink = N;
        size = N + 1;
        graph = new List[size];
        capacities = new int[size][size];
        flows = new int[size][size];

        for (int node = SOURCE; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int index = 0; index < data.length; index++) {
            int start = data[index][0];
            int end = data[index][1];
            connect(start, end, 1);
        }
    }

    private static void connect(int start, int end, int capacity) {
        graph[start].add(end);
        graph[end].add(start);
        capacities[start][end] = capacity;
    }

    private static int computeDinic() {
        int totalFlow = 0;

        while (true) {
            int[] level = bfs();

            if (level[sink] == NOT_VISITED) {
                break;
            }

            int[] work = new int[size];

            while (true) {
                int flowValue = dfs(SOURCE, INF, level, work);

                if (flowValue == 0) {
                    break;
                }

                totalFlow += flowValue;
            }
        }

        return totalFlow;
    }

    private static int[] bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        int[] level = new int[size];
        Arrays.fill(level, NOT_VISITED);
        level[SOURCE] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next: graph[cur]) {
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
        if (cur == sink) {
            return flowValue;
        }

        for (int index = work[cur]; index < graph[cur].size(); index++) {
            work[cur] = index;
            int next = graph[cur].get(index);

            if (level[next] != level[cur] + 1) {
                continue;
            }
            if (capacities[cur][next] - flows[cur][next] <= 0) {
                continue;
            }
            int minFlowValue = min(flowValue, capacities[cur][next] - flows[cur][next]);
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
