package dynamic_programming;

// https://www.acmicpc.net/problem/2109

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2109 {

    private static final int MAX = 10_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int money = 0;

        Queue<Lecture> pq = new PriorityQueue<>();

        for (int index = 1; index <= N; index++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int reward = Integer.parseInt(st.nextToken());
            int limitDay = Integer.parseInt(st.nextToken());

            pq.offer(new Lecture(reward, limitDay));
        }

        for (int day = 1; day <= MAX; day++) {
            if (pq.isEmpty()) {
                break;
            }
            Lecture lecture = pq.poll();
            money += lecture.reward;

            List<Lecture> tempLectures = new ArrayList<>();

            while (!pq.isEmpty()) {
                lecture = pq.poll();
                if (lecture.isEnd()) {
                    continue;
                }
                lecture.minusDay();
                tempLectures.add(lecture);
            }
            pq.addAll(tempLectures);
        }

        System.out.println(money);
    }

    private static class Lecture implements Comparable<Lecture> {
        int reward;
        int limitDay;

        Lecture(int reward, int limitDay) {
            this.reward = reward;
            this.limitDay = limitDay;
        }

        boolean isEnd() {
            return limitDay == 1;
        }

        void minusDay() {
            limitDay -= 1;
        }

        @Override
        public int compareTo(Lecture other) {
            if (this.limitDay == other.limitDay) {
                return Integer.compare(other.reward, this.reward);
            }
            return Integer.compare(this.limitDay, other.limitDay);
        }
    }
}
