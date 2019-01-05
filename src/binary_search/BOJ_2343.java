package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		long max = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			max += num;
		}
		System.out.println(binary_search(N, M, arr, max));
	}

	public static long binary_search(int N, int M, int[] arr, long max) {
		long res = 0;
		long left = 1;
		long right = max;
		long mid;

		while (left <= right) {
			int sum = 1;
			int blue = 0;
			mid = (left + right) >> 1;

			if (!possible(mid, arr, N)) {
				left = mid + 1;
				continue;
			}

			for (int i = 0; i < N; i++) {
				if (blue + arr[i] > mid) {
					sum += 1;
					blue = 0;
				}
				blue += arr[i];
			}

			if (sum <= M) {
				right = mid - 1;
				res = mid;
			} else
				left = mid + 1;
		}
		return res;
	}

	public static boolean possible(long mid, int[] arr, int N) {
		for (int i = 0; i < N; i++) {
			if (mid < arr[i])
				return false;
		}
		return true;
	}
}
