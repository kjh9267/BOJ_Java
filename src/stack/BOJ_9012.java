package stack;

// https://www.acmicpc.net/problem/9012

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class BOJ_9012 {

    private static final char LEFT_BRACKET = '(';

    private static final String YES = "YES";

    private static final String NO = "NO";

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            char[] data = br.readLine()
                    .toCharArray();

            int N = data.length;
            Deque<Character> deque = new ArrayDeque<>();

            if (isValidString(N, data, deque)) {
                sb.append(YES);
            }
            else {
                sb.append(NO);
            }
            sb.append(NEW_LINE);
        }
        System.out.print(sb);
    }

    private static boolean isValidString(int N, char[] data, Deque<Character> deque) {
        for (int index = 0; index < N; index++) {
            char value = data[index];
            if (value == LEFT_BRACKET) {
                deque.addLast(value);
            }
            else if (!hasPairBracket(deque)) {
                return false;
            }
        }

        if (!deque.isEmpty()) {
            return false;
        }

        return true;
    }

    private static boolean hasPairBracket(Deque<Character> deque) {
        if (deque.isEmpty()) {
            return false;
        }
        char value = deque.pollLast();
        if (value != LEFT_BRACKET) {
            return false;
        }
        return true;
    }
}
