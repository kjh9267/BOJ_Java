package bipartite_matching;

// https://www.acmicpc.net/problem/9525

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_9525 {

    private static final char WALL = 'X';

    private static final int[][] DIR = {{0, 1}, {1, 0}};

    private static int N;

    private static int size;

    private static char[][] grid;

    private static List<Integer>[] graph;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        init();
        int result = bipartiteMatch();

        System.out.println(result);
    }

    private static void init() {
        size = N * N + 2;
        graph = new List[size];
        int[][][] nodes = new int[N][N][2];
        int node = 0;

        for (int index = 1; index < size; index++) {
            graph[index] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (grid[row][col] == WALL) {
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

                        if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == N) {
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
            for (int col = 0; col < N; col++) {
                if (grid[row][col] == WALL) {
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
        A = new int[size];
        B = new int[size];

        for (int node = 1; node < size; node++) {
            visited = new boolean[size];

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
