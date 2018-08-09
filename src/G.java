import java.util.Scanner;

public class G {
	private static Scanner sc;
	private static int N, Q, T, L, R;
	
	public static void main(String args[]) throws Exception {
		sc = new Scanner(System.in);
		
		N = sc.nextInt();
		Q = sc.nextInt();

		for(int i = 0; i < N; i++) {
			sc.nextInt();
		}
		
		for(int i = 0; i < Q; i++) {
			T = sc.nextInt();
			L = sc.nextInt();
			R = sc.nextInt();

			if(T - N - 1 <= L && L <= T && T <= R) {
				System.out.println(T - L + 1);
			}
			else if(L <= T - N - 1 && T <= R) {
				System.out.println(N + 1);
			}
			else if(L <= T - N - 1 && T - N - 1 <= R && R <= T) {
				System.out.println(R - T + N +2);
			}
			else {
				System.out.println(0);
			}
		}
	}
}
