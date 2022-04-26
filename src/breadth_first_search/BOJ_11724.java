package breadth_first_search;

// https://www.acmicpc.net/problem/11724

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11724 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int path = 0; path < M; path++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        int result = dfsAll(graph, N);

        System.out.println(result);
    }

    private static int dfsAll(List<Integer>[] graph, int N) {
        boolean[] visited = new boolean[N + 1];
        int count = 0;

        for (int node = 1; node <= N; node++) {
            if (visited[node]) {
                 continue;
            }
            dfs(node, visited, graph);
            count += 1;
        }

        return count;
    }

    private static void dfs(int cur, boolean[] visited, List<Integer>[] graph) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;

        for (int next: graph[cur]) {
            dfs(next, visited, graph);
        }
    }
}
