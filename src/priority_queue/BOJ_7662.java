package priority_queue;

// https://www.acmicpc.net/problem/7662

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7662 {

    private static final String INSERT = "I";

    private static final String EMPTY = "EMPTY";

    private static final String SPACE = " ";

    private static final String NEW_LINE = "\n";

    private static final int MINIMUM = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            Queue<Long> maxPriorityQueue = new PriorityQueue<>();
            Queue<Long> minPriorityQueue = new PriorityQueue<>();
            Map<Long, Integer> numberCounts = new HashMap<>();

            int inputCount = Integer.parseInt(br.readLine());

            for (int input = 0; input < inputCount; input++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

                if (command.equals(INSERT)) {
                    insert(maxPriorityQueue, minPriorityQueue, numberCounts, number);
                } else {
                    delete(maxPriorityQueue, minPriorityQueue, number, numberCounts);
                }
            }

            makeResult(sb, maxPriorityQueue, minPriorityQueue, numberCounts);
        }

        System.out.print(sb);
    }

    private static void insert(Queue<Long> maxPriorityQueue, Queue<Long> minPriorityQueue, Map<Long, Integer> numberCounts, long number) {
        maxPriorityQueue.offer(-number);
        minPriorityQueue.offer(number);

        if (numberCounts.containsKey(number)) {
            numberCounts.put(number, numberCounts.get(number) + 1);
        }
        else {
            numberCounts.put(number, 1);
        }
    }

    private static void delete(Queue<Long> highPriorityQueue, Queue<Long> minPriorityQueue, long number, Map<Long, Integer> numberCounts) {
        if (number == MINIMUM) {
            deleteMinimumValue(minPriorityQueue, numberCounts);
        } else {
            deleteMaximumValue(highPriorityQueue, numberCounts);
        }
    }

    private static void deleteMaximumValue(Queue<Long> maxPriorityQueue, Map<Long, Integer> numberCounts) {
        cleanMaxPriorityQueue(maxPriorityQueue, numberCounts);

        if (!maxPriorityQueue.isEmpty()) {
            long number = -maxPriorityQueue.poll();
            numberCounts.put(number, numberCounts.get(number) - 1);
        }
    }

    private static void deleteMinimumValue(Queue<Long> minPriorityQueue, Map<Long, Integer> numberCounts) {
        cleanMinPriorityQueue(minPriorityQueue, numberCounts);

        if (!minPriorityQueue.isEmpty()) {
            long number = minPriorityQueue.poll();
            numberCounts.put(number, numberCounts.get(number) - 1);
        }
    }

    private static void cleanMaxPriorityQueue(Queue<Long> maxPriorityQueue, Map<Long, Integer> numberCounts) {
        while (!maxPriorityQueue.isEmpty() && numberCounts.get(-maxPriorityQueue.peek()) == 0) {
            maxPriorityQueue.poll();
        }
    }

    private static void cleanMinPriorityQueue(Queue<Long> minPriorityQueue, Map<Long, Integer> numberCounts) {
        while (!minPriorityQueue.isEmpty() && numberCounts.get(minPriorityQueue.peek()) == 0) {
            minPriorityQueue.poll();
        }
    }

    private static void makeResult(StringBuilder sb, Queue<Long> maxPriorityQueue, Queue<Long> minPriorityQueue, Map<Long, Integer> numberCounts) {
        cleanMaxPriorityQueue(maxPriorityQueue, numberCounts);
        cleanMinPriorityQueue(minPriorityQueue, numberCounts);

        if (maxPriorityQueue.isEmpty() || minPriorityQueue.isEmpty()) {
            sb.append(EMPTY)
                    .append(NEW_LINE);
        }
        else {
            sb.append(-maxPriorityQueue.poll())
                    .append(SPACE)
                    .append(minPriorityQueue.poll())
                    .append(NEW_LINE);
        }
    }
}
