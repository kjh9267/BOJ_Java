package strongly_connected_component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/2150
 *
 */

public class BOJ_2150 {
	private static int cnt;
	private static int scc;
	private static ArrayList<Integer>[] graph;
	private static ArrayList<Integer>[] res;
	private static int[] dfsN;
	private static boolean[] finished;
	private static Stack<Integer> stack;
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V + 1];
		dfsN = new int[V + 1];
		finished = new boolean[V + 1];
		stack = new Stack<>();
		res = new ArrayList[V + 1];
		
		for(int i = 0; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
			res[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A].add(B);
		}
		
		for(int i = 1; i < V + 1; i++)
			if(dfsN[i] == 0)
				dfs(i);
		
		sorting();
		
		sb.append(scc).append('\n');
		for(int i = 0; i < scc; i++) {
			for(int j = 0; j < res[i].size(); j++)
				sb.append(res[i].get(j)).append(' ');
			sb.append(-1).append('\n');
		}

		System.out.print(sb);
	}

	private static int dfs(int cur) {
		dfsN[cur] = ++cnt;
		stack.push(cur);
		
		int ancestor = dfsN[cur];
		for(int next : graph[cur]) {
			if(dfsN[next] == 0)
				ancestor = Math.min(ancestor, dfs(next));
			else if(!finished[next])
				ancestor = Math.min(ancestor, dfsN[next]);
		}
		
		if(ancestor == dfsN[cur])
			extract(cur);
			
		return ancestor;
	}

	private static void extract(int cur) {
		scc += 1;
		while(true) {
			int node = stack.pop();
			res[scc].add(node);
			finished[node] = true;
			if(node == cur)
				break;
		}
	}

	private static void sorting() {

		for(int i = 1; i < scc + 1; i++)
			Collections.sort(res[i]);
		
		Arrays.sort(res, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
				if(a.isEmpty())
					return 2;
				if(b.isEmpty())
					return -2;
				if(a.get(0) < b.get(0))
					return -1;
				else if(a.get(0) == b.get(0))
					return 0;
				else
					return 1;
			}
		});
	}
}
