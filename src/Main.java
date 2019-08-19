import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        if(a + b + c >= 100)
            System.out.println("OK");
        else {
            int temp = Math.min(a, b);
            temp = Math.min(temp, c);
            if(a == temp)
                System.out.println("Soongsil");
            else if(b == temp)
                System.out.println("Korea");
            else System.out.println("Hanyang");
        }
    }
}
