package breadth_first_search;

// https://www.acmicpc.net/problem/16928

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16928 {

    private static final int MAX = 101;

    private static final int START = 1;

    private static final int END = 100;

    private static final int NOT_VISITED = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] jumpingInfo = new int[MAX];

        for (int link = 0; link < N + M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            jumpingInfo[start] = end;
        }

        List<Integer>[] graph = initGraph(jumpingInfo);

        int result = bfs(graph);

        System.out.println(result);
    }

    private static List<Integer>[] initGraph(int[] jumpingInfo) {
        List<Integer>[] graph = new List[MAX];

        for (int node = 1; node < MAX; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int node = 1; node < MAX; node++) {
            int nextNodeEnd = Math.min(END, node + 6);
            for (int nextNode = node + 1; nextNode <= nextNodeEnd; nextNode++) {
                int jumping = jumpingInfo[nextNode];
                if (jumping > 0) {
                    graph[node].add(jumping);
                }
            }
        }

        for (int node = 1; node < MAX; node++) {
            int nextNodeEnd = Math.min(END, node + 6);
            for (int nextNode = node + 1; nextNode <= nextNodeEnd; nextNode++) {
                if (jumpingInfo[nextNode] > 0) {
                    continue;
                }
                graph[node].add(nextNode);
            }
        }

        return graph;
    }

    private static int bfs(List<Integer>[] graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(START);

        int[] visited = new int[MAX];
        Arrays.fill(visited, NOT_VISITED);

        visited[START] = 0;

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

        return visited[END];
    }
}
