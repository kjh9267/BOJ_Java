package simulation;

// https://www.acmicpc.net/problem/1091

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1091 {
    private static final int MAX = 1_000_000;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] target = new int[N];
        int[] data = new int[N];
        int[] shuffleTable = new int[N];
        int cnt = 0;

        for(int idx = 0; idx < N; idx++)
            target[idx] = idx % 3;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx = 0; idx < N; idx++)
            data[idx] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int idx = 0; idx < N; idx++)
            shuffleTable[idx] = Integer.parseInt(st.nextToken());

        while (!end(N, data, target)) {
            shuffle(N, data, shuffleTable);
            cnt += 1;

            if (cnt == MAX){
                cnt = -1;
                break;
            }
        }

        System.out.println(cnt);
    }

    private static boolean end(int N, int[] data, int[] target){

        for(int idx = 0; idx < N; idx++)
            if(data[idx] != target[idx])
                return false;

        return true;
    }

    private static void shuffle(int N, int[] data, int[] shuffleTable){
        int[] temp = new int[N];

        for(int idx = 0; idx < N; idx++) {
            int nextIdx = shuffleTable[idx];
            temp[nextIdx] = data[idx];
        }

        for(int idx = 0; idx < N; idx++)
            data[idx] = temp[idx];
    }
}
