package breadth_first_search;

// https://www.acmicpc.net/problem/2583

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class BOJ_2583 {

    private static final int[][] DIR = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] grid = new boolean[N + 1][M + 1];

        fillGrid(br, N, K, grid);

        List<Integer> areaSize = bfsAll(grid, N, M);

        System.out.println(areaSize.size());

        String result = areaSize.stream()
                .map(String::valueOf)
                .collect(joining(SPACE));

        System.out.println(result);
    }

    private static void fillGrid(BufferedReader br, int N, int K, boolean[][] grid) throws IOException {
        StringTokenizer st;
        for (int point = 0; point < K; point++) {
            st = new StringTokenizer(br.readLine());

            int leftX = Integer.parseInt(st.nextToken());
            int downY = N - Integer.parseInt(st.nextToken());
            int rightX = Integer.parseInt(st.nextToken());
            int upY = N - Integer.parseInt(st.nextToken());

            for (int y = upY; y < downY; y++) {
                for (int x = leftX; x < rightX; x++) {
                    grid[y][x] = true;
                }
            }
        }
    }

    private static List<Integer> bfsAll(boolean[][] grid, int N, int M) {
        boolean[][] visited = new boolean[N + 1][M + 1];
        List<Integer> areaSize = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (visited[y][x] || grid[y][x]) {
                    continue;
                }
                int size = bfs(x, y, grid, visited, N, M);
                areaSize.add(size);
            }
        }

        Collections.sort(areaSize);

        return areaSize;
    }

    private static int bfs(int x, int y, boolean[][] grid, boolean[][] visited, int N, int M) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        visited[y][x] = true;

        int size = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] dir: DIR) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (nextX < 0 || nextX == M || nextY < 0 || nextY == N) {
                    continue;
                }
                if (grid[nextY][nextX]) {
                    continue;
                }
                if (visited[nextY][nextX]) {
                    continue;
                }
                visited[nextY][nextX] = true;
                queue.offer(new Node(nextX, nextY));
                size += 1;
            }
        }

        return size;
    }

    private static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
