package disjoint_set;

// https://www.acmicpc.net/problem/17619

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17619 {

    private static final String TRUE = "1";

    private static final String FALSE = "0";

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        Link[] links = new Link[N];
        int[] parents = new int[N];

        for (int id = 0; id < N; id++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            links[id] = new Link(id, x1, x2, y);
            parents[id] = -x2;
        }

        Arrays.sort(links);

        connectLinks(N, links, parents);

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            int link1Id = Integer.parseInt(st.nextToken()) - 1;
            int link2Id = Integer.parseInt(st.nextToken()) - 1;

            if (find(link1Id, parents) == find(link2Id, parents)) {
                sb.append(TRUE);
            }
            else {
                sb.append(FALSE);
            }
            sb.append(NEW_LINE);
        }

        System.out.print(sb);
    }

    private static void connectLinks(int N, Link[] links, int[] parents) {
        for (int index = 0; index < N - 1; index++) {
            Link link1 = links[index];
            Link link2 = links[index + 1];

            int link1Parent = find(link1.id, parents);

            if (-parents[link1Parent] >= link2.startX) {
                merge(link1.id, link2.id, parents);
            }
        }
    }

    private static class Link implements Comparable<Link> {
        int id;
        int startX;
        int endX;
        int y;

        Link(int id, int startX, int endX, int y) {
            this.id = id;
            this.startX = startX;
            this.endX = endX;
            this.y = y;
        }

        @Override
        public int compareTo(Link other) {
            if (this.startX == other.startX) {
                return Integer.compare(this.endX, other.endX);
            }
            return Integer.compare(this.startX, other.startX);
        }
    }

    private static void merge(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x == y) {
            return ;
        }

        parents[x] = Math.min(parents[x], parents[y]);
        parents[y] = x;
    }

    private static int find(int x, int[] parents) {
        if (parents[x] < 0) {
            return x;
        }
        return parents[x] = find(parents[x], parents);
    }
}
