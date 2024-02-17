package suffix_array;

// https://www.acmicpc.net/problem/12917

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

import static java.lang.Math.max;

public class BOJ_12917 {

    private static int N;

    private static int prefix;

    private static char[] data;

    private static Integer[] suffixArray;

    private static int[] groups;

    private static int[] lcpArray;

    private static int result;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        if (groups[x] != groups[y]) {
            return Integer.compare(groups[x], groups[y]);
        }
        return Integer.compare(groups[x + prefix], groups[y + prefix]);
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = br.readLine()
                .trim()
                .toCharArray();

        N = data.length;

        computeSuffixArray();
        computeLCPArray();
        sweepAll();

        System.out.println(result);
    }

    private static void computeSuffixArray() {
        suffixArray = new Integer[N];
        groups = new int[N + 1];
        groups[N] = -1;

        for (int index = 0; index < N; index++) {
            suffixArray[index] = index;
        }

        for (int index = 0; index < N; index++) {
            groups[index] = data[index] - 'a';
        }

        prefix = 1;
        while (prefix < N) {
            Arrays.sort(suffixArray, COMPARATOR);
            int[] newGroups = new int[N + 1];
            newGroups[N] = -1;

            for (int index = 1; index < N; index++) {
                if (COMPARATOR.compare(suffixArray[index - 1], suffixArray[index]) < 0) {
                    newGroups[suffixArray[index]] = newGroups[suffixArray[index - 1]] + 1;
                }
                else {
                    newGroups[suffixArray[index]] = newGroups[suffixArray[index - 1]];
                }
            }

            groups = newGroups;
            prefix <<= 1;
        }
    }

    private static void computeLCPArray() {
        lcpArray = new int[N + 1];
        int[] indices = new int[N];

        for (int index = 0; index < N; index++) {
            indices[suffixArray[index]] = index;
        }

        int matched = 0;
        for (int index = 0; index < N; index++) {
            if (indices[index] == 0) {
                continue;
            }

            int prevIndex = suffixArray[indices[index] - 1];
            while (true) {
                if (prevIndex + matched >= N || index + matched >= N) {
                    break;
                }
                if (data[prevIndex + matched] != data[index + matched]) {
                    break;
                }
                matched += 1;
            }

            lcpArray[indices[index]] = matched;
            matched = max(0, matched - 1);
        }
    }

    private static void sweepAll() {
        int left = 0;

        for (int right = 0; right < N - 1; right++) {
            if (data[suffixArray[left]] != data[suffixArray[right + 1]]) {
                sweep(left, right + 1);
                left = right;
            }
        }

        sweep(left, N);
    }

    private static void sweep(int start, int end) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (int index = start; index <= end; index++) {
            result = max(result, index);

            while (!stack.isEmpty()) {
                if (lcpArray[stack.getLast()] < lcpArray[index]) {
                    break;
                }
                int poppedValue = stack.pollLast();

                if (!stack.isEmpty()) {
                    result = max(result, lcpArray[poppedValue] * (index - stack.getLast()));
                }
                else {
                    result = max(result, index - start);
                }
            }

            stack.offerLast(index);
        }
    }
}
