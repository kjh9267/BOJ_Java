package eulerian_circuit;

// https://www.acmicpc.net/problem/1199

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;

public class BOJ_1199 {

    private static final int IMPOSSIBLE = -1;

    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<Integer> result = new ArrayDeque<>();
        int[] work = new int[N];

        if (!isPossible(graph, N)) {
            System.out.println(IMPOSSIBLE);
        }
        else {
            dfs(0, result, graph, work, N);
            System.out.println(
                    result.stream()
                            .map(x -> String.valueOf(x + 1))
                            .collect(joining(SPACE))
            );
        }
    }

    private static boolean isPossible(int[][] graph, int N) {
        for (int row = 0; row < N; row++) {
            int sum = 0;
            for (int col = 0; col < N; col++) {
                sum += graph[row][col];
            }
            if (sum % 2 == 1) {
                return false;
            }
        }

        return true;
    }

    private static void dfs(int cur, Deque<Integer> result, int[][] graph, int[] work, int N) {
        int temp = work[cur];
        for (int next = temp; next < N; next++) {
            work[cur] = next;
            while (graph[cur][next] > 0) {
                graph[cur][next] -= 1;
                graph[next][cur] -= 1;
                dfs(next, result, graph, work, N);
            }
        }

        result.offerLast(cur);
    }
}
