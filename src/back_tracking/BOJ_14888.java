package back_tracking;

// https://www.acmicpc.net/problem/14888

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14888 {
    private static int N;
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int[] nums;
    private static int[] ops;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        ops = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++)
            ops[i] = Integer.parseInt(st.nextToken());

        dfs(1, nums[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int depth, int value){
        if(depth == N){
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        for(int idx = 0; idx < 4; idx++){
            if(ops[idx] == 0)
                continue;
            ops[idx] -= 1;
            if(idx == 0)
                dfs(depth + 1, value + nums[depth]);
            else if(idx == 1)
                dfs(depth + 1, value - nums[depth]);
            else if(idx == 2)
                dfs(depth + 1, value * nums[depth]);
            else
                dfs(depth + 1, value / nums[depth]);
            ops[idx] += 1;
        }
    }
}
