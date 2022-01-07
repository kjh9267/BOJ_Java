package implementation;

// https://www.acmicpc.net/problem/2960

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2960 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] isPrime = new boolean[N + 1];
		int cnt = 0;

		for (int number = 2; number <= N; number++) {
			if (isPrime[number]) {
				continue;
			}
			for (int multipleNumber = number; multipleNumber <= N; multipleNumber += number) {
				if (isPrime[multipleNumber]) {
					continue;
				}
				cnt += 1;
				if (cnt == K) {
					System.out.println(multipleNumber);
					System.exit(0);
				}
				isPrime[multipleNumber] = true;
			}
		}
	}
}
