package breadth_first_search;

// https://www.acmicpc.net/problem/7576

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {

    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int EMPTY = -1;
    private static final int TOMATO = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];

        for(int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for(int col = 0; col < M; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(grid, N, M));
    }

    private static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int bfs(int[][] grid, int N, int M) {

        int res = 0;
        Queue<Node> queue = new LinkedList<>();
        int[][] visited = new int[N][M];

        for(int row = 0; row < N; row++) {
            Arrays.fill(visited[row], -1);
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(grid[row][col] == TOMATO) {
                    queue.offer(new Node(row, col));
                    visited[row][col] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for(int[] dir: DIR) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                    continue;
                }
                if(grid[nextRow][nextCol] == EMPTY) {
                    continue;
                }
                if(visited[nextRow][nextCol] != -1) {
                    continue;
                }

                visited[nextRow][nextCol] = visited[cur.row][cur.col] + 1;
                queue.offer(new Node(nextRow, nextCol));

            }
        }

        for(int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                res = Math.max(res, visited[row][col]);
                if(visited[row][col] == -1 && grid[row][col] != EMPTY)
                    return -1;
            }
        }

        return res;
    }
}
