package bipartite_matching;

// https://www.acmicpc.net/problem/2570

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2570 {

    private static final char WALL = '#';

    private static final int[][] DIR = {{1, -1}, {1, 1}};

    private static final int SIZE = 10_000;

    private static char[][] grid;

    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        grid = new char[N][N];
        graph = new List[SIZE];

        for (int node = 0; node < SIZE; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int count = 0; count < M; count++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            grid[row][col] = WALL;
        }

        init(N);
        int result = bipartiteMatching();

        System.out.println(result);
    }

    private static void init(int N) {
        int[][][] nodeTable = new int[N][N][2];
        int node = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (nodeTable[row][col][0] == 0) {
                    fillNodeTable(N, row, col, 0, ++node, nodeTable);
                }
                if (nodeTable[row][col][1] == 0) {
                    fillNodeTable(N, row, col, 1, ++node, nodeTable);
                }
            }
        }

        connect(nodeTable, N);
    }

    private static void fillNodeTable(int N, int row, int col, int direction, int node, int[][][] nodeTable) {
        while (0 <= row && row < N && 0 <= col && col < N) {
            if (grid[row][col] == WALL) {
                break;
            }
            if (direction == 0) {
                nodeTable[row][col][0] = node;
            }
            else {
                nodeTable[row][col][1] = node;
            }
            row += DIR[direction][0];
            col += DIR[direction][1];
        }
    }

    private static void connect(int[][][] nodeTable, int N) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int left = nodeTable[row][col][0];
                int right = nodeTable[row][col][1];
                graph[left].add(right);
            }
        }
    }

    private static int bipartiteMatching() {
        int[] A = new int[SIZE];
        int[] B = new int[SIZE];
        int total = 0;

        for (int node = 1; node < SIZE; node++) {
            boolean[] visited = new boolean[SIZE];
            if (dfs(node, visited, A, B)) {
                total += 1;
            }
        }

        return total;
    }

    private static boolean dfs(int a, boolean[] visited, int[] A, int[] B) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b: graph[a]) {
            if (B[b] == 0 || dfs(B[b], visited, A, B)) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }
}
