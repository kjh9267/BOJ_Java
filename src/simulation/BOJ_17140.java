package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17140

public class BOJ_17140 {
    public static int row, col;
    public static int[][] grid;
    public static PriorityQueue<Node> pq;
    public static HashMap<Integer, Integer> cnts;

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
    public static int solve(int R, int C, int K){
        int res = 0;
        row = 3;
        col = 3;
        while (res <= 100){
            if(grid[R][C] == K)
                return res;
            if(row >= col)
                sort('r');
            else
                sort('c');
            res += 1;
        }
        return -1;
    }
    public static void sort(char flag){
        int[][] temp = new int[100][100];
        if(flag == 'c') {
            row = 0;
            for (int i = 0; i < 100; i++) {
                pq = new PriorityQueue<>();
                cnts = new HashMap<>();
                for (int j = 0; j < 100; j++) {
                    int value = grid[j][i];
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
                row = Math.max(row, len * 2);
                for (int j = 0; j < len * 2; j += 2) {
                    Node n = pq.poll();
                    if (j == 100)
                        break;
                    temp[j][i] = n.num;
                    if (j + 1 == 100)
                        break;
                    temp[j + 1][i] = n.cnt;
                }
            }
        }
        else{
            col = 0;
            for(int i = 0; i < 100; i++) {
                pq = new PriorityQueue<>();
                cnts = new HashMap<>();
                for (int j = 0; j < 100; j++) {
                    int value = grid[i][j];
                    if (value == 0)
                        continue;
                    if (cnts.containsKey(value))
                        cnts.put(value, cnts.get(value) + 1);
                    else
                        cnts.put(value, 1);
                }
                for(int key : cnts.keySet())
                    pq.offer(new Node(key, cnts.get(key)));
                int len = pq.size();
                col = Math.max(col, len * 2);
                for(int j = 0; j < len * 2; j += 2){
                    Node n = pq.poll();
                    if(j == 100)
                        break;
                    temp[i][j] = n.num;
                    if(j + 1 == 100)
                        break;
                    temp[i][j + 1] = n.cnt;
                }
            }
        }
        grid = temp;
    }
    public static class Node implements Comparable<Node>{
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
