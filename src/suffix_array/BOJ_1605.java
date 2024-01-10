package suffix_array;

// https://www.acmicpc.net/problem/1605

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_1605 {

    private static int[] groups;

    private static int prefix;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        if (groups[x] != groups[y]) {
            return Integer.compare(groups[x], groups[y]);
        }
        return Integer.compare(groups[x + prefix], groups[y + prefix]);
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String data = br.readLine();

        Integer[] suffixArray = computeSuffixArray(data, N);
        int[] lcpArray = computeLCPArray(suffixArray, data, N);
        int result = computeLongestDuplicatedSubstring(lcpArray, N);

        System.out.println(result);
    }

    private static Integer[] computeSuffixArray(String data, int N) {
        Integer[] suffixArray = new Integer[N];
        groups = new int[N + 1];
        groups[N] = -1;
        prefix = 1;

        for (int index = 0; index < N; index++) {
            suffixArray[index] = index;
        }

        for (int index = 0; index < N; index++) {
            groups[index] = data.charAt(index) - 'a';
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

        return suffixArray;
    }

    private static int[] computeLCPArray(Integer[] suffixArray, String data, int N) {
        int[] lcpArray = new int[N];
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
                if (prevIndex + matched >= N || index + matched >= N) {
                    break;
                }
                if (data.charAt(prevIndex + matched) != data.charAt(index + matched)) {
                    break;
                }
                matched += 1;
            }

            lcpArray[indices[index]] = matched;
            matched = Math.max(matched - 1, 0);
        }

        return lcpArray;
    }

    private static int computeLongestDuplicatedSubstring(int[] lcpArray, int N) {
        int size = 0;

        for (int index = 1; index < N; index++) {
            size = Math.max(size, lcpArray[index]);
        }

        return size;
    }
}
