package implementation;

// https://www.acmicpc.net/problem/1302

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BOJ_1302 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String,Integer> table = new HashMap<>();
        ArrayList<Pair> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            if (table.containsKey(word))
                table.put(word, table.get(word) + 1);
            else
                table.put(word, 1);
        }

        for(String word: table.keySet())
            list.add(new Pair(word, table.get(word)));

        Collections.sort(list);
        System.out.println(list.get(0).word);
    }

    private static class Pair implements Comparable<Pair>{
        String word;
        int cnt;

        public Pair(String word, int cnt){
            this.word = word;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.cnt == o.cnt)
                return this.word.compareTo(o.word);
            return o.cnt - this.cnt;
        }
    }
}
