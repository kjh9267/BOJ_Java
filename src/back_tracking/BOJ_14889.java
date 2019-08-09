package back_tracking;

// https://www.acmicpc.net/problem/14889

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
    public static int N, res = 1_000_000_000, all;
    public static int[][] adj;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                adj[i][j] = value;
                all += value;
            }
        }
        dfs(0, 0);
        System.out.println(res);
    }

    public static void dfs(int cur, int depth) {
        if (depth == N / 2) {
            check();
            return;
        }
        for (int i = cur; i < N; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            dfs(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    public static void check() {
        int sumA = 0, sumB = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                for (int j = 0; j < N; j++) {
                    if (visited[j])
                        sumA += adj[i][j];
                }
            } else {
                for (int j = 0; j < N; j++) {
                    if (!visited[j])
                        sumB += adj[i][j];
                }
            }
        }
        res = Math.min(res, Math.abs(sumA - sumB));
    }
}
