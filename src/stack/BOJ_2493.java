package stack;

// https://www.acmicpc.net/problem/2493

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2493 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] data = new int[N + 1];
        int[] res = new int[N + 1];
        Stack<Pair> stack = new Stack<>();

        for(int idx = 1; idx < N + 1; idx++)
            data[idx] = Integer.parseInt(st.nextToken());

        for(int idx = N; idx >= 1; idx--){
            int value = data[idx];
            while (!stack.isEmpty()){
                Pair top = stack.peek();
                if(value < top.value)
                    break;
                res[top.idx] = idx;
                    stack.pop();
            }
            stack.push(new Pair(idx, value));
        }

        while (!stack.isEmpty()){
            Pair top = stack.pop();
            res[top.idx] = 0;
        }

        for(int idx = 1; idx < N + 1; idx++)
            sb.append(res[idx]).append(SPACE);

        System.out.print(sb);
    }

    private static class Pair{
        int idx;
        int value;

        public Pair(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
    }
}
