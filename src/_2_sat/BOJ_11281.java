package _2_sat;

// https://www.acmicpc.net/problem/11281

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.min;

public class BOJ_11281 {

    private static final int NOT_VISITED = -1;

    private static final int TRUE = 1;

    private static final int FALSE = 0;

    private static final String SPACE = " ";

    private static int N;

    private static int size;

    private static int index;

    private static int scc;

    private static List<Integer>[] graph;

    private static int[] visited;

    private static Deque<Integer> deque;

    private static boolean[] finished;

    private static Set<Integer>[] sccs;

    private static List<Integer> nodes;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            x = toNode(x);
            y = toNode(y);
            graph[reversed(x)].add(y);
            graph[reversed(y)].add(x);
        }

        int result = dfsAll();
        if (result == FALSE) {
            System.out.println(FALSE);
        }
        else {
            System.out.println(TRUE);
            reverse();
            int[] booleans = computeBooleans();

            for (int node = 1; node <= N; node++) {
                sb.append(booleans[node])
                        .append(SPACE);
            }

            System.out.println(sb);
        }
    }

    private static void init() {
        size = N * 2 + 1;
        graph = new List[size];
        visited = new int[size];
        finished = new boolean[size];
        deque = new ArrayDeque<>();
        sccs = new Set[size];
        nodes = new ArrayList<>();

        Arrays.fill(visited, NOT_VISITED);

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
            sccs[node] = new HashSet<>();
        }
    }

    private static int toNode(int num) {
        if (num < 0) {
            return -num + N;
        }
        return num;
    }

    private static int reversed(int node) {
        if (node > N) {
            return node - N;
        }
        return node + N;
    }

    private static int dfsAll() {
        for (int node = 1; node < size; node++) {
            if (visited[node] == NOT_VISITED) {
                dfs(node);
            }
        }

        if (!isPossible()) {
            return FALSE;
        }

        return TRUE;
    }

    private static int dfs(int cur) {
        index += 1;
        visited[cur] = index;
        deque.offerLast(cur);

        int parent = index;
        for (int next: graph[cur]) {
            if (visited[next] == NOT_VISITED) {
                parent = min(parent, dfs(next));
            }
            else if (!finished[next]) {
                parent = min(parent, visited[next]);
            }
        }

        if (parent == visited[cur]) {
            extractScc(cur);
        }

        return parent;
    }

    private static void extractScc(int cur) {
        scc += 1;

        while (true) {
            int value = deque.pollLast();

            sccs[scc].add(value);
            nodes.add(value);
            finished[value] = true;

            if (value == cur) {
                break;
            }
        }
    }

    private static boolean isPossible() {
        for (Set<Integer> scc: sccs) {
            for (int node = 1; node < size; node++) {
                if (!scc.contains(node)) {
                    continue;
                }
                if (scc.contains(node - N) || scc.contains(node + N)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[] computeBooleans() {
        int[] booleans = new int[size];
        Arrays.fill(booleans, NOT_VISITED);

        for (int node: nodes) {
            if (booleans[node] == NOT_VISITED) {
                booleans[node] = FALSE;
                booleans[reversed(node)] = TRUE;
            }
        }

        return booleans;
    }

    private static void reverse() {
        int half = size >> 1;

        for (int index = 0 ; index < half; index++) {
            int temp = nodes.get(index);
            nodes.set(index, nodes.get(size - 2 - index));
            nodes.set(size - 2 - index, temp);
        }
    }
}
