package implementation;

// https://www.acmicpc.net/problem/2559

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559 {

    private static final int MIN = -10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        int[] sum = new int[N + 1];

        for (int index = 1; index <= N; index++) {
            sum[index] = sum[index - 1] + nums[index];
        }

        int max = MIN;
        for (int index = K; index <= N; index++) {
            max = Math.max(max, sum[index] - sum[index - K]);
        }

        System.out.println(max);
    }
}
