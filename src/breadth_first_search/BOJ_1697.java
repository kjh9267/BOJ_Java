package breadth_first_search;

// https://www.acmicpc.net/problem/1697

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {

    private static final int LIMIT = 100_000;

    private static final int NOT_VISITED = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = bfs(N, K);

        System.out.println(result);
    }

    private static int bfs(int start, int target) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int[] visited = new int[LIMIT + 1];
        Arrays.fill(visited, NOT_VISITED);

        visited[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            move(queue, visited, cur, cur + 1);
            move(queue, visited, cur, cur - 1);
            move(queue, visited, cur, cur * 2);
        }

        return visited[target];
    }

    private static void move(Queue<Integer> queue, int[] visited, int cur, int next) {
        if (next < 0 || next > LIMIT) {
            return;
        }
        if (visited[next] != NOT_VISITED) {
            return;
        }
        queue.offer(next);
        visited[next] = visited[cur] + 1;
    }
}