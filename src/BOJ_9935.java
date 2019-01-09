

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class BOJ_9935 {
	public static HashMap<Character, Integer> bomb_index;
	public static int bomb_len, str_len;
	public static char to_top;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		String bomb = br.readLine();
		ArrayDeque<Character> from = new ArrayDeque<>();
		ArrayDeque<Character> to = new ArrayDeque<>();
		bomb_index = new HashMap<>(); 
		str_len = str.length();
		bomb_len = bomb.length();

		init(bomb, str, from, bomb_index);
		solve(from, to, bomb);
		
		int size = to.size();
	
		while(!to.isEmpty()) {
			sb.append(to.pop());
		}
		
		System.out.println(size == 0 ? "FRULA" : sb);
	}

	public static void init(String bomb, String str, ArrayDeque<Character> from, HashMap<Character, Integer> bomb_index) {
		for (int i = 0; i < bomb_len; i++) {
			bomb_index.put(bomb.charAt(i), i);
		}
		for (int i = 0; i < str_len; i++) {
			from.push(str.charAt(i));
		}
	}
	
	public static void solve(ArrayDeque<Character> from, ArrayDeque<Character> to, String bomb) {
		int pointer = bomb_len - 1;

		while (!from.isEmpty()) {
			char cur = from.pop();
			to.push(cur);
			System.out.println(cur + " " + to.toString() + " " + from.toString());
			if (cur == bomb.charAt(pointer))
				pointer = check(pointer, to);
			else
				pointer = bomb_len - 1;
			System.out.println(pointer);
			System.out.println(cur + " " + to.toString() + " " + from.toString() + "\n");
		}
	}
	
	public static int check(int pointer, ArrayDeque<Character> to) {
		if (pointer == 0) {
			for (int i = 0; i < bomb_len; i++)
				to.pop();
			
			if(!to.isEmpty())
				to_top = to.peek();
			
			if (bomb_index.containsKey(to_top) && bomb_index.get(to_top) != 0) {
				pointer = bomb_index.get(to_top);
			} 
			else
				pointer = bomb_len;
		}
		return pointer - 1;
	}
}
