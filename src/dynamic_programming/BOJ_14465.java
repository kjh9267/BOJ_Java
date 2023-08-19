package dynamic_programming;

// https://www.acmicpc.net/problem/14465

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14465 {

    private static final int ON = 1;

    private static final int OFF = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] lights = new int[N + 1];
        int[] dp = new int[N + 1];

        Arrays.fill(lights, ON);

        for (int count = 0; count < B; count++) {
            int num = Integer.parseInt(br.readLine());
            lights[num] = OFF;
        }

        for (int index = 1; index <= N; index++) {
            dp[index] = dp[index - 1] + lights[index];
        }

        int maxValue = 0;
        for (int right = K; right <= N; right++) {
            maxValue = Math.max(maxValue, dp[right] - dp[right - K]);
        }

        System.out.println(K - maxValue);
    }
}
