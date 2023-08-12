package disjoint_set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.String.format;

public class BOJ_7511 {

    private static final String NEW_LINE = "\n";

    private static final String TEMPLATE = "Scenario %s:";

    private static final String CONNECTED = "1";

    private static final String NOT_CONNECTED = "0";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            sb.append(format(TEMPLATE, test))
                    .append(NEW_LINE);

            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());

            List<Integer>[] graph = new List[N];

            for (int node = 0; node < N; node++) {
                graph[node] = new ArrayList<>();
            }

            for (int link = 0; link < K; link++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                graph[A].add(B);
                graph[B].add(A);
            }

            int[] parents = new int[N];
            Arrays.fill(parents, -1);

            dfsAll(graph, parents, N);

            int M = Integer.parseInt(br.readLine());

            for (int link = 0; link < M; link++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());

                if (find(U, parents) == find(V, parents)) {
                    sb.append(CONNECTED);
                }
                else {
                    sb.append(NOT_CONNECTED);
                }
                sb.append(NEW_LINE);
            }
            if (test < T) {
                sb.append(NEW_LINE);
            }
        }
        System.out.print(sb);
    }

    private static boolean merge(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x == y) {
            return false;
        }
        if (parents[x] < parents[y]) {
            parents[x] += parents[y];
            parents[y] = x;
        }
        else {
            parents[y] += parents[x];
            parents[x] = y;
        }
        return true;
    }

    private static int find(int x, int[] parents) {
        if (parents[x] < 0) {
            return x;
        }
        parents[x] = find(parents[x], parents);
        return parents[x];
    }

    private static void dfsAll(List<Integer>[] graph, int[] parents, int N) {
        for (int node = 0; node < N; node++) {
            int parentNode = find(node, parents);
            if (parents[parentNode] < -1) {
                continue;
            }
            dfs(node, graph, parents);
        }
    }

    private static void dfs(int cur, List<Integer>[] graph, int[] parents) {
        for (int next: graph[cur]) {
            if (!merge(cur, next, parents)) {
                continue;
            }
            dfs(next, graph, parents);
        }
    }
}
