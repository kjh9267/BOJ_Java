/**
 * @author Junho
 * 
 * @see https://www.acmicpc.net/problem/1939
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Test {
	public static int N, M, source, sink, MAX = 0;
	public static ArrayList<Node>[] graph;
	public static boolean[] visited;
	public static boolean flag;
	
	public static void main(String[] args) throws Exception{
		InputReader in = new InputReader(System.in);
		N = in.readInt();
		M = in.readInt();
		graph = new ArrayList[N + 1];
		
		for(int i = 1; i < N + 1; i++)
			graph[i] = new ArrayList<Node>();
		
		for(int i = 0; i < M; i++) {
			int x = in.readInt();
			int y = in.readInt();
			int cost = in.readInt();
			graph[x].add(new Node(y, cost));
			graph[y].add(new Node(x, cost));
			MAX = Math.max(MAX, cost);
		}
		source = in.readInt();
		sink = in.readInt();
		
		System.out.println(binarySearch());
	}
	
	public static class Node{
		int next;
		int cost;
		public Node(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
	}
	
	public static int binarySearch() {
		int lo = 1;
		int hi = MAX + 1;
		
		while(lo + 1 < hi) {
			int mid = (lo + hi) >> 1;
			visited = new boolean[N + 1];
			visited[source] = true;
			flag = false;
			dfs(source, mid);
			if(flag)
				lo = mid;
			else
				hi = mid;
		}
		return lo;
	}
	
	public static void dfs(int cur, int cost) {
		if(cur == sink) {
			flag = true;
			return;
		}
		for(Node n : graph[cur]) {
			if(visited[n.next])
				continue;
			if(n.cost < cost)
				continue;
			visited[n.next] = true;
			dfs(n.next, cost);
		}
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

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}
}

