package suffix_array;

// https://www.acmicpc.net/problem/13264

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_13264 {

    private static final String NEW_LINE = "\n";

    private static int prefix;

    private static Integer[] suffixArray;

    private static int[] groups;

    private static final Comparator<Integer> COMPARATOR = (x, y) -> {
        if (groups[x] == groups[y]) {
            return Integer.compare(groups[x + prefix], groups[y + prefix]);
        }
        return Integer.compare(groups[x], groups[y]);
    };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine()
                .trim();
        int N = data.length();
        computeSuffixArray(data, N);

        for (int index: suffixArray) {
            sb.append(index)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void computeSuffixArray(String data, int N) {
        suffixArray = new Integer[N];
        groups = new int[N + 1];
        groups[N] = -1;

        for (int index = 0; index < N; index++) {
            suffixArray[index] = index;
            groups[index] = data.charAt(index) - 'a';
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
}
