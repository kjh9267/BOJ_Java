package implementation;

// https://www.acmicpc.net/problem/12115

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12115 {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] data = new int[N][M];

        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < M; col++){
                data[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0){
            int cnt = 0;
            st = new StringTokenizer(br.readLine());
            int[] query = new int[M];

            for(int i = 0; i < M; i++)
                query[i] = Integer.parseInt(st.nextToken());

            for(int row = 0; row < N; row++){
                boolean key = true;
                for(int col = 0; col < M; col++){
                    if(query[col] == -1)
                        continue;
                    if(query[col] != data[row][col]) {
                        key = false;
                        break;
                    }
                }
                if(key)
                    cnt += 1;
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
}
