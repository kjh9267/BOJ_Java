package depth_first_search;

// https://www.acmicpc.net/problem/16437

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_16437 {
    private static ArrayList<Integer>[] tree;
    private static int[] cost;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        cost = new int[N + 1];

        for(int idx = 1; idx < N + 1; idx++)
            tree[idx] = new ArrayList<>();

        for(int cur = 2; cur < N + 1; cur++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sign = st.nextToken().charAt(0) == 'S'? 1: -1;
            int cnt = Integer.parseInt(st.nextToken());
            int nxt = Integer.parseInt(st.nextToken());
            tree[nxt].add(cur);
            cost[cur] = sign * cnt;
        }

        System.out.println(dfs(1));
    }

    private static long dfs(int cur){
        if(cur == 0)
            return 0;

        long value = cost[cur];

        for(int nxt: tree[cur])
            value += dfs(nxt);

        return Math.max(0, value);
    }
}
