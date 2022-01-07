package implementation;

// https://www.acmicpc.net/problem/12115

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12115 {

    private static final char NEW_LINE = '\n';

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] data = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                data[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int count = 0;
            st = new StringTokenizer(br.readLine());
            int[] query = new int[M];

            for (int index = 0; index < M; index++) {
                query[index] = Integer.parseInt(st.nextToken());
            }

            count = getCount(N, M, data, count, query);

            sb.append(count)
                    .append(NEW_LINE);
        }
        System.out.print(sb);
    }

    private static int getCount(int N, int M, int[][] data, int cnt, int[] query) {
        for (int row = 0; row < N; row++) {
            boolean key = true;

            for (int col = 0; col < M; col++) {
                if (query[col] == -1) {
                    continue;
                }
                if (query[col] != data[row][col]) {
                    key = false;
                    break;
                }
            }
            if (key) {
                cnt += 1;
            }
        }
        return cnt;
    }
}
