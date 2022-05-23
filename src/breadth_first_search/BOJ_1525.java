package breadth_first_search;

// https://www.acmicpc.net/problem/1525

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1525 {

    private static final String TARGET = "123456780";

    private static final int IMPOSSIBLE = -1;

    private static final int N = 3;

    private static final char ZERO = '0';

    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] grid = new char[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                grid[row][col] = st.nextToken()
                        .charAt(0);
            }
        }

        int time = bfs(grid);

        System.out.println(time);
    }

    private static int bfs(char[][] grid) {
        Queue<char[][]> queue = new LinkedList<>();
        queue.offer(grid);

        Set<String> visited = new HashSet<>();
        String gridSequence = toSequence(grid);
        visited.add(gridSequence);

        if (gridSequence.equals(TARGET)) {
            return 0;
        }

        int time = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            time += 1;

            for (int count = 0; count < length; count++ ) {
                char[][] curGrid = queue.poll();

                int[] rowCol = findZeroRowCol(curGrid);
                int row = rowCol[0];
                int col = rowCol[1];

                for (int[] dir: DIR) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    char[][] nextGrid = copyGrid(curGrid);

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == N) {
                        continue;
                    }
                    swap(row, col, nextRow, nextCol, nextGrid);
                    gridSequence = toSequence(nextGrid);

                    if (gridSequence.equals(TARGET)) {
                        return time;
                    }
                    if (visited.contains(gridSequence)) {
                        continue;
                    }
                    visited.add(toSequence(nextGrid));
                    queue.offer(nextGrid);
                }
            }
        }

        return IMPOSSIBLE;
    }

    private static String toSequence(char[][] grid) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append(grid[row][col]);
            }
        }

        return sb.toString();
    }

    private static int[] findZeroRowCol(char[][] grid) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (grid[row][col] == ZERO) {
                    return new int[] {row, col};
                }
            }
        }

        return new int[] {-1, -1};
    }

    private static char[][] copyGrid(char[][] grid) {
        char[][] newGrid = new char[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                newGrid[row][col] = grid[row][col];
            }
        }
        return newGrid;
    }

    private static void swap(int row, int col, int nextRow, int nextCol, char[][] grid) {
        char temp = grid[row][col];
        grid[row][col] = grid[nextRow][nextCol];
        grid[nextRow][nextCol] = temp;
    }
}
