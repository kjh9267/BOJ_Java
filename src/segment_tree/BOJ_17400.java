package segment_tree;

// https://www.acmicpc.net/problem/17400

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17400 {

    private static final int UPDATE = 2;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] evenNums = new int[N + 1];
        int[] oddNums = new int[N + 1];

        int height = (int) Math.ceil(Math.log(N) / Math.log(2));
        int size = (int) Math.pow(2, height + 1);

        long[] evenTree = new long[size];
        long[] oddTree = new long[size];
        st = new StringTokenizer(br.readLine());

        for (int index = 1; index <= N; index++) {
            if (index % 2 == 1) {
                oddNums[index] = Integer.parseInt(st.nextToken());
            }
            else {
                evenNums[index] = Integer.parseInt(st.nextToken());
            }
        }

        init(1, 1, N, oddTree, oddNums);
        init(1, 1, N, evenTree, evenNums);

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == UPDATE) {
                int index = Integer.parseInt(st.nextToken());
                int diff = Integer.parseInt(st.nextToken());
                if (index % 2 == 0) {
                    update(1, index, 1, N, diff, evenTree);
                }
                else {
                    update(1, index, 1, N, diff, oddTree);
                }
            }
            else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                long sumOfOddIndices = sum(1, 1, N, left, right, oddTree);
                long sumOfEvenIndices = sum(1, 1, N, left, right, evenTree);
                long value = Math.abs(sumOfEvenIndices - sumOfOddIndices);
                sb.append(value)
                        .append(NEW_LINE);
            }
        }
        System.out.print(sb);
    }

    private static long init(int node, int start, int end, long[] tree, int[] nums) {
        if (start == end) {
            return tree[node] = nums[start];
        }
        int mid = (start + end) >> 1;
        tree[node] = init(node * 2, start, mid, tree, nums) + init(node * 2 + 1, mid + 1, end, tree, nums);
        return tree[node];
    }

    private static void update(int node, int index, int start, int end, int diff, long[] tree) {
        if (!(start <= index && index <= end)) {
            return;
        }

        tree[node] += diff;

        if (start == end) {
            return;
        }

        int mid = (start + end) >> 1;
        update(node * 2, index, start, mid, diff, tree);
        update(node * 2 + 1, index, mid + 1, end, diff, tree);
    }

    private static long sum(int node, int start, int end, int left, int right, long[] tree) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        return sum(node * 2, start, mid, left, right, tree) + sum(node * 2 + 1, mid + 1, end, left, right, tree);
    }
}
