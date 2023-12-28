package suffix_array;

// https://www.acmicpc.net/problem/11479

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;

public class BOJ_11479 {

    private static int prefix;

    private static int[] groups;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        if (groups[x] != groups[y]) {
            return groups[x] - groups[y];
        }
        return groups[x + prefix] - groups[y + prefix];
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] data = br.readLine()
                .toCharArray();
        int N = data.length;
        Integer[] suffixArray = new Integer[N];
        int[] lcpArray = new int[N];
        computeSuffixArray(suffixArray, data, N);
        computeLCPArray(lcpArray, suffixArray, data, N);

        long subStringCount = countDuplicatedSubString(suffixArray, lcpArray, N);
        System.out.println(subStringCount);
    }

    private static void computeSuffixArray(Integer[] suffixArray, char[] data, int N) {
        prefix = 1;
        groups = new int[N + 1];
        groups[N] = -1;

        for (int index = 0; index < N; index++) {
            suffixArray[index] = index;
        }

        for (int index = 0; index < N; index++) {
            groups[index] = data[index];
        }

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

    private static void computeLCPArray(int[] lcpArray, Integer[] suffixArray, char[] data, int N) {
        int[] indices = new int[N];
        int matched = 0;

        for (int index = 0; index < N; index++) {
            indices[suffixArray[index]] = index;
        }

        for (int index = 0; index < N; index++) {
            if (indices[index] == 0) {
                continue;
            }
            int prevIndex = suffixArray[indices[index] - 1];

            while (true) {
                if (index + matched >= N || prevIndex + matched >= N) {
                    break;
                }
                if (data[index + matched] != data[prevIndex + matched]) {
                    break;
                }
                matched += 1;
            }

            lcpArray[indices[index]] = matched;
            matched = max(0, matched - 1);
        }
    }

    private static long countDuplicatedSubString(Integer[] suffixArray, int[] lcpArray, int N) {
        long count = 0;

        for (int index = 0; index < N; index++) {
            count += N - suffixArray[index] - lcpArray[index];
        }

        return count;
    }
}
