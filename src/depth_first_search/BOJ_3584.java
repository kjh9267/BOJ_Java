package depth_first_search;

// https://www.acmicpc.net/problem/3584

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3584 {
    private static int[] adj;
    private static boolean[] visited;
    private static int res;

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0){
            int N = Integer.parseInt(br.readLine());
            adj = new int[N + 1];
            visited = new boolean[N + 1];
            for(int i = 0; i < N - 1; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                adj[B] = A;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            dfs(x);
            dfs(y);
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

    private static void dfs(int cur){
        if(visited[cur]) {
            res = cur;
            return;
        }
        visited[cur] = true;
        dfs(adj[cur]);
    }
}
