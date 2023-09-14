package segment_tree;

// https://www.acmicpc.net/problem/14428

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14428 {

    private static final int UPDATE = 1;

    private static final int INF = 1_000_000_001;

    private static final Node INF_NODE = new Node(INF, INF);

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N + 1];
        int height = (int) (Math.log(N) / Math.log(2)) + 1;
        int size = (int) Math.pow(2, height + 1);
        Node[] tree = new Node[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= N; index++) {
            int num = Integer.parseInt(st.nextToken());
            nums[index] = num;
        }

        init(1, 1, N, tree, nums);
        int M = Integer.parseInt(br.readLine());

        for (int query = 0; query < M; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == UPDATE) {
                int index = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                update(1, index, value, 1, N, tree);
            }
            else {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                Node minimum = findMinimumValue(1, 1, N, left, right, tree);
                sb.append(minimum.index)
                        .append(NEW_LINE);
            }
        }

        System.out.print(sb);
    }

    private static class Node implements Comparable<Node> {
        int value;
        int index;

        Node (int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node other) {
            if (this.value == other.value) {
                return Integer.compare(this.index, other.index);
            }
            return Integer.compare(this.value, other.value);
        }
    }

    private static Node init(int node, int start, int end, Node[] tree, int[] nums) {
        if (start == end) {
            return tree[node] = new Node(nums[start], start);
        }
        int mid = (start + end) >> 1;

        Node leftNode = init(node << 1, start, mid, tree, nums);
        Node rightNode = init((node << 1) + 1, mid + 1, end, tree, nums);

        if (leftNode.compareTo(rightNode) < 0) {
            return tree[node] = leftNode;
        }
        return tree[node] = rightNode;
    }

    private static Node update(int node, int index, int value, int start, int end, Node[] tree) {
        if (!(start <= index && index <= end)) {
            return tree[node];
        }

        if (tree[node].index == index) {
            tree[node] = new Node(value, index);
        }

        if (start == end) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        Node leftNode = update(node << 1, index, value, start, mid, tree);
        Node rightNode = update((node << 1) + 1, index, value, mid + 1, end, tree);

        if (leftNode.compareTo(rightNode) < 0) {
            return tree[node] = leftNode;
        }
        return tree[node] = rightNode;
    }

    private static Node findMinimumValue(int node, int start, int end, int left, int right, Node[] tree) {
        if (left > end || right < start) {
            return INF_NODE;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        Node leftNode = findMinimumValue(node << 1, start, mid, left, right, tree);
        Node rightNode = findMinimumValue((node << 1) + 1, mid + 1, end, left, right, tree);

        if (leftNode.compareTo(rightNode) < 0) {
            return leftNode;
        }
        return rightNode;
    }
}
