package back_tracking;

// https://www.acmicpc.net/problem/26170

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_26170 {

    private static final int ROW_SIZE = 5;

    private static final int COL_SIZE = 5;

    private static final int WALL = -1;

    private static final int APPLE = 1;

    private static final int IMPOSSIBLE = -1;

    private static final int TARGET = 3;

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] grid = new int[ROW_SIZE][COL_SIZE];

        for (int row = 0; row < ROW_SIZE; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < COL_SIZE; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());

        int time = bfs(grid, startRow, startCol);
        System.out.println(time);
    }

    private static class Node {
        int row;
        int col;
        int appleCount;

        int[][] grid;

        Node (int row, int col, int appleCount, int[][] grid) {
            this.row = row;
            this.col = col;
            this.appleCount = appleCount;
            this.grid = grid;
        }
    }

    private static int bfs(int[][] grid, int startRow, int startCol) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startRow, startCol, 0, grid));

        int time = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time += 1;
            for (int count = 0; count < size; count++) {
                Node cur = queue.poll();

                if (cur.appleCount == TARGET) {
                    return time;
                }

                int[][] nextGrid = copy(cur.grid);
                nextGrid[cur.row][cur.col] = WALL;

                for (int[] dir: DIR) {
                    int nextRow = cur.row + dir[0];
                    int nextCol = cur.col + dir[1];

                    if (nextRow < 0 || nextRow == ROW_SIZE || nextCol < 0 || nextCol == COL_SIZE) {
                        continue;
                    }
                    if (nextGrid[nextRow][nextCol] == WALL) {
                        continue;
                    }
                    if (grid[nextRow][nextCol] == APPLE) {
                        queue.offer(new Node(nextRow, nextCol, cur.appleCount + 1, nextGrid));
                    }
                    else {
                        queue.offer(new Node(nextRow, nextCol, cur.appleCount, nextGrid));
                    }
                }
            }
        }

        return IMPOSSIBLE;
    }

    private static int[][] copy(int[][] grid) {
        int[][] newGrid = new int[ROW_SIZE][COL_SIZE];

        for (int row = 0; row < ROW_SIZE; row++) {
            for (int col = 0; col < COL_SIZE; col++) {
                newGrid[row][col] = grid[row][col];
            }
        }

        return newGrid;
    }
}
