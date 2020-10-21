package breadth_first_search;

// https://www.acmicpc.net/problem/13460

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13460 {

    private static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int N;
    private static int M;
    private static int nrx;
    private static int nry;
    private static int nbx;
    private static int nby;
    private static char[][] grid;
    private static HashSet<String> visited;
    private static Queue<Node> queue;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new char[N][M];
        queue = new LinkedList<>();
        visited = new HashSet<>();

        for(int i = 0; i < N; i++)
            grid[i] = br.readLine().toCharArray();

        int bx = 0, by = 0, rx = 0, ry = 0;
        for(int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (grid[row][col] == 'B') {
                    bx += col;
                    by += row;
                } else if (grid[row][col] == 'R') {
                    rx += col;
                    ry += row;
                }
            }
        }
        queue.add(new Node(rx, ry, bx, by, grid));
        visited.add(rx + " " + ry + " " + bx + " " + by);
        System.out.println(bfs());
    }

    private static int bfs(){
        int cnt = 0;
        while (!queue.isEmpty()){
            int len = queue.size();
            cnt += 1;
            if(cnt == 11)
                return -1;
            while(len-- > 0) {
                Node cur = queue.poll();
                for (int[] dir : DIR) {
                    int rx = cur.rx, ry = cur.ry, bx = cur.bx, by = cur.by;
                    boolean goal = false, dup = false, die = false;
                    while (true) {
                        int xx = rx + dir[0];
                        int yy = ry + dir[1];
                        int value = cur.grid[yy][xx];
                        if (value == 'B')
                            dup = true;
                        else if (value == '#') {
                            nrx = dup ? rx - dir[0] : rx;
                            nry = dup ? ry - dir[1] : ry;
                            break;
                        }
                        else if (value == 'O' && !dup)
                                goal = true;
                        rx = xx;
                        ry = yy;
                    }
                    dup = false;
                    while (true){
                        int xx = bx + dir[0];
                        int yy = by + dir[1];
                        int value = cur.grid[yy][xx];
                        if(value == 'R')
                            dup = true;
                        else if(value == '#') {
                            nbx = dup ? bx - dir[0] : bx;
                            nby = dup ? by - dir[1] : by;
                            break;
                        }
                        else if(value == 'O')
                            die = true;
                        bx = xx;
                        by = yy;
                    }
                    if(goal && !die)
                        return cnt;
                    if(die)
                        continue;
                    if (visited.contains(nrx + " " + nry + " " + nbx + " " + nby))
                        continue;
                    char[][] nextGrid = copy(cur.grid);
                    nextGrid[cur.ry][cur.rx] = '.';
                    nextGrid[cur.by][cur.bx] = '.';
                    nextGrid[nry][nrx] = 'R';
                    nextGrid[nby][nbx] = 'B';
                    queue.add(new Node(nrx, nry, nbx, nby, nextGrid));
                    visited.add(nrx + " " + nry + " " + nbx + " " + nby);
                }
            }
        }
        return -1;
    }

    private static char[][] copy(char[][] grid){
        char[][] res = new char[N][M];
        for(int row = 0; row < N; row++)
            for(int col = 0; col < M; col++)
                res[row][col] = grid[row][col];
        return res;
    }

    private static class Node{
        int rx, ry, bx, by;
        char[][] grid;
        public Node(int rx, int ry, int bx, int by, char[][] grid){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.grid = grid;
        }
    }
}
