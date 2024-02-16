package heavy_light_decomposition;

// https://www.acmicpc.net/problem/25078

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.*;

public class BOJ_25078 {

    private static final int ROOT = 0;

    private static final int EMPTY = -1;

    private static final String NEW_LINE = "\n";

    private static final String INSTALL = "install";

    private static int N;

    private static List<Integer>[] tree;

    private static int index;

    private static int group;

    private static int[] treeSizes;

    private static int[] indices;

    private static int[] groups;

    private static int[] depths;

    private static int[] heads;

    private static int[] parents;

    private static int[] maxIndices;

    private static int[] segmentTree;

    private static int[] lazy;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        return Integer.compare(treeSizes[y], treeSizes[x]);
    };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int node = 1; node < N; node++) {
            int parent = Integer.parseInt(st.nextToken());
            tree[parent].add(node);
        }

        dfs(0);
        decompose(0, 0);

        int Q = Integer.parseInt(br.readLine());

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int node = Integer.parseInt(st.nextToken());
            node = indices[node];

            if (command.equals(INSTALL)) {
                int result = install(node, indices[ROOT]);
                sb.append(result);
            }
            else {
                int result = uninstall(node);
                sb.append(result);
            }
            sb.append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init() {
        tree = new List[N + 1];
        treeSizes = new int[N + 1];
        indices = new int[N + 1];
        groups = new int[N + 1];
        depths = new int[N + 1];
        heads = new int[N + 1];
        parents = new int[N + 1];
        maxIndices = new int[N + 1];
        int height = (int) (ceil(log(N) / log(2)) + 1);
        int size = (int) pow(2, height);
        segmentTree = new int[size];
        lazy = new int[size];

        for (int node = 0; node < N; node++) {
            tree[node] = new ArrayList<>();
        }

        Arrays.fill(treeSizes, 1);
        Arrays.fill(lazy, EMPTY);
    }

    private static int dfs(int cur) {
        for (int next: tree[cur]) {
            treeSizes[cur] += dfs(next);
        }

        return treeSizes[cur];
    }

    private static int decompose(int cur, int depth) {
        index += 1;
        indices[cur] = index;
        groups[index] = group;
        depths[index] = depth;

        if (heads[group] == 0) {
            heads[group] = index;
        }

        if (tree[cur].isEmpty()) {
            maxIndices[index] = index;
            return index;
        }

        Collections.sort(tree[cur], COMPARATOR);
        int heavy = tree[cur].get(0);
        parents[index + 1] = index;
        int maxIndex = decompose(heavy, depth + 1);

        for (int next: tree[cur]) {
            if (next != heavy) {
                parents[index + 1] = indices[cur];
                group += 1;
                maxIndex = decompose(next, depth + 1);
            }
        }

        maxIndices[indices[cur]] = maxIndex;

        return index;
    }

    private static int install(int x, int y) {
        int total = 0;
        int installed = 0;

        while (groups[x] != groups[y]) {
            int xHead = heads[groups[x]];
            int yHead = heads[groups[y]];

            if (depths[xHead] < depths[yHead]) {
                int temp = x;
                x = y;
                y = temp;
            }

            xHead = heads[groups[x]];
            installed += read(1, 1, N, xHead, x);
            total += x - xHead + 1;
            rangeUpdate(1, 1, N, xHead, x, 1);

            x = parents[xHead];
        }

        if (depths[x] < depths[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        installed += read(1, 1, N, y, x);
        total += x - y + 1;
        rangeUpdate(1, 1, N, y, x, 1);

        return total - installed;
    }

    private static int uninstall(int x) {
        int y = maxIndices[x];

        int installed = read(1, 1, N, x, y);
        rangeUpdate(1, 1, N, x, y, 0);

        return installed;
    }

    private static int read(int node, int start, int end, int left, int right) {
        propagate(node, start, end);
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) >> 1;
        int leftNode = read(node * 2, start, mid, left, right);
        int rightNode = read(node * 2 + 1, mid + 1, end, left, right);

        return leftNode + rightNode;
    }

    private static void propagate(int node, int start, int end) {
        if (lazy[node] == EMPTY) {
            return;
        }

        segmentTree[node] = (end - start + 1) * lazy[node];

        if (start != end) {
            lazy[node * 2] = lazy[node];
            lazy[node * 2 + 1] = lazy[node];
        }

        lazy[node] = EMPTY;
    }

    private static int rangeUpdate(int node, int start, int end, int left, int right, int value) {
        propagate(node, start, end);

        if (left > end || right < start) {
            return segmentTree[node];
        }
        if (left <= start && end <= right) {
            segmentTree[node] = (end - start + 1) * value;

            if (start != end) {
                lazy[node * 2] = value;
                lazy[node * 2 + 1] = value;
            }

            return segmentTree[node];
        }

        int mid = (start + end) >> 1;
        int leftNode = rangeUpdate(node * 2, start, mid, left, right, value);
        int rightNode = rangeUpdate(node * 2 + 1, mid + 1, end, left, right, value);
        segmentTree[node] = leftNode + rightNode;

        return segmentTree[node];
    }
}
