package back_tracking;

// https://www.acmicpc.net/problem/15686

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15686 {
    private static int N;
    private static int M;
    private static int cnt;
    private static int res = Integer.MAX_VALUE;
    private static char[][] grid;
    private static ArrayList<Node> chicken;
    private static ArrayList<Node> home;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();

        for(int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                char value = st.nextToken().charAt(0);
                grid[row][col] = value;
                if (value == '2') {
                    chicken.add(new Node(col, row));
                    cnt += 1;
                }
                else if(value == '1'){
                    home.add(new Node(col, row));
                }
            }
        }
        for(int i = 1; i <= M; i++)
            dfs(0, 0, i, new Node[i]);
        System.out.println(res);
    }

    private static void dfs(int depth, int cur, int end, Node[] data){
        if(depth == end) {
            res = Math.min(res, check(data));
            return;
        }
        if(cur == cnt)
            return;
        data[depth] = chicken.get(cur);
        dfs(depth + 1, cur + 1, end, data);
        dfs(depth, cur + 1, end, data);
    }

    private static int check(Node[] data){
        int res = 0;
        for(Node h : home){
            int temp = Integer.MAX_VALUE;
            for(Node c : data) {
                temp = Math.min(temp, Math.abs(h.x - c.x) + Math.abs(h.y - c.y));
            }
            res += temp;
        }
        return res;
    }

    private static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
