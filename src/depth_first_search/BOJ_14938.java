package depth_first_search;

// https://www.acmicpc.net/problem/14938

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14938 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int node = 1; node <= n; node++) {
            items[node] = Integer.parseInt(st.nextToken());
        }

        List<Node>[] graph = new List[n + 1];

        for (int node = 1; node <= n; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < r; link++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, l));
            graph[b].add(new Node(a, l));
        }

        int maxValue = dfsAll(graph, items, n, m);

        System.out.println(maxValue);
    }

    private static class Node {
        int id;
        int dist;

        Node (int next, int dist) {
            this.id = next;
            this.dist = dist;
        }
    }

    private static int dfsAll(List<Node>[] graph, int[] items, int n, int m) {
        int maxValue = 0;

        for (int node = 1; node <= n; node++) {
            int[] value = new int[1];
            boolean[][] visited = new boolean[n + 1][16];
            visited[node][0] = true;

            boolean[] isCollected = new boolean[n + 1];

            dfs(new Node(node, 0), graph, items, visited, isCollected, value, m);

            maxValue = Math.max(maxValue, value[0]);
        }

        return maxValue;
    }

    private static void dfs(Node node, List<Node>[] graph, int[] items, boolean[][] visited, boolean[] isCollected, int[] value, int m) {

        for (Node nextNode: graph[node.id]) {
            int nextDist = node.dist + nextNode.dist;
            if (nextDist > m) {
                continue;
            }

            if (isVisited(visited, nextNode, nextDist)) {
                continue;
            }
            visited[node.id][nextDist] = true;

            dfs(new Node(nextNode.id, nextDist), graph, items, visited, isCollected, value, m);
        }

        if (!isCollected[node.id]) {
            value[0] += items[node.id];
        }
        isCollected[node.id] = true;
    }

    private static boolean isVisited(boolean[][] visited, Node nextNode, int nextDist) {
        for (int dist = 0; dist <= nextDist; dist++) {
            if (visited[nextNode.id][dist]) {
                return true;
            }
        }

        return false;
    }
}
