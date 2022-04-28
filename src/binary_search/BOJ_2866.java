package binary_search;

// https://www.acmicpc.net/problem/2866

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.toList;

public class BOJ_2866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] data = new char[R][C];

        for (int row = 0; row < R; row++) {
            data[row] = br.readLine().toCharArray();
        }

        String[] words = new String[C];

        for (int col = 0; col < C; col++) {
            StringBuilder sb = new StringBuilder();
            for (int row = R - 1; row >= 0; row--) {
                char character = data[row][col];
                sb.append(character);
            }
            words[col] = sb.toString();
        }

        Arrays.sort(words);

        List<char[]> wordsList = Arrays.stream(words)
                .map(String::toCharArray)
                .collect(toList());

        int count = calculateCount(R, C, wordsList);

        System.out.println(count);
    }

    private static int calculateCount(int R, int C, List<char[]> words) {
        int lo = -1;
        int hi = R;

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            if (hasSameWords(C, words, mid)) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }

        return R - hi;
    }

    private static boolean hasSameWords(int C, List<char[]> words, int size) {
        for (int index = 0; index < C; index++) {
            if (hasSameWord(index, C, words, size)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasSameWord(int lo, int hi, List<char[]> words, int size) {
        String target = extractWord(size, words.get(lo));

        while (lo + 1 < hi) {
            int mid = (lo + hi) >> 1;
            String word = extractWord(size, words.get(mid));

            if (word.compareTo(target) < 0) {
                lo = mid;
            }
            else if (word.compareTo(target) > 0) {
                hi = mid;
            }
            else {
                return true;
            }
        }

        return false;
    }

    private static String extractWord(int size, char[] words) {
        StringBuilder sb = new StringBuilder();
        
        for (int index = 0; index < size; index++) {
            sb.append(words[index]);
        }
        
        return sb.toString();
    }
}
