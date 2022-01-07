package implementation;

// https://www.acmicpc.net/problem/1002

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1002 {

    private static final String NEW_LINE = "\n";

    private static final int IMPOSSIBLE = 0;

    private static final int INFINITY = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            if (x1 == x2 && y1 == y2) {
                if (r1 == r2) {
                    sb.append(INFINITY).append(NEW_LINE);
                } else {
                    sb.append(IMPOSSIBLE).append(NEW_LINE);
                }
            } else {
                double dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                if (dist == r1 + r2 || dist == Math.abs(r1 - r2)) {
                    sb.append(1)
                            .append(NEW_LINE);
                } else if (dist < r1 + r2 && dist > Math.abs(r1 - r2)) {
                    sb.append(2)
                            .append(NEW_LINE);
                } else {
                    sb.append(0)
                            .append(NEW_LINE);
                }
            }
        }
        System.out.print(sb.toString());
    }
}
