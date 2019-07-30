import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
public class Test {
	public static int N;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		IR in = new IR(System.in);
		N = in.rI();

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
