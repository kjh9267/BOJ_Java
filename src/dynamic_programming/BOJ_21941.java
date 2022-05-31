package dynamic_programming;

// https://www.acmicpc.net/problem/21941

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21941 {

    private static final int TARGET_STRING_SIZE = 100;
    
    private static String data;
    
    private static int N;
    
    private static int M;
    
    private static TargetString[] targetStrings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        data = br.readLine();

        N = data.length();
        M = Integer.parseInt(br.readLine());

        targetStrings = new TargetString[M + 1];

        for (int index = 1; index <= M; index++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String value = st.nextToken();
            int score = Integer.parseInt(st.nextToken());

            targetStrings[index] = new TargetString(value, value.length(), score);
        }

        int[][] dp = new int[N][TARGET_STRING_SIZE + 1];

        int maxScore = dfs(0, 0, dp);

        System.out.println(maxScore);
    }

    private static class TargetString {
        String value;
        int size;
        int score;

        TargetString(String value, int size, int score) {
            this.value = value;
            this.size = size;
            this.score = score;
        }
    }

    private static int dfs(int dataIndex, int targetStringIndex, int[][] dp) {
        if (dataIndex == N) {
            return 0;
        }
        if (dp[dataIndex][targetStringIndex] != 0) {
            return dp[dataIndex][targetStringIndex];
        }
        
        for (int nextTargetStringIndex = 1; nextTargetStringIndex <= M; nextTargetStringIndex++) {
            TargetString targetString = targetStrings[nextTargetStringIndex];
            
            if (dataIndex + targetString.size > N) {
                continue;
            }
            if (!isTargetString(dataIndex, targetString)) {
                continue;
            }

            dp[dataIndex][targetStringIndex] = Math.max(dp[dataIndex][targetStringIndex], dfs(dataIndex + targetString.size, nextTargetStringIndex, dp) + targetString.score);
        }

        dp[dataIndex][targetStringIndex] = Math.max(dp[dataIndex][targetStringIndex], dfs(dataIndex + 1, 0, dp) + 1);

        return dp[dataIndex][targetStringIndex];
    }
    
    private static boolean isTargetString(int dataIndex, TargetString targetString) {
        for (int index = dataIndex; index < dataIndex + targetString.size; index++) {
            if (data.charAt(index) != targetString.value.charAt(index - dataIndex)) {
                return false;
            }
        }

        return true;
    }
}
