package depth_first_search;

// https://www.acmicpc.net/problem/2210

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2210 {
    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int MAX = 5;
    private static final int TARGET_DEPTH = 6;
    private static int[][] grid;
    private static final Set<Integer> visited = new HashSet<>();
    private static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        grid = new int[MAX][MAX];

        for (int row = 0; row < MAX; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < MAX; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < MAX; row++) {
            for (int col = 0; col < MAX; col++) {
                dfs(col, row, 1, 0);
            }
        }

        System.out.println(result);
    }

    private static void dfs(int x, int y, int depth, int num) {
        if (x < 0 || x >= MAX || y < 0 || y >= MAX) {
            return;
        }
        if (depth == TARGET_DEPTH + 1) {
            if (!visited.contains(num)) {
                visited.add(num);
                result += 1;
            }
        return;
        }
        for (int[] dir: DIR) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            dfs(nextX, nextY, depth + 1, grid[y][x] + (depth * 10 * num));
        }
    }
}
