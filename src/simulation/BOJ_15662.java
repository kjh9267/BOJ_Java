package simulation;

// https://www.acmicpc.net/problem/15662

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15662 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] data = new int[T + 1][9];

        for(int i = 1; i <= T; i++){
            String line = br.readLine();
            for(int j = 1; j <= 8; j++){
                data[i][j] = line.charAt(j - 1) - '0';
            }
        }
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0){
            boolean[] rotate = new boolean[T + 1];
            int[] dir = new int[T + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            dir[num] = Integer.parseInt(st.nextToken());
            rotate[num] = true;

            for(int i = num; i <= T - 1; i++){
                if(data[i][3] != data[i + 1][7] && rotate[i]) {
                    rotate[i + 1] = true;
                    dir[i + 1] = -dir[i];
                }
            }
            for(int i = num; i >= 2; i--){
                if(data[i][7] != data[i - 1][3] && rotate[i]) {
                    rotate[i - 1] = true;
                    dir[i - 1] = -dir[i];
                }
            }
            for(int i = 1; i <= T; i++){
                if(rotate[i]){
                    if(dir[i] == 1){
                        int temp = data[i][8];
                        for(int j = 8; j >= 2; j--)
                            data[i][j] = data[i][j - 1];
                        data[i][1] = temp;
                    }
                    else {
                        int temp = data[i][1];
                        for(int j = 1; j <= 7; j++)
                            data[i][j] = data[i][j + 1];
                        data[i][8] = temp;
                    }
                }
            }
        }
        int res = 0;
        for(int i = 1; i <= T; i++){
            if(data[i][1] == 1)
                res += 1;
        }
        System.out.println(res);
    }
}
