package binary_search;

// https://www.acmicpc.net/problem/2143

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2143 {

    private static List<Integer> sumOfAllRangeInB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= N; index++) {
            A[index] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int index = 1; index <= M; index++) {
            B[index] = Integer.parseInt(st.nextToken());
        }

        int[] sumOfA = new int[N + 1];
        int[] sumOfB = new int[M + 1];

        fillSumArray(A, sumOfA, N);
        fillSumArray(B, sumOfB, M);

        initSumOfAllRangeInB(M, sumOfB);

        System.out.println(calculateCount(T, sumOfA, N));
    }

    private static void fillSumArray(int[] nums, int[] sum, int length) {
        for (int index = 1; index <= length; index++) {
            sum[index] = sum[index - 1] + nums[index];
        }
    }

    private static void initSumOfAllRangeInB(int M, int[] sumOfB) {
        sumOfAllRangeInB = new ArrayList<>();

        for (int left = 1; left <= M; left++) {
            for (int right = left; right <= M; right++) {
                sumOfAllRangeInB.add(sumOfB[right] - sumOfB[left - 1]);
            }
        }

        Collections.sort(sumOfAllRangeInB);
    }

    private static long calculateCount(int T, int[] sumOfA, int N) {
        long count = 0;

        for (int left = 1; left <= N; left++) {
            for (int right = left; right <= N; right++) {
                int sum = sumOfA[right] - sumOfA[left - 1];
                int target = T - sum;

                count += upperBound(target) - lowerBound(target) + 1;
            }
        }

        return count;
    }

    private static int upperBound(int target) {
        int lo = -1;
        int hi = sumOfAllRangeInB.size();

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            if (sumOfAllRangeInB.get(mid) <= target) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }

        return lo;
    }

    private static int lowerBound(int target) {
        int lo = -1;
        int hi = sumOfAllRangeInB.size();

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            if (sumOfAllRangeInB.get(mid) >= target) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return hi;
    }
}
