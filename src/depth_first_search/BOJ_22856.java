package depth_first_search;

// https://www.acmicpc.net/problem/22856

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_22856 {

    private static final int EMPTY = -1;

    private static final int RIGHT = 1;

    private static List<Integer>[] tree;

    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new List[N + 1];

        for (int node = 1; node <= N; node++) {
            tree[node] = new ArrayList<>();
        }

        for (int count = 0; count < N; count++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int leftNode = Integer.parseInt(st.nextToken());
            int rightNode = Integer.parseInt(st.nextToken());
            tree[node].add(leftNode);
            tree[node].add(rightNode);
        }

        dfs(1, true);

        System.out.println(count - 1);
    }

    private static boolean dfs(int cur, boolean right) {
        if (right && tree[cur].isEmpty()) {
            count += 1;
            return true;
        }

        count += 1;

        for (int index = 0; index < tree[cur].size(); index++) {
            int next = tree[cur].get(index);

            if (next == EMPTY) {
                if (right && index == RIGHT) {
                    return true;
                }
                continue;
            }

            if (dfs(next, right && index == RIGHT)) {
                return true;
            }
            count += 1;
        }

        return false;
    }
}
