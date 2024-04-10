package bipartite_matching;

// https://www.acmicpc.net/problem/5780

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_5780 {

    private static final int NOT_VISITED = -1;

    private static final String NEW_LINE = "\n";

    private static final int[][] DIR = {{0, 1}, {1, 0}};

    private static int N;

    private static int M;

    private static int size;

    private static List<Integer>[] graph;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            int K = Integer.parseInt(br.readLine());
            boolean[][] grid = new boolean[N][M];

            for (int count = 0; count < K; count++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                grid[y][x] = true;
            }

            init(grid);
            int result = bipartiteMatch();
            sb.append(result)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init(boolean[][] grid) {
        size = N * M;
        graph = new List[size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col]) {
                    continue;
                }
                int node = toNode(row, col);

                for (int[] dir: DIR) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M) {
                        continue;
                    }
                    if (grid[nextRow][nextCol]) {
                        continue;
                    }
                    int nextNode = toNode(nextRow, nextCol);
                    if ((row + col) % 2 == 0) {
                        graph[node].add(nextNode);
                    }
                    else {
                        graph[nextNode].add(node);
                    }
                }
            }
        }
    }

    private static int toNode(int row, int col) {
        return row * M + col;
    }

    private static int bipartiteMatch() {
        int total = 0;
        A = new int[size];
        B = new int[size];

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int node = 0; node < size; node++) {
            visited = new boolean[size];

            if (dfs(node)) {
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

        for (int b: graph[a]) {
            if (B[b] == NOT_VISITED || dfs(B[b])) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }
}
