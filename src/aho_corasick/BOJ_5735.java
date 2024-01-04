package aho_corasick;

// https://www.acmicpc.net/problem/5735

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5735 {

    private static final Map<Character, Integer> TABLE = new HashMap<>();

    private static final String SYMBOLS = "!?.,:;-_'#$%&/=*+(){}[]";

    private static final int CHILDREN_SIZE = SYMBOLS.length() + 10 + 26 + 26;

    private static final String NEW_LINE = "\n";

    private static int result;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        initTable();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            TrieNode root = new TrieNode(false, new TrieNode[CHILDREN_SIZE], null);

            for (int count = 0; count < N; count++) {
                String word = br.readLine();
                root.insert(-1, word.toCharArray());
            }

            bfs(root);

            result = 0;
            for (int count = 0; count < M; count++) {
                String data = br.readLine();
                find(root, data.toCharArray());
            }
            sb.append(result)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void initTable() {
        for (int index = 0; index < SYMBOLS.length(); index++) {
            TABLE.put(SYMBOLS.charAt(index), index);
        }

        int index = SYMBOLS.length();

        for (int num = 48; num <= 57; num++) {
            TABLE.put((char) num, index);
            index += 1;
        }

        for (int num = 65; num < 91; num++) {
            TABLE.put((char) num, index);
            index += 1;
        }

        for (int num = 97; num < 123; num++) {
            TABLE.put((char) num, index);
            index += 1;
        }
    }
    private static class TrieNode {
        boolean end;
        TrieNode[] children;
        TrieNode fail;

        TrieNode(boolean end, TrieNode[] children, TrieNode fail) {
            this.end = end;
            this.children = children;
            this.fail = fail;
        }

        void insert(int depth, char[] word) {
            if (depth == word.length - 1) {
                end = true;
                return;
            }

            int index = TABLE.get(word[depth + 1]);

            if (children[index] == null) {
                children[index] = new TrieNode(false, new TrieNode[CHILDREN_SIZE], null);
            }

            children[index].insert(depth + 1, word);
        }
    }

    private static void bfs(TrieNode root) {
        Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(root);

        root.fail = root;

        while (!queue.isEmpty()) {
            TrieNode cur = queue.poll();

            for (int index = 0; index < CHILDREN_SIZE; index++) {
                TrieNode next = cur.children[index];

                if (next == null) {
                    continue;
                }
                if (cur == root) {
                    next.fail = root;
                }
                else {
                    TrieNode node = cur.fail;

                    while (node != root && node.children[index] == null) {
                        node = node.fail;
                    }
                    if (node.children[index] != null) {
                        node = node.children[index];
                    }

                    next.fail = node;
                }

                queue.offer(next);
                next.end = next.fail.end || next.end;
            }
        }
    }

    private static void find(TrieNode root, char[] data) {
        TrieNode node = root;

        for (char c: data) {
            if (!TABLE.containsKey(c)) {
                node = root;
                continue;
            }
            int index = TABLE.get(c);

            while (node != root && node.children[index] == null) {
                node = node.fail;
            }

            if (node.children[index] != null) {
                node = node.children[index];
            }

            if (node.end) {
                result += 1;
                node = root;
            }
        }
    }
}
