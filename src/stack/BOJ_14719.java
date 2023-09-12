package stack;

// https://www.acmicpc.net/problem/14719

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14719 {

    private static final int RIGHT = 1;

    private static final int LEFT = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] sticks = new int[W + 2];
        st = new StringTokenizer(br.readLine());

        int stickSize = 0;
        for (int index = 1; index <= W; index++) {
            int value = Integer.parseInt(st.nextToken());
            sticks[index] = value;
            stickSize += value;
        }

        Deque<Integer> deque = new ArrayDeque<>();

        int total = 0;
        total = updateTotal(total, 1, 0, RIGHT, W, sticks, deque);
        total = updateTotal(total, W, W + 1, LEFT, W, sticks, deque);

        System.out.println(total - stickSize);
    }

    private static int updateTotal(int total, int startIndex, int maxIndex, int direction, int W, int[] sticks, Deque<Integer> deque) {
        int max = sticks[maxIndex];
        int duplicate = 0;
        deque.offerLast(maxIndex);

        for (int index = startIndex; 1 <= index && index <= W; index += direction) {
            if (direction == RIGHT) {
                if (sticks[index] < max) {
                    continue;
                }
            }
            else if (sticks[index] <= max) {
                continue;
            }

            maxIndex = deque.pollLast();
            deque.offerLast(index);
            int sum = Math.abs(index - maxIndex) * sticks[maxIndex];

            total += sum;
            maxIndex = index;
            max = sticks[maxIndex];
        }

        while (!deque.isEmpty()) {
            int index = deque.pollLast();
            total += sticks[index];
            duplicate += sticks[index];
        }

        if (direction == RIGHT) {
            return total;
        }

        return total - duplicate;
    }
}
