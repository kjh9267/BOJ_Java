package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5549 {
	
	public static class Area{
		private int jungle;
		private int ocean;
		private int ice;
		public Area(int jungle, int ocean, int ice) {
			this.jungle = jungle;
			this.ocean = ocean;
			this.ice = ice;
		}
	}
	
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine().trim());	
		char[][] graph = new char[M+1][N+1];
		Area[][] dp = new Area[M+1][N+1];
		
		for(int i = 1; i < M + 1; i++) {
			String line = br.readLine();
			for(int j = 1; j < N + 1; j++) {
				graph[i][j] = line.charAt(j-1);
			}
		}
		
		for(int i = 0; i < M + 1 ; i++) {
			dp[i][0] = new Area(0,0,0);
		}
		
		for(int i = 0; i < N + 1; i++) {
			dp[0][i] = new Area(0,0,0);
		}
		
		if(graph[1][1] == 'J') {
			dp[1][1] = new Area(1,0,0);
		}
		else if(graph[1][1] == 'O') {
			dp[1][1] = new Area(0,1,0);
		}
		else {
			dp[1][1] = new Area(0,0,1);
		}
		
		for(int i = 2; i < M + 1; i++) {
			int prevJ = dp[i-1][1].jungle;
			int prevO = dp[i-1][1].ocean;
			int prevI = dp[i-1][1].ice;
			if(graph[i][1] == 'J') {
				dp[i][1] = new Area(prevJ + 1, prevO, prevI);
			}
			else if(graph[i][1] == 'O') {
				dp[i][1] = new Area(prevJ, prevO + 1, prevI);
			}
			else {
				dp[i][1] = new Area(prevJ, prevO, prevI + 1);
			}
		}
		
		for(int i = 2; i < N + 1; i++) {
			int prevJ = dp[1][i-1].jungle;
			int prevO = dp[1][i-1].ocean;
			int prevI = dp[1][i-1].ice;
			if(graph[1][i] == 'J') {
				dp[1][i] = new Area(prevJ + 1, prevO, prevI);
			}
			else if(graph[1][i] == 'O') {
				dp[1][i] = new Area(prevJ, prevO + 1, prevI);
			}
			else {
				dp[1][i] = new Area(prevJ, prevO, prevI + 1);
			}
		}
		
		for(int i = 2; i < M + 1; i++) {
			for(int j = 2; j < N + 1; j++) {
				int prevJ = dp[i][j-1].jungle + dp[i-1][j].jungle - dp[i-1][j-1].jungle;
				int prevO = dp[i][j-1].ocean + dp[i-1][j].ocean - dp[i-1][j-1].ocean;
				int prevI = dp[i][j-1].ice + dp[i-1][j].ice - dp[i-1][j-1].ice;
				if(graph[i][j] == 'J') {
					dp[i][j] = new Area(prevJ + 1, prevO, prevI);
				}
				else if(graph[i][j] == 'O') {
					dp[i][j] = new Area(prevJ, prevO + 1, prevI);
				}
				else {
					dp[i][j] = new Area(prevJ, prevO, prevI + 1);
				}
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int jungle = dp[c][d].jungle + dp[a-1][b-1].jungle - dp[a-1][d].jungle - dp[c][b-1].jungle;
			int ocean = dp[c][d].ocean + dp[a-1][b-1].ocean - dp[a-1][d].ocean - dp[c][b-1].ocean;
			int ice = dp[c][d].ice + dp[a-1][b-1].ice - dp[a-1][d].ice - dp[c][b-1].ice;
			
			System.out.println(jungle + " " + ocean + " " + ice);
		}
	}
}
