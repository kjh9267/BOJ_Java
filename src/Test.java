

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2820
 *
 */

public class Test {
	public static int N, cnt = 1;
	public static int[] tree, data, start, end, temp;
	public static ArrayList<Integer>[] adj;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		InputReader in = new InputReader(System.in);
		N = in.readInt();
		int M = in.readInt();
		adj = new ArrayList[N + 1];
		data = new int[N + 1];
		start = new int[N + 1];
		end = new int[N + 1];
		tree = new int[N + 1];
		temp = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++)
			adj[i] = new ArrayList<Integer>();
		
		temp[1] = in.readInt();
		for(int i = 2; i < N + 1; i++) {
//			st = new StringTokenizer(br.readLine());
			int cost = in.readInt();
			int parent = in.readInt();
			adj[parent].add(i);
			temp[i] = cost;
		}
		
		dfs(1);
		for(int i = 1; i < N + 1; i++)
			data[start[i]] = temp[i];

		for(int i = 1; i < N + 1; i++) {
			update(start[i], data[start[i]]);
			update(start[i] + 1, -data[start[i]]);
		}

		for(int i = 0 ; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
			char op = in.readString().charAt(0);
			if(op == 'p') {
				int s = in.readInt();
				int c = in.readInt();
				update(start[s] + 1, c);
				update(end[s] + 1, -c);
			}
			else {
				int s = in.readInt();
				sb.append(get(start[s])).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static void dfs(int cur) {
		start[cur] = cnt++;
		for(int next : adj[cur])
			dfs(next);
		end[cur] = cnt - 1;
	}
	
	public static void update(int idx, int diff) {
		while(idx < N + 1) {
			tree[idx] += diff;
			idx += (-idx & idx);
		}
	}
	
	public static int get(int idx) {
		int acc = 0;
		while(idx > 0) {
			acc += tree[idx];
			idx -= (-idx & idx);
		}
		return acc;
	}
	
	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}
		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}
