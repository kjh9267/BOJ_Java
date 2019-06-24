package geometry;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/3053
 *
 */

public class BOJ_3053 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int R = Integer.parseInt(br.readLine());
	
		System.out.println(Math.PI * Math.pow(R, 2));
		System.out.println(Math.pow((R / Math.sqrt(2)) * 2, 2));
	}
}
