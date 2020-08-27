import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static final char TARGET = 'a';

    public static void main(String[] args) {
        System.out.println(solution("babaa"));
        System.out.println(solution("ababa"));
        System.out.println(solution("aba"));
        System.out.println(solution("bbbbb"));
    }

    public static int solution(String S) {
        char[] strings = S.toCharArray();
        int N = strings.length;

        int aCount = 0;
        for(char str: strings) {
            aCount += str == TARGET ? 1: 0;
        }

        if(aCount % 3 != 0) return 0;
        if(aCount == 0) return (N - 2) * (N - 1) / 2;

        int tied = aCount / 3;
        int count = 0;

        ArrayList<Integer> boundary = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            if(strings[i] == TARGET) count++;

            if(count == 1) boundary.add(i);
            if(count == tied) {
                boundary.add(i);
                count = 0;
            }
        }
        System.out.println(Arrays.toString(boundary.toArray()));

        return (boundary.get(2) - boundary.get(1)) * (boundary.get(4) - boundary.get(3));
    }
}