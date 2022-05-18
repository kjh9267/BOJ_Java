package breadth_first_search;

// https://www.acmicpc.net/problem/24445

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_24445 {

    private static final int NOT_VISITED = 0;

    private static final String NEW_LINE = "\n";

    private static int count = 1;

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

        int[] result = bfs(graph, start, N);

        for (int node = 1; node <= N; node++) {
            int count = result[node];

            sb.append(count)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static int[] bfs(List<Integer>[] graph, int start, int N) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int[] visited = new int[N + 1];
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next: graph[cur]) {
                if (visited[next] != NOT_VISITED) {
                    continue;
                }
                count += 1;
                visited[next] = count;
                queue.offer(next);
            }
        }

        return visited;
    }
}
