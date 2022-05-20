package binary_search;

// https://www.acmicpc.net/problem/2665

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2665 {

    private static final char WALL = '0';

    private static final int MAX = 2_500;

    private static final int START_ROW = 0;

    private static final int START_COL = 0;

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static char[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        grid = new char[N][N];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        int minCount = binarySearch(N);

        System.out.println(minCount);
    }

    private static int binarySearch(int N) {
        if (N == 1) {
            return 0;
        }

        int lo = -1;
        int hi = MAX + 1;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            if (isPossible(N, mid)) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return hi;
    }

    private static boolean isPossible(int N, int wallCount) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(START_ROW, START_COL, 0));

        boolean[][][] visited = new boolean[N][N][wallCount + 1];
        visited[START_ROW][START_COL][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] dir: DIR) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (nextCol < 0 || nextCol == N || nextRow < 0 || nextRow == N) {
                    continue;
                }
                if (nextRow == N - 1 && nextCol == N - 1) {
                    return true;
                }
                if (grid[nextRow][nextCol] == WALL) {
                    if (cur.wallCount + 1 <= wallCount) {
                        offerNextNode(cur, nextRow, nextCol, queue, visited, 1, wallCount);
                    }
                }
                else {
                    offerNextNode(cur, nextRow, nextCol, queue, visited, 0, wallCount);
                }
            }
        }
        return false;
    }

    private static void offerNextNode(Node cur, int row, int col, Queue<Node> queue, boolean[][][] visited, int count, int wallCount) {
        if (visited[row][col][cur.wallCount + count]) {
            return;
        }
        visited[row][col][cur.wallCount + count] = true;
        queue.offer(new Node(row, col, cur.wallCount + count));
    }

    private static class Node {
        int row;
        int col;
        int wallCount;

        Node(int row, int col, int wallCount) {
            this.row = row;
            this.col = col;
            this.wallCount = wallCount;
        }
    }
}
