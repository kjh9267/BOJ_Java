package implementation;

// https://www.acmicpc.net/problem/15903

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class BOJ_15903 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Card> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int count = 0; count < N; count++) {
            long num = Integer.parseInt(st.nextToken());
            pq.offer(new Card(num));
        }

        for (int count = 0; count < M; count++) {
            Card card1 = pq.poll();
            Card card2 = pq.poll();
            pq.offer(new Card(card1.num + card2.num));
            pq.offer(new Card(card1.num + card2.num));
        }

        long result = 0;

        for (int count = 0; count < N; count++) {
            Card card = pq.poll();
            result += card.num;
        }

        System.out.println(result);
    }

    private static class Card implements Comparable<Card> {

        long num;

        public Card(long num) {
            this.num = num;
        }

        @Override
        public int compareTo(Card o) {
            if (this.num > o.num) {
                return 1;
            } else if (this.num == o.num) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
