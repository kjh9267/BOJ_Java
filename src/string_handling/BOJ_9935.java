package string_handling;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_9935 {
	private static int bomb_len;
	private static int str_len;
	private static int pointer;
	private static char[] str;
	private static char[] bomb;
	private static char[] res;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		str = br.readLine().toCharArray();
		bomb = br.readLine().toCharArray();
		str_len = str.length;
		bomb_len = bomb.length;
		res = new char[str_len];
		pointer = 0;

		for(int i = 0; i < str_len; i++) {
			res[pointer] = str[i];
			pointer++;
			if(check(i))
				pointer -= bomb_len;
		}
		
		for(int i = 0; i < pointer; i++) {
			sb.append(res[i]);
		}
		
		System.out.println(pointer == 0 ? "FRULA" : sb);
	}

	private static boolean check (int index) {
		if(str[index] == bomb[bomb_len - 1] && pointer >= bomb_len)
			if(isBomb())
				return true;
		return false;
	}

	private static boolean isBomb() {
		for(int i = 0, j = pointer - bomb_len; i < bomb_len; i++, j++)
			if(bomb[i] != res[j])
				return false;
		return true;
	}
}
