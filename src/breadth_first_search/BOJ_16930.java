package breadth_first_search;

// https://www.acmicpc.net/problem/16930

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16930 {

    private static final int IMPOSSIBLE = -1;

    private static final char WALL = '#';

    private static final int DIR_SIZE = 4;

    private static final int INF = 1_000_001;

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static int endX;

    private static int endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[][] grid = new char[N][M];

        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            for (int col = 0; col < M; col++) {
                grid[row][col] = line.charAt(col);
            }
        }

        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;
        endY = Integer.parseInt(st.nextToken()) - 1;
        endX = Integer.parseInt(st.nextToken()) - 1;

        if (startX == endX && startY == endY) {
            System.out.println(0);
        }
        else {
            int result = bfs(grid, N, M, K, startX, startY);
            System.out.println(result);
        }
    }

    private static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private boolean isEnd() {
            return x == endX && y == endY;
        }
    }

    private static int bfs(char[][] grid, int N, int M, int K, int startX, int startY) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY));

        int[][] visited = new int[N][M];

        for (int row = 0; row < N; row++) {
            Arrays.fill(visited[row], INF);
        }

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time += 1;
            for (int count = 0; count < size; count++) {
                Node cur = queue.poll();
                boolean[] impossibleDirections = new boolean[DIR_SIZE];

                for (int direction = 0; direction < DIR_SIZE; direction++) {
                    for (int jump = 1; jump <= K; jump++) {

                        if (impossibleDirections[direction]) {
                            break;
                        }
                        int nextX = cur.x + DIR[direction][0] * jump;
                        int nextY = cur.y + DIR[direction][1] * jump;

                        if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) {
                            impossibleDirections[direction] = true;
                            continue;
                        }
                        if (grid[nextY][nextX] == WALL) {
                            impossibleDirections[direction] = true;
                            continue;
                        }
                        if (visited[nextY][nextX] < time) {
                            break;
                        }
                        Node nextNode = new Node(nextX, nextY);
                        if (visited[nextY][nextX] != time) {
                            queue.offer(nextNode);
                        }
                        visited[nextY][nextX] = time;

                        if (nextNode.isEnd()) {
                            return time;
                        }
                    }
                }
            }
        }

        return IMPOSSIBLE;
    }
}
