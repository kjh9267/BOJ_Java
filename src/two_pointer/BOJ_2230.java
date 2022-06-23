package two_pointer;

// https://www.acmicpc.net/problem/2230

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];

        for (int index = 0; index < N; index++) {
            nums[index] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        int left = 0;
        long minDiff = Long.MAX_VALUE;

        for (int right = 0; right < N; right++) {
            int rightNum = nums[right];
            int leftNum = nums[left];

            while (rightNum - leftNum >= M && left < right) {
                minDiff = Math.min(minDiff, rightNum - leftNum);
                left += 1;
                leftNum = nums[left];
            }
        }

        if (N == 1) {
            System.out.println(0);
        }
        else {
            System.out.println(minDiff);
        }
    }
}
