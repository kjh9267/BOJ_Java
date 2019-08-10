package depth_first_search;

// https://www.acmicpc.net/problem/16234

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_16234 {
    public static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int N, L, R, depth, sum, value;
    public static int[][] grid;
    public static boolean[][] visited;
    public static Stack<Node> stack;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        stack = new Stack<Node>();
        int res = 0;

        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++){
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            boolean key = true;
            visited = new boolean[N][N];
            for(int row = 0; row < N; row++){
                for(int col = 0; col < N; col++){
                    if(visited[row][col])
                        continue;
                    depth = 0;
                    sum = 0;
                    dfs(col, row);
                    value = sum / depth;
                    while (!stack.isEmpty()){
                        Node cur = stack.pop();
                        grid[cur.y][cur.x] = value;
                    }
                    if(depth > 1)
                        key = false;
                }
            }
            if(key)
                break;
            res += 1;
        }
        System.out.println(res);
    }

    public static void dfs(int x, int y){
        if(visited[y][x])
            return;
        visited[y][x] = true;
        depth += 1;
        sum += grid[y][x];
        stack.push(new Node(x, y));
        for(int[] d: DIR){
            int xx = x + d[0];
            int yy = y + d[1];
            if(yy < 0 || yy >= N || xx < 0 || xx >= N)
                continue;
            int diff = Math.abs(grid[yy][xx] - grid[y][x]);
            if(L <= diff && diff <= R)
                dfs(xx, yy);
        }
    }

    public static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
