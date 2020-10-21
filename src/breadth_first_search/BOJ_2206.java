package breadth_first_search;

// https://www.acmicpc.net/problem/2206

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static final int EMPTY = 0;
    private static final int WALL = 1;

    public static void main (String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] grid = new int[N][M];

        for(int row = 0; row < N; row++) {
            String line = br.readLine();
            for(int col = 0; col < M; col++) {
                grid[row][col] = line.charAt(col) - '0';
            }
        }

        System.out.println(bfs(N, M, grid));

    }

    private static int bfs(int N, int M, int[][] grid) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0));

        int[][][] visited = new int[N][M][2];
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] dir: DIR) {
                int nextRow = cur.row + dir[0];
                int nextCol = cur.col + dir[1];

                if (nextCol < 0 || nextCol == M || nextRow < 0 || nextRow == N) {
                    continue;
                }

                int nextValue = grid[nextRow][nextCol];
                int[] nextVisited = visited[nextRow][nextCol];

                if (nextValue == EMPTY && nextVisited[cur.wall] == 0) {
                    queue.offer(new Node(nextRow, nextCol, cur.wall));
                    visited[nextRow][nextCol][cur.wall] = visited[cur.row][cur.col][cur.wall] + 1;
                } else if (nextValue == WALL && nextVisited[WALL] == 0 && cur.wall == 0) {
                    queue.offer(new Node(nextRow, nextCol, cur.wall + 1));
                    visited[nextRow][nextCol][cur.wall + 1] = visited[cur.row][cur.col][cur.wall] + 1;
                }
            }
        }

        int[] targetVisited = visited[N - 1][M - 1];
        int max = Math.max(targetVisited[0], targetVisited[1]);
        int min = Math.min(targetVisited[0], targetVisited[1]);

        if (min == 0) {
            return max == 0 ? -1 : max;
        } else {
            return min;
        }
    }

    private static class Node {
        int row;
        int col;
        int wall;

        public Node (int row, int col, int wall) {
            this.row = row;
            this.col = col;
            this.wall = wall;
        }
    }
}
