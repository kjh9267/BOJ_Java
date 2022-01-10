package depth_first_search;

// https://www.acmicpc.net/problem/2668

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2668 {

    private final static int NOT_VISITED = 0;

    private final static int FIRST_VISITED = 1;

    private final static int SECOND_VISITED = 2;

    private final static String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N + 1];

        for (int index = 1; index <= N; index++) {
            int num = Integer.parseInt(br.readLine());
            data[index] = num;
        }

        int[] visited = new int[N + 1];

        for (int num: data) {
            dfs(num, data, visited);
        }

        int count = 0;

        StringBuilder sb = new StringBuilder();

        for (int number = 1; number <= N; number++) {
            int visitedCount = visited[number];

            if (visitedCount == SECOND_VISITED) {
                count += 1;
                sb.append(number)
                        .append(NEW_LINE);
            }
        }

        System.out.println(count);
        System.out.print(sb);
    }

    private static void dfs(int currentNumber, int[] data, int[] visited) {
        int nextNumber = data[currentNumber];

        if (visited[nextNumber] == NOT_VISITED) {
            visited[nextNumber] = FIRST_VISITED;
            dfs(nextNumber, data, visited);
        }

        if (visited[nextNumber] == FIRST_VISITED) {
            visited[nextNumber] = SECOND_VISITED;
            dfs(nextNumber, data, visited);
        }

        if (visited[currentNumber] == FIRST_VISITED) {
            visited[currentNumber] = NOT_VISITED;
        }
    }
}
