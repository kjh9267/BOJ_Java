package math;

// https://www.acmicpc.net/problem/26090

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.sqrt;

public class BOJ_26090 {

    private static final int LIMIT = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int index = 0; index < N; index++) {
            data[index] = Integer.parseInt(st.nextToken());
        }

        boolean[] primes = init();
        int result = 0;

        for (int x = 0; x < N; x++) {
            int sum = 0;

            for (int y = x; y < N; y++) {
                int size = y - x + 1;
                sum += data[y];

                if (primes[size] && primes[sum]) {
                    result += 1;
                }
            }
        }

        System.out.println(result);
    }

    private static boolean[] init() {
        int sqrt = (int) sqrt(LIMIT);
        boolean[] primes = new boolean[LIMIT];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (int x = 2; x < sqrt + 1; x++) {
            if (!primes[x]) {
                continue;
            }
            for (int y = x * 2; y < LIMIT; y += x) {
                primes[y] = false;
            }
        }

        return primes;
    }
}
