package bipartite_matching;

// https://www.acmicpc.net/problem/12427

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12427 {

    private static final char WALL = '#';

    private static final int NOT_VISITED = -1;

    private static final String NEW_LINE = "\n";

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static final int[] Z_DIR = {-1, 1};

    private static int K;

    private static int N;

    private static int M;

    private static int node;

    private static int size;

    private static char[][][] grid;

    private static int[][][] nodes;

    private static boolean[][] graph;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            node = 0;
            grid = new char[K][N][M];
            nodes = new int[K][N][M];

            for (int z = 0; z < K; z++) {
                for (int y = 0; y < N; y++) {
                    grid[z][y] = br.readLine()
                            .toCharArray();

                    Arrays.fill(nodes[z][y], NOT_VISITED);
                }
            }

            bfsAll();
            init();
            int total = bipartiteMatch();

            sb.append(String.format("Case #%d: %d", test + 1, node - total))
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static class Node {
        int x;
        int y;

        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfsAll() {
        for (int z = 0; z < K; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (grid[z][y][x] == WALL) {
                        continue;
                    }
                    if (nodes[z][y][x] != NOT_VISITED) {
                        continue;
                    }
                    bfs(x, y, z);
                    node += 1;
                }
            }
        }
    }

    private static void bfs(int x, int y, int z) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        nodes[z][y][x] = node;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] dir: DIR) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (nextY < 0 || nextY == N || nextX < 0 || nextX == M) {
                    continue;
                }
                if (grid[z][nextY][nextX] == WALL) {
                    continue;
                }
                if (nodes[z][nextY][nextX] != NOT_VISITED) {
                    continue;
                }
                nodes[z][nextY][nextX] = node;
                queue.offer(new Node(nextX, nextY));
            }
        }
    }

    private static void init() {
        size = node;
        graph = new boolean[size][size];

        for (int z = 0; z < K; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (grid[z][y][x] == WALL) {
                        continue;
                    }
                    for (int diff: Z_DIR) {
                        int nextZ = z + diff;

                        if (nextZ < 0 || nextZ == K) {
                            continue;
                        }
                        if (grid[nextZ][y][x] == WALL) {
                            continue;
                        }

                        int curNode = nodes[z][y][x];
                        int nextNode = nodes[nextZ][y][x];

                        if (z % 2 == 0) {
                            graph[curNode][nextNode] = true;
                        }
                        else {
                            graph[nextNode][curNode] = true;
                        }
                    }
                }
            }
        }
    }

    private static int bipartiteMatch() {
        int total = 0;
        A = new int[size];
        B = new int[size];

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int a = 0; a < size; a++) {
            visited = new boolean[size];

            if (dfs(a)) {
                total += 1;
            }
        }

        return total;
    }

    private static boolean dfs(int a) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b = 0; b < size; b++) {
            if (!graph[a][b]) {
                continue;
            }
            if (B[b] == NOT_VISITED || dfs(B[b])) {
                A[a] = b;
                B[b] = a;

                return true;
            }
        }

        return false;
    }
}
