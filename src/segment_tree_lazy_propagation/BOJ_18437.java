package segment_tree_lazy_propagation;

// https://www.acmicpc.net/problem/18437

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class BOJ_18437 {

    private static final String NEW_LINE = "\n";

    private static final int EMPTY = -1;

    private static final int ON = 1;

    private static final int OFF = 2;

    private static int index;

    private static Range[] ranges;

    private static List<Integer>[] graph;

    private static int[] tree;

    private static int[] lazy;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        ranges = new Range[N + 1];
        int height = (int) ceil(log(N) / log(2)) + 1;
        int size = (int) pow(2, height);
        tree = new int[size];
        lazy = new int[size];

        Arrays.fill(lazy, EMPTY);

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        for (int node = 2; node <= N; node++) {
            int parent = Integer.parseInt(st.nextToken());
            graph[parent].add(node);
        }

        dfs(1);
        init(1, 1, N);

        int M = Integer.parseInt(br.readLine());

        for (int query = 0; query < M; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());

            if (command == ON) {
                if (ranges[index].start != ranges[index].end) {
                    rangeUpdate(1, 1, N, ranges[index].start + 1, ranges[index].end, 1);
                }
            }
            else if (command == OFF) {
                if (ranges[index].start != ranges[index].end) {
                    rangeUpdate(1, 1, N, ranges[index].start + 1, ranges[index].end, 0);
                }
            }
            else {
                int value = read(1, 1, N, ranges[index].start + 1, ranges[index].end);
                sb.append(value)
                        .append(NEW_LINE);
            }
        }

        System.out.print(sb);
    }

    private static class Range {
        int start;
        int end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static void dfs(int cur) {
        index += 1;
        int start = index;

        for (int next: graph[cur]) {
            dfs(next);
        }

        ranges[cur] = new Range(start, index);
    }

    private static int init(int node, int start, int end) {
        if (start == end) {
            tree[node] = 1;
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int leftNode = init(node * 2, start, mid);
        int rightNode = init(node * 2 + 1, mid + 1, end);
        tree[node] = leftNode + rightNode;

        return tree[node];
    }

    private static int rangeUpdate(int node, int start, int end, int left, int right, int value) {
        propagate(node, start, end);
        if (left > end || right < start) {
            return tree[node];
        }
        if (left <= start && end <= right) {
            tree[node] = value * (end - start + 1);

            if (start != end) {
                lazy[node * 2] = value;
                lazy[node * 2 + 1] = value;
            }

            return tree[node];
        }

        int mid = (start + end) >> 1;
        int leftNode = rangeUpdate(node * 2, start, mid, left, right, value);
        int rightNode = rangeUpdate(node * 2 + 1, mid + 1, end, left, right, value);
        tree[node] = leftNode + rightNode;

        return tree[node];
    }

    private static void propagate(int node, int start, int end) {
        if (lazy[node] == EMPTY) {
            return;
        }

        tree[node] = lazy[node] * (end - start + 1);

        if (start != end) {
            lazy[node * 2] = lazy[node];
            lazy[node * 2 + 1] = lazy[node];
        }

        lazy[node] = EMPTY;
    }

    private static int read(int node, int start, int end, int left, int right) {
        propagate(node, start, end);

        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) >> 1;
        int leftNode = read(node * 2, start, mid, left, right);
        int rightNode = read(node * 2 + 1, mid + 1, end, left, right);

        return leftNode + rightNode;
    }
}
