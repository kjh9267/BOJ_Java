package heavy_light_decomposition;

// https://www.acmicpc.net/problem/5916

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5916 {

    private static final String P = "P";

    private static final String NEW_LINE = "\n";

    private static List<Integer>[] tree;

    private static int[] treeSizes;

    private static int N;

    private static int index;

    private static int group;

    private static boolean[] visited;

    private static int[] indices;

    private static int[] groups;

    private static int[] heads;

    private static int[] depths;

    private static int[] parents;

    private static int[] aTree;

    private static int[] mTree;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        return Integer.compare(treeSizes[y], treeSizes[x]);
    };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tree = new List[N + 1];

        for (int node = 0; node <= N; node++) {
            tree[node] = new ArrayList<>();
        }

        for (int count = 0; count < N - 1; count++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree[start].add(end);
            tree[end].add(start);
        }

        init();

        for (int query = 0; query < M; query++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x = indices[Integer.parseInt(st.nextToken())];
            int y = indices[Integer.parseInt(st.nextToken())];

            if (command.equals(P)) {
                compute(x, y, 1);
            }
            else {
                int total = compute(x, y, 0);
                sb.append(total)
                        .append(NEW_LINE);
            }
        }

        System.out.print(sb);
    }

    private static void init() {
        treeSizes = new int[N + 1];
        visited = new boolean[N + 1];
        heads = new int[N + 1];
        indices = new int[N + 1];
        depths = new int[N + 1];
        groups = new int[N + 1];
        parents = new int[N + 1];
        aTree = new int[N + 1];
        mTree = new int[N + 1];

        Arrays.fill(treeSizes, 1);
        dfs(1, 1);

        visited[1] = true;
        decompose(1, 0);
    }

    private static int dfs(int cur, int prev) {
        for (int next: tree[cur]) {
            if (next != prev) {
                treeSizes[cur] += dfs(next, cur);
            }
        }

        return treeSizes[cur];
    }

    private static void decompose(int cur, int depth) {
        index += 1;
        indices[cur] = index;
        groups[index] = group;
        depths[index] = depth;

        if (heads[group] == 0) {
            heads[group] = index;
        }

        Collections.sort(tree[cur], COMPARATOR);
        int heavy = tree[cur].get(0);

        if (visited[heavy]) {
            if (treeSizes[cur] == 1) {
                return;
            }
            heavy = tree[cur].get(1);
        }

        visited[heavy] = true;
        parents[index + 1] = indices[cur];
        decompose(heavy, depth + 1);

        for (int next: tree[cur]) {
            if (visited[next]) {
                continue;
            }
            if (next == heavy) {
                continue;
            }
            visited[next] = true;
            parents[index + 1] = indices[cur];
            group += 1;
            decompose(next, depth + 1);
        }
    }

    private static int compute(int x, int y, int value) {
        int total = 0;

        while (groups[x] != groups[y]) {
            int xHead = heads[groups[x]];
            int yHead = heads[groups[y]];

            if (depths[xHead] < depths[yHead]) {
                int temp = x;
                x = y;
                y = temp;
            }

            xHead = heads[groups[x]];
            rangeUpdate(xHead, x, value);
            total += read(x) - read(xHead - 1);
            x = parents[xHead];
        }

        if (x == y) {
            return total;
        }

        if (depths[x] < depths[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        rangeUpdate(y + 1, x, value);
        total += read(x) - read(y);

        return total;
    }

    private static int read(int index) {
        int i = index;
        int a = 0;
        int m = 0;

        while (i >= 1) {
            a += aTree[i];
            m += mTree[i];
            i -= (i & -i);
        }

        return m * index + a;
    }

    private static void rangeUpdate(int left, int right, int value) {
        update(left, value, -(left - 1) * value);
        update(right + 1, -value, right * value);
    }

    private static void update(int index, int mValue, int aValue) {
        while (index <= N) {
            aTree[index] += aValue;
            mTree[index] += mValue;
            index += (index & -index);
        }
    }
}
