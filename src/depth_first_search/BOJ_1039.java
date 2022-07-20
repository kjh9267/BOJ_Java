package depth_first_search;

// https://www.acmicpc.net/problem/1039

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1039 {

    private static final int ZERO = 0;

    private static int length;

    private static List<Integer> nums;

    private static Set<Integer>[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        length = String.valueOf(N)
                .length();

        int[] numArray = initNumArray(N);
        nums = new ArrayList<>();
        visited = new Set[K];

        for (int depth = 0; depth < K; depth++) {
            visited[depth] = new HashSet<>();
        }

        dfs(0, numArray, K);

        Collections.sort(nums);

        if (nums.isEmpty()) {
            System.out.println(-1);
        }
        else {
            System.out.println(nums.get(nums.size() - 1));
        }
    }

    private static int[] initNumArray(int N) {
        int[] numArray = new int[length];
        int index = length - 1;
        int mod = 10;

        while (index >= 0) {
            numArray[index] = (N % mod) / (mod / 10);
            mod *= 10;
            index -= 1;
        }

        return numArray;
    }

    private static void dfs(int depth, int[] numArray, int K) {
        int num = toNum(numArray);

        if (depth == K) {
            nums.add(num);
            return;
        }
        if (visited[depth].contains(num)) {
            return;
        }

        visited[depth].add(num);
        for (int left = 0; left < length; left++) {
            for (int right = left + 1; right < length; right++) {
                if (left == ZERO && numArray[right] == ZERO) {
                    continue;
                }
                int[] nextNumArray = swap(numArray, left, right);
                dfs(depth + 1, nextNumArray, K);
            }
        }
    }

    private static int toNum(int[] numArray) {
        int num = 0;
        int multiple = (int) Math.pow(10, length - 1);

        for (int index = 0; index < length; index++) {
            num += numArray[index] * multiple;
            multiple /= 10;
        }

        return num;
    }

    private static int[] swap(int[] numArray, int left, int right) {
        int[] newNumArray = new int[length];

        for (int index = 0; index < length; index++) {
            newNumArray[index] = numArray[index];
        }

        newNumArray[right] = numArray[left];
        newNumArray[left] = numArray[right];

        return newNumArray;
    }
}
