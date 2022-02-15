package depth_first_search;

// https://www.acmicpc.net/problem/24479

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.Collections.sort;
import static java.util.stream.Collectors.joining;

public class BOJ_24479 {

    private static int count;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()) - 1;

        List<Integer>[] graph = new ArrayList[N];

        for (int node = 0; node < N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            graph[start].add(end);
            graph[end].add(start);
        }

        boolean[] visited = new boolean[N];
        int[] result = new int[N];
        dfs(R, visited, result, graph);

        System.out.println(
                Arrays.stream(result)
                        .mapToObj(String::valueOf)
                        .collect(joining(NEW_LINE))
        );
    }

    private static void dfs(int cur, boolean[] visited, int[] result, List<Integer>[] graph) {
        if (visited[cur]) {
            return ;
        }

        visited[cur] = true;
        result[cur] = ++count;

        List<Integer> nextNodes = graph[cur];
        sort(nextNodes);

        for (Integer nextNode: nextNodes) {
            dfs(nextNode, visited, result, graph);
        }
    }
}
