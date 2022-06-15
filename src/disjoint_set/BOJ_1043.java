package disjoint_set;

// https://www.acmicpc.net/problem/1043

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1043 {

    private static Set<Integer> falseSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        st.nextToken();

        falseSet = new HashSet<>();

        while (st.hasMoreTokens()) {
            int value = Integer.parseInt(st.nextToken());
            falseSet.add(value);
        }

        List<Integer>[] parties = new List[M];

        for (int party = 0; party < M; party++) {
            parties[party] = new ArrayList<>();
        }

        int[] parents = new int[N + 1];
        Arrays.fill(parents, -1);

        for (int party = 0; party < M; party++) {
            st = new StringTokenizer(br.readLine());
            int participantNum = Integer.parseInt(st.nextToken());

            int[] participants = new int[participantNum];

            for (int num = 0; num < participantNum; num++) {
                int participant = Integer.parseInt(st.nextToken());
                participants[num] = participant;
                parties[party].add(participant);
            }

            for (int num = 1; num < participantNum; num++) {
                merge(participants[num], participants[num - 1], parents);
            }
        }

        int count = 0;

        for (int party = 0; party < M; party++) {
            if (isPossible(parents, parties[party])) {
                count += 1;
            }
        }

        System.out.println(count);
    }

    private static boolean isPossible(int[] parents, List<Integer> parties) {
        for (int participant: parties) {
            int parent = find(participant, parents);

            if (falseSet.contains(parent)) {
                return false;
            }
        }

        return true;
    }

    private static void merge(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x == y) {
            return;
        }
        if (falseSet.contains(x)) {
            parents[y] = x;
        }
        else {
            parents[x] = y;
        }
    }

    private static int find(int x, int[] parents) {
        if (parents[x] < 0) {
            return x;
        }

        return parents[x] = find(parents[x], parents);
    }
}
