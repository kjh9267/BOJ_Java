package minimum_spanning_tree;

// https://www.acmicpc.net/problem/1774

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1774 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Point[] points = new Point[N];

        for (int id = 0; id < N; id++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[id] = new Point(id, x, y);
        }

        int[] parents = new int[N];
        Arrays.fill(parents, -1);

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int point1 = Integer.parseInt(st.nextToken()) - 1;
            int point2 = Integer.parseInt(st.nextToken()) - 1;
            merge(point1, point2, parents);
        }

        Queue<Link> pq = initPriorityQueue(points);

        double minimumCost = minimumSpanningTree(pq, parents, N, M);

        System.out.println(String.format("%.2f", minimumCost));
    }

    private static class Point {
        int id;
        int x;
        int y;

        Point(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }

    private static class Link implements Comparable<Link> {
        int point1;
        int point2;
        double cost;

        Link(int point1, int point2, double cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Link other) {
            return Double.compare(this.cost, other.cost);
        }
    }

    private static boolean merge(int point1, int point2, int[] parents) {
        point1 = find(point1, parents);
        point2 = find(point2, parents);

        if (point1 == point2) {
            return false;
        }
        if (parents[point1] < parents[point2]) {
            parents[point1] += parents[point2];
            parents[point2] = point1;
        }
        else {
            parents[point2] += parents[point1];
            parents[point1] = point2;
        }

        return true;
    }

    private static int find(int pointId, int[] parents) {
        if (parents[pointId] < 0) {
            return pointId;
        }
        return parents[pointId] = find(parents[pointId], parents);
    }

    private static Queue<Link> initPriorityQueue(Point[] points) {
        Queue<Link> pq = new PriorityQueue<>();

        for (Point point1: points) {
            for (Point point2: points) {
                double cost = Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
                pq.offer(new Link(point1.id, point2.id, cost));
            }
        }

        return pq;
    }

    private static double minimumSpanningTree(Queue<Link> pq, int[] parents, int N, int M) {
        double minimumCost = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            Link link = pq.poll();

            if (!merge(link.point1, link.point2, parents)) {
                continue;
            }
            count += 1;
            minimumCost += link.cost;

            if (count == N - 1) {
                break;
            }
        }

        return minimumCost;
    }
}
