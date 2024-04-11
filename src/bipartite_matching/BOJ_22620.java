package bipartite_matching;

// https://www.acmicpc.net/problem/22620

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class BOJ_22620 {

    private static final double INF = 14_142.1356237310;

    private static final double DIFF = 1e-10;

    private static final int NOT_VISITED = -1;

    private static final int MINIMUM_VALUE = -1;

    private static final String NEW_LINE = "\n";

    private static int N;

    private static int M;

    private static Camp[] camps;

    private static Castle[] castles;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            camps = new Camp[N];
            castles = new Castle[M];

            for (int index = 0; index < N; index++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int velocity = Integer.parseInt(st.nextToken());
                camps[index] = new Camp(x, y, velocity);
            }

            for (int index = 0; index < M; index++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                castles[index] = new Castle(x, y);
            }

            double time = binarySearch();
            sb.append(time)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static class Camp {
        int x;
        int y;
        int velocity;

        Camp(int x, int y, int velocity) {
            this.x = x;
            this.y = y;
            this.velocity = velocity;
        }
    }

    private static class Castle {
        int x;
        int y;

        Castle(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double binarySearch() {
        double lo = 0.0;
        double hi = INF;

        while (lo + DIFF < hi) {
            double mid = (lo + hi) / 2;
            double time = bipartiteMatch(mid);

            if (time > MINIMUM_VALUE) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return hi;
    }

    private static double bipartiteMatch(double limit) {
        double maxTime = MINIMUM_VALUE;
        A = new int[N];
        B = new int[M];

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int a = 0; a < N; a++) {
            visited = new boolean[N];
            dfs(a, limit);
        }

        for (int b = 0; b < M; b++) {
            if (B[b] == NOT_VISITED) {
                return MINIMUM_VALUE;
            }
            double dist = computeDist(camps[B[b]], castles[b]);
            double time = dist / camps[B[b]].velocity;
            maxTime = max(maxTime, time);
        }

        return maxTime;
    }

    private static boolean dfs(int a, double limit) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b = 0; b < M; b++) {
            double dist = computeDist(camps[a], castles[b]);
            double time = dist / camps[a].velocity;

            if (time > limit) {
                continue;
            }
            if (B[b] == NOT_VISITED || dfs(B[b], limit)) {
                A[a] = b;
                B[b] = a;
                return true;
            }
        }

        return false;
    }

    private static double computeDist(Camp camp, Castle castle) {
        return sqrt(pow(camp.x - castle.x, 2) + pow(camp.y - castle.y, 2));
    }
}
