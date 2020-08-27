package divide_conquer;

// https://www.acmicpc.net/problem/1780

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1780 {
    private static int N;
    private static int[][] grid;
    private static int[] result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        result = new int[3];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);

        for(int res: result) {
            System.out.println(res);
        }
    }

    private static void solve(int x, int y, int size) {
        for (int target = -1; target <= 1; target++) {
            if (isSame(x, y, size, target)) {
                result[target + 1] += 1;
                return;
            }
        }

        size /= 3;
        int[] diff = {0, size, size * 2};

        for (int diffX: diff) {
            for (int diffY: diff) {
                solve(x + diffX, y + diffY, size);
            }
        }
    }

    private static boolean isSame(int x, int y, int size, int target) {
        for (int row = y; row < y + size; row++) {
            for (int col = x; col < x + size; col++) {
                if (grid[row][col] != target) {
                    return false;
                }
            }
        }
        return true;
    }
}
