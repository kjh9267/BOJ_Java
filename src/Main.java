import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        String[][] grid = new String[N][N];

        for(int row = 0; row < N; row++){
            st = new StringTokenizer(br.readLine());
            for(int col = 0; col < N; col++){
                grid[row][col] = st.nextToken();
            }
        }
        solve(N, W, grid, sb);
        System.out.print(sb);
    }

    public static void solve(int N, int W, String[][] grid, StringBuilder sb){
        ArrayDeque<String> data = new ArrayDeque<>();
        int mid = N / 2;
        int clock = W > 0 ? 1 : -1;
        for(int start = 0; start < mid; start++){
            int end = N - start;

            for(int col = start; col < end; col++)
                data.addLast(grid[start][col]);
            for(int row = start + 1; row < end; row++)
                data.addLast(grid[row][end - 1]);
            for(int col = end - 2; col >= start; col--)
                data.addLast(grid[end - 1][col]);
            for(int row = end - 2; row > start; row--)
                data.addLast(grid[row][start]);

            int len = (end - start - 1) * 4;
            int cnt = Math.abs(W) % len;

            while (cnt-- > 0){
                if(clock == 1)
                    data.addFirst(data.pollLast());
                else
                    data.addLast(data.pollFirst());
            }
            clock *= -1;

            for(int col = start; col < end; col++)
                grid[start][col] = data.pollFirst();
            for(int row = start + 1; row < end; row++)
                grid[row][end - 1] = data.pollFirst();
            for(int col = end - 2; col >= start; col--)
                grid[end - 1][col] = data.pollFirst();
            for(int row = end - 2; row > start; row--)
                grid[row][start] = data.pollFirst();
        }

        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                sb.append(grid[row][col]).append(col != N - 1 ? ' ' : '\n');
            }
        }
    }
}
