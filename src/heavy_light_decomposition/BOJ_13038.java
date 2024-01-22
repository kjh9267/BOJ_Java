package heavy_light_decomposition;

// https://www.acmicpc.net/problem/13038

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.*;

public class BOJ_13038 {

    private static final int READ = 1;

    private static int index;

    private static int group;

    private static List<Integer>[] tree;

    private static int[] treeSizes;

    private static int[] indices;

    private static int[] groups;

    private static int[] depths;

    private static int[] heads;

    private static int[] parents;

    private static int[] segmentTree;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        return Integer.compare(treeSizes[y], treeSizes[x]);
    };

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int count = 0; count < N - 1; count++) {
            data[count] = Integer.parseInt(st.nextToken());
        }

        init(data, N);

        int Q = Integer.parseInt(br.readLine());

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == READ) {
                int A = indices[Integer.parseInt(st.nextToken())];
                int B = indices[Integer.parseInt(st.nextToken())];

                if (A > B) {
                    int temp = A;
                    A = B;
                    B = temp;
                }

                int dist = computeDist(A, B, N);
                sb.append(dist)
                        .append(NEW_LINE);
            }
            else {
                int index = indices[Integer.parseInt(st.nextToken())];
                update(1, 1, N, index);
            }
        }

        System.out.print(sb);
    }

    private static void init(int[] data, int N) {
        tree = new List[N + 1];
        treeSizes = new int[N + 1];
        indices = new int[N + 1];
        groups = new int[N + 1];
        depths = new int[N + 1];
        heads = new int[N + 1];
        parents = new int[N + 1];
        int height = (int) ceil(log(N) / log(2)) + 1;
        int size = (int) pow(2, height);
        segmentTree = new int[size];

        for (int node = 1; node <= N; node++) {
            tree[node] = new ArrayList<>();
        }

        for (int node = 2; node <= N; node++) {
            int parent = data[node - 2];
            tree[parent].add(node);
        }

        Arrays.fill(treeSizes, 1);

        dfs(1, 0);
        decompose(1, 0, 0);
        initSegmentTree(1, 1, N);
    }

    private static int dfs(int cur, int prev) {
        for (int next: tree[cur]) {
            if (next != prev) {
                treeSizes[cur] += dfs(next, cur);
            }
        }

        return treeSizes[cur];
    }

    private static void decompose(int cur, int prev, int depth) {
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

        parents[index + 1] = index;
        decompose(heavy, cur, depth + 1);

        for (int next: tree[cur]) {
            if (next != heavy) {
                group += 1;
                parents[index + 1] = indices[cur];
                decompose(next, cur, depth + 1);
            }
        }
    }

    private static int computeDist(int x, int y, int N) {
        int dist = 0;

        while (groups[x] != groups[y]) {
            int xHead = heads[groups[x]];
            int yHead = heads[groups[y]];

            if (depths[xHead] < depths[yHead]) {
                int temp = x;
                x = y;
                y = temp;
            }

            xHead = heads[groups[x]];

            dist += read(1, 1, N, xHead, x);

            x = parents[xHead];
        }

        if (x == y) {
            return dist;
        }

        if (depths[x] < depths[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        return dist + read(1, 1, N, y + 1, x);
    }

    private static int initSegmentTree(int node, int start, int end) {
        if (start == end) {
            return segmentTree[node] = 1;
        }

        int mid = (start + end) >> 1;
        int leftNode = initSegmentTree(node * 2, start, mid);
        int rightNode = initSegmentTree(node * 2 + 1, mid + 1, end);

        return segmentTree[node] = leftNode + rightNode;
    }

    private static int read(int node, int start, int end, int left, int right) {
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

    private static int update(int node, int start, int end, int index) {
        if (!(start <= index && index <= end)) {
            return segmentTree[node];
        }
        if (start == end) {
            return segmentTree[node] = 0;
        }

        int mid = (start + end) >> 1;
        int leftNode = update(node * 2, start, mid, index);
        int rightNode = update(node * 2 + 1, mid + 1, end, index);

        return segmentTree[node] = leftNode + rightNode;
    }
}
