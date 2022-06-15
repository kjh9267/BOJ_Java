package breadth_first_search;

// https://www.acmicpc.net/problem/2479

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class BOJ_2497 {

    private static final int NOT_VISITED = -1;

    private static final String SPACE = " ";

    private static final String IMPOSSIBLE = "-1";

    private static Set<Integer> targets;

    private static int N;

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        nums = new int[N];

        for (int node = 0; node < N; node++) {
            nums[node] = Integer.parseInt(br.readLine(), 2);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        targets = new HashSet<>();

        int num = (int) Math.pow(2, K - 1);
        while (num > 0) {
            targets.add(num);
            num >>= 1;
        }

        int[] way = bfs(start);

        String path = findPath(way, start, end);

        System.out.println(path);
    }

    private static int[] bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] visited = new boolean[N];
        visited[start] = true;

        int[] way = new int[N];
        Arrays.fill(way, NOT_VISITED);

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();
            int currentNum = nums[currentIndex];

            for (int nextIndex = 0; nextIndex < N; nextIndex++) {
                int nextNum = nums[nextIndex];
                int xor = currentNum^nextNum;
                if (!targets.contains(xor)) {
                    continue;
                }
                if (visited[nextIndex]) {
                    continue;
                }
                visited[nextIndex] = true;
                queue.offer(nextIndex);
                way[nextIndex] = currentIndex;
            }
        }

        return way;
    }

    private static String findPath(int[] way, int start, int end) {
        if (way[end] == NOT_VISITED) {
            return IMPOSSIBLE;
        }

        Deque<String> deque = new ArrayDeque<>();
        deque.offerFirst(String.valueOf(end + 1));

        int node = end;

        while (node != start) {
            deque.offerFirst(String.valueOf(way[node] + 1));
            node = way[node];
        }

        return deque.stream()
                .collect(joining(SPACE));
    }
}
