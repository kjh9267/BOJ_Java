package binary_indexed_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2268

public class BOJ_2268 {
    private static long[] tree;
    private static long[] data;
    private static final String NEW_LINE = "\n";
    private static int N;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        tree = new long[N + 1];
        data = new long[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(op == 1) {
                long diff = b - data[a];
                data[a] = b;
                update(a, diff);
            }
            else if (a < b) {
                sb.append(sum(b) - sum(a - 1)).append(NEW_LINE);
            }
            else{
                sb.append(sum(a) - sum(b - 1)).append(NEW_LINE);

            }
        }
        System.out.print(sb.toString());
    }

    private static void update(int idx, long diff) {
        while(idx < N + 1) {
            tree[idx] += diff;
            idx += (-idx & idx);
        }
    }

    private static long sum(int idx) {
        long res = 0;
        while(idx > 0) {
            res += tree[idx];
            idx -= (-idx & idx);
        }
        return res;
    }
}
