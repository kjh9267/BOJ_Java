package implementation;

// https://www.acmicpc.net/problem/1913

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1913 {

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static final String SPACE = " ";

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int direction = 0;

        Point point = initPoint(N);

        int[][] graph = new int[N][N];
        graph[point.y][point.x] = 1;

        for (int threshold = 1; threshold < N; threshold++) {
            point.move(threshold, direction, graph);
            direction = changeDirection(direction);
            point.move(threshold, direction, graph);
            direction = changeDirection(direction);

            if (threshold == N - 1) {
                point.move(threshold, direction, graph);
            }
        }

        System.out.println(getResult(N, target, graph));
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private void move(int threshold, int direction, int[][] graph) {
            for (int count = 0; count < threshold; count++) {
                int nextX = x + DIR[direction][0];
                int nextY = y + DIR[direction][1];
                graph[nextY][nextX] = graph[y][x] + 1;
                x = nextX;
                y = nextY;
            }
        }
    }

    private static Point initPoint(int N) {
        int x;
        int y;

        if (N % 2 == 0) {
            x = N / 2 - 1;
            y = N / 2;
        } else {
            x = N / 2;
            y = N / 2;
        }

        return new Point(x, y);
    }

    private static int changeDirection(int direction) {
        if (++direction == 4) {
            direction = 0;
        }
        return direction;
    }

    private static StringBuilder getResult(int N, int target, int[][] graph) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append(graph[row][col])
                        .append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (graph[row][col] == target) {
                    sb.append(row + 1)
                            .append(SPACE)
                            .append(col + 1);
                }
            }
        }

        return sb;
    }
}
