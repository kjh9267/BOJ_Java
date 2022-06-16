package two_pointer;

// https://www.acmicpc.net/problem/16472

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16472 {

    private static final int ALPHABET_SIZE = 26;

    private static int alphabetCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[] sequence = br.readLine()
                .toCharArray();

        int M = sequence.length;

        convertToIndex(sequence, M);

        int[] counts = new int[ALPHABET_SIZE];
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < M; right++) {
            char rightAlphabet = sequence[right];

            counts[rightAlphabet] += 1;
            if (counts[rightAlphabet] == 1) {
                alphabetCount += 1;
            }
            if (alphabetCount == N + 1) {
                left = moveLeft(sequence, counts, left);
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }

    private static void convertToIndex(char[] sequence, int M) {
        for (int index = 0; index < M; index++) {
            sequence[index] -= 'a';
        }
    }

    private static int moveLeft(char[] sequence, int[] counts, int left) {
        while (true) {
            char leftAlphabet = sequence[left];
            left += 1;
            counts[leftAlphabet] -= 1;
            if (counts[leftAlphabet] == 0) {
                alphabetCount -= 1;
                break;
            }
        }
        return left;
    }
}
