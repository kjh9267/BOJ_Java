import java.util.*;

public class Temp {

	private static int N, M, cnt;

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		dfs(0, 1, "");
	}

	private static void dfs(int step, int n, String result) {
//		System.out.println(result);
//		if (step >= M) {
//			System.out.println(result);
//			return;
//		}
		System.out.println(++cnt + "  "  + " " +  " " + step + " " + result);
		for (int i = n; i <= N; i++) {
			
			dfs(step + 1, i + 1, result + i + " ");}
	}
}