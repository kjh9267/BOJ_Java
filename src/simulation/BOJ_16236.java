package simulation;

// https://www.acmicpc.net/problem/16236

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16236 {

    private static final int EMPTY = 0;

    private static final int SHARK = 9;

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int N;

    private static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        Shark shark = null;

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                int value = Integer.parseInt(st.nextToken());
                grid[row][col] = value;

                if (value == SHARK) {
                    shark = new Shark(row, col, 2, 0, 0);
                }
            }
        }

        int eatTime = findEatTime(shark);

        System.out.println(eatTime);
    }

    private static class Shark implements Comparable<Shark> {
        int row;
        int col;
        int size;
        int eatCount;
        int dist;

        Shark(int row, int col, int size, int eatCount, int dist) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.eatCount = eatCount;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shark other) {
            if (this.dist == other.dist) {
                if (this.row == other.row) {
                    return Integer.compare(this.col, other.col);
                }
                return Integer.compare(this.row, other.row);
            }
            return Integer.compare(this.dist, other.dist);
        }
    }

    private static int findEatTime(Shark shark) {
        int eatTime = 0;

        while (true) {
            grid[shark.row][shark.col] = EMPTY;
            shark = findNextLocation(shark);
            grid[shark.row][shark.col] = EMPTY;
            eatTime += shark.dist;

            if (shark.dist == 0) {
                break;
            }
        }

        return eatTime;
    }

    private static Shark findNextLocation(Shark shark) {
        shark = new Shark(shark.row, shark.col, shark.size, shark.eatCount, 0);

        Queue<Shark> queue = new LinkedList<>();
        queue.offer(shark);

        boolean[][] visited = new boolean[N][N];
        visited[shark.row][shark.col] = true;

        List<Shark> nextLocations = new ArrayList<>();

        int dist = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            dist += 1;

            for (int loop = 0; loop < length; loop++) {
                Shark cur = queue.poll();

                for (int[] dir : DIR) {
                    int nextRow = cur.row + dir[0];
                    int nextCol = cur.col + dir[1];

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == N) {
                        continue;
                    }
                    int nextValue = grid[nextRow][nextCol];
                    if (cur.size < nextValue) {
                        continue;
                    }
                    if (visited[nextRow][nextCol]) {
                        continue;
                    }
                    visited[nextRow][nextCol] = true;
                    int nextEatCount = cur.eatCount;
                    int nextSize = cur.size;

                    if (canEat(cur, nextValue)) {
                        nextEatCount = cur.eatCount + 1;
                    }
                    if (nextEatCount == cur.size) {
                        nextSize = cur.size + 1;
                        nextEatCount = 0;
                    }
                    if (canEat(cur, nextValue)) {
                        nextLocations.add(new Shark(nextRow, nextCol, nextSize, nextEatCount, dist));
                    }

                    queue.offer(new Shark(nextRow, nextCol, nextSize, nextEatCount, dist));
                }
            }
        }

        Collections.sort(nextLocations);

        if (nextLocations.isEmpty()) {
            return shark;
        }

        return nextLocations.get(0);
    }

    private static boolean canEat(Shark cur, int nextValue) {
        return nextValue != EMPTY && nextValue < cur.size;
    }
}
