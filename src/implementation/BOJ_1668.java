package implementation;

// https://www.acmicpc.net/problem/1668

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1668 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];

        for(int i = 0; i < N; i++)
            data[i] = Integer.parseInt(br.readLine());

        int len = 0, cnt = 0;
        for(int i = 0; i < N; i++){
            if(data[i] > len){
                cnt += 1;
                len = data[i];
            }
        }
        System.out.println(cnt);

        len = 0;
        cnt = 0;
        for(int i = N - 1; i >= 0; i--){
            if(data[i] > len){
                cnt += 1;
                len = data[i];
            }
        }
        System.out.println(cnt);
    }
}
