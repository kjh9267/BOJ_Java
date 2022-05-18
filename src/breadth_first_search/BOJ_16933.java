package breadth_first_search;

// https://www.acmicpc.net/problem/16933

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16933 {

    private static final char WALL = '1';

    private static final int START_ROW = 0;

    private static final int START_COL = 0;

    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static char[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        grid = new char[N][M];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        int result = bfs(N, M, K);

        System.out.println(result);
    }

    private static int bfs(int N, int M, int K) {
        if (N == 1 && M == 1) {
            return 1;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(START_ROW, START_COL, 0, 0));

        boolean[][][][] visited = new boolean[N][M][2][K + 1];
        visited[START_ROW][START_COL][0][0] = true;

        int time = 1;
        while (!queue.isEmpty()) {
            int length = queue.size();
            time += 1;

            for (int count = 0; count < length; count++) {
                Node cur = queue.poll();

                for (int[] dir : DIR) {
                    int nextRow = cur.row + dir[0];
                    int nextCol = cur.col + dir[1];

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M) {
                        continue;
                    }

                    if (nextRow == N - 1 && nextCol == M - 1) {
                        return time;
                    }

                    move(K, queue, visited, cur, nextRow, nextCol);
                }
            }
        }

        return -1;
    }

    private static void move(int K, Queue<Node> queue, boolean[][][][] visited, Node cur, int nextRow, int nextCol) {
        if (grid[nextRow][nextCol] == WALL) {
            if (cur.night == 0) {
                if (cur.wallCount < K) {
                    offerNextNode(cur, queue, visited, nextRow, nextCol, 1);
                }
            }
            else {
                offerNextNode(cur, queue, visited);
            }
        }
        else {
            offerNextNode(cur, queue, visited, nextRow, nextCol, 0);
        }
    }

    private static void offerNextNode(Node cur, Queue<Node> queue, boolean[][][][] visited, int nextRow, int nextCol, int wallCount) {
        int nextNight = cur.night == 1 ? 0 : 1;

        if (visited[nextRow][nextCol][nextNight][cur.wallCount + wallCount]) {
            return;
        }

        visited[nextRow][nextCol][nextNight][cur.wallCount + wallCount] = true;
        queue.offer(new Node(nextRow, nextCol, nextNight, cur.wallCount + wallCount));
    }

    private static void offerNextNode(Node cur, Queue<Node> queue, boolean[][][][] visited) {
        int nextNight = cur.night == 1 ? 0 : 1;

        if (visited[cur.row][cur.col][nextNight][cur.wallCount]) {
            return;
        }

        visited[cur.row][cur.col][nextNight][cur.wallCount] = true;
        queue.offer(new Node(cur.row, cur.col, nextNight, cur.wallCount));
    }

    private static class Node {
        int row;
        int col;
        int night;
        int wallCount;

        Node(int row, int col, int night, int wallCount) {
            this.row = row;
            this.col = col;
            this.night = night;
            this.wallCount = wallCount;
        }
    }
}
