package dinic;

// https://www.acmicpc.net/problem/3295

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_3295 {

    private static final int SOURCE = 0;

    private static final int INF = 1_001;

    private static final int NOT_VISITED = -1;

    private static final String NEW_LINE = "\n";

    private static List<Integer>[] graph;

    private static List<Integer>[] capacities;

    private static List<Integer>[] flows;

    private static int sink;

    private static int size;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] data = new int[M][2];

            for (int link = 0; link < M; link++) {
                st = new StringTokenizer(br.readLine());
                data[link][0] = Integer.parseInt(st.nextToken()) + 1;
                data[link][1] = Integer.parseInt(st.nextToken()) + 1;
            }

            init(data, N);
            int total = dinic();
            sb.append(total)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init(int[][] data, int N) {
        sink = N * 2 + 2;
        size = N * 2 + 3;
        graph = new List[size];
        capacities = new List[size];
        flows = new List[size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
            capacities[node] = new ArrayList<>();
            flows[node] = new ArrayList<>();
        }

        for (int node = 1; node <= N; node++) {
            graph[SOURCE].add(node * 2 + 1);
            graph[node * 2 + 1].add(SOURCE);
            capacities[SOURCE].add(1);
            capacities[node * 2 + 1].add(0);
            flows[SOURCE].add(0);
            flows[node * 2 + 1].add(0);

            graph[node * 2].add(sink);
            graph[sink].add(node * 2);
            capacities[node * 2].add(1);
            capacities[sink].add(0);
            flows[node * 2].add(0);
            flows[sink].add(0);
        }

        for (int[] link: data) {
            int start = link[0];
            int end = link[1];

            graph[start * 2 + 1].add(end * 2);
            graph[end * 2].add(start * 2 + 1);
            capacities[start * 2 + 1].add(1);
            capacities[end * 2].add(0);
            flows[start * 2 + 1].add(0);
            flows[end * 2].add(0);
        }
    }

    private static int dinic() {
        int total = 0;

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

                total += flowValue;
            }
        }

        return total;
    }

    private static int[] bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        int[] level = new int[size];
        Arrays.fill(level, NOT_VISITED);
        level[SOURCE] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int index = 0; index < graph[cur].size(); index++) {
                int next = graph[cur].get(index);

                if (level[next] != NOT_VISITED) {
                    continue;
                }
                if (capacities[cur].get(index) - flows[cur].get(index) <= 0) {
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
            int capacity = capacities[cur].get(index);
            int flow = flows[cur].get(index);
            if (capacity - flow <= 0) {
                continue;
            }
            int minFlowValue = min(flowValue, capacity - flow);
            minFlowValue = dfs(next, minFlowValue, level, work);

            if (minFlowValue == 0) {
                continue;
            }

            flows[cur].set(index, flows[cur].get(index) + minFlowValue);
            int reverseIndex = graph[next].indexOf(cur);
            flows[next].set(reverseIndex, flows[next].get(reverseIndex) - minFlowValue);

            return minFlowValue;
        }

        return 0;
    }
}
