package divide_conquer;

// https://www.acmicpc.net/problem/4779

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;

public class BOJ_4779 {

    private static final int LIMIT = 12;

    private static final char MINUS = '-';

    private static final char SPACE = ' ';

    private static final String NEW_LINE = "\n";

    private static char[] data;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, String> table = new HashMap<>();

        for (int exp = 0; exp <= LIMIT; exp++) {
            StringBuilder sb = new StringBuilder();
            int size = (int) pow(3, exp);
            data = new char[size + 1];
            Arrays.fill(data, MINUS);

            solve(1, size, false);

            for (int index = 1; index <= size; index++) {
                sb.append(data[index]);
            }

            table.put(exp, sb.toString());
        }

        StringBuilder sb = new StringBuilder();

        while (true) {
            int N;

            try {
                N = Integer.parseInt(br.readLine());
            }
            catch (Exception e) {
                break;
            }

            String result = table.get(N);
            sb.append(result)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void solve(int start, int end, boolean mid) {
        if (mid) {
            for (int index = start; index <= end; index++) {
                data[index] = SPACE;
            }
            return;
        }
        if (start == end) {
            return;
        }

        int diff = (end - start + 1) / 3;
        int left = start + diff - 1;
        int right = start + diff * 2 - 1;

        solve(start, left, false);
        solve(left + 1, right, true);
        solve(right + 1, end, false);
    }
}
