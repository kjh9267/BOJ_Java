package dynamic_programming;

// https://www.acmicpc.net/problem/1311

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1311 {

    private static final int MAX = 200_001;

    private static int N;

    private static int[][] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];

        for (int person = 0; person < N; person++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int work = 0; work < N; work++) {
                costs[person][work] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] visited = new boolean[N];

        int worksLength = (int) Math.pow(2, N);

        int[][] dp = new int[N][worksLength];

        for (int person = 0; person < N; person++) {
            Arrays.fill(dp[person], MAX);
        }

        int result = dfs(0, 0, dp, visited);

        System.out.println(result);
    }

    private static int dfs(int person, int works, int[][] dp, boolean[] visited) {
        if (person == N) {
            return 0;
        }
        if (dp[person][works] != MAX) {
            return dp[person][works];
        }

        for (int nextWork = 0; nextWork < N; nextWork++) {
            if (visited[nextWork]) {
                continue;
            }
            visited[nextWork] = true;
            dp[person][works] = Math.min(dp[person][works], dfs(person + 1, works | (1 << nextWork) , dp, visited) + costs[person][nextWork]);
            visited[nextWork] = false;
        }

        return dp[person][works];
    }
}
