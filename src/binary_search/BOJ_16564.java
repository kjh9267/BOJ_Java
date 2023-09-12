package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/16564
 *
 */

public class BOJ_16564 {

	private static final int LIMIT = 2_000_000_001;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] levels = new int[N];
		
		for (int index = 0; index < N; index++) {
			levels[index] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(levels);
		
		System.out.println(binarySearch(N, K, levels));
	}

	private static long binarySearch(int N, int K, int[] levels) {
		long lo = 1;
		long hi = LIMIT;
		
		while (lo + 1 < hi) {
			long mid = (lo + hi) >> 1;

			if (isPossible(N, K, levels, mid)) {
				lo = mid;
			}
			else {
				hi = mid;
			}
		}

		return lo;
	}

	private static boolean isPossible(int N, int K, int[] levels, long target) {
		int sum = K;

		for (int index = 0; index < N; index++) {
			if (levels[index] >= target) {
				continue;
			}
			sum -= target - levels[index];

			if (sum < 0) {
				return false;
			}
		}

		return true;
	}
}
