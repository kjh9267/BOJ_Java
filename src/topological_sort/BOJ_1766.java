package topological_sort;

// https://www.acmicpc.net/problem/1766

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766 {

    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegrees = new int[N + 1];

        List<Integer>[] graph = new List[N + 1];

        for (int problem = 1; problem <= N; problem++) {
            graph[problem] = new ArrayList<>();
        }

        for (int info = 0; info < M; info++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            inDegrees[B] += 1;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int problem = 1; problem <= N; problem++) {
            if (inDegrees[problem] == 0) {
                pq.offer(problem);
            }
        }

        while (!pq.isEmpty()) {
            Integer currentProblem = pq.poll();

            result.append(currentProblem)
                    .append(SPACE);

            for (int nextProblem: graph[currentProblem]) {
                inDegrees[nextProblem] -= 1;
                if (inDegrees[nextProblem] == 0) {
                    pq.offer(nextProblem);
                }
            }
        }

        System.out.print(result);
    }
}
