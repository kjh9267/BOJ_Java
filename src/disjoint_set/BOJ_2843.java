package disjoint_set;

// https://www.acmicpc.net/problem/2843

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2843 {
    private static int[] adj, parent;

    public static void main(String[] args) throws Exception{
        Stack<String> stack = new Stack<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        adj = new int[N + 1];
        parent = new int[N + 1];
        boolean[] destroyed = new boolean[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int idx = 1; idx <= N; idx++)
            parent[idx] = -idx;

        for(int cur = 1; cur <= N; cur++) {
            int nxt = Integer.parseInt(st.nextToken());
            adj[cur] = nxt;
        }
        int Q = Integer.parseInt(br.readLine());
        int[][] queries = new int[Q][2];

        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());
            queries[q][0] = op;
            queries[q][1] = node;
            if(op == 2)
                destroyed[node] = true;
        }

        for(int node = 1; node <= N; node++)
            if (!destroyed[node] && adj[node] != 0)
                merge(node, adj[node]);

        for(int q = Q - 1; q >= 0; q--){
            int node = queries[q][1];
            if(queries[q][0] == 2)
                merge(node, adj[node]);
            else
                stack.push(parent[find(node)] == 0? "CIKLUS" : -parent[find(node)] + "");
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    private static int find(int x){
        if(parent[x] <= 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y)
            parent[y] = 0;
        else
            parent[x] = y;
    }
}
