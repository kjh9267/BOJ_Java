package binary_search;

// https://www.acmicpc.net/problem/20168

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20168 {

    private static final int INF = 1_001;

    private static final int IMPOSSIBLE = -1;

    private static List<Node>[] graph;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        int result = binarySearch(A, B, C, N);

        if (result == INF) {
            System.out.println(IMPOSSIBLE);
        }
        else {
            System.out.println(result);
        }
    }

    private static class Node {
        int id;
        int cost;

        Node (int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private static int binarySearch(int A, int B, int C, int N) {
        int lo = 0;
        int hi = INF;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            visited = new boolean[N + 1];

            if (dfs(A, C, mid, B)) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return hi;
    }

    private static boolean dfs(int cur, int C, int limit, int B) {
        if (cur == B) {
            return true;
        }
        if (visited[cur]) {
            return false;
        }
        visited[cur] = true;

        for (Node next: graph[cur]) {
            if (C - next.cost < 0) {
                continue;
            }
            if (next.cost > limit) {
                continue;
            }
            if (dfs(next.id, C - next.cost, limit, B)) {
                return true;
            }
        }

        return false;
    }
}
