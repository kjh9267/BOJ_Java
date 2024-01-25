package dinic;

// https://www.acmicpc.net/problem/1671

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1671 {

    private static final int SOURCE = 0;

    private static final int CAPACITY = 2;

    private static final int NOT_VISITED = -1;

    private static final int INF = 51;

    private static int sink;

    private static int size;

    private static List<Integer>[] graph;

    private static List<Integer>[] capacities;

    private static List<Integer>[] flows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Shark[] sharks = new Shark[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Shark shark = new Shark(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );

            sharks[index] = shark;
        }

        init(sharks, N);
        int flowValue = dinic();

        System.out.println(N - flowValue);
    }

    private static class Shark implements Comparable<Shark> {
        int size;
        int speed;
        int intelligence;

        Shark(int size, int speed, int intelligence) {
            this.size = size;
            this.speed = speed;
            this.intelligence = intelligence;
        }

        @Override
        public int compareTo(Shark other) {
            if (this.size == other.size && this.speed == other.speed && this.intelligence == other.intelligence) {
                return 0;
            }
            if (this.size >= other.size && this.speed >= other.speed && this.intelligence >= other.intelligence) {
                return 1;
            }
            return -1;
        }
    }

    private static void init(Shark[] sharks, int N) {
        sink = N * 2 * 2 + 2;
        size = N * 2 * 2 + 3;
        graph = new List[size];
        capacities = new List[size];
        flows = new List[size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
            capacities[node] = new ArrayList<>();
            flows[node] = new ArrayList<>();
        }

        for (int node = 1; node <= N; node++) {
            graph[SOURCE].add(node * 2);
            graph[node * 2].add(SOURCE);
            capacities[SOURCE].add(CAPACITY);
            capacities[node * 2].add(0);
            flows[SOURCE].add(0);
            flows[node * 2].add(0);
        }

        for (int node = N + 1; node <= N * 2; node++) {
            graph[node * 2 + 1].add(sink);
            graph[sink].add(node * 2 + 1);
            capacities[node * 2 + 1].add(1);
            capacities[sink].add(0);
            flows[node * 2 + 1].add(0);
            flows[sink].add(0);
        }

        for (int node = 1; node <= N * 2; node++) {
            graph[node * 2].add(node * 2 + 1);
            graph[node * 2 + 1].add(node * 2);
            capacities[node * 2].add(CAPACITY);
            capacities[node * 2 + 1].add(0);
            flows[node * 2].add(0);
            flows[node * 2 + 1].add(0);
        }

        for (int node = 1; node <= N; node++) {
            Shark shark = sharks[node - 1];

            for (int nextNode = 1; nextNode <= N; nextNode++) {
                if (node == nextNode) {
                    continue;
                }
                Shark nextShark = sharks[nextNode - 1];

                if (shark.compareTo(nextShark) > 0 || (shark.compareTo(nextShark) == 0 && node < nextNode)) {
                    graph[node * 2 + 1].add((nextNode + N) * 2);
                    graph[(nextNode + N) * 2].add(node * 2 + 1);
                    capacities[node * 2 + 1].add(1);
                    capacities[(nextNode + N) * 2].add(0);
                    flows[node * 2 + 1].add(0);
                    flows[(nextNode + N) * 2].add(0);
                }
            }
        }
    }

    private static int dinic() {
        int totalFlow = 0;

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
                totalFlow += flowValue;
            }
        }

        return totalFlow;
    }

    private static int[] bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(SOURCE);

        int[] level = new int[size];
        Arrays.fill(level, NOT_VISITED);
        level[SOURCE] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int index = 0; index < graph[cur].size(); index++) {
                int next = graph[cur].get(index);
                int capacity = capacities[cur].get(index);
                int flow = flows[cur].get(index);

                if (level[next] != NOT_VISITED) {
                    continue;
                }
                if (capacity - flow <= 0) {
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
            int capacity = capacities[cur].get(index);
            int flow = flows[cur].get(index);

            if (level[next] != level[cur] + 1) {
                continue;
            }
            if (capacity - flow <= 0) {
                continue;
            }
            int minFlowValue = Math.min(flowValue, capacity - flow);
            minFlowValue = dfs(next, minFlowValue, level, work);

            if (minFlowValue == 0) {
                continue;
            }

            flows[cur].set(index, flows[cur].get(index) + minFlowValue);
            int reverseIndex = graph[next].indexOf(cur);
            flows[next].set(reverseIndex, flows[next].get(reverseIndex) - minFlowValue);

            return minFlowValue;
        }

        return 0;
    }
}
