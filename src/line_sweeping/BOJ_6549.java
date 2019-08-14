package line_sweeping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6549

public class BOJ_6549 {
    public static void main(String[] args)throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String line = br.readLine();
            if (line.equals("0"))
                break;
            Stack<Integer> stack = new Stack<Integer>();
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int[] data = new int[N + 2];
            long res = 0;

            for (int i = 1; i < N + 1; i++)
                data[i] = Integer.parseInt(st.nextToken());

            stack.push(0);
            for (int idx = 1; idx < N + 2; idx++) {
                while (!stack.isEmpty() && data[idx] <= data[stack.peek()]) {
                    int cur = stack.pop();
                    int top = stack.isEmpty() ? 0 : stack.peek();
                    res = Math.max(res, (long)data[cur] * (long)(idx - top - 1));
                }
                stack.push(idx);
            }
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }
}
