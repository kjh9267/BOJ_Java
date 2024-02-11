package dijkstra;

// https://www.acmicpc.net/problem/13219

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13219 {

    private static final long INF = 160_000_000_000L;

    private static final int WALL = -1;

    private static final int IMPOSSIBLE = -1;

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static int[][] grid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken()) - 1;
        int startY = Integer.parseInt(st.nextToken()) - 1;
        int endX = Integer.parseInt(st.nextToken()) - 1;
        int endY = Integer.parseInt(st.nextToken()) - 1;
        grid = new int[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        long result = dijkstra(startX, startY, endX, endY, N);

        if (grid[startY][startX] == WALL || grid[endY][endX] == WALL) {
            System.out.println(IMPOSSIBLE);
        }
        else if (result == INF) {
            System.out.println(IMPOSSIBLE);
        }
        else {
            System.out.println(result);
        }
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        long cost;

        Node(int x, int y, long cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    private static long dijkstra(int startX, int startY, int endX, int endY, int N) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startX, startY, grid[startY][startX]));

        long[][] dist = new long[N][N];
        for (int row = 0; row < N; row++) {
            Arrays.fill(dist[row], INF);
        }
        dist[startY][startX] = grid[startY][startX];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int[] dir: DIR) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (nextX < 0 || nextX == N || nextY < 0 || nextY == N) {
                    continue;
                }
                if (grid[nextY][nextX] == WALL) {
                    continue;
                }
                if (dist[nextY][nextX] <= dist[cur.y][cur.x] + grid[nextY][nextX]) {
                    continue;
                }
                dist[nextY][nextX] = dist[cur.y][cur.x] + grid[nextY][nextX];
                pq.offer(new Node(nextX, nextY, dist[nextY][nextX]));
            }
        }

        return dist[endY][endX];
    }
}
