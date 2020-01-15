package string_handing;

// https://www.acmicpc.net/problem/11655

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_11655 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringBuilder sb = new StringBuilder();

        for(int idx = 0; idx < line.length(); idx++){
            char value = line.charAt(idx);
            int small = value - 'a';
            int big = value - 'A';
            if(0 <= small && small < 26)
                sb.append((char) (((small + 13) % 26) + 'a'));
            else if(0 <= big && big < 26)
                sb.append((char) (((big + 13) % 26) + 'A'));
            else
                sb.append(value);
        }
        System.out.print(sb);
    }
}
