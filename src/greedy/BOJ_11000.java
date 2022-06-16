package greedy;

// https://www.acmicpc.net/problem/11000

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_11000 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<ReadyLecture> ready = new PriorityQueue<>();
        Queue<RunningLecture> running = new PriorityQueue<>();

        for (int lecture = 0; lecture < N; lecture++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());

            ready.offer(new ReadyLecture(startTime, endTime));
        }

        ReadyLecture readyLecture = ready.poll();
        running.offer(new RunningLecture(readyLecture.startTime, readyLecture.endTime));

        while (!ready.isEmpty()) {
            readyLecture = ready.poll();
            RunningLecture runningLecture = running.peek();

            if (runningLecture.endTime <= readyLecture.startTime) {
                running.poll();
            }

            running.offer(new RunningLecture(readyLecture.startTime, readyLecture.endTime));
        }

        System.out.println(running.size());
    }

    private static class ReadyLecture implements Comparable<ReadyLecture> {
        int startTime;
        int endTime;

        ReadyLecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(ReadyLecture other) {
            if (this.startTime == other.startTime) {
                return Integer.compare(this.endTime, other.endTime);
            }
            return Integer.compare(this.startTime, other.startTime);
        }
    }

    private static class RunningLecture implements Comparable<RunningLecture> {
        int startTime;
        int endTime;

        RunningLecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(RunningLecture other) {
            if (this.endTime == other.endTime) {
                return Integer.compare(this.startTime, other.startTime);
            }
            return Integer.compare(this.endTime, other.endTime);
        }
    }
}
