package minimum_spanning_tree;

// https://www.acmicpc.net/problem/4386

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4386 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Star[] stars = new Star[N];

        for (int id = 0; id < N; id++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[id] = new Star(x, y);
        }

        Queue<Link> pq = initPriorityQueue(stars, N);
        double minimumCost = minimumSpanningTree(pq, N);

        System.out.println(minimumCost);
    }

    private static class Star {
        double x;
        double y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Link implements Comparable<Link> {
        int star1;
        int star2;
        double cost;

        Link(int star1, int star2, double cost) {
            this.star1 = star1;
            this.star2 = star2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Link other) {
            return Double.compare(this.cost, other.cost);
        }
    }

    private static Queue<Link> initPriorityQueue(Star[] stars, int N) {
        Queue<Link> pq = new PriorityQueue<>();

        for (int id1 = 0; id1 < N; id1++) {
            Star star1 = stars[id1];
            for (int id2 = id1 + 1; id2 < N; id2++) {
                Star star2 = stars[id2];
                double cost = Math.sqrt(Math.pow(star1.x - star2.x, 2) + Math.pow(star1.y - star2.y, 2));
                pq.offer(new Link(id1, id2, cost));
            }
        }

        return pq;
    }

    private static double minimumSpanningTree(Queue<Link> pq, int N) {
        double minimumCost = 0;
        int count = 0;

        int[] parents = new int[N];
        Arrays.fill(parents, -1);

        while (!pq.isEmpty()) {
            Link link = pq.poll();

            if (merge(link.star1, link.star2, parents)) {
                minimumCost += link.cost;

                if (count == N - 1) {
                    break;
                }
            }
        }

        return minimumCost;
    }

    private static boolean merge(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x == y) {
            return false;
        }
        if (parents[x] < parents[y]) {
            parents[x] += parents[y];
            parents[y] = x;
        }
        else {
            parents[y] += parents[x];
            parents[x] = y;
        }

        return true;
    }

    private static int find(int x, int[] parents) {
        if (parents[x] < 0) {
            return x;
        }
        return parents[x] = find(parents[x], parents);
    }
}
