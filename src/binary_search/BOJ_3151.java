package binary_search;

// https://www.acmicpc.net/problem/3151

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3151 {

    private static int N;

    private static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int index = 0; index < N; index++) {
            nums[index] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        long teamCount = findTeamCount();

        System.out.println(teamCount);
    }

    private static long findTeamCount() {
        long teamCount = 0;

        for (int personA = 0; personA < N; personA++) {
            for (int personB = personA + 1; personB < N; personB++) {
                long sum = nums[personA] + nums[personB];

                int leftIndex = findLeftIndex(personB, sum);
                int rightIndex = findRightIndex(personB, sum);

                teamCount += rightIndex - leftIndex + 1;
            }
        }

        return teamCount;
    }

    private static int findLeftIndex(int lo, long sum) {
        int hi = N;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;

            if (sum + nums[mid] >= 0) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }

        return hi;
    }

    private static int findRightIndex(int lo, long sum) {
        int hi = N;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;

            if (nums[mid] + sum <= 0) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }

        return lo;
    }
}
