package simulation;

// https://www.acmicpc.net/problem/18405

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18405 {

    private static final char EMPTY = 0;

    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][N];

        Queue<Point>[] points = new Queue[K + 1];

        for (int virus = 1; virus <= K; virus++) {
            points[virus] = new LinkedList<>();
        }

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int value = Integer.parseInt(st.nextToken());
                grid[row][col] = value;

                if (value == EMPTY) {
                    continue;
                }

                points[value].offer(new Point(row, col));
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int targetRow = Integer.parseInt(st.nextToken()) - 1;
        int targetCol = Integer.parseInt(st.nextToken()) - 1;

        simulate(grid, points, N, K, S);

        System.out.println(grid[targetRow][targetCol]);
    }

    private static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static void simulate(int[][] grid, Queue<Point>[] points, int N, int K, int S) {
        int limit = Math.min(S, (int) Math.sqrt(Math.pow(N, 2) + Math.pow(N, 2)));

        for (int time = 1; time <= limit; time++) {
            moveAll(grid, points, N, K);
        }
    }

    private static void moveAll(int[][] grid, Queue<Point>[] points, int N, int K) {
        for (int virus = 1; virus <= K; virus++) {
            int length = points[virus].size();

            for (int count = 0; count < length; count++) {
                Queue<Point> queue = points[virus];
                Point point = queue.poll();
                move(grid, N, virus, point, queue);
            }
        }
    }

    private static void move(int[][] grid, int N, int virus, Point point, Queue<Point> queue) {
        for (int[] dir: DIR) {
            int nextRow = point.row + dir[0];
            int nextCol = point.col + dir[1];

            if (nextCol < 0 || nextCol == N || nextRow < 0 || nextRow == N) {
                continue;
            }
            if (grid[nextRow][nextCol] != EMPTY) {
                continue;
            }
            grid[nextRow][nextCol] = virus;
            queue.offer(new Point(nextRow, nextCol));
        }
    }
}
