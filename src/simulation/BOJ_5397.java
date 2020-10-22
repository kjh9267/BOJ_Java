package simulation;

// https://www.acmicpc.net/problem/5397

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_5397 {

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0){
            ArrayDeque<Character> left = new ArrayDeque<>();
            ArrayDeque<Character> right = new ArrayDeque<>();
            char[] ops = br.readLine().toCharArray();

            for(int idx = 0; idx < ops.length; idx++){
                char op = ops[idx];
                if(op == '<'){
                    if(!left.isEmpty())
                        right.addFirst(left.pollLast());
                }
                else if(op == '>'){
                    if(!right.isEmpty())
                        left.addLast(right.pollFirst());
                }
                else if(op == '-'){
                    if(!left.isEmpty())
                        left.pollLast();
                }
                else {
                    left.addLast(op);
                }
            }
            while (!left.isEmpty())
                sb.append(left.pollFirst());
            while ((!right.isEmpty()))
                sb.append(right.pollFirst());
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
