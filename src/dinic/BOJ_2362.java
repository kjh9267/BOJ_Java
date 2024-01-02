package dinic;

// https://www.acmicpc.net/problem/2362

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2362 {

    private static final char WALL = '+';

    private static final char PRISONER = 'X';

    private static final char DOOR = 'O';

    private static final int NOT_VISITED = -1;

    private static final String YES = "Yes";

    private static final String NO = "No";

    private static final int INF = 1_000_001;

    private static final int[][] UP_RIGHT_DIR = {{-1, 0}, {0, 1}};

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int source;

    private static int sink;

    private static List<Integer>[] graph;

    private static List<Integer>[] capacities;

    private static List<Integer>[] flows;

    private static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[][] grid = new char[N][N];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        init(grid, N);

        if (!isPossible(grid, N)) {
            System.out.println(NO);
            System.exit(0);
        }

        int result = dinic();

        if (result <= K) {
            System.out.println(YES);
            System.out.println(result);
        }
        else {
            System.out.println(NO);
        }
    }

    private static boolean isPossible(char[][] grid, int N) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (grid[row][col] != PRISONER) {
                    continue;
                }

                for (int[] dir: DIR) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == N) {
                        continue;
                    }
                    if (grid[nextRow][nextCol] == DOOR) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void init(char[][] grid, int N) {
        size = N * N * 2 + 2;
        source = N * N * 2;
        sink = N * N * 2 + 1;
        graph = new List[size];
        capacities = new List[size];
        flows = new List[size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
            capacities[node] = new ArrayList<>();
            flows[node] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (grid[row][col] == WALL) {
                    continue;
                }

                int node = flat(row, col, N);

                graph[node * 2].add(node * 2 + 1);
                graph[node * 2 + 1].add(node * 2);
                if (grid[row][col] == DOOR || grid[row][col] == PRISONER) {
                    capacities[node * 2].add(INF);
                }
                else {
                    capacities[node * 2].add(1);
                }
                capacities[node * 2 + 1].add(0);
                flows[node * 2].add(0);
                flows[node * 2 + 1].add(0);

                if (grid[row][col] == PRISONER) {
                    graph[source].add(node * 2);
                    graph[node * 2].add(source);
                    capacities[source].add(INF);
                    capacities[node * 2].add(0);
                    flows[source].add(0);
                    flows[node * 2].add(0);
                }
                else if (grid[row][col] == DOOR) {
                    graph[node * 2 + 1].add(sink);
                    graph[sink].add(node * 2 + 1);
                    capacities[node * 2 + 1].add(INF);
                    capacities[sink].add(0);
                    flows[node * 2 + 1].add(0);
                    flows[sink].add(0);
                }

                for (int[] dir: UP_RIGHT_DIR) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == N) {
                        continue;
                    }
                    if (grid[nextRow][nextCol] == WALL) {
                        continue;
                    }
                    int nextNode = flat(nextRow, nextCol, N);
                    graph[node * 2 + 1].add(nextNode * 2);
                    graph[nextNode * 2].add(node * 2 + 1);
                    graph[nextNode * 2 + 1].add(node * 2);
                    graph[node * 2].add(nextNode * 2 + 1);
                    capacities[node * 2 + 1].add(1);
                    capacities[nextNode * 2].add(0);
                    capacities[nextNode * 2 + 1].add(1);
                    capacities[node * 2].add(0);
                    flows[node * 2 + 1].add(0);
                    flows[nextNode * 2].add(0);
                    flows[nextNode * 2 + 1].add(0);
                    flows[node * 2].add(0);
                }
            }
        }
    }

    private static int flat(int row, int col, int N) {
        return row * N + col;
    }

    private static int dinic() {
        int totalFlow = 0;

        while (true) {
            int[] level = bfs();

            if (level[sink] == NOT_VISITED) {
                break;
            }

            int[] work = new int[sink];
            while (true) {
                int flowValue = dfs(source, INF, level, work);

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
        queue.offer(source);

        int[] level = new int[size];
        Arrays.fill(level, NOT_VISITED);
        level[source] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int index = 0; index < graph[cur].size(); index++) {
                int next = graph[cur].get(index);
                int capacity = capacities[cur].get(index);
                int flow = flows[cur].get(index);

                if (level[next] != NOT_VISITED) {
                    continue;
                }
                if (capacity - flow <= 0) {
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
            int capacity = capacities[cur].get(index);
            int flow = flows[cur].get(index);

            if (level[next] != level[cur] + 1) {
                continue;
            }
            if (capacity - flow <= 0) {
                continue;
            }
            int minFlowValue = Math.min(flowValue, capacity - flow);
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
