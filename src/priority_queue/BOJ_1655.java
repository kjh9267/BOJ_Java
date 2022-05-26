package priority_queue;

// https://www.acmicpc.net/problem/1655

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_1655 {
    
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        for (int index = 0; index < N; index++) {
            int num = Integer.parseInt(br.readLine());
            nums[index] = num;
        }

        String midNums = findMidNums(nums, N);

        System.out.println(midNums);
    }

    private static String findMidNums(int[] nums, int N) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> minPq = new PriorityQueue<>();
        Queue<Integer> maxPq = new PriorityQueue<>();

        if (N == 1) {
            return String.valueOf(nums[0]);
        }

        if (nums[0] > nums[1]) {
            initPq(minPq, maxPq, nums, 0, 1);
            initStringBuilder(nums, 1, sb);
        }
        else {
            initPq(minPq, maxPq, nums, 1, 0);
            initStringBuilder(nums, 0, sb);
        }

        for (int index = 2; index < N; index++) {
            int num = nums[index];
            if (num > -maxPq.peek()) {
                minPq.offer(num);
            }
            else {
                maxPq.offer(-num);
            }

            if (maxPq.size() > minPq.size() + 1) {
                minPq.offer(-maxPq.poll());
            }
            else if (maxPq.size() < minPq.size()) {
                maxPq.offer(-minPq.poll());
            }

            sb.append(-maxPq.peek())
                    .append(NEW_LINE);
        }
        
        return sb.toString();
    }

    private static void initPq(Queue<Integer> minPq, Queue<Integer> maxPq, int[] nums, int bigIndex, int smallIndex) {
        minPq.offer(nums[bigIndex]);
        maxPq.offer(-nums[smallIndex]);
    }

    private static void initStringBuilder(int[] nums, int smallIndex, StringBuilder sb) {
        sb.append(nums[0])
                .append(NEW_LINE)
                .append(nums[smallIndex])
                .append(NEW_LINE);
    }
}


