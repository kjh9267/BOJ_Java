package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1963 {
	public static boolean[] isPrime;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		isPrime = new boolean[10000];
		int T = Integer.parseInt(br.readLine());
		
		init();
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			int res = bfs(from, to);
			sb.append(res == -1 ? "Impossible" : res).append('\n');
		}
		System.out.print(sb);
	}
	
	public static void init() {
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		
		for(int i = 4; i < 10000; i+=2)
			isPrime[i] = false;
		
		for(int i = 3; i < 10000; i+=2)
			for(int j = i*i; j < 10000; j+=i*2)
				isPrime[j] = false;
	}
	
	public static void check(int cur, int next, int[] visited, Queue<Integer> queue) {
		if(isPrime[next] && visited[next] == -1) {
			queue.offer(next);
			visited[next] = visited[cur] + 1;
		}
	}
	
	public static int bfs(int from, int to) {
		Queue<Integer> queue = new LinkedList<>();
		int[] visited = new int[10000];
		queue.offer(from);
		Arrays.fill(visited, -1);
		visited[from] = 0;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			int thousand = cur / 1000;
			int hundred = (cur % 1000) / 100;
			int ten = (cur % 100) / 10;
			int one = cur % 10;
			
			for(int i = 1; i < 10; i++) {
				int next = i * 1000 + hundred * 100 + ten * 10 + one;
				check(cur, next, visited, queue);
			}
			for(int i = 0; i < 10; i++) {
				int next = thousand * 1000 + i * 100 + ten * 10 + one;
				check(cur, next, visited, queue);
			}
			for(int i = 0; i < 10; i++) {
				int next = thousand * 1000 + hundred * 100 + i * 10 + one;
				check(cur, next, visited, queue);
			}
			for(int i = 0; i < 10; i++) {
				int next = thousand * 1000 + hundred * 100 + ten * 10 + i;
				check(cur, next, visited, queue);
			}
		}
		return visited[to];
	}
}
