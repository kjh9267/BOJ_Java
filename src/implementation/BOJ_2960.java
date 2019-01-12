package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2960 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[] nums = new boolean[N + 1];
		int cnt = 0;

		for (int i = 2; i <= N; i++) {
			if (nums[i])
				continue;
			for (int j = i; j <= N; j += i) {
				if (nums[j])
					continue;
				cnt++;
				if (cnt == K) {
					System.out.println(j);
					System.exit(0);
				}
				nums[j] = true;
			}
		}

	}
}
