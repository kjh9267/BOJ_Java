package disjoint_set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_4195 {
	public static HashMap<String, Integer> map;
	public static int[] parent;
	public static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int F = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			parent = new int[F * 2 + 1];
			Arrays.fill(parent, -1);
			int cnt = 0;
			res = 2;
			
			while (F-- > 0) {
				st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();

				if (!map.containsKey(A)) {
					map.put(A, ++cnt);
				}
				if (!map.containsKey(B)) {
					map.put(B, ++cnt);
				}
				System.out.println((merge(map.get(A),map.get(B))));
			}
		}
	}
	
	public static int find(int x) {
		if(parent[x] < 0) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	public static int merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) {
		}
		else if(parent[x] > parent[y]) {
			parent[y] += parent[x];
			parent[x] = y;
		}
		else {
			parent[x] += parent[y];
			parent[y] = x;
		}
		
		return parent[x] < 0 ? -parent[x] : -parent[y];
	}
}