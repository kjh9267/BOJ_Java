package depth_first_search;

// https://www.acmicpc.net/problem/17220

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17220 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N];

        for (int node = 0; node < N; node++) {
            graph[node] = new ArrayList<>();
        }

        for (int link = 0; link < M; link++) {
            st = new StringTokenizer(br.readLine());
            int A = st.nextToken().charAt(0) - 65;
            int B = st.nextToken().charAt(0) - 65;
            graph[A].add(B);
        }

        st = new StringTokenizer(br.readLine());
        boolean[] isTarget = initTargets(st, N);
        boolean[] visited = new boolean[N];
        int[] sourceCandidates = new int[N];

        List<Integer> sources = findSources(N, graph, visited, sourceCandidates);

        visited = new boolean[N];
        int[] validNodesNumber = new int[1];
        for (int source: sources) {
            countValidNodesNumber(source, validNodesNumber, graph, visited, isTarget, sources);
        }

        System.out.println(validNodesNumber[0] - sources.size());
    }

    private static boolean[] initTargets(StringTokenizer st, int N) {
        st.nextToken();
        boolean[] isTarget = new boolean[N];

        while (st.hasMoreTokens()) {
            int target = st.nextToken().charAt(0) - 65;
            isTarget[target] = true;
        }

        return isTarget;
    }

    private static List<Integer> findSources(int N, List<Integer>[] graph, boolean[] visited, int[] sourceCandidates) {
        List<Integer> result = new ArrayList<>();
        int sources = (int) Math.pow(2, N) - 1;

        for (int node = 0; node < N; node++) {
            int sourceCandidate = findSourceCandidates(node, graph, visited, (int) Math.pow(2, N) - 1);
            sourceCandidates[node] = sourceCandidate;
        }

        for (int node = 0; node < N; node++) {
            sources &= sourceCandidates[node];
        }

        while (sources > 0) {
            int source = sources & -sources;
            result.add((int) (Math.log(source) / Math.log(2)));
            sources -= source;
        }

        return result;
    }

    private static int findSourceCandidates(int cur, List<Integer>[] graph, boolean[] visited, int sourceCandidates) {
        if (visited[cur]) {
            sourceCandidates ^= (1 << cur);
            return sourceCandidates;
        }
        visited[cur] = true;

        for (int next: graph[cur]) {
            sourceCandidates = findSourceCandidates(next, graph, visited, sourceCandidates);
        }

        return sourceCandidates;
    }

    private static void countValidNodesNumber(int cur, int[] validCount, List<Integer>[] graph, boolean[] visited, boolean[] isTarget, List<Integer> sources) {
        if (isTarget[cur]) {
            if (sources.contains(cur)) {
                validCount[0] += 1;
            }
            return;
        }
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        validCount[0] += 1;

        for (int next: graph[cur]) {
            countValidNodesNumber(next, validCount, graph, visited, isTarget, sources);
        }
    }
}
