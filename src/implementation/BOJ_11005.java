package implementation;

// https://www.acmicpc.net/problem/11005

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11005 {

    private static final char A = 'A';

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        StringBuilder result = new StringBuilder();
        int mod;

        while (N > 0) {
            mod = (N % B);
            result.insert(0, convert(mod));
            N /= B;
        }

        if (N < B && N != 0) {
            result.append(N);
        }

        System.out.println(result);
    }

    private static String convert(int num) {
        if (num < 10) {
            return String.valueOf(num);
        }

        return String.valueOf((char) (A + (num - 10)));
    }
}