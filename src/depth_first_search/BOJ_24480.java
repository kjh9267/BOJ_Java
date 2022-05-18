package depth_first_search;

// https://www.acmicpc.net/problem/24480

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_24480 {

    private static final int NOT_VISITED = 0;

    private static final String NEW_LINE = "\n";

    private static int count;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        for (int node = 1; node <= N; node++) {
            Collections.sort(graph[node], Collections.reverseOrder());
        }

        int[] visited = new int[N + 1];

        dfs(start, graph, visited);

        for (int node = 1; node <= N; node++) {
            int count = visited[node];

            sb.append(count)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void dfs(int cur, List<Integer>[] graph, int[] visited) {
        if (visited[cur] != NOT_VISITED) {
            return;
        }

        count += 1;
        visited[cur] = count;

        for (int next: graph[cur]) {
            dfs(next, graph, visited);
        }
    }
}
