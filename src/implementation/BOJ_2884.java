package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2884{

	  public static void main(String[] args) throws Exception {
		  
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  StringTokenizer st = new StringTokenizer(br.readLine());
		  
		  int H = Integer.parseInt(st.nextToken());
		  int M = Integer.parseInt(st.nextToken());
		  
		  int minutes = 60 * H + M;
		  
		  minutes -= 45;
		  
		  if(minutes < 0) {
			  minutes += 24 * 60;
		  }
		  
		  H = minutes / 60;
		  M = minutes % 60;
		  
		  System.out.println(H + " " + M);
	  }
}