package depth_first_search;

// https://www.acmicpc.net/problem/14657

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14657 {

    private static int maxCount;

    private static int leaf;

    private static final int INF = 500_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<Node>[] tree = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            tree[node] = new ArrayList<>();
        }

        for (int link = 0; link < N - 1; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            tree[start].add(new Node(end, cost));
            tree[end].add(new Node(start, cost));
        }

        Set<Integer> leafCandidates = new HashSet<>();
        Set<Integer> timeCandidates = new HashSet<>();
        findLeaf(1, 0, 0, tree, leafCandidates);
        leafCandidates = new HashSet<>();
        findLeaf(leaf, 0, 0, tree, leafCandidates);

        int minTotal = INF;
        for (int start: leafCandidates) {
            dfs(start, 0, 0, 0, tree, timeCandidates, minTotal);
            if (!timeCandidates.isEmpty()) {
                minTotal = timeCandidates.stream()
                        .reduce((x, y) -> x.compareTo(y) < 0 ? x : y)
                        .get();
            }
        }

        if (minTotal % T > 0) {
            System.out.println(minTotal / T + 1);
        }
        else {
            System.out.println(minTotal / T);
        }
    }

    private static class Node {
        int id;
        int cost;

        Node(int id, int cost) {
            this.id = id;
            this.cost = cost;
        }
    }

    private static void findLeaf(int cur, int prev, int count, List<Node>[] tree, Set<Integer> leafCandidates) {
        if (maxCount <= count) {
            maxCount = count;
            leaf = cur;
            leafCandidates.add(cur);
        }

        for (Node next: tree[cur]) {
            if (next.id == prev) {
                continue;
            }
            findLeaf(next.id, cur, count + 1, tree, leafCandidates);
        }
    }

    private static void dfs(int cur, int prev, int count, int total, List<Node>[] tree, Set<Integer> candidates, int minTotal) {
        if (minTotal < total) {
            return;
        }
        if (maxCount <= count) {
            maxCount = count;
            candidates.add(total);
        }

        for (Node next: tree[cur]) {
            if (next.id == prev) {
                continue;
            }
            dfs(next.id, cur, count + 1, total + next.cost, tree, candidates, minTotal);
        }
    }
}
