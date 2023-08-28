package depth_first_search;

// https://www.acmicpc.net/problem/1240

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1240 {

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < N - 1; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            boolean[] visited = new boolean[N + 1];
            int[] dist = new int[1];
            dfs(start, dist, end, graph, visited);
            sb.append(dist[0])
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static class Node {
        int id;
        int cost;

        Node (int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private static boolean dfs(int cur, int[] dist, int target, List<Node>[] graph, boolean[] visited) {
        if (cur == target) {
            return true;
        }
        if (visited[cur]) {
            return false;
        }
        visited[cur] = true;

        for (Node next: graph[cur]) {
            dist[0] += next.cost;
            if (dfs(next.id, dist, target, graph, visited)) {
                return true;
            }
            dist[0] -= next.cost;
        }

        return false;
    }
}
