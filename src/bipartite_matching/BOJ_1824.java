package bipartite_matching;

// https://www.acmicpc.net/problem/1824

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.String.format;

public class BOJ_1824 {

    private static final String SPACE = " ";

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(br.readLine());
        Set<String> walls = new HashSet<>();

        for (int count = 0; count < L; count++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (x > y) {
                int temp = x;
                x = y;
                y = temp;
            }

            walls.add(format("%s %s", x, y));
        }

        init(walls);
        bipartiteMatch();

        for (int a = 1; a < size; a++) {
            if (A[a] == 0) {
                continue;
            }
            sb.append(a)
                    .append(SPACE)
                    .append(A[a])
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init(Set<String> walls) {
        size = N * M + 1;
        graph = new List[size];

        for (int node = 1; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                int node = toNode(row, col);

                for (int[] dir: DIR) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];

                    if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M) {
                        continue;
                    }
                    int nextNode = toNode(nextRow, nextCol);
                    if (walls.contains(format("%s %s", node, nextNode))) {
                        continue;
                    }
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
        return row * M + col + 1;
    }

    private static void bipartiteMatch() {
        A = new int[size];
        B = new int[size];

        for (int node = 1; node < size; node ++) {
            visited = new boolean[size];
            dfs(node);
        }
    }

    private static boolean dfs(int a) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b: graph[a]) {
            if (B[b] == 0 || dfs(B[b])) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }
}
