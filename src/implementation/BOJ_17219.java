package implementation;

// https://www.acmicpc.net/problem/17219

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_17219 {
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<String, String> table = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        while (N-- > 0){
            st = new StringTokenizer(br.readLine());
            String url = st.nextToken();
            String password = st.nextToken();
            table.put(url, password);
        }

        while (M-- > 0){
            String url = br.readLine();
            sb.append(table.get(url)).append(NEW_LINE);
        }

        System.out.print(sb);
    }
}
