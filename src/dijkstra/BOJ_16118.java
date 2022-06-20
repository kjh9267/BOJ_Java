package dijkstra;

// https://www.acmicpc.net/problem/16118

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16118 {

    private static final int START = 1;

    private static final long INF = 22_000_000_000L;

    private static int N;

    private static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken()) * 2;

            graph[start].add(new Node(end, cost, 0));
            graph[end].add(new Node(start, cost, 0));
        }

        Dijkstra foxDijkstra = new FoxDijkstra();
        Dijkstra wolfDijkstra = new WolfDijkstra();

        long[][] foxDist = foxDijkstra.execute();
        long[][] wolfDist = wolfDijkstra.execute();

        int possibleCount = 0;

        for (int node = START + 1; node <= N; node++) {
            long foxMinDist = Math.min(foxDist[node][0], foxDist[node][1]);
            long wolfMinDist = Math.min(wolfDist[node][0], wolfDist[node][1]);

            if (foxMinDist < wolfMinDist) {
                possibleCount += 1;
            }
        }

        System.out.println(possibleCount);
    }

    private static class Node implements Comparable<Node> {
        int id;
        long cost;
        int depth;

        Node(int id, long cost, int depth) {
            this.id = id;
            this.cost = cost;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    private interface Dijkstra {
        long[][] execute();
    }

    private abstract static class DefaultDijkstra implements Dijkstra {

        @Override
        public long[][] execute() {
            Queue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(START, 0, 0));

            long[][] dist = new long[N + 1][2];

            for (int node = 1; node <= N; node++) {
                Arrays.fill(dist[node], INF);
            }

            dist[START][0] = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int nextDepth = (cur.depth + 1) % 2;

                if (dist[cur.id][cur.depth] < cur.cost) {
                    continue;
                }

                for (Node next: graph[cur.id]) {
                    long nextCost = calculateNextCost(cur, next);
                    if (dist[next.id][nextDepth] <= dist[cur.id][cur.depth] + nextCost) {
                        continue;
                    }
                    dist[next.id][nextDepth] = dist[cur.id][cur.depth] + nextCost;
                    pq.offer(new Node(next.id, dist[next.id][nextDepth], nextDepth));
                }
            }

            return dist;
        }

        abstract long calculateNextCost(Node cur, Node next);
    }

    private static class FoxDijkstra extends DefaultDijkstra {

        @Override
        long calculateNextCost(Node cur, Node next) {
            return next.cost;
        }
    }

    private static class WolfDijkstra extends DefaultDijkstra {

        @Override
        long calculateNextCost(Node cur, Node next) {
            if ((cur.depth % 2) == 0) {
                return next.cost / 2;
            }
            return next.cost * 2;
        }
    }
}
