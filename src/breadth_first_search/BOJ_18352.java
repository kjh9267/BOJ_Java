package breadth_first_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352 {

    private static final int NOT_VISITED = -1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        int[] dist = bfs(graph, N, X);
        String result = findTargetNodes(dist, K, N);

        System.out.print(result);
    }

    private static int[] bfs(List<Integer>[] graph, int N, int X) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(X);

        int[] visited = new int[N + 1];
        Arrays.fill(visited, NOT_VISITED);
        visited[X] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next: graph[cur]) {
                if (visited[next] != NOT_VISITED) {
                    continue;
                }
                visited[next] = visited[cur] + 1;
                queue.offer(next);
            }
        }

        return visited;
    }

    private static String findTargetNodes(int[] dist, int X, int N) {
        StringBuilder sb = new StringBuilder();
        List<Integer> result = new ArrayList<>();

        for (int node = 1; node <= N; node++) {
            if (dist[node] == X) {
                result.add(node);
            }
        }

        Collections.sort(result);

        for (int node: result) {
            sb.append(node)
                    .append(NEW_LINE);
        }

        if (result.isEmpty()) {
            return sb.append(-1)
                    .append(NEW_LINE)
                    .toString();
        }

        return sb.toString();
    }
}
