package breadth_first_search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1107
 *
 */

public class BOJ_1107 {
	private static boolean[] buttons = new boolean[10];
	private static int[] visited = new int[1_000_001];

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		Arrays.fill(buttons, true);
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			buttons[num] = false;
		}
		
		Arrays.fill(visited, 1_000_001);
		System.out.println(bfs(N));
	}

	private static class Num {
		int num;
		int cnt;

		public Num(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	private static Queue<Integer> numGenerator() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(100);
		for (int num = 0; num < 1_000_000; num++) {
			if (check(num)) {
				if (num < 10) {
					queue.add(num);
					visited[num] = 1;
				}
				else if (num < 100) {
					queue.add(num);
					visited[num] = 2;
				}
				else if (num < 1000) {
					queue.add(num);
					visited[num] = 3;
				}
				else if (num < 10000) {
					queue.add(num);
					visited[num] = 4;
				}
				else if (num < 100000) {
					queue.add(num);
					visited[num] = 5;
				}
				else {
					queue.add(num);
					visited[num] = 6;
				}
			}
		}
		return queue;
	}

	private static boolean check(int num) {
		int len = (num + "").toString().length();
		for (int i = 1; i <= len; i++) {
			int n = (num % (int) Math.pow(10, i)) / ((int) Math.pow(10,i) / 10);
			if (!buttons[n]) {
				return false;
			}
		}
		return true;
	}

	private static int bfs(int N) {
		int[] diff = {-1, 1};
		Queue<Integer> queue = numGenerator();
		visited[100] = 0;

		if (N == 100) {
			System.out.println(0);
			System.exit(0);
		}
		int res = 1_000_001;
		while (!queue.isEmpty()) {
			int len = queue.size();
			int cur = queue.poll();
			for (int d : diff) {
				int nxt = cur + d;
				if (0 <= nxt && nxt < 1_000_000 && visited[nxt] > visited[cur]) {
					queue.add(nxt);
					visited[nxt] = Math.min(visited[nxt], visited[cur] + 1);
				}
			}
		}
		return visited[N];
	}
}
