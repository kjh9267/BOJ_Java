package back_tracking;

// https://www.acmicpc.net/problem/20208

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20208 {
    private static char[][] grid;
    private static boolean[][] visited;
    private static int M;
    private static ArrayList<Milk> milks;
    private static int homeX;
    private static int homeY;
    private static int maxVisitMilkCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][N];
        grid = new char[N][N];
        milks = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                grid[row][col] = st.nextToken().charAt(0);

                if (grid[row][col] == '1') {
                    homeX += col;
                    homeY += row;
                    milks.add(new Milk(homeX, homeY));
                }
                else if (grid[row][col] == '2') {
                    milks.add(new Milk(col, row));
                }
            }
        }

        dfs(homeX, homeY, H, 0);
        System.out.println(Math.max(maxVisitMilkCount - 1, 0));
    }

    private static class Milk {
        int x;
        int y;

        private Milk(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int findDistance(int x, int y, int nextX, int nextY) {
        return Math.abs(x - nextX) + Math.abs(y - nextY);
    }

    private static void dfs(int x, int y, int health, int visitMilkCount) {
        if (x == homeX && y == homeY && visitMilkCount > 0) {
            maxVisitMilkCount = Math.max(maxVisitMilkCount, visitMilkCount);
            return;
        }

        for (Milk next: milks) {
            if (visited[next.y][next.x]) {
                continue;
            }
            if (x == next.x && y == next.y) {
                continue;
            }
            int dist = findDistance(x, y, next.x, next.y);
            if (dist > health) {
                continue;
            }
            visited[next.y][next.x] = true;
            dfs(next.x, next.y, health - dist + M, visitMilkCount + 1);
            visited[next.y][next.x] = false;
        }
    }
}
