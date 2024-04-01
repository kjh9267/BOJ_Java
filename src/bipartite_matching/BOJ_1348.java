package bipartite_matching;

// https://www.acmicpc.net/problem/1348

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.max;

public class BOJ_1348 {

    private static final char CAR = 'C';

    private static final char PARKING_LOT = 'P';

    private static final char WALL = 'X';

    private static final int NOT_VISITED = -1;

    private static final int IMPOSSIBLE = -1;

    private static final int INF = 2_501;

    private static final int[][] DIR = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    private static int R;

    private static int C;

    private static int size;

    private static char[][] grid;

    private static List<Node> cars;

    private static List<Integer>[] graph;

    private static int[][] costs;

    private static int[] A;

    private static int[] B;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        grid = new char[R][C];

        for (int row = 0; row < R; row++) {
            grid[row] = br.readLine()
                    .toCharArray();
        }

        init();
        int result = binarySearch();

        System.out.println(result);
    }

    private static class Node {
        int id;
        int startX;
        int startY;
        int x;
        int y;

        Node(int id, int startX, int startY, int x, int y) {
            this.id = id;
            this.startX = startX;
            this.startY = startY;
            this.x = x;
            this.y = y;
        }
    }

    private static void init() {
        size = R * C;
        graph = new List[size];
        costs = new int[size][size];

        for (int node = 0; node < size; node++) {
            graph[node] = new ArrayList<>();
        }

        findCars();
        bfs();
    }

    private static void findCars() {
        cars = new ArrayList<>();
        int id = 0;

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (grid[row][col] != CAR) {
                    continue;
                }
                cars.add(new Node(id, col, row, col, row));
                id += 1;
            }
        }
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        int[][][] visited = new int[R][C][cars.size()];

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                Arrays.fill(visited[row][col], NOT_VISITED);
            }
        }

        for (Node car: cars) {
            queue.offer(car);
            visited[car.y][car.x][car.id] = 0;
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int[] dir: DIR) {
                int nextX = cur.x + dir[0];
                int nextY = cur.y + dir[1];

                if (nextX < 0 || nextX == C || nextY < 0 || nextY == R) {
                    continue;
                }
                if (grid[nextY][nextX] == WALL) {
                    continue;
                }
                if (visited[nextY][nextX][cur.id] != NOT_VISITED) {
                    continue;
                }
                queue.offer(new Node(cur.id, cur.startX, cur.startY, nextX, nextY));
                visited[nextY][nextX][cur.id] = visited[cur.y][cur.x][cur.id] + 1;

                if (grid[nextY][nextX] == PARKING_LOT) {
                    connect(toNode(cur.startY, cur.startX), toNode(nextY, nextX), visited[nextY][nextX][cur.id]);
                }
            }
        }
    }

    private static int toNode(int row, int col) {
        return row * C + col;
    }

    private static void connect(int start, int end, int cost) {
        graph[start].add(end);
        costs[start][end] = cost;
    }

    private static int binarySearch() {
        int lo = -1;
        int hi = INF;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            int result = bipartiteMatch(mid);

            if (result != IMPOSSIBLE) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        if (hi == INF) {
            return IMPOSSIBLE;
        }

        return hi;
    }

    private static int bipartiteMatch(int limit) {
        int total = 0;
        int time = 0;
        A = new int[size];
        B = new int[size];

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int node = 0; node < size; node++) {
            visited = new boolean[size];

            if (dfs(node, limit)) {
                total += 1;
            }
        }

        if (total < cars.size()) {
            return IMPOSSIBLE;
        }

        for (int a = 0; a < size; a++) {
            if (A[a] != NOT_VISITED) {
                time = max(time, costs[a][A[a]]);
            }
        }

        return time;
    }

    private static boolean dfs(int a, int limit) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b: graph[a]) {
            if (costs[a][b] > limit) {
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
}
