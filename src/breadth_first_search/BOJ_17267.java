package breadth_first_search;

// https://www.acmicpc.net/problem/17267

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17267 {

    private static final char WALL = '1';

    private static final char START = '2';

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        char[][] grid = new char[N][M];

        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            for (int col = 0; col < M; col++) {
                char value = line.charAt(col);
                grid[row][col] = value;
            }
        }

        int result = bfs(grid, N, M, L, R);
        System.out.println(result);
    }

    private static int bfs(char[][] grid, int N, int M, int L, int R) {
        Node start = findStart(grid, N, M, L, R);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);

        boolean[][] visited = new boolean[N][M];
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int direction = 0; direction < 4; direction++) {
                int nextX = cur.x + DIR[direction][0];
                int nextY = cur.y + DIR[direction][1];
                if (cur.left == 0 && direction == 3) {
                    continue;
                }
                if (cur.right == 0 && direction == 1) {
                    continue;
                }
                if (!offerNextNode(direction, nextX, nextY, grid, N, M, queue, visited, cur)) {
                    continue;
                }
                if (direction == 1 || direction == 3) {
                    continue;
                }
                while (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N) {
                    nextX += DIR[direction][0];
                    nextY += DIR[direction][1];
                    if (!offerNextNode(direction, nextX, nextY, grid, N, M, queue, visited, cur)) {
                        break;
                    }
                }
            }
        }

        return countArea(visited, N, M);
    }

    private static boolean offerNextNode(int direction, int nextX, int nextY, char[][] grid, int N, int M, Queue<Node> queue, boolean[][] visited, Node cur) {
        if (nextX < 0 || nextX == M || nextY < 0 || nextY == N) {
            return false;
        }
        if (grid[nextY][nextX] == WALL) {
            return false;
        }
        if (visited[nextY][nextX]) {
            return false;
        }
        visited[nextY][nextX] = true;

        if (direction == 1) {
            queue.offer(new Node(nextX, nextY, cur.left, cur.right - 1));
        }
        else if (direction == 3) {
            queue.offer(new Node(nextX, nextY, cur.left - 1, cur.right));
        }
        else {
            queue.offer(new Node(nextX, nextY, cur.left, cur.right));
        }

        return true;
    }

    private static class Node {
        int x;
        int y;
        int left;
        int right;

        Node (int x, int y, int left, int right) {
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }
    }

    private static Node findStart(char[][] grid, int N, int M, int L, int R) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == START) {
                    return new Node(col, row, L, R);
                }
            }
        }

        return null;
    }

    private static int countArea(boolean[][] visited, int N, int M) {
        int count = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (visited[row][col]) {
                    count += 1;
                }
            }
        }

        return count;
    }
}
