package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_5676 {
	public static int[] tree, nums;
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = "";
			line = br.readLine();
			if (line == null)
				break;
			StringTokenizer st = new StringTokenizer(line);
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int height = (int) Math.ceil(Math.log(N) / Math.log(2));
			int size = (int) Math.pow(2, height + 1);

			tree = new int[size];
			nums = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(num > 0)
					nums[i] = 1;
				else if (num == 0)
					nums[i] = 0;
				else
					nums[i] = -1;
			}
			init(1, 1, N);

			for (int i = 0; i < K; i++) {
				System.out.println(Arrays.toString(tree));

				st = new StringTokenizer(br.readLine());
				char op = st.nextToken().charAt(0);
				if (op == 'C') {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					if (b > 0)
						nums[a] = 1;
					else if (b == 0)
						nums[a] = 0;
					else
						nums[a] = -1;
					update(1, 1, N, a, nums[a]);
				} else {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int res = multi(1, 1, N, a, b);
					if (res > 0)
						sb.append("+");
					else if (res == 0)
						sb.append("0");
					else
						sb.append("-");
				}
			}
			System.out.println(sb.toString());
		}
	}

	public static int init(int node, int start, int end) {
		if (start == end)
			return tree[node] = nums[start];
		int mid = (start + end) / 2;
		return tree[node] = init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end);
	}

	public static int update(int node, int start, int end, int index, int diff) {
		if (!(start <= index && index <= end))
			return tree[node];
		if (start == end)
			return tree[node] = diff;
		int mid = (start + end) / 2;
		return tree[node] = update(node * 2, start, mid, index, diff)
				* update(node * 2 + 1, mid + 1, end, index, diff);
	}

	public static int multi(int node, int start, int end, int left, int right) {
		if (left > end || right < start)
			return 1;
		if (left <= start && end <= right)
			return tree[node];
		int mid = (start + end) / 2;
		return multi(node * 2, start, mid, left, right) * multi(node * 2 + 1, mid + 1, end, left, right);
	}
}
