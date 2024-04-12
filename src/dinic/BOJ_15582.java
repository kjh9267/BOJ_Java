package dinic;

// https://www.acmicpc.net/problem/15582

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.*;

public class BOJ_15582 {

    private static final String NEW_LINE = "\n";

    private static final int INF = 8_000_001;

    private static final int NOT_VISITED = -1;

    private static final int IMPOSSIBLE = -1;

    private static final int SOURCE = 0;

    private static int N;

    private static int M;

    private static int C;

    private static int K;

    private static Person[] people;

    private static Station[] stations;

    private static List<Integer>[] data;

    private static int sink;

    private static int size;

    private static List<Integer>[] graph;

    private static int[][] capacities;

    private static int[][] flows;

    private static int[][] dists;

    private static int[] result;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        people = new Person[N];
        stations = new Station[M];
        data = new List[K];

        for (int index = 0; index < N; index++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            people[index] = new Person(x, y);
        }

        for (int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stations[index] = new Station(x, y);
        }

        for (int index = 0; index < K; index++) {
            data[index] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            st.nextToken();

            while (st.hasMoreTokens()) {
                data[index].add(Integer.parseInt(st.nextToken()));
            }
        }

        int dist = binarySearch();

        if (dist == INF) {
            System.out.println(IMPOSSIBLE);
        }
        else {
            System.out.println(dist);

            for (int node: result) {
                sb.append(node)
                        .append(NEW_LINE);
            }

            System.out.print(sb);
        }
    }

    private static class Person {
        int x;
        int y;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Station {
        int x;
        int y;

        Station(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void init(int limit) {
        sink = N + M + K + 2;
        size = N + M + K + 3;
        graph = new List[size];
        capacities = new int[size][size];
        flows = new int[size][size];
        dists = new int[N][M];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int node = 1; node <= N; node++) {
            connect(SOURCE, node, 1);
        }

        for (int dataIndex = 0; dataIndex < K; dataIndex++) {
            for (int stationIndex: data[dataIndex]) {
                connect(N + stationIndex, N + M + dataIndex + 1, INF);
            }
        }

        for (int node = N + M + 1; node <= N + M + K; node++) {
            connect(node, sink - 1, C);
        }

        connect(sink - 1, sink, N);

        for (int personIndex = 0; personIndex < N; personIndex++) {
            for (int stationIndex = 0; stationIndex < M; stationIndex++) {
                int dist = computeDist(people[personIndex], stations[stationIndex]);

                if (dist > limit) {
                    continue;
                }
                connect(personIndex + 1, N + stationIndex + 1, 1);
                dists[personIndex][stationIndex] = dist;
            }
        }
    }

    private static void connect(int start, int end, int capacity) {
        graph[start].add(end);
        graph[end].add(start);
        capacities[start][end] = capacity;
    }

    private static int computeDist(Person person, Station station) {
        return (int) pow(person.x - station.x, 2) + (int) pow(person.y - station.y, 2);
    }

    private static int binarySearch() {
        int lo = -1;
        int hi = INF;
        int[] prevResult = new int[N];
        result = new int[N];

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;

            init(mid);
            computeDinic();
            computeResult();

            if (isPossible()) {
                hi = mid;
                prevResult = result;
            }
            else {
                lo = mid;
                result = prevResult;
            }
        }

        return hi;
    }

    private static void computeDinic() {
        while (true) {
            int[] level = bfs();

            if (level[sink] == NOT_VISITED) {
                break;
            }

            int[] work = new int[size];
            while (true) {
                int flowValue = dfs(SOURCE, INF, level, work);

                if (flowValue == 0) {
                    break;
                }
            }
        }
    }

    private static int[] bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        int[] level = new int[size];
        Arrays.fill(level, NOT_VISITED);
        level[SOURCE] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next: graph[cur]) {
                if (level[next] != NOT_VISITED) {
                    continue;
                }
                if (capacities[cur][next] - flows[cur][next] <= 0) {
                    continue;
                }
                level[next] = level[cur] + 1;
                queue.offer(next);
            }
        }

        return level;
    }

    private static int dfs(int cur, int flowValue, int[] level, int[] work) {
        if (cur == sink) {
            return flowValue;
        }

        for (int index = work[cur]; index < graph[cur].size(); index++) {
            work[cur] = index;
            int next = graph[cur].get(index);

            if (level[next] != level[cur] + 1) {
                continue;
            }
            if (capacities[cur][next] - flows[cur][next] <= 0) {
                continue;
            }
            int minFlowValue = min(flowValue, capacities[cur][next] - flows[cur][next]);
            minFlowValue = dfs(next, minFlowValue, level, work);

            if (minFlowValue == 0) {
                continue;
            }

            flows[cur][next] += minFlowValue;
            flows[next][cur] -= minFlowValue;

            return minFlowValue;
        }

        return 0;
    }

    private static boolean isPossible() {
        for (int person = 1; person <= N; person++) {
            int sum = 0;

            for (int station = N + 1; station <= N + M; station++) {
                if (flows[person][station] > 0) {
                    sum += 1;
                }
            }
            if (sum == 0) {
                return false;
            }
        }

        return true;
    }

    private static void computeResult() {
        for (int person = 1; person <= N; person++) {
            for (int station = N + 1; station <= N + M; station++) {
                if (flows[person][station] <= 0) {
                    continue;
                }
                result[person - 1] = station - N;
            }
        }
    }
}
