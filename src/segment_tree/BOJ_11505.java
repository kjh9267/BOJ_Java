package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11505 {
	private static long[] tree;
	private static int height;
	private static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int loop = M + K;
		
		height = 1;
		while (height < N) height <<= 1;
		tree = new long[height << 1];
		Arrays.fill(tree, 1);
		
		for(int i = 1; i < N + 1; i++) {
			long val = Long.parseLong(br.readLine());
			update(i,val);
		}
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (a == 1) update(b,c);
			else sb.append(multi(1,1,height,b,c)).append("\n");
		}
		System.out.print(sb);
	}

	private static void update(int node, long value) {
		node += height - 1;
		tree[node] = value;
		while(node > 1) {
			node >>= 1;
			tree[node] = tree[node << 1] * tree[(node << 1) + 1] % MOD;
		}
	}

	private static long multi(int node, int start, int end, int left, int right) {
		if(left > end || right < start) return 1;
		if(left <= start && end <= right) return tree[node] % MOD;
		int mid = (start + end) >> 1;
		return multi(node << 1, start, mid, left, right)
				* multi((node << 1) + 1, mid + 1, end, left, right) % MOD;
	}
}
