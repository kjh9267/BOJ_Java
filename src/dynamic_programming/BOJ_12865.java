package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12865 {

    private static int N;

    private static int K;

    private static Product[] products;

    private static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        products = new Product[N];

        for (int productIndex = 0; productIndex < N; productIndex++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            products[productIndex] = new Product(weight, value);
        }

        dp = new int[N][K + 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }

    private static class Product {
        int weight;
        int value;

        Product(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static int dfs(int productIndex, int sumOfWeight) {
        if (productIndex == N) {
            return 0;
        }
        if (dp[productIndex][sumOfWeight] != -1) {
            return dp[productIndex][sumOfWeight];
        }

        dp[productIndex][sumOfWeight] = dfs(productIndex + 1, sumOfWeight);

        Product product = products[productIndex];

        if (sumOfWeight + product.weight <= K) {
            dp[productIndex][sumOfWeight] = Math.max(dp[productIndex][sumOfWeight], dfs(productIndex + 1, sumOfWeight + product.weight) + product.value);
        }

        return dp[productIndex][sumOfWeight];
    }
}
