package breadth_first_search;

// https://www.acmicpc.net/problem/14217

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;

public class BOJ_14217 {

    private static final int START = 0;

    private static final int ADD_LINK = 1;

    private static final int NOT_VISITED = -1;

    private static final String NEW_LINE = "\n";

    private static final String SPACE = " ";

    private static int N;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] graph = new boolean[N][N];

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            graph[start][end] = true;
            graph[end][start] = true;
        }

        int Q = Integer.parseInt(br.readLine());

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            if (command == ADD_LINK) {
                graph[start][end] = true;
                graph[end][start] = true;
            }
            else {
                graph[start][end] = false;
                graph[end][start] = false;
            }

            int[] dist = bfs(graph);

            String distString = Arrays.stream(dist)
                    .mapToObj(String::valueOf)
                    .collect(joining(SPACE));

            sb.append(distString)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static int[] bfs(boolean[][] graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(START);

        int[] visited = new int[N];
        Arrays.fill(visited, NOT_VISITED);
        visited[START] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next = 0; next < N; next++) {
                if (!graph[cur][next]) {
                    continue;
                }
                if (visited[next] != NOT_VISITED) {
                    continue;
                }
                visited[next] = visited[cur] + 1;
                queue.offer(next);
            }
        }

        return visited;
    }
}
