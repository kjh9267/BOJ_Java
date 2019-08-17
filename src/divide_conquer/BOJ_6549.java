package divide_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6549

public class BOJ_6549 {
    public static int[] data;

    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String line = br.readLine();
            if (line.equals("0"))
                break;
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            data = new int[N];
            for (int i = 0; i < N; i++)
                data[i] = Integer.parseInt(st.nextToken());
            sb.append(solve(0, N)).append('\n');
        }
        System.out.print(sb);
    }

    public static long solve(int start, int end) {
        if(start == end - 1)
            return data[start];

        int mid = (start + end) / 2;
        long res = Math.max(solve(start, mid), solve(mid, end));

        int left = mid;
        int right = mid;
        long height = data[mid];

        while(start < left || right < end - 1) {
            long leftHeight = start != left ? Math.min(height, data[left - 1]) : -1;
            long rightHeight = right != end - 1 ? Math.min(height, data[right + 1]) : -1;

            if(leftHeight > rightHeight) {
                height = leftHeight;
                left -= 1;
            }
            else {
                height = rightHeight;
                right += 1;
            }
            res = Math.max(res, (right - left + 1) * height);
        }
        return res;
    }
}
