package minimum_spanning_tree;

// https://www.acmicpc.net/problem/17472

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472 {
    private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int N;
    private static int M;
    private static int[][] grid;
    private static PriorityQueue<Node> pq = new PriorityQueue<>();
    private static boolean[][] visited;
    private static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];
        visited = new boolean[N][M];

        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < M; col++){
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(visited[row][col] || grid[row][col] == 0)
                    continue;
                dfs(col, row, ++cnt);
            }
        }
        parent = new int[cnt];
        Arrays.fill(parent, -1);

        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(grid[row][col] == 0)
                    continue;
                for(int idx = 0; idx < 4; idx++)
                    connect(col, row, grid[row][col], idx, 0);
            }
        }
        System.out.println(MST(cnt));
    }

    private static void dfs(int x, int y, int cnt){
        if(visited[y][x])
            return;
        visited[y][x] = true;
        grid[y][x] = cnt;
        for(int[] dir: DIR){
            int xx = x + dir[0];
            int yy = y + dir[1];
            if(!(0 <= xx && xx < M && 0 <= yy && yy < N))
                continue;
            if(grid[yy][xx] == 0)
                continue;
            dfs(xx, yy, cnt);
        }
    }

    private static void connect(int x, int y, int start, int idx, int depth){
        int xx = x + DIR[idx][0];
        int yy = y + DIR[idx][1];
        if(!(0 <= xx && xx < M && 0 <= yy && yy < N))
            return;
        if(grid[yy][xx] == start)
            return;
        if(grid[yy][xx] != 0 && depth > 1)
            pq.offer(new Node(start - 1, grid[yy][xx] - 1, depth));
        else if(grid[yy][xx] == 0)
            connect(xx, yy, start, idx, depth + 1);
    }

    private static int find(int x){
        if(parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y)
            return false;
        if(parent[x] > parent[y]){
            parent[y] += parent[x];
            parent[x] = y;
            return true;
        }
        parent[x] += parent[y];
        parent[y] = x;
        return true;
    }

    private static int MST(int cnt){
        int link = 0, minCost = 0;
        while (!pq.isEmpty()){
            Node cur = pq.poll();
            if(merge(cur.from, cur.to)){
                minCost += cur.cost;
                if(cnt - 1 == ++link)
                    break;
            }
        }
        return link == cnt - 1 ? minCost : -1;
    }

    private static class Node implements Comparable<Node>{
        int from, to, cost;
        public Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
