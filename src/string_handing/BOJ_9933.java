package string_handing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_9933 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> words = new ArrayList<>();
		
		while(N-- > 0) {
			String word = br.readLine();
			words.add(word);
			String rev_word = reverse(word);
			
			if(words.contains(rev_word)) {
				int len = word.length();
				System.out.println(len + " " + rev_word.charAt(len / 2));
				break;
			}
		}
	}
	
	public static String reverse(String word) {
		String res = "";
		int len = word.length();
		
		while(--len >= 0) {
			res += word.charAt(len);
		}
		return res;
	}
}
