package bipartite_matching;

// https://www.acmicpc.net/problem/17238

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17238 {

    private static final long INF = 10_000_000_000L;

    private static final int NOT_VISITED = -1;

    private static int N;

    private static int[] pineapples;

    private static int[] breads;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pineapples = new int[N];
        breads = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            pineapples[index] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int index = 0; index < N; index++) {
            breads[index] = Integer.parseInt(st.nextToken());
        }

        long result = binarySearch();

        System.out.println(result);
    }

    private static long binarySearch() {
        long lo = 0;
        long hi = INF;

        while (lo + 1 < hi) {
            long mid = (lo + hi) >> 1;

            if (bipartiteMatch(mid)) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }

        return lo;
    }

    private static boolean bipartiteMatch(long limit) {
        A = new int[N];
        B = new int[N];

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int a = 0; a < N; a++) {
            visited = new boolean[N];
            dfs(a, limit);
        }

        for (int a = 0; a < N; a++) {
            if (A[a] == NOT_VISITED) {
                return false;
            }
        }

        return true;
    }

    private static boolean dfs(int a, long limit) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b = 0; b < N; b++) {
            if ((pineapples[a] ^ breads[b]) < limit) {
                continue;
            }
            if (B[b] == NOT_VISITED || dfs(B[b], limit)) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }
}
