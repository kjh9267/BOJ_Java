package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @author Junho
 * @see https://www.acmicpc.net/problem/11286
 */

public class BOJ_11286 {

    private static final String NEW_LINE = "\n";

    private static final String ZERO = "0";

    private static final int DELETE = 0;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Number> pq = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int inputNumber = Integer.parseInt(br.readLine());

            if (inputNumber == DELETE) {
                delete(sb, pq);
            } else {
                add(pq, inputNumber);
            }
        }

        System.out.println(sb);
    }

    private static void add(PriorityQueue<Number> pq, int inputNumber) {
        pq.add(new Number(inputNumber));
    }

    private static void delete(StringBuilder sb, PriorityQueue<Number> pq) {
        if (pq.isEmpty()) {
            sb.append(ZERO)
                    .append(NEW_LINE);
        } else {
            Number number = pq.poll();
            sb.append(number.value)
                    .append(NEW_LINE);
        }
    }

    private static class Number implements Comparable<Number> {
        int value;

        Number(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Number o) {
            if (Math.abs(this.value) == Math.abs(o.value)) {
                return this.value - o.value;
            }
            return Math.abs(this.value) - Math.abs(o.value);
        }
    }
}
