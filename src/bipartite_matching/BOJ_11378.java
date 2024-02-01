package bipartite_matching;

// https://www.acmicpc.net/problem/11378

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11378 {

    private static List<Integer>[] graph;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int person = 1; person <= N; person++) {
            st = new StringTokenizer(br.readLine());
            int workCount = Integer.parseInt(st.nextToken());
            
            for (int count = 0; count < workCount; count++) {
                int work = Integer.parseInt(st.nextToken());
                graph[person].add(work);
            }
        }

        int result = bipartiteMatch(N, M, K);
        System.out.println(result);
    }

    private static int bipartiteMatch(int N, int M, int K) {
        A = new int[N + 1];
        B = new int[M + 1];
        int total = 0;

        for (int person = 1; person <= N; person++) {
            visited = new boolean[N + 1];

            if (dfs(person)) {
                total += 1;
            }
        }

        for (int person = 1; person <= N; person++) {
            while (K > 0) {
                visited = new boolean[N + 1];

                if (dfs(person)) {
                    total += 1;
                    K -= 1;
                }
                else {
                    break;
                }
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
            if (B[b] == 0 || dfs(B[b])) {
                A[a] = b;
                B[b] = a;

                return true;
            }
        }

        return false;
    }
}
