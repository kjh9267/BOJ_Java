package bipartite_matching;

// https://www.acmicpc.net/problem/11014

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11014 {

    private static final char WALL = 'x';

    private static final String NEW_LINE = "\n";

    private static final int NOT_VISITED = -1;

    private static final int[][] DIR = {{-1, -1}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 1}};

    private static int N;

    private static int M;

    private static int size;

    private static char[][] grid;

    private static List<Integer>[] graph;

    private static int[] A;

    private static int[] B;

    private static boolean[] visisted;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        for (int test = 0; test < C; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            grid = new char[N][M];

            for (int row = 0; row < N; row++) {
                grid[row] = br.readLine()
                        .toCharArray();
            }

            init();
            int total = bipartiteMatch();

            sb.append(countEmpty() - total)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init() {
        size = N * M;
        graph = new List[size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col += 2) {
                if (grid[row][col] == WALL) {
                    continue;
                }

                for (int[] dir: DIR) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M) {
                        continue;
                    }
                    if (grid[nextRow][nextCol] == WALL) {
                        continue;
                    }
                    graph[toNode(row, col)].add(toNode(nextRow, nextCol));
                }
            }
        }
    }

    private static int toNode(int row, int col) {
        return M * row + col;
    }

    private static int bipartiteMatch() {
        int total = 0;
        A = new int[size];
        B = new int[size];

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (col % 2 == 1) {
                    continue;
                }
                visisted = new boolean[size];
                if (dfs(toNode(row, col))) {
                    total += 1;
                }
            }
        }

        return total;
    }

    private static boolean dfs(int a) {
        if (visisted[a]) {
            return false;
        }
        visisted[a] = true;

        for (int b: graph[a]) {
            if (B[b] == NOT_VISITED || dfs(B[b])) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }

    private static int countEmpty() {
        int count = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] != WALL) {
                    count += 1;
                }
            }
        }

        return count;
    }
}
