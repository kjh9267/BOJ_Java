package simulation;

// https://www.acmicpc.net/problem/20056

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20056 {

    private static final int[][] DIR = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<FireBall> fireBalls = new ArrayList<>();
        List<FireBall>[][] grid = new List[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                grid[row][col] = new ArrayList<>();
            }
        }

        for (int index = 0; index < M; index++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            fireBalls.add(new FireBall(row, col, mass, speed, direction));
        }

        for (int loop = 0; loop < K; loop++) {
            for (FireBall fireBall: fireBalls) {
                fireBall.move(grid);
            }
            fireBalls.clear();
            splitFireballs(grid, fireBalls);
        }

        int sumOfMass = 0;
        for (FireBall fireBall: fireBalls) {
            sumOfMass += fireBall.mass;
        }

        System.out.println(sumOfMass);
    }

    private static class FireBall {
        int row;
        int col;
        int mass;
        int speed;
        int direction;

        FireBall(int row, int col, int mass, int speed, int direction) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }

        void move(List<FireBall>[][] grid) {
            this.row = (this.row + DIR[direction][0] * speed) % N;
            this.col = (this.col + DIR[direction][1] * speed) % N;

            if (this.row < 0) {
                this.row = N + this.row;
            }
            if (this.col < 0) {
                this.col = N + this.col;
            }

            grid[this.row][this.col].add(this);
        }

        boolean isDirectionEven() {
            return this.direction % 2 == 0;
        }
    }

    private static void splitFireballs(List<FireBall>[][] grid, List<FireBall> fireBalls) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                List<FireBall> gridFireBalls = grid[row][col];

                if (gridFireBalls.size() < 2) {

                    for (FireBall fireBall: gridFireBalls) {
                        fireBalls.add(new FireBall(fireBall.row, fireBall.col, fireBall.mass, fireBall.speed, fireBall.direction));
                    }
                    grid[row][col].clear();
                    continue;
                }

                int sumOfMass = 0;
                int sumOfSpeed = 0;
                boolean isAllEven = true;
                boolean isAllOdd = true;

                for (FireBall fireBall: gridFireBalls) {
                    sumOfMass += fireBall.mass;
                    sumOfSpeed += fireBall.speed;
                    if (fireBall.isDirectionEven()) {
                        isAllOdd = false;
                    }
                    else {
                        isAllEven = false;
                    }
                }

                int mass = sumOfMass / 5;
                int speed = sumOfSpeed / gridFireBalls.size();
                int directionStart = isAllEven || isAllOdd ? 0: 1;

                grid[row][col].clear();

                if (mass == 0) {
                    continue;
                }

                for (int direction = directionStart; direction < 8; direction += 2) {
                    fireBalls.add(new FireBall(row, col, mass, speed, direction));
                }
            }
        }
    }
}
