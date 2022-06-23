package brute_force;

// https://www.acmicpc.net/problem/1062

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1062 {

    private static final int ALPHABET_SIZE = 26;

    private static String[] words;

    private static char[] alphabets;

    private static int K;

    private static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        for (int index = 0; index < N; index++) {
            words[index] = br.readLine();
        }

        alphabets = new char[ALPHABET_SIZE];

        for (int ascii = 97; ascii < 123; ascii++) {
            alphabets[ascii - 97] = (char) ascii;
        }

        if (K < 5) {
            System.out.println(0);
        }
        else {
            HashSet<Character> pickedAlphabets = initPickedAlphabets();
            dfs(-1, 0, pickedAlphabets);
            System.out.println(maxCount);
        }
    }

    private static HashSet<Character> initPickedAlphabets() {
        HashSet<Character> pickedAlphabets = new HashSet<>();
        pickedAlphabets.add('a');
        pickedAlphabets.add('n');
        pickedAlphabets.add('t');
        pickedAlphabets.add('i');
        pickedAlphabets.add('c');
        return pickedAlphabets;
    }

    private static void dfs(int alphabetIndex, int depth, Set<Character> pickedAlphabets) {
        if (depth == K - 5) {
            int count = calculateCountOfReadableWords(pickedAlphabets);
            maxCount = Math.max(maxCount, count);
            return;
        }

        for (int nextAlphabetIndex = alphabetIndex + 1; nextAlphabetIndex < ALPHABET_SIZE; nextAlphabetIndex++) {
            char alphabet = alphabets[nextAlphabetIndex];

            if (pickedAlphabets.contains(alphabet)) {
                continue;
            }

            pickedAlphabets.add(alphabet);
            dfs(nextAlphabetIndex, depth + 1, pickedAlphabets);
            pickedAlphabets.remove(alphabet);
        }
    }

    private static int calculateCountOfReadableWords(Set<Character> pickedAlphabets) {
        int count = 0;

        for (String word: words) {
            if (canRead(pickedAlphabets, word)) {
                count += 1;
            }
        }

        return count;
    }

    private static boolean canRead(Set<Character> pickedAlphabets, String word) {
        for (int index = 0; index < word.length(); index++) {
            char alphabet = word.charAt(index);
            if (!pickedAlphabets.contains(alphabet)) {
                return false;
            }
        }

        return true;
    }
}
