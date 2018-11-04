package segment_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {
	public static int height, size;
	public static long[] segment;
	public static final String NEW_LINE = "\n";
	
	public static void update(int i, long value) {
		i += size/2 - 1;
		segment[i] = value;
		while(i > 1) {
			i /= 2;
			segment[i] = segment[i*2] + segment[i*2 + 1];
		}
	}
	
	public static long sum(long L, long R, int nodeNum, int nodeL, int nodeR) {
//		System.out.println(L + " " + R + " " + nodeL + " " + nodeR);
		if(nodeR == nodeL) {
			int temp = nodeL;
			return segment[temp];
		}
		if(R < nodeL || nodeR < L) return 0;
		if(L <= nodeL && nodeR <= R) return segment[nodeNum];
		int mid = (nodeL + nodeR) / 2;
		return sum(L, R, nodeNum*2, nodeL, mid) % Long.MAX_VALUE + 
				sum(L, R, nodeNum*2 + 1, mid+1, nodeR) % Long.MAX_VALUE;
	}
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		height = (int) Math.ceil((Math.log(N)/Math.log(2)));
		size = (int) Math.pow(2, height+1);
		segment = new long[size];
//		System.out.println(size + "\n");
		for(int i = 1; i < N + 1; i++) {
			long num = Long.parseLong(br.readLine()) % Long.MAX_VALUE;
			update(i,num);
		}
		
//		for(int i = 1; i < size; i++) {
//			System.out.println(i + " " + segment[i]);
//		}
		
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				update((int)b, c);
//				System.out.println();
//				for(int j = 0; j < size; j++) {
//					System.out.println(j + " " + segment[j]);
//				}
			}
			else {
				sb.append(sum(b, c, 1, 1, N)).append(NEW_LINE);
			}
		}
		System.out.println(sb.toString());
	}
}
