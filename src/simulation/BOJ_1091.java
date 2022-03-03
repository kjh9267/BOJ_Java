package simulation;

// https://www.acmicpc.net/problem/1091

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1091 {
    private static final int MAX = 1_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] target = new int[N];
        int[] data = new int[N];
        int[] shuffleTable = new int[N];

        for (int idx = 0; idx < N; idx++) {
            target[idx] = idx % 3;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            data[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            shuffleTable[idx] = Integer.parseInt(st.nextToken());
        }

        int count = calculateCount(N, target, data, shuffleTable);

        System.out.println(count);
    }

    private static int calculateCount(int N, int[] target, int[] data, int[] shuffleTable) {
        int count = 0;

        while (!isEnd(N, data, target)) {
            shuffle(N, data, shuffleTable);
            count += 1;

            if (count == MAX) {
                count = -1;
                break;
            }
        }
        return count;
    }

    private static boolean isEnd(int N, int[] data, int[] target) {

        for (int idx = 0; idx < N; idx++) {
            if (data[idx] != target[idx]) {
                return false;
            }
        }

        return true;
    }

    private static void shuffle(int N, int[] data, int[] shuffleTable) {
        int[] temp = new int[N];

        for (int idx = 0; idx < N; idx++) {
            int nextIdx = shuffleTable[idx];
            temp[nextIdx] = data[idx];
        }

        for (int idx = 0; idx < N; idx++) {
            data[idx] = temp[idx];
        }
    }
}
