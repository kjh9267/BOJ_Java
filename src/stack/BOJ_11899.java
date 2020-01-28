package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/11899

public class BOJ_11899 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data = br.readLine();
        Stack<Character> stack = new Stack<>();
        int N = data.length();

        for(int idx = 0; idx < N; idx++){
            char value = data.charAt(idx);
            if(value == ')' && !stack.isEmpty() && stack.peek() == '(')
                stack.pop();
            else
                stack.push(value);
        }

        System.out.println(stack.size());
    }
}
