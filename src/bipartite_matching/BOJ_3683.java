package bipartite_matching;

// https://www.acmicpc.net/problem/3683

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3683 {

    private static final char CAT = 'C';

    private static final String NEW_LINE = "\n";

    private static final int NOT_VISITED = -1;

    private static int people;

    private static int[] A;

    private static int[] B;

    private static boolean[][] graph;

    private static boolean[] visited;

    private static boolean[] catUsers;


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            st.nextToken();
            people = Integer.parseInt(st.nextToken());
            graph = new boolean[people][people];
            catUsers = new boolean[people];
            int[][] data = new int[people][2];

            for (int person = 0; person < people; person++) {
                st = new StringTokenizer(br.readLine());
                String animal = st.nextToken();
                char firstAnimal = animal.charAt(0);
                int firstNum = Integer.parseInt(animal.substring(1));

                animal = st.nextToken();
                int secondNum = Integer.parseInt(animal.substring(1));

                if (firstAnimal == CAT) {
                    data[person][0] = firstNum;
                    data[person][1] = secondNum;
                    catUsers[person] = true;
                }
                else {
                    data[person][0] = secondNum;
                    data[person][1] = firstNum;
                }
            }

            init(data, catUsers);

            int total = bipartiteMatch();

            sb.append(people - total)
                    .append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void init(int[][] data, boolean[] catUsers) {
        for (int person = 0; person < people; person++) {
            connect(person, data, catUsers);
        }
    }

    private static void connect(int person, int[][] data, boolean[] catUsers) {
        int cat = data[person][0];
        int dog = data[person][1];

        if (catUsers[person]) {
            for (int other = 0; other < people; other++) {
                if (catUsers[other]) {
                    continue;
                }
                if (data[other][1] == dog) {
                    graph[person][other] = true;
                }
            }
        }
        else {
            for (int other = 0; other < people; other++) {
                if (!catUsers[other]) {
                    continue;
                }
                if (data[other][0] == cat) {
                    graph[other][person] = true;
                }
            }
        }
    }

    private static int bipartiteMatch() {
        A = new int[people];
        B = new int[people];
        int total = 0;

        Arrays.fill(A, NOT_VISITED);
        Arrays.fill(B, NOT_VISITED);

        for (int a = 0; a < people; a++) {
            visited = new boolean[people];

            if (dfs(a)) {
                total += 1;
            }
        }

        return total;
    }

    private static boolean dfs(int a) {
        if (visited[a]) {
            return false;
        }
        visited[a] = true;

        for (int b = 0; b < people; b++) {
            if (catUsers[b]) {
                continue;
            }
            if (!graph[a][b]) {
                continue;
            }
            if (B[b] == NOT_VISITED || dfs(B[b])) {
                A[a] = b;
                B[b] = a;

                return true;
            }
        }

        return false;
    }
}
