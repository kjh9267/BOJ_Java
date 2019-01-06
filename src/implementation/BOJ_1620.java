package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1620 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 0;

		Map<String, Integer> name = new HashMap<>();
		String[] num = new String[N + 1];

		while (N-- > 0) {
			String line = br.readLine();
			name.put(line, ++cnt);
			num[cnt] = line;
		}
		while (M-- > 0) {
			String line = br.readLine();
			int ascii = line.charAt(0) - 'A';

			if (0 <= ascii && ascii < 26)
				sb.append(name.get(line)).append('\n');
			else {
				int index = Integer.parseInt(line);
				sb.append(num[index]).append('\n');
			}
		}
		System.out.println(sb);
	}
}
