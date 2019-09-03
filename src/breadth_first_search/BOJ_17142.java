package breadth_first_search;

// https://www.acmicpc.net/problem/17142

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17142 {
    public static final int MAX = Integer.MAX_VALUE;
    public static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0 ,-1}};
    public static int N, M, vCnt, res = MAX;
    public static int[][] grid;
    public static ArrayList<Node> virus;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        virus = new ArrayList<>();
        boolean key = true;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int value = Integer.parseInt(st.nextToken());
                grid[i][j] = value;
                if(value == 2) {
                    virus.add(new Node(j, i));
                    vCnt += 1;
                }
                if (value == 0)
                    key = false;
            }
        }
        if(key)
            System.out.println(0);
        else {
            dfs(0, 0, new Node[M]);
            System.out.println(res == MAX ? -1 : res);
        }
    }
    public static void dfs(int cur, int depth, Node[] data){
        if(depth == M) {
            res = Math.min(res, bfs(data));
            return;
        }
        for(int idx = cur; idx < vCnt; idx++){
            data[depth] = virus.get(idx);
            dfs(idx + 1,depth + 1, data);
        }
    }
    public static int bfs(Node[] data){
        Queue<Node> queue = new LinkedList<>();
        int[][] visited = new int[N][N];
        for(int i = 0; i < N; i++)
            Arrays.fill(visited[i], -1);
        for(Node n : data){
            queue.offer(n);
            visited[n.y][n.x] = 0;
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int[] dir : DIR) {
                int xx = cur.x + dir[0];
                int yy = cur.y + dir[1];
                if (!(0 <= xx && xx < N && 0 <= yy && yy < N))
                    continue;
                if (visited[yy][xx] != -1)
                    continue;
                if (grid[yy][xx] == 1)
                    continue;
                queue.add(new Node(xx, yy));
                visited[yy][xx] = visited[cur.y][cur.x] + 1;
            }
        }
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j< N; j++){
                if(grid[i][j] == 1)
                    continue;
                if(visited[i][j] == -1)
                    return MAX;
                if (grid[i][j] == 2)
                    visited[i][j] -= 1;
                cnt = Math.max(cnt, visited[i][j]);
            }
        }
        return cnt;
    }
    public static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
