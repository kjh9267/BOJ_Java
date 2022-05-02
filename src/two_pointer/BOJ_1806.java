package two_pointer;

// https://www.acmicpc.net/problem/1806

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {

    private static final int MAX_LENGTH = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int index = 0; index < N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        int result = calculateMinimumLength(nums, N, S);

        System.out.println(result);
    }

    private static int calculateMinimumLength(int[] nums, int N, int S) {
        int minimumLength = MAX_LENGTH;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < N; right++) {
            sum += nums[right];

            while (sum >= S) {
                int length = right - left + 1;
                minimumLength = Math.min(minimumLength, length);
                sum -= nums[left];
                left += 1;
            }
        }

        return minimumLength == MAX_LENGTH ? 0: minimumLength;
    }
}
