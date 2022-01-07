package implementation;

// https://www.acmicpc.net/problem/2002

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_2002 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> in = new HashMap<>();
        HashMap<String, Integer> out = new HashMap<>();
        int cnt = 0;

        for (int num = 0; num < N; num++) {
            String car = br.readLine();
            in.put(car, num);
        }

        for (int num = 0; num < N; num++) {
            String car = br.readLine();
            out.put(car, num);
        }

        for (String Car1 : in.keySet()) {
            int inNum1 = in.get(Car1);
            int outNum1 = out.get(Car1);

            for (String Car2 : in.keySet()) {
                int inNum2 = in.get(Car2);
                int outNum2 = out.get(Car2);

                if (inNum1 > inNum2 && outNum1 < outNum2) {
                    cnt += 1;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}
