package bipartite_matching;

// https://www.acmicpc.net/problem/16726

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16726 {

    private static final char WALL = 'X';

    private static final int NOT_VISITED = -1;

    private static final int[][] DIR = {{0, 1}, {1, 0}};

    private static int N;

    private static int M;

    private static int size;

    private static char[][] grid;

    private static List<Integer>[] graph;

    private static boolean[] visited;

    private static int[] A;

    private static int[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        init();
        int total = bipartiteMatch();

        System.out.println(N * M - computeWallCount() - total);
    }

    private static void init() {
        size = N * M;
        graph = new List[size];

        for (int index = 0; index < size; index++) {
            graph[index] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == WALL) {
                    continue;
                }
                int node = toNode(row, col);

                for (int[] dir: DIR) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M) {
                        continue;
                    }
                    if (grid[nextRow][nextCol] == WALL) {
                        continue;
                    }
                    if (isEven(row, col)) {
                        graph[node].add(toNode(nextRow, nextCol));
                    }
                    else {
                        graph[toNode(nextRow, nextCol)].add(node);
                    }
                }
            }
        }
    }

    private static int toNode(int row, int col) {
        return row * M + col;
    }

    private static boolean isEven(int row, int col) {
        return (row + col) % 2 == 0;
    }

    private static int bipartiteMatch() {
        int total = 0;
        A = new int[size];
        B = new int[size];

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int node = 0; node < size; node++) {
            visited = new boolean[size];
            dfs(node);
        }

        for (int node = 0; node < size; node++) {
            if (B[node] != NOT_VISITED) {
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
            if (B[b] == NOT_VISITED || dfs(B[b])) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }

    private static int computeWallCount() {
        int wallCount = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == WALL) {
                    wallCount += 1;
                }
            }
        }

        return wallCount;
    }
}
