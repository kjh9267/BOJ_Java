package segment_tree_lazy_propagation;

// https://www.acmicpc.net/problem/12844

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class BOJ_12844 {

    private static final int UPDATE = 1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int node = 1; node <= N; node++) {
            nums[node] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int height = (int) ceil(log(N) / log(2)) + 1;
        int size = (int) pow(2, height);
        int[] tree = new int[size];
        int[] lazy = new int[size];
        boolean[] oddRanges = new boolean[size];

        init(1, 1, N, tree, nums, oddRanges);

        for (int query = 0; query < M; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == UPDATE) {
                int left = Integer.parseInt(st.nextToken()) + 1;
                int right = Integer.parseInt(st.nextToken()) + 1;
                int value = Integer.parseInt(st.nextToken());
                rangeUpdate(1, 1, N, left, right, tree, lazy, value, oddRanges);

            }
            else {
                int left = Integer.parseInt(st.nextToken()) + 1;
                int right = Integer.parseInt(st.nextToken()) + 1;
                int value = read(1, 1, N, left, right, tree, lazy, oddRanges);
                sb.append(value)
                        .append(NEW_LINE);
            }
        }

        System.out.print(sb);
    }

    private static int init(int node, int start, int end, int[] tree, int[] nums, boolean[] oddRanges) {
        if ((end - start + 1) % 2 == 1) {
            oddRanges[node] = true;
        }
        if (start == end) {
            return tree[node] = nums[start];
        }

        int mid = (start + end) >> 1;
        int leftNode = init(node * 2, start, mid, tree, nums, oddRanges);
        int rightNode = init(node * 2 + 1, mid + 1, end, tree, nums, oddRanges);

        return tree[node] = leftNode ^ rightNode;
    }

    private static int rangeUpdate(int node, int start, int end, int left, int right, int[] tree, int[] lazy, int value, boolean[] oddRanges) {
        propagate(node, start, end, lazy, tree, oddRanges);
        if (left > end || right < start) {
            return tree[node];
        }
        if (left <= start && end <= right) {
            if (oddRanges[node]) {
                tree[node] ^= value;
            }

            if (start == end) {
                return tree[node];
            }

            lazy[node * 2] ^= value;
            lazy[node * 2 + 1] ^= value;

            return tree[node];
        }

        int mid = (start + end) >> 1;
        int leftNode = rangeUpdate(node * 2, start, mid, left, right, tree, lazy, value, oddRanges);
        int rightNode = rangeUpdate(node * 2 + 1, mid + 1, end, left, right, tree, lazy, value, oddRanges);

        return tree[node] = leftNode ^ rightNode;
    }

    private static void propagate(int node, int start, int end, int[] lazy, int[] tree, boolean[] oddRanges) {
        if (lazy[node] == 0) {
            return;
        }

        if (oddRanges[node]) {
            tree[node] ^= lazy[node];
        }

        if (start != end) {
            lazy[node * 2] ^= lazy[node];
            lazy[node * 2 + 1] ^= lazy[node];
        }

        lazy[node] = 0;
    }

    private static int read(int node, int start, int end, int left, int right, int[] tree, int[] lazy, boolean[] oddRanges) {
        propagate(node, start, end, lazy, tree, oddRanges);
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int leftNode = read(node * 2, start, mid, left, right, tree, lazy, oddRanges);
        int rightNode = read(node * 2 + 1, mid + 1, end, left, right, tree, lazy, oddRanges);

        return leftNode ^ rightNode;
    }
}
