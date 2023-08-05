package breadth_first_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11967 {

    private static final int START_X = 1;

    private static final int START_Y = 1;

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] grid = new boolean[N + 1][N + 1];
        grid[START_Y][START_X] = true;

        List<Point>[][] switches = new List[N + 1][N + 1];

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                switches[row][col] = new ArrayList<>();
            }
        }

        for (int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            switches[startY][startX].add(new Point(endX, endY));
        }

        boolean[][] light = bfs(grid, N, M, switches);

        int lightCount = countLight(N, M, light);

        System.out.println(lightCount);
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static boolean[][] bfs(boolean[][] grid, int N, int M, List<Point>[][] switches) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(START_X, START_Y));

        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[START_Y][START_X] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            turnOnLight(grid, switches, queue, point, visited, N, M);

            for (int[] dir: DIR) {
                int nextX = point.x + dir[0];
                int nextY = point.y + dir[1];

                if (!canGo(nextX, M, nextY, N, grid)) {
                    continue;
                }
                if (visited[nextY][nextX]) {
                    continue;
                }
                visited[nextY][nextX] = true;
                queue.offer(new Point(nextX, nextY));
            }
        }

        return grid;
    }

    private static boolean isConnected(boolean[][] grid, int N, int M, Point point) {
        if (point.x == START_X && point.y == START_Y) {
            return true;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);

        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[point.y][point.x] = true;

        while (!queue.isEmpty()) {
            Point curPoint = queue.poll();
            for (int[] dir: DIR) {
                int nextX = curPoint.x + dir[0];
                int nextY = curPoint.y + dir[1];

                if (!canGo(nextX, M, nextY, N, grid)) {
                    continue;
                }
                if (!grid[nextY][nextX]) {
                    continue;
                }
                if (visited[nextY][nextX]) {
                    continue;
                }
                visited[nextY][nextX] = true;
                queue.offer(new Point(nextX, nextY));
            }
        }

        return visited[START_Y][START_X];
    }

    private static boolean canGo(int nextX, int M, int nextY, int N, boolean[][] grid) {
        if (nextX < 1 || nextX > N || nextY < 1 || nextY > N) {
            return false;
        }

        if (!grid[nextY][nextX]) {
            return false;
        }

        return true;
    }

    private static void turnOnLight(boolean[][] grid, List<Point>[][] switches, Queue<Point> queue, Point point, boolean[][] visited, int N, int M) {

        for (Point nextPoint: switches[point.y][point.x]) {
            grid[nextPoint.y][nextPoint.x] = true;
            boolean canReach = isConnected(grid, N, M, nextPoint);

            if (visited[nextPoint.y][nextPoint.x]) {
                continue;
            }
            if (!canReach) {
                continue;
            }

            queue.offer(nextPoint);
        }
    }

    private static int countLight(int N, int M, boolean[][] light) {
        int lightCount = 0;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (light[row][col]) {
                    lightCount += 1;
                }
            }
        }

        return lightCount;
    }
}
