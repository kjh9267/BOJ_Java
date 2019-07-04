package tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/1991
 *
 */

public class BOJ_1991 {
	public static Node[] tree;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new Node[26];
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cur = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0) - 'A';
			int right = st.nextToken().charAt(0) - 'A';
			tree[cur] = new Node(left, right);
		}
		preorder(0);
		sb.append('\n');
		inorder(0);
		sb.append('\n');
		postorder(0);
		System.out.println(sb);
	}
	
	public static class Node{
		int left, right;
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	
	public static void preorder(int cur) {
		if(cur < 0)
			return;
		sb.append((char) (cur + 65));
		preorder(tree[cur].left);
		preorder(tree[cur].right);
	}
	
	public static void inorder(int cur) {
		if(cur < 0)
			return;
		inorder(tree[cur].left);
		sb.append((char) (cur + 65));
		inorder(tree[cur].right);
	}
	
	public static void postorder(int cur) {
		if(cur < 0)
			return;
		postorder(tree[cur].left);
		postorder(tree[cur].right);
		sb.append((char) (cur + 65));
	}
}
