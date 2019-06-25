import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] arr = new long[K];
		long max = 0;
		
		for(int i = 0; i < K; i++) {
			int num = Integer.parseInt(br.readLine());
			if(max < num)
				max = num;
			arr[i] = num;
		}
		System.out.println(binary_search(N, K, max, arr));
	}
	
	public static long binary_search(int N, int K, long max, long[] arr) {
		long lo = 1;
		long hi = max + 1;
		long mid;

		while(lo + 1 < hi) {
			long cnt = 0;
			mid = (lo + hi) >> 1;
			
			for(int i = 0; i < K; i++)
				cnt += arr[i] / mid;

			if(cnt >= N)
				lo = mid;
			else
				hi = mid;
		}
		return lo;
	}
}
