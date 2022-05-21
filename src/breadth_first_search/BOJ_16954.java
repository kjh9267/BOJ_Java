package breadth_first_search;

// https://www.acmicpc.net/problem/16954

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16954 {

    private static final int N = 8;

    private static final char WALL = '#';

    private static final char EMPTY = '.';

    private static final int START_ROW = 7;

    private static final int START_COL = 0;

    private static final int TARGET_ROW = 0;

    private static final int TARGET_COL = 7;

    private static final int POSSIBLE = 1;

    private static final int IMPOSSIBLE = 0;

    private static final int[][] DIR = {{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] grid = new char[N][N];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        int result = bfs(grid);

        System.out.println(result);
    }

    private static int bfs(char[][] grid) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(START_ROW, START_COL, grid));

        boolean[][][] visited = new boolean[N][N][N];

        for (int fall = 0; fall < N; fall++) {
            int length = queue.size();

            for (int count = 0; count < length; count++) {
                Node cur = queue.poll();

                for (int[] dir : DIR) {
                    int nextRow = cur.row + dir[0];
                    int nextCol = cur.col + dir[1];

                    if (nextRow == TARGET_ROW && nextCol == TARGET_COL) {
                        return POSSIBLE;
                    }
                    if (nextCol < 0 || nextCol == N || nextRow < 0 || nextRow == N) {
                        continue;
                    }
                    if (visited[nextRow][nextCol][fall]) {
                        continue;
                    }
                    Node nextNode = new Node(nextRow, nextCol, cur.grid);
                    if (!nextNode.canMove()) {
                        continue;
                    }
                    visited[nextRow][nextCol][fall] = true;
                    queue.offer(nextNode);
                }
            }
        }
        return queue.isEmpty() ? IMPOSSIBLE: POSSIBLE;
    }

    private static class Node {
        int row;
        int col;
        char[][] grid;

        Node(int row, int col, char[][] grid) {
            this.row = row;
            this.col = col;
            this.grid = copyGrid(grid);
        }

        public boolean canMove() {
            if (grid[row][col] == WALL) {
                return false;
            }

            for (int col = 0; col < N; col++) {
                if (grid[N - 1][col] == WALL) {
                    grid[N - 1][col] = EMPTY;
                }
            }

            for (int row = N - 2; row >= 0; row--) {
                for (int col = 0; col < N; col++) {
                    if (grid[row][col] != WALL) {
                        continue;
                    }
                    if (!canFallWall(row, col)) {
                        return false;
                    }
                    fallWall(row, col);
                }
            }
            return true;
        }

        private boolean canFallWall(int row, int col) {
            if (row + 1 == this.row && col == this.col) {
                return false;
            }
            return true;
        }

        private void fallWall(int row, int col) {
            grid[row + 1][col] = WALL;
            grid[row][col] = EMPTY;
        }

        private char[][] copyGrid(char[][] grid) {
            char[][] newGrid = new char[N][N];

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    newGrid[row][col] = grid[row][col];
                }
            }

            return newGrid;
        }
    }
}
