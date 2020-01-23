package depth_first_search;

// https://www.acmicpc.net/problem/4803

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_4803 {
    private static final String NEW_LINE = "\n";
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited, finished, isTree, checked;
    private static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = 0;

        while (true){
            testCase += 1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0)
                break;

            int cnt = 0;
            adj = new ArrayList[N + 1];
            parent = new int[N + 1];
            isTree = new boolean[N + 1];
            checked = new boolean[N + 1];
            Arrays.fill(parent, -1);
            Arrays.fill(isTree, true);

            for(int idx = 1; idx < N + 1; idx++)
                adj[idx] = new ArrayList<>();

            while (M-- > 0){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj[from].add(to);
                adj[to].add(from);
                merge(to, from);
            }

            for(int node = 1; node < N + 1; node++){
                if(isTree[find(node)]){
                    visited = new boolean[N + 1];
                    finished = new boolean[N + 1];
                    dfs(node, node);
                }
            }

            for(int node = 1; node < N + 1; node++){
                int p = find(node);
                if(!checked[p] && isTree[p]){
                    cnt += 1;
                    checked[p] = true;
                }
            }

            sb.append("Case ").append(testCase).append(": ");
            if(cnt == 0)
                sb.append("No trees.");
            else if(cnt == 1)
                sb.append("There is one tree.");
            else
                sb.append("A forest of ").append(cnt).append(" trees.");
            sb.append(NEW_LINE);
        }
        System.out.print(sb.toString());
    }

    public static void dfs(int cur, int prev){
        if(visited[cur]){
            if(finished[cur]) isTree[parent[cur]] = false;
            return;
        }
        visited[cur] = true;
        for(int nxt: adj[cur]){
            if(nxt == prev)
                continue;
            dfs(nxt, cur);
        }
        finished[cur] = true;
    }

    public static int find(int x){
        if(parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void merge(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y)
            return;
        if(parent[x] < parent[y]){
            parent[x] += parent[y];
            parent[y] = x;
        }
        else{
            parent[y] += parent[x];
            parent[x] = y;
        }
    }
}
