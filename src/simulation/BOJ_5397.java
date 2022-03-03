package simulation;

// https://www.acmicpc.net/problem/5397

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BOJ_5397 {

    private static final char LEFT_BRACKET = '<';

    private static final char RIGHT_BRACKET = '>';

    private static final char MINUS = '-';

    private static final char NEW_LINE = '\n';

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            ArrayDeque<Character> left = new ArrayDeque<>();
            ArrayDeque<Character> right = new ArrayDeque<>();
            char[] ops = br.readLine().toCharArray();

            startKeyLogger(left, right, ops);
            join(sb, left, right);
        }
        System.out.print(sb);
    }

    private static void startKeyLogger(ArrayDeque<Character> left, ArrayDeque<Character> right, char[] ops) {
        for (int idx = 0; idx < ops.length; idx++) {
            char op = ops[idx];
            if (op == LEFT_BRACKET) {
                moveLeft(left, right);
            } else if (op == RIGHT_BRACKET) {
                moveRight(left, right);
            } else if (op == MINUS) {
                delete(left);
            } else {
                add(left, op);
            }
        }
    }

    private static void moveLeft(ArrayDeque<Character> left, ArrayDeque<Character> right) {
        if (!left.isEmpty()) {
            right.addFirst(left.pollLast());
        }
    }

    private static void moveRight(ArrayDeque<Character> left, ArrayDeque<Character> right) {
        if (!right.isEmpty()) {
            left.addLast(right.pollFirst());
        }
    }

    private static void delete(ArrayDeque<Character> left) {
        if (!left.isEmpty()) {
            left.pollLast();
        }
    }

    private static void add(ArrayDeque<Character> left, char op) {
        left.addLast(op);
    }

    private static void join(StringBuilder sb, ArrayDeque<Character> left, ArrayDeque<Character> right) {
        while (!left.isEmpty()) {
            sb.append(left.pollFirst());
        }
        while (!right.isEmpty()) {
            sb.append(right.pollFirst());
        }
        sb.append(NEW_LINE);
    }
}
