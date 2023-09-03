package priority_queue;

// https://www.acmicpc.net/problem/21939

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21939 {

    private static final int LIMIT = 100_000;

    private static final int EASY = -1;

    private static final String RECOMMEND = "recommend";

    private static final String ADD = "add";

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] isInQueue = new int[LIMIT + 1];
        Queue<HardProblem> hardProblems = new PriorityQueue<>();
        Queue<EasyProblem> easyProblems = new PriorityQueue<>();

        for (int count = 0; count < N; count++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            isInQueue[num] = 1;
            hardProblems.offer(new HardProblem(num, cost, 1));
            easyProblems.offer(new EasyProblem(num, cost, 1));
        }


        int M = Integer.parseInt(br.readLine());
        for (int time = 1; time <= M; time++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (command.equals(RECOMMEND)) {
                if (num == EASY) {
                    EasyProblem problem = easyProblems.poll();
                    while (isInQueue[problem.num] != problem.time) {
                        problem = easyProblems.poll();
                    }
                    sb.append(problem.num)
                            .append(NEW_LINE);
                    easyProblems.offer(problem);
                }
                else {
                    HardProblem problem = hardProblems.poll();
                    while (isInQueue[problem.num] != problem.time) {
                        problem = hardProblems.poll();
                    }
                    sb.append(problem.num)
                            .append(NEW_LINE);
                    hardProblems.offer(problem);
                }
            }
            else if (command.equals(ADD)) {
                int cost = Integer.parseInt(st.nextToken());
                hardProblems.offer(new HardProblem(num, cost, time));
                easyProblems.offer(new EasyProblem(num, cost, time));
                isInQueue[num] = time;
            }
            else {
                isInQueue[num] = 0;
            }
        }

        System.out.print(sb);
    }

    private static class HardProblem implements Comparable<HardProblem> {
        int num;
        int cost;
        int time;

        HardProblem(int num, int cost, int time) {
            this.num = num;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(HardProblem other) {
            if (this.cost == other.cost) {
                return Integer.compare(other.num, this.num);
            }
            return Integer.compare(other.cost, this.cost);
        }
    }

    private static class EasyProblem implements Comparable<EasyProblem> {
        int num;
        int cost;
        int time;

        EasyProblem(int num, int cost, int time) {
            this.num = num;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(EasyProblem other) {
            if (this.cost == other.cost) {
                return Integer.compare(this.num, other.num);
            }
            return Integer.compare(this.cost, other.cost);
        }
    }
}
