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
	private static int N;
	private static int M;
	private static int S;
	private static int P;
	private static int cnt;
	private static int DAGIndex = -1;
	private static int DAGLen;
	private static ArrayList<Integer>[] graph;
	private static ArrayList<Integer>[] DAG;
	private static int[] atm;
	private static int[] indegree;
	private static int[] dfsN;
	private static int[] DAGCheck;
	private static int[] res;
	private static int[] sum;
	private static boolean[] finished;
	private static boolean[] restaurants;
	private static boolean[] hasRestaurant;
	private static boolean[] canGo;
	private static Stack<Integer> stack;
	private static ArrayList<ArrayList<Integer>> scc;
	
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

		DAGModeling();
		topologicalSort(DAGLen);

		int max = 0;
		for(int i = 0; i < DAGLen; i++)
			if(max < res[i] && hasRestaurant[i] && canGo[i])
				max = res[i];

		System.out.println(max);
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

	private static void DAGModeling() {
		DAGLen = DAGIndex + 1;
		indegree = new int[DAGLen];
		DAG = new ArrayList[DAGLen];
		res = new int[DAGLen];
		hasRestaurant = new boolean[DAGLen];
		sum = new int[DAGLen];
		canGo = new boolean[DAGLen];
		
		for(int i = 0; i < DAGLen; i++)
			DAG[i] = new ArrayList<>();
		
		for(int i = 0; i < DAGLen; i++) {
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
	}

	private static void topologicalSort(int N) {
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
