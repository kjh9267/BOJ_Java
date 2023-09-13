package lowest_common_ancestor;

// https://www.acmicpc.net/problem/11437

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11437 {

    private static final int START = 1;

    private static final int NOT_VISITED = -1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] tree = new List[N + 1];

        for (int node = START; node <= N; node++) {
            tree[node] = new ArrayList<>();
        }

        for (int link = 0; link < N - 1; link++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end);
            tree[end].add(start);
        }

        int logN = (int) (Math.log(N) / Math.log(2));
        int[][] parents = new int[N + 1][logN + 1];
        int[] depth = bfs(tree, parents, N);
        initParents(N, parents, logN);

        int M = Integer.parseInt(br.readLine());

        for (int pair = 0; pair < M; pair++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int lowestCommonAncestor = findLowestCommonAncestor(x, y, depth, parents, logN);
            sb.append(lowestCommonAncestor)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static int[] bfs(List<Integer>[] tree, int[][] parents, int N) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(START);

        int[] visited = new int[N + 1];
        Arrays.fill(visited, NOT_VISITED);
        visited[START] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next: tree[cur]) {
                if (visited[next] != NOT_VISITED) {
                    continue;
                }
                visited[next] = visited[cur] + 1;
                parents[next][0] = cur;
                queue.offer(next);
            }
        }

        return visited;
    }

    private static void initParents(int N, int[][] parents, int logN) {
        for (int exp = 1; exp <= logN; exp++) {
            for (int node = START; node <= N; node++) {
                int prevNode = parents[node][exp - 1];
                parents[node][exp] = parents[prevNode][exp - 1];}
        }
    }

    private static int findLowestCommonAncestor(int x, int y, int[] depth, int[][] parents, int logN) {
        if (depth[x] < depth[y]) {
            int temp = y;
            y = x;
            x = temp;
        }

        for (int exp = logN; exp >= 0; exp--) {
            if (Math.pow(2, exp) > depth[x] - depth[y]) {
                continue;
            }
            if (depth[y] > depth[parents[x][exp]]) {
                continue;
            }
            x = parents[x][exp];
        }

        for (int exp = logN; exp >= 0; exp--) {
            if (x == y) {
                break;
            }
            if (parents[x][exp] != parents[y][exp]) {
                x = parents[x][exp];
                y = parents[y][exp];
            }
        }

        if (x == y) {
            return x;
        }

        return parents[x][0];
    }
}
