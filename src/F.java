import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class F {
	static long[] damage;
	
	static StringTokenizer st;
	
	public static void query2(int s, int e, int l, int N, int i) {
		int temp = 0;
		for (int j = s; j < N + 1; j++) {
			if (j < e + 1) {
				temp += 1;
			}
			damage[j] += (temp * l);
		}
	}
	
	public static void init(StringTokenizer st, int N) {
		for (int i = 1; i < N + 1; i++) {
			damage[i] = damage[i - 1] + Integer.parseInt(st.nextToken());
		}
	}
	
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		int N = Integer.parseInt(st.nextToken());
		int Q1 = Integer.parseInt(st.nextToken());
		int Q2 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine().trim());
		damage = new long[N + 1];

		init(st, N);

		for (int i = 0; i < Q1 + Q2; i++) {
			st = new StringTokenizer(br.readLine().trim());

			int query = Integer.parseInt(st.nextToken());

			if (query == 1) {
				int n = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				System.out.println(damage[m] - damage[n - 1]);
			} 
			else {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				query2(s,e,l,N,i);
			}
		}
	}
}