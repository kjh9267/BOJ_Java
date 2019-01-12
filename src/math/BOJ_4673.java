package math;

import java.util.Arrays;

public class BOJ_4673 {
	public static final int MAX = 10_000;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		boolean[] self = new boolean[MAX + 1];
		
		Arrays.fill(self, true);
		
		for(int i = 1; i <= MAX; i++) {
			int num = i;
			int sum = i;
			
			while(num != 0) {
				sum += num % 10;
				num /= 10;
			}
			if(sum <= MAX)
				self[sum] = false;
		}
		for(int i = 1; i <= MAX; i++)
			if(self[i])
				sb.append(i).append('\n');
		System.out.print(sb);
	}
}
