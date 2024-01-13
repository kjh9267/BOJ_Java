package heavy_light_decomposition;

// https://www.acmicpc.net/problem/13309

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.log;
import static java.lang.Math.pow;

public class BOJ_13309 {

    private static List<Integer>[] tree;

    private static int[] treeSizes;

    private static int[] indices;

    private static int[] groups;

    private static int[] depths;

    private static int[] heads;

    private static int[] parents;

    private static boolean[] segmentTree;

    private static int index;

    private static int group;

    private static int N;

    private static final int UPDATE = 1;

    private static final String YES = "YES";

    private static final String NO = "NO";

    private static final String NEW_LINE = "\n";

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        return Integer.compare(treeSizes[y], treeSizes[x]);
    };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        tree = new List[N + 1];
        treeSizes = new int[N + 1];
        groups = new int[N + 1];
        heads = new int[N + 1];
        depths = new int[N + 1];
        indices = new int[N + 1];
        parents = new int[N + 1];

        int height = (int) Math.ceil(log(N)/ log(2)) + 1;
        int size = (int) pow(2, height);

        segmentTree = new boolean[size];

        Arrays.fill(treeSizes, 1);

        for (int node = 1; node <= N; node++) {
            tree[node] = new ArrayList<>();
        }

        for (int node = 2; node <= N; node++) {
            int parent = Integer.parseInt(br.readLine());
            tree[parent].add(node);
        }

        dfs(1);
        decompose(1, 0);

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int command = Integer.parseInt(st.nextToken());

            boolean notConnected = isNotConnected(indices[left], indices[right]);

            if (notConnected) {
                sb.append(NO);
            }
            else {
                sb.append(YES);
            }
            sb.append(NEW_LINE);

            if (command == UPDATE) {
                if (notConnected) {
                    update(1, 1, N, indices[right]);
                }
                else {
                    update(1, 1, N, indices[left]);
                }
            }
        }

        System.out.print(sb);
    }

    private static int dfs(int cur) {
        for (int next: tree[cur]) {
            treeSizes[cur] += dfs(next);
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

        if (tree[cur].isEmpty()) {
            return;
        }

        Collections.sort(tree[cur], COMPARATOR);

        int heavy = tree[cur].get(0);
        parents[index + 1] = indices[cur];
        decompose(heavy, depth + 1);

        for (int next: tree[cur]) {
            if (next != heavy) {
                parents[index + 1] = indices[cur];
                group += 1;
                decompose(next, depth + 1);
            }
        }
    }

    private static boolean isNotConnected(int x, int y) {
        boolean notConnected = false;

        while (groups[x] != groups[y]) {
            int xHead = heads[groups[x]];
            int yHead = heads[groups[y]];

            if (depths[xHead] < depths[yHead]) {
                int temp = x;
                x = y;
                y = temp;
            }

            int head = heads[groups[x]];

            notConnected = notConnected || read(1, 1, N, head, x);

            x = parents[head];
        }

        if (x == y) {
            return notConnected;
        }

        if (depths[x] < depths[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        return notConnected || read(1, 1, N, y + 1, x);
    }

    private static boolean read(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return false;
        }
        if (left <= start && end <= right) {
            return segmentTree[node];
        }

        int mid = (start + end) >> 1;
        boolean leftNode = read(node * 2, start, mid, left, right);
        boolean rightNode = read(node * 2 + 1, mid + 1, end, left, right);

        return leftNode || rightNode;
    }

    private static boolean update(int node, int start, int end, int index) {
        if (!(start <= index && index <= end)) {
            return false;
        }
        if (start == end) {
            return segmentTree[node] = true;
        }

        int mid = (start + end) >> 1;
        boolean leftNode = update(node * 2, start, mid, index);
        boolean rightNode = update(node * 2 + 1, mid + 1, end, index);

        return segmentTree[node] = segmentTree[node] || leftNode || rightNode;
    }
}
