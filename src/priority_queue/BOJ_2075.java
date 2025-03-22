package priority_queue;

// https://www.acmicpc.net/problem/2075

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> pq = new PriorityQueue<>();

        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                pq.offer(-Integer.parseInt(st.nextToken()));
            }
        }

        for (int count = 0; count < N - 1; count++) {
            pq.poll();
        }

        System.out.println(-pq.poll());
    }
}
