package simulation;

// https://www.acmicpc.net/problem/16235

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_16235 {
    public static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public static int N;
    public static ArrayDeque<Integer>[][] trees,live, dead;
    public static int[][] grid, add;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        trees = new ArrayDeque[N][N];
        grid = new int[N][N];
        add = new int[N][N];
        live = new ArrayDeque[N][N];
        dead = new ArrayDeque[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                trees[i][j] = new ArrayDeque<>();
                live[i][j] = new ArrayDeque<>();
                dead[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < N; i++)
            Arrays.fill(grid[i], 5);

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (M-- > 0){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees[r][c].offer(age);
        }
        while (K-- > 0){
            spring();
            summer();
            fall();
            winter();
        }
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                cnt += trees[i][j].size();
            }
        }
        System.out.println(cnt);
    }
    public static void spring(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                while (!trees[i][j].isEmpty()){
                    int tree = trees[i][j].poll();
                    if(grid[i][j] >= tree){
                        grid[i][j] -= tree;
                        live[i][j].addLast(tree + 1);
                    }
                    else
                        dead[i][j].push(tree);
                }
                while (!live[i][j].isEmpty())
                    trees[i][j].addFirst(live[i][j].pollLast());
            }
        }
    }
    public static void summer(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                while (!dead[i][j].isEmpty())
                    grid[i][j] += dead[i][j].pop() / 2;
            }
        }
    }
    public static void fall(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++) {
                for (int tree : trees[i][j]) {
                    if (tree % 5 == 0) {
                        for (int[] dir : DIR) {
                            int nx = j + dir[0];
                            int ny = i + dir[1];
                            if(!(0 <= nx && nx < N && 0 <= ny && ny < N))
                                continue;
                            trees[ny][nx].addFirst(1);
                        }
                    }
                }
            }
        }
    }
    public static void winter(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] += add[i][j];
            }
        }
    }
}
