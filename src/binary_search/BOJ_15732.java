package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15732 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		Rule[] rules = new Rule[K];
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			rules[K] = new Rule(A, B, C);
		}
		
		System.out.println(binary_search(N, rules, D));
	}
	
	public static class Rule{
		int A, B, C;
		
		public Rule(int A, int B, int C) {
			this.A = A;
			this.B = B;
			this.C = C;
		}
	}
	
	public static int binary_search(int N, Rule[] rules, int D) {
		int res = 0;
		int left = 1;
		int right = N;
		int mid;
		
		while(left <= right) {
			long sum = 0;
			mid = (left + right) >> 1;
		
			for(Rule r : rules) {
				int end = Math.min(mid, r.B);
				if(r.A <= end)
					sum += (end - r.A) / r.C + 1;
			}
			
			if(sum >= D) {
				res = mid;
				right = mid - 1;
			}
			else
				left = mid + 1;
		}
		return res;
	}
}
