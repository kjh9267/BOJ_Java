package aho_corasick;

// https://www.acmicpc.net/problem/9250

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_9250 {

    private static final int CHILDREN_SIZE = 26;

    private static final String NEW_LINE = "\n";

    private static final String YES = "YES";

    private static final String NO = "NO";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        TrieNode trie = new TrieNode(false, null, new TrieNode[CHILDREN_SIZE]);
        trie.root = trie;

        for (int index = 0; index < N; index++) {
            words[index] = br.readLine()
                    .trim();

            trie.insert(words[index], -1);
        }

        trie.bfs();

        int Q = Integer.parseInt(br.readLine());

        for (int index = 0; index < Q; index++) {
            String data = br.readLine();

            if (trie.find(data)) {
                sb.append(YES);
            }
            else {
                sb.append(NO);
            }
            sb.append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static class TrieNode {
        static TrieNode root;
        boolean end;
        TrieNode fail;
        TrieNode[] children;

        TrieNode(boolean end, TrieNode fail, TrieNode[] children) {
            this.end = end;
            this.fail = fail;
            this.children = children;
        }

        void insert(String word, int depth) {
            if (depth == word.length() - 1) {
                end = true;
                return;
            }

            int index = word.charAt(depth + 1) - 'a';

            if (children[index] == null) {
                children[index] = new TrieNode(false, null, new TrieNode[CHILDREN_SIZE]);
            }

            children[index].insert(word, depth + 1);
        }

        static void bfs() {
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
                    next.end = next.end || next.fail.end;
                    queue.offer(next);
                }
            }
        }

        static boolean find(String data) {
            TrieNode node = root;

            for (char c: data.toCharArray()) {
                int index = c - 'a';

                while (node != root && node.children[index] == null) {
                    node = node.fail;
                }

                if (node.children[index] != null) {
                    node = node.children[index];
                }

                if (node.end) {
                    return true;
                }
            }

            return false;
        }
    }
}
