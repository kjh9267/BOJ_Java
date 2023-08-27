package topological_sort;

// https://www.acmicpc.net/problem/14567

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14567 {

    private static final String SPACE = " ";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegrees = new int[N];
        List<Integer>[] graph = new List[N];

        for (int lecture = 0; lecture < N; lecture++) {
            graph[lecture] = new ArrayList<>();
        }

        for (int count = 0; count < M; count++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            inDegrees[B] += 1;
            graph[A].add(B);
        }

        int[] result = topologicalSort(inDegrees, graph, N);

        for (int num: result) {
            sb.append(num)
                    .append(SPACE);
        }

        System.out.println(sb);
    }

    private static int[] topologicalSort(int[] inDegrees, List<Integer>[] graph, int N) {
        int[] result = new int[N];
        Queue<Integer> queue = new LinkedList<>();

        for (int lecture = 0; lecture < N; lecture++) {
            if (inDegrees[lecture] == 0) {
                queue.offer(lecture);
            }
        }

        int depth = 0;
        while (!queue.isEmpty()) {
            depth += 1;
            int size = queue.size();

            for (int count = 0; count < size; count++) {
                int cur = queue.poll();
                result[cur] = depth;

                for (int next: graph[cur]) {
                    inDegrees[next] -= 1;
                    if (inDegrees[next] > 0) {
                        continue;
                    }
                    queue.offer(next);
                }
            }
        }

        return result;
    }
}
