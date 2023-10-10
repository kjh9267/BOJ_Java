package segment_tree_lazy_propagation;

// https://www.acmicpc.net/problem/15967

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class BOJ_15967 {

    private static final int READ = 1;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q1 = Integer.parseInt(st.nextToken());
        int Q2 = Integer.parseInt(st.nextToken());
        int Q = Q1 + Q2;
        int[] nums = new int[N + 1];
        int height = (int) ceil(log(N) / log(2)) + 1;
        int size = (int) pow(2, height);
        long[] tree = new long[size];
        int[] lazy = new int[size];

        st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        init(1, 1, N, tree, nums);

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == READ) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long value = read(1, 1, N, left, right, tree, lazy);

                sb.append(value)
                        .append(NEW_LINE);
            }
            else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                rangeUpdate(1, 1, N, left, right, tree, lazy, value);
            }
        }

        System.out.print(sb);
    }

    private static long init(int node, int start, int end, long[] tree, int[] nums) {
        if (start == end) {
            return tree[node] = nums[start];
        }

        int mid = (start + end) >> 1;
        long leftNode = init(node * 2, start, mid, tree, nums);
        long rightNode = init(node * 2 + 1, mid + 1, end, tree, nums);

        return tree[node] = leftNode + rightNode;
    }

    private static void propagate(int node, int start, int end, int[] lazy, long[] tree) {
        if (lazy[node] == 0) {
            return;
        }

        tree[node] += lazy[node] * (end - start + 1);
        if (start != end) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }

    private static long rangeUpdate(int node, int start, int end, int left, int right, long[] tree, int[] lazy, int value) {
        propagate(node, start, end, lazy, tree);
        if (left > end || right < start) {
            return tree[node];
        }
        if (left <= start && end <= right) {
            tree[node] += value * (end - start + 1);

            if (start == end) {
                return tree[node];
            }

            lazy[node * 2] += value;
            lazy[node * 2 + 1] += value;

            return tree[node];
        }

        int mid = (start + end) >> 1;
        long leftNode = rangeUpdate(node * 2, start, mid, left, right, tree, lazy, value);
        long rightNode = rangeUpdate(node * 2 + 1, mid + 1, end, left, right, tree, lazy, value);

        return tree[node] = leftNode + rightNode;
    }

    private static long read(int node, int start, int end, int left, int right, long[] tree, int[] lazy) {
        propagate(node, start, end, lazy, tree);
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        long leftNode = read(node * 2, start, mid, left, right, tree, lazy);
        long rightNode = read(node * 2 + 1, mid + 1, end, left, right, tree, lazy);

        return leftNode + rightNode;
    }
}
