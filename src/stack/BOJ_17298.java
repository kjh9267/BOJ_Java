package stack;

// https://www.acmicpc.net/problem/17298

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298 {
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] data = new int[N];
        int[] res = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Pair> stack = new Stack<>();

        for(int idx = 0; idx < N; idx++)
            data[idx] = Integer.parseInt(st.nextToken());

        stack.push(new Pair(0, data[0]));
        for(int idx = 1; idx < N; idx++){
            int curValue = data[idx];
            while (!stack.isEmpty()) {
                Pair top = stack.peek();
                if(top.value >= curValue)
                    break;
                stack.pop();
                res[top.idx] = curValue;
            }
            stack.push(new Pair(idx, curValue));
        }

        while (!stack.isEmpty()){
            Pair top = stack.pop();
            res[top.idx] = -1;
        }

        for(int value: res)
            sb.append(value).append(SPACE);

        System.out.print(sb);
    }

    public static class Pair{
        int idx;
        int value;

        public Pair(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
    }
}
