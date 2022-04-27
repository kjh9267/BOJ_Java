package binary_search;

// https://www.acmicpc.net/problem/1920

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {

    private static final String CONTAINS = "1";

    private static final String NOT_CONTAINS = "0";

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int index = 0; index < N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int loop = 0; loop < M; loop++) {
            int targetNumber = Integer.parseInt(st.nextToken());

            if (binarySearch(targetNumber, nums, N)) {
                sb.append(CONTAINS);
            }
            else {
                sb.append(NOT_CONTAINS);
            }
            sb.append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static boolean binarySearch(int target, int[] nums, int N) {
        int lo = -1;
        int hi = N;

        while (lo + 1 < hi) {
            int mid = (lo + hi) / 2;

            if (nums[mid] < target) {
                lo = mid;
            }
            else if (nums[mid] > target) {
                hi = mid;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
