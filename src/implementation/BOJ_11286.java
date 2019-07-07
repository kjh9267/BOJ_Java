package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/11286
 *
 */

public class BOJ_11286 {
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Num> pq = new PriorityQueue<Num>();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			int op = Integer.parseInt(br.readLine());
			if(op == 0) {
				if(pq.isEmpty())
					sb.append("0\n");
				else
					sb.append(pq.poll().num).append('\n');
			}
			else
				pq.add(new Num(op));
		}
		System.out.println(sb);
	}
	
	public static class Num implements Comparable<Num>{
		int num;
		public Num(int num) {
			this.num = num;
		}
		@Override
		public int compareTo(Num o) {
			if(Math.abs(this.num) == Math.abs(o.num))
				return this.num - o.num;
			return Math.abs(this.num) - Math.abs(o.num);
		}
		
	}
}
