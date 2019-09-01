package back_tracking;

// https://www.acmicpc.net/problem/14501

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14501 {
    public static int N, res;
    public static int[] time, cost;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N];
        cost = new int[N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, 0);
        System.out.println(res);
    }
    public static void dfs(int depth, int value){
        if(depth > N)
            return;
        res = Math.max(res, value);
        for(int idx = depth; idx < N; idx++){
            if(visited[idx])
                continue;
            visited[idx] = true;
            dfs(idx + time[idx], value + cost[idx]);
            visited[idx] = false;
        }
    }
}
