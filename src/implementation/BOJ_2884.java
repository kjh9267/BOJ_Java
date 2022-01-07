package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2884

public class BOJ_2884 {

    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int minutes = 60 * H + M - 45;

        if (minutes < 0) {
            minutes += 24 * 60;
        }

        H = minutes / 60;
        M = minutes % 60;

        System.out.println(H + SPACE + M);
    }
}