package kmp;

// https://www.acmicpc.net/problem/17228

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17228 {

    private static Set<Integer> words;

    private static int count;

    private static List<Node>[] graph;

    private static int[] fix;

    private static StringBuilder data;

    private static String word;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new List[N];

        for (int node = 0; node < N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < N - 1; link++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            String value = st.nextToken();

            graph[start].add(new Node(end, value));
            graph[end].add(new Node(start, value));
        }

        word = br.readLine()
                .trim();

        words = new HashSet<>();
        data = new StringBuilder();
        fix = new int[N];
        init(fix, word);

        dfs(0, -1,0, 0);

        System.out.println(words.size());
    }

    private static class Node {
        int id;
        String value;

        Node (int id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    private static class KMPState {
        int start;
        int matched;

        KMPState (int start, int matched) {
            this.start = start;
            this.matched = matched;
        }

        int findIndex() {
            return start + matched;
        }
    }

    private static void dfs(int cur, int prev, int start, int matched) {
        count += 1;
        KMPState state = new KMPState(start, matched);

        if (cur > 0) {
            kmp(state, data, word, fix);
        }

        for (Node next: graph[cur]) {
            if (next.id == prev) {
                continue;
            }
            data.append(next.value);
            dfs(next.id, cur, state.start, state.matched);
            data.delete(data.length() - 1, data.length());
        }
    }

    private static void kmp(KMPState state, StringBuilder data, String word, int[] fix) {
        while (state.findIndex() < data.length()) {
            if (state.matched < word.length() && word.charAt(state.matched) == data.charAt(state.findIndex())) {
                state.matched += 1;
                if (state.matched == word.length()) {
                    words.add(count);
                }
            }
            else if (state.matched == 0) {
                state.start += 1;
            }
            else {
                state.start += state.matched - fix[state.matched - 1];
                state.matched = fix[state.matched - 1];
            }
        }
    }

    private static void init(int[] fix, String word) {
        KMPState state = new KMPState(1, 0);

        while (state.findIndex() < word.length()) {
            if (word.charAt(state.matched) == word.charAt(state.findIndex())) {
                state.matched += 1;
                fix[state.findIndex() - 1] = state.matched;
            }
            else if (state.matched == 0) {
                state.start += 1;
            }
            else {
                state.start += state.matched - fix[state.matched - 1];
                state.matched = fix[state.matched - 1];
            }
        }
    }
}
