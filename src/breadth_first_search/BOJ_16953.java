package breadth_first_search;

// https://www.acmicpc.net/problem/16953

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16953 {

    private static final int IMPOSSIBLE = -1;

    private static final int LIMIT = 30;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int result = bfs(A, B);
        System.out.println(result);
    }

    private static int bfs(int A, int B) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(B);

        Set<Integer> visited = new HashSet<>();
        visited.add(B);

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            time += 1;

            if (time > LIMIT) {
                return IMPOSSIBLE;
            }

            for (int count = 0; count < size; count++) {
                int cur = queue.poll();

                if (cur == A) {
                    return time;
                }

                int next = cur / 10;
                if (cur % 10 == 1 && !visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }

                next = cur / 2;
                if (cur % 2 == 0 && !visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }

        return IMPOSSIBLE;
    }
}
