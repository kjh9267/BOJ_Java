package bipartite_matching;

// https://www.acmicpc.net/problem/1760

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1760 {

    private static final int HOLE = 1;

    private static final int WALL = 2;

    private static final int SIZE = 10_000;

    private static final int[][] DIR = {{0, 1}, {1, 0}};

    private static int N;

    private static int M;

    private static int[][] grid;

    private static List<Integer>[] graph;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        init();
        int result = bipartiteMatch();

        System.out.println(result);
    }

    private static void init() {
        graph = new List[SIZE];
        int[][][] nodes = new int[N][M][2];
        int node = 0;

        for (int index = 1; index < SIZE; index++) {
            graph[index] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == WALL) {
                    continue;
                }
                if (grid[row][col] == HOLE) {
                    continue;
                }
                for (int direction = 0; direction < 2; direction++) {
                    if (nodes[row][col][direction] > 0) {
                        continue;
                    }
                    node += 1;
                    int nextRow = row;
                    int nextCol = col;

                    while (true) {
                        nodes[nextRow][nextCol][direction] = node;
                        nextRow += DIR[direction][0];
                        nextCol += DIR[direction][1];

                        if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M) {
                            break;
                        }
                        if (grid[nextRow][nextCol] == WALL) {
                            break;
                        }
                    }
                }
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == WALL) {
                    continue;
                }
                if (grid[row][col] == HOLE) {
                    continue;
                }
                int start = nodes[row][col][0];
                int end = nodes[row][col][1];
                graph[start].add(end);
            }
        }
    }

    private static int bipartiteMatch() {
        int total = 0;
        A = new int[SIZE];
        B = new int[SIZE];

        for (int node = 1; node < SIZE; node++) {
            visited = new boolean[SIZE];

            if (dfs(node)) {
                total += 1;
            }
        }

        return total;
    }

    private static boolean dfs(int a) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b: graph[a]) {
            if (B[b] == 0 || dfs(B[b])) {
                A[a] = b;
                B[b] = a;

                return true;
            }
        }

        return false;
    }
}
