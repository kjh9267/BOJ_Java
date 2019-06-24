import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String num = br.readLine();
			boolean key = true;
			if(num.equals("0"))
				break;
			int len = num.length();
			for(int i = 0; i < len / 2; i++) {
				if(num.charAt(i) != num.charAt(len - 1 - i)) {
					key = false;
					break;
				}
			}
			if(key)
				sb.append("yes\n");
			else
				sb.append("no\n");
		}
		System.out.println(sb);
	}
}