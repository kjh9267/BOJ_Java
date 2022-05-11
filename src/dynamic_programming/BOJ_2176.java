package dynamic_programming;

// https://www.acmicpc.net/problem/2176

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2176 {

    private static final long MAX = 1_000_000_001L;

    private static final int START = 1;

    private static final int DESTINATION = 2;

    private static final int NOT_VISITED = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        long[] dist = bfs(graph, N);

        int[] dp = new int[N + 1];

        Arrays.fill(dp, NOT_VISITED);

        dfs(START, graph, dist, dp);

        System.out.println(dp[START]);
    }

    private static class Node {
        int id;
        long cost;

        Node(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private static long[] bfs(List<Node>[] graph, int N) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(DESTINATION, 0));

        long[] dist = new long[N + 1];
        Arrays.fill(dist, MAX);
        dist[DESTINATION] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            for (Node nextNode: graph[curNode.id]) {
                if (dist[nextNode.id] <= dist[curNode.id] + nextNode.cost) {
                    continue;
                }

                dist[nextNode.id] = dist[curNode.id] + nextNode.cost;
                queue.offer(new Node(nextNode.id, dist[nextNode.id]));
            }
        }

        return dist;
    }

    private static int dfs(int cur, List<Node>[] graph, long[] dist, int[] dp) {
        if (cur == DESTINATION) {
            return 1;
        }

        if (dp[cur] != NOT_VISITED) {
            return dp[cur];
        }

        dp[cur] = 0;

        for (Node nextNode: graph[cur]) {
            if (dist[nextNode.id] >= dist[cur]) {
                continue;
            }
            dp[cur] += dfs(nextNode.id, graph, dist, dp);
        }

        return dp[cur];
    }
}
