package breadth_first_search;

// https://www.acmicpc.net/problem/12919

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12919 {

    private static final int POSSIBLE = 1;

    private static final int IMPOSSIBLE = 0;

    private static final char A = 'A';

    private static final char B = 'B';

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        System.out.println(bfs(S, T));
    }

    private static int bfs(String S, String T) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(T);

        Set<String> visited = new HashSet<>();
        visited.add(T);

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            if (cur.equals(S)) {
                return POSSIBLE;
            }
            if (cur.length() < S.length()) {
                break;
            }

            StringBuilder nextStringBuilder = new StringBuilder(cur);

            if (cur.charAt(cur.length() - 1) == A) {
                String next = nextStringBuilder.delete(cur.length() - 1, cur.length())
                        .toString();

                nextStringBuilder.append(A);
                updateVisited(visited, next, queue);
            }

            if (cur.charAt(0) == B) {
                String next = nextStringBuilder
                        .reverse()
                        .delete(cur.length() - 1, cur.length())
                        .toString();

                updateVisited(visited, next, queue);
            }
        }

        return IMPOSSIBLE;
    }

    private static void updateVisited(Set<String> visited, String next, Queue<String> queue) {
        if (!visited.contains(next)) {
            visited.add(next);
            queue.offer(next);
        }
    }
}
