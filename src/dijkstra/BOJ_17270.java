package dijkstra;

// https://www.acmicpc.net/problem/17270

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17270 {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N, M;
    private static ArrayList<Node>[] adj;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N + 1];

        for(int node = 1; node < N + 1; node++)
            adj[node] = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adj[x].add(new Node(y, cost));
            adj[y].add(new Node(x, cost));
        }

        st = new StringTokenizer(br.readLine());
        int J = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] jDist = dijkstra(J);
        int[] sDist = dijkstra(S);

        System.out.println(findNode(jDist, sDist));
    }

    public static class Node implements Comparable<Node>{
        int node;
        int cost;

        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];

        pq.offer(new Node(start, 0));
        Arrays.fill(dist, MAX);
        dist[start] = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node n: adj[cur.node]){
                if(dist[n.node] > dist[cur.node] + n.cost){
                    dist[n.node] = dist[cur.node] + n.cost;
                    pq.offer(new Node(n.node, dist[n.node]));
                }
            }
        }

        return dist;
    }

    public static int findNode(int[] jDist, int[] sDist){
        ArrayList<Integer> candi = new ArrayList<>();
        int mini = MAX;
        int jMini = MAX;

        for(int node = 1; node < N + 1; node++) {
            if(jDist[node] == 0 || sDist[node] == 0)
                continue;
            mini = Math.min(mini, jDist[node] + sDist[node]);
        }

        for(int node = 1; node < N + 1; node++){
            if(jDist[node] == 0 || sDist[node] == 0)
                continue;
            if(mini != jDist[node] + sDist[node])
                continue;
            if(jDist[node] > sDist[node])
                continue;
            jMini = Math.min(jMini, jDist[node]);
        }

        for(int node = 1; node < N + 1; node++) {
            if (jDist[node] == jMini) return node;
        }

        return -1;
    }
}
