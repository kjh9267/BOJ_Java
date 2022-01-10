package math;

// https://www.acmicpc.net/problem/17103

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_17103 {

    private static final int MAX = 1_000_001;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isPrime = initPrimes();

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int test = 0; test < T; test++) {
            int N = Integer.parseInt(br.readLine());
            int count = updateCount(isPrime, N);

            sb.append(count)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static boolean[] initPrimes() {
        boolean[] isPrime = new boolean[MAX];

        Arrays.fill(isPrime, true);

        isPrime[1] = false;

        double sqrt = Math.sqrt(MAX);

        for (int num = 2; num < sqrt + 1; num++) {
            if (!isPrime[num]) {
                continue;
            }
            for (int multipleNum = num + num; multipleNum < MAX; multipleNum += num) {
                isPrime[multipleNum] = false;
            }
        }
        return isPrime;
    }

    private static int updateCount(boolean[] isPrime, int N) {
        int count = 0;
        int half = N / 2 + 1;

        for (int num = 1; num < half; num++) {
            if (isPrime[num] && isPrime[N - num]) {
                count += 1;
            }
            else if (isPrime[num] && N == num + num) {
                count += 1;
            }
        }
        return count;
    }
}
