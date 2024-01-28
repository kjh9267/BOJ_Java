package _2_sat;

// https://www.acmicpc.net/problem/3648

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_3648 {

    private static final String YES = "yes";

    private static final String NO = "no";

    private static final String NEW_LINE = "\n";

    private static int N;

    private static int M;

    private static int size;

    private static int index;

    private static List<Integer>[] graph;

    private static int[] visited;

    private static boolean[] finished;

    private static Deque<Integer> deque;

    private static List<Set<Integer>> sccs;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st;

            try {
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
            }
            catch (Exception e) {
                break;
            }

            size = N * 2 + 1;
            graph = new List[size];
            visited = new int[size];
            finished = new boolean[size];
            deque = new ArrayDeque<>();
            sccs = new ArrayList<>();

            for (int node = 1; node < size; node++) {
                graph[node] = new ArrayList<>();
            }

            graph[invert(1)].add(1);

            for (int count = 0; count < M; count++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                x = toNode(x);
                y = toNode(y);

                graph[invert(x)].add(y);
                graph[invert(y)].add(x);
            }

            boolean possible = dfsAll();

            if (possible) {
                sb.append(YES);
            }
            else {
                sb.append(NO);
            }
            sb.append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static int toNode(int num) {
        if (num < 0) {
            return -num + N;
        }
        return num;
    }

    private static int invert(int num) {
        if (num > N) {
            return num - N;
        }
        return num + N;
    }

    private static boolean dfsAll() {
        for (int node = 1; node < size; node++) {
            if (visited[node] == 0) {
                dfs(node);
            }
        }

        for (Set<Integer> scc: sccs) {
            for (int value: scc) {
                if (scc.contains(value - N) || scc.contains(value + N)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int dfs(int cur) {
        index += 1;
        visited[cur] = index;
        deque.offerLast(cur);

        int node = visited[cur];

        for (int next: graph[cur]) {
            if (visited[next] == 0) {
                node = min(node, dfs(next));
            }
            else if (!finished[next]) {
                node = min(node, visited[next]);
            }
        }

        if (node == visited[cur]) {
            extract(cur);
        }

        return node;
    }

    private static void extract(int cur) {
        HashSet<Integer> scc = new HashSet<>();
        sccs.add(scc);

        while (true) {
            int value = deque.pollLast();
            finished[cur] = true;
            scc.add(value);

            if (value == cur) {
                break;
            }
        }
    }
}
