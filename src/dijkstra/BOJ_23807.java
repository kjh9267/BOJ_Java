package dijkstra;

// https://www.acmicpc.net/problem/23807

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_23807 {

    private static final long INF = 300_000_000_001L;

    private static final int IMPOSSIBLE = -1;

    private static final int TARGET_CANDIDATES_COUNT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] candidates = new int[P];
        int[] indexOfCandidates = new int[N + 1];

        indexOfCandidates[start] = 0;
        for (int index = 0; index < P; index++) {
            int node = Integer.parseInt(st.nextToken());
            candidates[index] = node;
            indexOfCandidates[node] = index + 1;
        }

        long[][] dist = new long[N + 1][P + 1];

        for (int node = 0; node <= N; node++) {
            Arrays.fill(dist[node], INF);
        }

        dijkstra(start, 0, graph, dist, N);

        for (int index = 1; index <= P; index++) {
            int startOfCandidate = candidates[index - 1];
            dijkstra(startOfCandidate, index, graph, dist, N);
        }

        boolean[] visited = new boolean[N + 1];
        long[] minValue = {INF};
        int[] targetCandidates = new int[TARGET_CANDIDATES_COUNT];

        dfs(0, candidates, targetCandidates, visited, minValue, dist, end, indexOfCandidates);

        System.out.println(minValue[0] == INF ? IMPOSSIBLE: minValue[0]);
    }

    private static class Node implements Comparable<Node> {
        int id;
        long cost;

        Node (int id, long cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    private static void dfs(int depth, int[] candidates, int[] targetCandidates, boolean[] visited, long[] minValue, long[][] dist, int end, int[] indexOfCandidates) {
        if (depth == TARGET_CANDIDATES_COUNT) {
            countMinValue(targetCandidates, minValue, dist, end, indexOfCandidates);
            return;
        }

        for (int index = 0; index < candidates.length; index++) {
            if (visited[index]) {
                continue;
            }
            visited[index] = true;
            targetCandidates[depth] = candidates[index];
            dfs(depth + 1, candidates, targetCandidates, visited, minValue, dist, end, indexOfCandidates);
            visited[index] = false;
        }
    }

    private static void countMinValue(int[] targetCandidates, long[] minValue, long[][] dist, int end, int[] indexOfCandidates) {
        long value = dist[targetCandidates[0]][0];

        for (int index = 0; index < TARGET_CANDIDATES_COUNT - 1; index++) {
            int s = targetCandidates[index];
            int indexOfS = indexOfCandidates[s];
            int e = targetCandidates[index + 1];
            value += dist[e][indexOfS];
        }

        int s = targetCandidates[TARGET_CANDIDATES_COUNT - 1];
        int indexOfS = indexOfCandidates[s];
        value += dist[end][indexOfS];

        minValue[0] = Math.min(minValue[0], value);
    }

    private static void dijkstra(int start, int candidateIndex, List<Node>[] graph, long[][] dist, int N) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        dist[start][candidateIndex] = 0;

        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.id]) {
                continue;
            }
            visited[cur.id] = true;

            for (Node next: graph[cur.id]) {
                if (dist[next.id][candidateIndex] <= dist[cur.id][candidateIndex] + next.cost) {
                    continue;
                }
                dist[next.id][candidateIndex] = dist[cur.id][candidateIndex] + next.cost;
                pq.offer(new Node(next.id, dist[next.id][candidateIndex]));
            }
        }
    }
}
