package two_pointer;

// https://www.acmicpc.net/problem/14465

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14465 {

    private static final int ON = 1;

    private static final int OFF = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] lights = new int[N + 1];

        Arrays.fill(lights, ON);

        for (int count = 0; count < B; count++) {
            int num = Integer.parseInt(br.readLine());
            lights[num] = OFF;
        }

        int min = 0;
        for (int index = 1; index <= K; index++) {
            if (lights[index] == OFF) {
                min += 1;
            }
        }

        int left = 1;
        int prevValue = min;
        for (int right = K + 1 ; right <= N; right++) {
            int value = prevValue;
            if (lights[left] == OFF) {
                value -= 1;
            }
            if (lights[right] == OFF) {
                value += 1;
            }
            min = Math.min(min, value);
            prevValue = value;
            left += 1;
        }

        System.out.println(min);
    }
}

