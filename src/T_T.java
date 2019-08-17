import java.util.Scanner;

public class T_T {
    public static void main(String[] args) throws Exception{
        Scanner sc  = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

//        if(x > 0)
//            System.out.println(y > 0 ? 1 : 4);
//        else
//            System.out.println(y > 0 ? 2 : 3);

        System.out.println(x > 0 ? (y > 0 ? 1 : 4) : (y > 0 ? 2 : 3));
    }
}
