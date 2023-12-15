package depth_first_search;

// https://www.acmicpc.net/problem/1260

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1260 {

    private static final String SPACE = " ";

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        List<Integer>[] graph = new List[N + 1];
        Queue<Integer> result = new LinkedList<>();

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graph[end].add(start);
        }

        for (int node = 1; node <= N; node++) {
            Collections.sort(graph[node]);
        }

        boolean[] visited = new boolean[N + 1];
        dfs(V, graph, visited, result);
        insert(result, sb);

        visited = new boolean[N + 1];
        bfs(graph, visited, result, V);
        insert(result, sb);

        System.out.print(sb);
    }

    private static void insert(Queue<Integer> result, StringBuilder sb) {
        while (!result.isEmpty()) {
            int value = result.poll();
            sb.append(value)
                    .append(SPACE);
        }
        sb.append(NEW_LINE);
    }

    private static void dfs(int cur, List<Integer>[] graph, boolean[] visited, Queue<Integer> result) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        result.offer(cur);

        for (int next: graph[cur]) {
            dfs(next, graph, visited, result);
        }
    }

    private static void bfs(List<Integer>[] graph, boolean[] visited, Queue<Integer> result, int V) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(V);

        visited[V] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.offer(cur);

            for (int next: graph[cur]) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                queue.offer(next);
            }
        }
    }
}
