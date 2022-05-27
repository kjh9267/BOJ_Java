package disjoint_set;

// https://www.acmicpc.net/problem/16932

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_16932 {

    private static final char EMPTY = '0';

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static char[][] grid;

    private static int N;

    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new char[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                grid[row][col] = st.nextToken()
                        .charAt(0);
            }
        }

        int[] parents = new int[N * M];

        dfsAll(parents);
        int maxSize = findMaxSize(parents);

        System.out.println(maxSize);
    }

    private static void dfsAll(int[] parents) {
        boolean[][] visited = new boolean[N][M];
        Arrays.fill(parents, -1);

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == EMPTY) {
                    continue;
                }
                if (visited[row][col]) {
                    continue;
                }
                dfs(row, col, grid, visited, parents);
            }
        }
    }

    private static void dfs(int row, int col, char[][] grid, boolean[][] visited, int[] parents) {
        int num = flat(row, col);

        for (int[] dir: DIR) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (!isInGrid(nextRow, nextCol)) {
                continue;
            }
            if (grid[nextRow][nextCol] == EMPTY) {
                continue;
            }
            if (visited[nextRow][nextCol]) {
                continue;
            }

            visited[nextRow][nextCol] = true;

            dfs(nextRow, nextCol, grid, visited, parents);

            int nextNum = flat(nextRow, nextCol);
            merge(num, nextNum, parents);
        }
    }

    private static boolean isInGrid(int row, int col) {
        return 0 <= row && row < N && 0 <= col && col < M;
    }

    private static int flat(int row, int col) {
        return row * M + col;
    }

    private static void merge(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x == y) {
            return;
        }
        if (parents[x] < parents[y]) {
            parents[x] += parents[y];
            parents[y] = x;
        }
        else {
            parents[y] += parents[x];
            parents[x] = y;
        }
    }

    private static int find(int x, int[] parents) {
        if (parents[x] < 0) {
            return x;
        }
        return parents[x] = find(parents[x], parents);
    }

    private static int findMaxSize(int[] parents) {
        int maxSize = 0;

        for (int size: parents) {
            maxSize = Math.max(maxSize, -size);
        }

        maxSize = findConnectedMaxSize(maxSize, parents);

        return maxSize;
    }

    private static int findConnectedMaxSize(int maxSize, int[] parents) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] != EMPTY) {
                    continue;
                }
                int size = findConnectedSize(parents, row, col);
                maxSize = Math.max(maxSize, size);
            }
        }

        return maxSize;
    }

    private static int findConnectedSize(int[] parents, int row, int col) {
        int size = 1;
        Set<Integer> alreadyAdd = new HashSet<>();

        for (int[] dir: DIR) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            if (!isInGrid(nextRow, nextCol)) {
                continue;
            }
            if (grid[nextRow][nextCol] == EMPTY) {
                continue;
            }
            int num = flat(nextRow, nextCol);
            num = find(num, parents);

            if (alreadyAdd.contains(num)) {
                continue;
            }
            alreadyAdd.add(num);
            size += -parents[num];
        }

        return size;
    }
}
