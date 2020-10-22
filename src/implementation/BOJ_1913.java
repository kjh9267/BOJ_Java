package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1913 {
	private static final int[][] DIR = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	private static int index;
	private static int startX;
	private static int startY;
	private static int nextX;
	private static int nextY;
	private static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int target = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		startX = 0;
		startY = 0;

		if (N % 2 == 0) {
			startX = N / 2 - 1;
			startY = N / 2;
		} else {
			startX = N / 2;
			startY = N / 2;
		}

		graph[startY][startX] = 1;

		for (int i = 1; i < N; i++) {
			go(i);
			cycle();
			go(i);
			cycle();
			if(i == N - 1)
				go(i);
		}
		System.out.println(res(N,target));
	}

	private static void cycle() {
		if(++index == 4)
			index = 0;
	}

	private static void go(int threshold) {
		for (int i = 0; i < threshold; i++) {
			nextX = startX + DIR[index][0];
			nextY = startY + DIR[index][1];
			graph[nextY][nextX] = graph[startY][startX] + 1;
			startX = nextX;
			startY = nextY;
		}
	}

	private static StringBuilder res(int N, int target) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append('\n');
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(graph[i][j] == target) {
					sb.append((i + 1) + " " + (j + 1));
				}
			}
		}
		return sb;
	}
}
