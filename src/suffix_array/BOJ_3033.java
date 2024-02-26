package suffix_array;

// https://www.acmicpc.net/problem/3033

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;

public class BOJ_3033 {

    private static int N;

    private static int prefix;

    private static char[] data;

    private static Integer[] suffixArray;

    private static int[] groups;

    private static int[] lcpArray;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        if (groups[x] == groups[y]) {
            return Integer.compare(groups[x + prefix], groups[y + prefix]);
        }
        return Integer.compare(groups[x], groups[y]);
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = br.readLine()
                .trim()
                .toCharArray();

        computeSuffixArray();
        computeLCPArray();
        int result = solve();

        System.out.println(result);
    }

    private static void computeSuffixArray() {
        suffixArray = new Integer[N];
        groups = new int[N + 1];
        groups[N] = -1;

        for (int index = 0; index < N; index++) {
            suffixArray[index] = index;
            groups[index] = data[index] - 'a';
        }

        prefix = 1;
        while (prefix < N) {
            int[] newGroups = new int[N + 1];
            newGroups[N] = -1;

            Arrays.sort(suffixArray, COMPARATOR);

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
        lcpArray = new int[N];
        int[] indices = new int[N];

        for (int index = 0; index < N; index++) {
            indices[suffixArray[index]] = index;
        }

        int matched = 0;
        for (int index = 0; index < N; index++) {
            if (indices[index]== 0) {
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

    private static int solve() {
        int maxSize = 0;

        for (int index = 1; index < N; index++) {
            maxSize = max(maxSize, lcpArray[index]);
        }

        return maxSize;
    }
}
