package breadth_first_search;

// https://www.acmicpc.net/problem/2206

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    private static final int[][] DIR = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] grid = new char[N][M];

        for (int row = 0; row < N; row++) {
            grid[row] = br.readLine().toCharArray();
        }

        int[] res = bfs(N, M, grid);
        int max = Math.max(res[0], res[1]);
        int min = Math.min(res[0], res[1]);
        if (min == 0) {
            System.out.println(max == 0 ? -1 : max);
        } else {
            System.out.println(min);
        }
    }

    public static int[] bfs(int N, int M, char[][] grid) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, 0));

        int[][][] visited = new int[N][M][2];
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] dir: DIR) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];
                if (nextX < 0 || nextX == M || nextY < 0 || nextY == N)
                    continue;
                char nextValue = grid[nextY][nextX];
                int[] nextVisited = visited[nextY][nextX];

                if (nextValue == '0' && nextVisited[cur.wall] == 0) {
                    queue.offer(new Node(nextX, nextY, cur.wall));
                    visited[nextY][nextX][cur.wall] = visited[cur.y][cur.x][cur.wall] + 1;
                } else if (nextValue == '1' && nextVisited[1] == 0 && cur.wall == 0) {
                    queue.offer(new Node(nextX, nextY, cur.wall + 1));
                    visited[nextY][nextX][cur.wall + 1] = visited[cur.y][cur.x][cur.wall] + 1;
                }
            }
        }
        return visited[N - 1][M - 1];
    }

    public static class Node {
        int x;
        int y;
        int wall;

        public Node (int x, int y, int wall) {
            this.x = x;
            this.y = y;
            this.wall = wall;
        }
    }
}
