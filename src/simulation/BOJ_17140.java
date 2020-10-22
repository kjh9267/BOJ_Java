package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17140

public class BOJ_17140 {
    private static int row;
    private static int col;
    private static int[][] grid;
    private static PriorityQueue<Node> pq;
    private static HashMap<Integer, Integer> cnts;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken()) - 1;
        int C = Integer.parseInt(st.nextToken()) - 1;
        int K = Integer.parseInt(st.nextToken());
        grid = new int[100][100];

        for(int row = 0; row < 3; row++){
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 3; col++){
                grid[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solve(R, C, K));
    }

    private static int solve(int R, int C, int K){
        int res = 0;
        row = 3;
        col = 3;
        while (res <= 100){
            if(grid[R][C] == K)
                return res;
            if(row >= col)
                sort(false);
            else
                sort(true);
            res += 1;
        }
        return -1;
    }

    private static void sort(boolean flag) {
        int[][] temp = new int[100][100];
        int end = 0;
        for (int i = 0; i < 100; i++) {
            pq = new PriorityQueue<>();
            cnts = new HashMap<>();
            for (int j = 0; j < 100; j++) {
                int value = flag ? grid[j][i] : grid[i][j];
                if (value == 0)
                    continue;
                if (cnts.containsKey(value))
                    cnts.put(value, cnts.get(value) + 1);
                else
                    cnts.put(value, 1);
            }
            for (int key : cnts.keySet())
                pq.offer(new Node(key, cnts.get(key)));
            int len = pq.size();
            end = Math.max(end, len * 2);
            for (int j = 0; j < len * 2; j += 2) {
                Node n = pq.poll();
                if (j == 100)
                    break;
                if (flag)
                    temp[j][i] = n.num;
                else
                    temp[i][j] = n.num;
                if (j + 1 == 100)
                    break;
                if (flag)
                    temp[j + 1][i] = n.cnt;
                else
                    temp[i][j + 1] = n.cnt;
            }
        }
        if (flag)
            row = end;
        else
            col = end;
        grid = temp;
    }

    private static class Node implements Comparable<Node>{
        int num;
        int cnt;
        public Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
        @Override
        public int compareTo(Node o) {
            if(this.cnt == o.cnt)
                return this.num - o.num;
            return this.cnt - o.cnt;
        }
    }
}
