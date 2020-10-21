package back_tracking;

// https://www.acmicpc.net/problem/1342

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_1342 {
    private static int N;
    private static int res;
    private static char[] data;
    private static boolean[] visited;
    private static HashMap<Character, Integer> cnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = br.readLine().toCharArray();
        N = data.length;
        visited = new boolean[N];
        cnt = new HashMap<>();

        for(int i = 0; i < N; i++){
            if(cnt.containsKey(data[i]))
                cnt.put(data[i], cnt.get(data[i]) + 1);
            else
                cnt.put(data[i], 1);
        }
        dfs(0, ' ', new char[N]);
        for(int c : cnt.values()){
            for(int i = 1; i <= c; i++){
                res /= i;
            }
        }
        System.out.println(res);
    }
    private static void dfs(int depth, char prev, char[] s){
        if(depth == N && check(s)){
                res += 1;
                return;
        }
        for(int i = 0; i < N; i++){
            if(prev == data[i])
                continue;
            if(visited[i])
                continue;
            visited[i] = true;
            char temp = s[depth];
            s[depth] = data[i];
            dfs(depth + 1, data[i], s);
            s[depth] = temp;
            visited[i] = false;
        }

    }
    private static boolean check(char[] s){
        for(int i = 0; i < N - 1; i++){
            if(s[i] == s[i + 1])
                return false;
        }
        return true;
    }
}
