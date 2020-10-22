package dijkstra;

// https://www.acmicpc.net/problem/17396

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17396 {
    private static final long INF = Long.MAX_VALUE;
    private static int N;
    private static int M;
    private static ArrayList<Node>[] adj;
    private static char[] sight;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sight = new char[N];

        adj = new ArrayList[N];
        for(int i = 0; i < N; i++)
            adj[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            sight[i] = st.nextToken().charAt(0);

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if((from != N - 1 && sight[from] == '1') || (sight[to] == '1' && to != N - 1))
                continue;
            adj[from].add(new Node(to, cost));
            adj[to].add(new Node(from, cost));
        }
        sight[N - 1] = '0';
        long res = dijkstra();
        System.out.println(res == INF ? -1 : res);
    }

    private static long dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[N];
        pq.offer(new Node(0, 0));
        Arrays.fill(dist, INF);
        dist[0] = 0;
        while (!pq.isEmpty()){
            Node cur = pq.poll();
            if(sight[cur.node] == '1')
                continue;
            sight[cur.node] = '1';
            for(Node n : adj[cur.node]){
                if(dist[n.node] <= dist[cur.node] + n.cost)
                    continue;
                dist[n.node] = dist[cur.node] + n.cost;
                pq.offer(new Node(n.node, dist[n.node]));
            }
        }
        return dist[N - 1];
    }

    private static class Node implements Comparable<Node>{
        int node;
        long cost;
        public Node(int node, long cost){
            this.node = node;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost < o.cost ? -1 : 1;
        }
    }
}
