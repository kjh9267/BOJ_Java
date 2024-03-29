package bipartite_matching;

// https://www.acmicpc.net/problem/12843

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_12843 {

    private static final char COMPUTER = 'c';

    private static List<Integer>[] graph;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[] data = new char[N + 1];
        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int index = 0; index < N; index++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            char department = st.nextToken()
                    .charAt(0);
            data[id] = department;
        }

        for (int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (data[start] == COMPUTER) {
                graph[start].add(end);
            }
            else {
                graph[end].add(start);
            }
        }

        int total = bipartiteMatch(N);

        System.out.println(N - total);
    }

    private static int bipartiteMatch(int N) {
        int total = 0;
        A = new int[N + 1];
        B = new int[N + 1];

        for (int node = 1; node <= N; node++) {
            visited = new boolean[N + 1];

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
            if (B[b] == 0 || dfs(B[b])) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }
}
