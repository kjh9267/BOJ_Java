package dynamic_programming;

// https://www.acmicpc.net/problem/17390

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17390 {

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] data = new int[N + 1];
        int[] dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N + 1; i++)
            data[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(data);

        for(int i = 1; i < N + 1; i++)
            dp[i] = dp[i - 1] + data[i];

        while (Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            sb.append(dp[R] - dp[L - 1]).append('\n');
        }
        System.out.print(sb);
    }
}
