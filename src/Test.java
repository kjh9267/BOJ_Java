import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test {
	public static int[] data;
	public static boolean[][] visited, visit;
	public static int target;
	
	public static final int[][] DIR = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		data = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i <N; i++)
			data[i] = Integer.parseInt(st.nextToken()) - 1;
		
		visited = new boolean[3][3];
		visited[data[0] / 3][data[0] % 3] = true;
		String res = "YES";
		for(int i = 0; i < N - 1; i++) {
			target = data[i + 1];
			visit = new boolean[3][3];
			if(!search(data[i]))
				res = "NO";
		}
		System.out.println(res);
		
		
	}
	public static boolean search(int i) {
		System.out.println(i);
		int row = i / 3;
		int col = i % 3;
		if(visited[row][col]) {
			if(i == target)
				return false;
			else {
				for(int[] dir : DIR) {
					int nextX = col + dir[0];
					int nextY = row + dir[1];
					if(nextX < 0 || nextX >= 3 || nextY < 0 || nextY >= 3)
						continue;
					if(visit[nextY][nextX])
						continue;
					visit[nextY][nextX] = true;
					if(search(nextY * 3 + nextX))
						return true;
				}
			}
		}
		else {
			if(i == target) {
				visited[row][col] = true;
				return true;
			}
				
		}
		return false;		
	}
	
}