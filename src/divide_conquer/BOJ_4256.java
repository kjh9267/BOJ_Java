package divide_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.stream.Collectors.joining;

public class BOJ_4256 {

    private static final String SPACE = " ";

    private static int[] preOrder;

    private static int[] inOrder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 0 ; test < T; test++) {
            int n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            preOrder = initOrder(st, n);

            st = new StringTokenizer(br.readLine());
            inOrder = initOrder(st, n);

            List<Integer> postOrderResult = postOrder(0, 0, n, new ArrayList<>());

            System.out.println(
                    postOrderResult.stream()
                            .map(String::valueOf)
                            .collect(joining(SPACE))
            );
        }
    }

    private static int[] initOrder(StringTokenizer st, int n) {
        int[] order = new int[n];

        for (int node = 0; node < n; node++) {
            order[node] = Integer.parseInt(st.nextToken());
        }

        return order;
    }

    private static List<Integer> postOrder (int root, int start, int end, List<Integer> postOrderResult) {
        for (int node = start; node < end; node++) {
            if (inOrder[node] != preOrder[root]) {
                continue;
            }
            postOrder(root + 1, start, node, postOrderResult);
            postOrder(root + node - start + 1, node + 1, end, postOrderResult);

            postOrderResult.add(preOrder[root]);
        }

        return postOrderResult;
    }
}
