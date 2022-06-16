package greedy;

// https://www.acmicpc.net/problem/13904

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13904 {

    private static final int MAX_DAY = 1_001;

    private static int maxScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Assignment> pq = new PriorityQueue<>();

        for (int index = 0; index < N; index++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int endDay = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            Assignment assignment = new Assignment(endDay, score);
            pq.offer(assignment);
        }

        maxScore = 0;

        boolean[] visited = new boolean[MAX_DAY];

        while (!pq.isEmpty()) {
            Assignment assignment = pq.poll();

            matchAssignment(visited, assignment);
        }

        System.out.println(maxScore);
    }

    private static void matchAssignment(boolean[] visited, Assignment assignment) {
        int day = assignment.endDay;

        while (day >= 1) {
            if (!visited[day]) {
                visited[day] = true;
                maxScore += assignment.score;
                break;
            }
            day -= 1;
        }
    }

    private static class Assignment implements Comparable<Assignment> {
        int endDay;
        int score;

        Assignment(int endDay, int score) {
            this.endDay = endDay;
            this.score = score;
        }

        @Override
        public int compareTo(Assignment other) {
            if (this.score == other.score) {
                return Integer.compare(this.endDay, other.endDay);
            }
            return Integer.compare(other.score, this.score);
        }
    }
}
