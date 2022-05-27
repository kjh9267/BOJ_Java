package implementation;

// https://www.acmicpc.net/problem/1013

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1013 {

    private static final int START = 1;

    private static final int ONE_ZERO = 2;

    private static final int ZERO_PLUS = 4;

    private static final int ONE_PLUS = 8;

    private static final int NO_MATCH = -1;

    private static final char ONE = '1';

    private static final char ZERO = '0';

    private static final String YES = "YES";

    private static final String NO = "NO";

    private static final String NEW_LINE = "\n";

    private static int state;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            char[] nums = br.readLine().trim()
                    .toCharArray();

            int N = nums.length;

            if (N == 1) {
                sb.append(NO)
                        .append(NEW_LINE);
                continue;
            }

            state = START;

            for (int index = 0; index < N; index++) {
                if (state == START) {
                    index = doStart(nums, index, N);
                }
                else if (state == ONE_ZERO) {
                    doOneZero(nums, index);
                }
                else if (state == ZERO_PLUS) {
                    doZeroPlus(nums, index);
                }
                else if (state == ONE_PLUS) {
                    index = doOnePlus(nums, index, N);
                }
            }

            sb.append(state == START || state == 8 ? YES: NO)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static int doStart(char[] nums, int index, int N) {
        if (index < N - 1 && nums[index] == ONE && nums[index + 1] == ZERO) {
            state <<= 1;
            index += 1;
        }
        else if (index < N - 1 && nums[index] == ZERO && nums[index + 1] == ONE) {
            index += 1;
        }
        else {
            state = NO_MATCH;
        }
        return index;
    }

    private static void doOneZero(char[] nums, int index) {
        if (nums[index] == ZERO) {
            state <<= 1;
        }
        else if (nums[index] == ONE) {
            state = NO_MATCH;
        }
    }

    private static void doZeroPlus(char[] nums, int index) {
        if (nums[index] == ONE) {
            state <<= 1;
        }
    }

    private static int doOnePlus(char[] nums, int index, int N) {
        if (index < N - 2 && nums[index] == ONE && nums[index + 1] == ZERO && nums[index + 2] == ONE) {
            state = START;
            index += 2;
        }
        else if (index < N - 1 && nums[index] == ONE && nums[index + 1] == ZERO) {
            state = ONE_ZERO;
            index += 1;
        }
        else if (nums[index] != ONE) {
            state = START;
            index -= 1;
        }

        return index;
    }
}
