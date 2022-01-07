package implementation;

// https://www.acmicpc.net/problem/1668

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1668 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];

        for (int index = 0; index < N; index++) {
            data[index] = Integer.parseInt(br.readLine());
        }

        int cnt = getCnt(data, 0, N, 1);

        System.out.println(cnt);

        cnt = getCnt(data, N - 1, -1, -1);

        System.out.println(cnt);
    }

    private static int getCnt(int[] data, int start, int end, int diff) {
        int len = 0;
        int cnt = 0;

        for (int index = start; index != end; index += diff) {
            if (data[index] > len) {
                cnt += 1;
                len = data[index];
            }
        }
        return cnt;
    }
}
