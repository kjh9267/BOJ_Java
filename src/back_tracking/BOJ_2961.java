package back_tracking;

// https://www.acmicpc.net/problem/2961

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2961 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Ingredient[] ingredients = new Ingredient[N];

        for (int index = 0; index < N; index++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int multiple = Integer.parseInt(st.nextToken());
            int sum = Integer.parseInt(st.nextToken());

            ingredients[index] = new Ingredient(multiple, sum);
        }

        System.out.println(dfsAll(ingredients, N));
    }

    private static class Ingredient {
        int multiple;
        int sum;

        Ingredient (int multiple, int sum) {
            this.multiple = multiple;
            this.sum = sum;
        }
    }

    private static long dfsAll(Ingredient[] ingredients, int N) {
        long[] minValue = {Long.MAX_VALUE};

        for (int target = 1; target <= N; target++) {
            dfs(0, 0, target, new ArrayList<>(), minValue, ingredients, N);
        }

        return minValue[0];
    }

    private static void dfs(int cur, int depth, int target, List<Ingredient> data, long[] minValue, Ingredient[] ingredients, int N) {
        if (depth != 0 && depth == target) {
            updateMinValue(data, minValue);
            return;
        }

        for (int next = cur; next < N; next++) {
            int multiple = ingredients[next].multiple;
            int sum = ingredients[next].sum;

            data.add(new Ingredient(multiple, sum));
            dfs(next + 1, depth + 1, target, data, minValue, ingredients, N);
            data.remove(data.size() - 1);
        }
    }

    private static void updateMinValue(List<Ingredient> data, long[] minValue) {
        long multiple = 1;
        long sum = 0;

        for (Ingredient ingredient: data) {
            multiple *= ingredient.multiple;
            sum += ingredient.sum;
        }

        minValue[0] = Math.min(minValue[0], Math.abs(multiple - sum));
    }
}
