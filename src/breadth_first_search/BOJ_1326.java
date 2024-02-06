package breadth_first_search;

// https://www.acmicpc.net/problem/1326

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1326 {

    private static final int NOT_VISITED = -1;

    private static int[] data;

    private static int N;

    private static int start;

    private static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int index = 0; index < N; index++) {
            data[index] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken()) - 1;
        end = Integer.parseInt(st.nextToken()) - 1;

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int[] visited = new int[N];
        Arrays.fill(visited, NOT_VISITED);
        visited[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int left = cur;
            int right = cur;

            while (true) {
                left -= data[cur];

                if (left < 0) {
                    break;
                }
                if (visited[left] == NOT_VISITED) {
                    visited[left] = visited[cur] + 1;
                    queue.offer(left);
                }
            }

            while (true) {
                right += data[cur];

                if (right >= N) {
                    break;
                }
                if (visited[right] == NOT_VISITED) {
                    visited[right] = visited[cur] + 1;
                    queue.offer(right);
                }
            }
        }

        return visited[end];
    }
}
