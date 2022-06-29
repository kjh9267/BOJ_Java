package topological_sort;

// https://www.acmicpc.net/problem/3665

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3665 {

    private static final String NEW_LINE = "\n";

    private static final String SPACE = " ";

    private static final String IMPOSSIBLE = "IMPOSSIBLE";

    private static final String AMBIGUOUS = "?";

    private static int N;

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            int[] indexesOfNums = new int[N + 1];
            int[] inDegrees = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int index = 0; index < N; index++) {
                int num = Integer.parseInt(st.nextToken());
                nums[index] = num;
                indexesOfNums[num] = index;
                inDegrees[num] = index;
            }

            int M = Integer.parseInt(br.readLine());
            for (int change = 0; change < M; change++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                if (indexesOfNums[A] < indexesOfNums[B]) {
                    inDegrees[A] += 1;
                    inDegrees[B] -= 1;
                }
                else {
                    inDegrees[A] -= 1;
                    inDegrees[B] += 1;
                }
            }

            String sortResult = topologicalSort(inDegrees);

            sb.append(sortResult)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static String topologicalSort(int[] inDegrees) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = initQueue(inDegrees);

        boolean ambiguous = false;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int nextNodeCount = findNextNodeCount(inDegrees, queue);

            if (nextNodeCount > 1) {
                ambiguous = true;
            }

            sb.append(cur)
                    .append(SPACE);
        }

        for (int num = 1; num <= N; num++) {
            int inDegree = inDegrees[num];

            if (inDegree > 0) {
                return IMPOSSIBLE;
            }
        }

        if (ambiguous) {
            return AMBIGUOUS;
        }

        return sb.toString();
    }

    private static Queue<Integer> initQueue(int[] inDegrees) {
        Queue<Integer> queue = new LinkedList<>();

        for (int num: nums) {
            int inDegree = inDegrees[num];
            if (inDegree == 0) {
                queue.offer(num);
            }
        }
        return queue;
    }

    private static int findNextNodeCount(int[] inDegrees, Queue<Integer> queue) {
        int count = 0;

        for (int next: nums) {
            if (inDegrees[next] == 0) {
                continue;
            }
            inDegrees[next] -= 1;

            if (inDegrees[next] == 0) {
                queue.offer(next);
                count += 1;
            }
        }

        return count;
    }
}
