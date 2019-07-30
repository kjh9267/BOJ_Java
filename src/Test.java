import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
public class Test {
	public static long[] tree;
	public static int N;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		IR in = new IR(System.in);
		N = in.rI();
		tree = new long[N + 1];
		for(int i = 1; i <= N; i++) {
			int num = in.rI();
			update(i, num);
			update(i + 1, -num);
		}
		System.out.println(Arrays.toString(tree));
		int Q = in.rI();
		while(Q-- > 0) {
			int op = in.rI();
			if(op == 1) {
				int s = in.rI();
				int e = in.rI();
//				update(s, 1);
//				update(e + 1, -(e-s+1)*(e-s+2)/2);
				long cnt = 1;
				for(int i = s; i <= e;i ++) {
					update(i,1);
//					update(i+1,-cnt++);
				}
			}
			else
				sb.append(get(in.rI())).append('\n');
		}
		System.out.println(Arrays.toString(tree));
		System.out.println(sb);
	}
	public static void update(int idx, long value) {
		while(idx < N + 1) {
			tree[idx] += value;
			idx += (-idx & idx);
		}
	}
	public static long get(int idx) {
		long acc = 0;
		while(idx > 0) {
			acc += tree[idx];
			idx -= -idx & idx;
		}
		return acc;
	}
	private static class IR {
		private InputStream stream;
		private byte[] b = new byte[1024];
		private int cC;
		private int nCs;
		private SCF fi;
		public IR(InputStream stream) {
			this.stream = stream;
		}
		public int r() {
			if (nCs == -1)
				throw new InputMismatchException();
			if (cC >= nCs) {
				cC = 0;
				try {
					nCs = stream.read(b);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (nCs <= 0)
					return -1;
			}
			return b[cC++];
		}
		public int rI() {
			int c = r();
			while (isS(c))
				c = r();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = r();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = r();
			} while (!isS(c));
			return res * sgn;
		}
		public long rL() {
			int c = r();
			while (isS(c))
				c = r();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = r();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = r();
			} while (!isS(c));
			return res * sgn;
		}
		public boolean isS(int c) {
			if (fi != null)
				return fi.isS(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
		public interface SCF {
			public boolean isS(int ch);
		}
	}
}
