package breadth_first_search;

// https://www.acmicpc.net/problem/16397

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16397 {
    private static final int MAX = 99_999;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int res = N == G ? 0 : bfs(N, T, G);
        System.out.println(res == -1 ? "ANG" : res);
    }

    private static int bfs(int N, int T, int G){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[MAX + 1];
        queue.offer(N);
        visited[N] = true;
        int cnt = 0;

        while (!queue.isEmpty()){
            int len = queue.size();
            cnt += 1;

            while (len-- > 0){
                int cur = queue.poll();
                int nxt = cur + 1;
                if(nxt == G)
                    return cnt;
                if(nxt <= MAX && !visited[nxt]){
                    queue.offer(nxt);
                    visited[nxt] = true;
                }
                int nxtTemp = cur * 2;
                nxt = tempToNxt(nxtTemp);
                if(nxtTemp <= MAX && nxt == G)
                    return cnt;
                if(nxtTemp <= MAX && !visited[nxt]){
                    queue.offer(nxt);
                    visited[nxt] = true;
                }
            }
            if(cnt == T)
                return -1;
        }
        return -1;
    }

    private static int tempToNxt(int num){
        if(num == 0)
            return 0;
        int n = 1;
        while (num % n != num)
            n *= 10;
        return num - (n / 10);
    }
}
