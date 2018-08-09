import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class E {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] sequence = new int[N];
		
		for(int i = 0; i < N; i++) {
			sequence[i] = Integer.parseInt(st.nextToken());
		}
		
		
	}
}
