package strongly_connected_component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 
 * @author Junho
 *
 * @see https://www.acmicpc.net/problem/4013
 *
 */

public class BOJ_4013 {
	public static int N, M, S, P, cnt, DAGIndex = -1;
	public static ArrayList<Integer>[] graph, DAG;
	public static int[] atm, indegree, dfsN, DAGCheck, res, sum;
	public static boolean[] finished, restaurants, hasRestaurant, canGo;
	public static Stack<Integer> stack;
	public static ArrayList<ArrayList<Integer>> scc;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		atm = new int[N + 1];
		restaurants = new boolean[N + 1];
		dfsN = new int[N + 1];
		finished = new boolean[N + 1];
		stack = new Stack<>();
		scc = new ArrayList<>();
		DAGCheck = new int[N + 1];
		
		for(int i = 0; i < N + 1; i++)
			graph[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
		}
		
		for(int i = 1; i < N + 1; i++) 
			atm[i] = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < P; i++) {
			int index = Integer.parseInt(st.nextToken());
			restaurants[index] = true;
		}
		for(int i = 1; i < N; i++)
			if(!finished[i])
				dfs(i);

		indegree = new int[DAGIndex + 1];
		DAG = new ArrayList[DAGIndex + 1];
		res = new int[DAGIndex + 1];
		hasRestaurant = new boolean[DAGIndex + 1];
		sum = new int[DAGIndex + 1];
		canGo = new boolean[DAGIndex + 1];
		
		for(int i = 0; i <= DAGIndex; i++)
			DAG[i] = new ArrayList<>();
		
		for(int i = 0; i <= DAGIndex; i++) {
			for(int node : scc.get(i)) {
				if(restaurants[node])
					hasRestaurant[DAGCheck[node]] = true;
				sum[i] += atm[node];
				for(int next : graph[node]) {
					if(DAGCheck[node] != DAGCheck[next]) {
						DAG[i].add(DAGCheck[next]);
						indegree[DAGCheck[next]] += 1;
					}
				}
			}
		}

		topologicalSort(DAGIndex + 1);

		int max = 0;
		for(int i = 0; i < DAGIndex + 1; i++)
			if(max < res[i] && hasRestaurant[i] && canGo[i])
				max = res[i];

		System.out.println(max);
	}
	
	public static class SCCNode{
		int next;
		int cost;
		
		public SCCNode(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
	}
	
	public static int dfs(int cur) {
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
		
	public static void extract(int cur) {
		DAGIndex += 1;
		scc.add(new ArrayList<Integer>());
		while(true) {
			int node = stack.pop();
			scc.get(DAGIndex).add(node);
			finished[node] = true;
			DAGCheck[node] = DAGIndex;
			if(node == cur)
				break;
		}
	}
	
	public static void topologicalSort(int N) {
		Queue<Integer> queue = new LinkedList<>();

		for(int i = 0; i < N; i++) {
			res[i] = sum[i];
			if(i == DAGCheck[S])
				canGo[i] = true;
			if(indegree[i] == 0)
				queue.offer(i);
		}
		
		while(N-- > 0) {
			int cur = queue.poll();
			for(int next : DAG[cur]) {
				if(canGo[cur]) {
					res[next] = Math.max(res[next], sum[next] + res[cur]);
					canGo[next] = true;
				}
				if (--indegree[next] == 0) 
					queue.offer(next);
			}
		}
	}
}
