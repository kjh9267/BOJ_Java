package dijkstra;

// https://www.acmicpc.net/problem/11909

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11909 {

    private static final int START_X = 0;

    private static final int START_Y = 0;

    private static final int INF = Integer.MAX_VALUE;

    private static final int[][] DIR = {{1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] grid = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int result = dijkstra(grid, N);
        System.out.println(result);
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    private static int dijkstra(int[][] grid, int N) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(START_X, START_Y, 0));

        int[][] dist = new int[N][N];
        for (int row = 0; row < N; row++) {
            Arrays.fill(dist[row], INF);
        }
        dist[START_Y][START_X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int[] dir: DIR) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (nextX < 0 || nextX == N || nextY < 0 || nextY == N) {
                    continue;
                }
                int cost = Math.max(0, grid[nextY][nextX] - grid[cur.y][cur.x] + 1);

                if (dist[nextY][nextX] <= dist[cur.y][cur.x] + cost) {
                    continue;
                }
                dist[nextY][nextX] = dist[cur.y][cur.x] + cost;
                pq.offer(new Node(nextX, nextY, dist[nextY][nextX]));
            }
        }
        return dist[N - 1][N - 1];
    }
}
