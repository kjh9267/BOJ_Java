package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3649 {
	
	 public static void main(String[] args) {

	        sc.init();
	        String size = "";
	        while ( (size = sc.readLine()) != null ){
	            int size1 = Integer.parseInt(size);
	            solve(size1);
	        }
	    }
	    
	    static private void solve(int size) {
	        
	        int legoNum = sc.nextInt();
	        int[] legoSize = new int[legoNum];
	        
	        for( int i = 0; i < legoNum; i++ ){
	            legoSize[i] = sc.nextInt();
	        }
	        
	        size = size * 10000000;
	        Arrays.sort(legoSize);
	 
	        int l1 = 0;
	        int l2 = 0;
	        boolean isOkay = false;
	        
	        int flagL = 0;
	        int flagR = legoNum-1;
	        
	        int sum = 0;
	        while ( flagL < flagR ){
	            sum = legoSize[flagL] + legoSize[flagR];
	            
	            if( sum == size ) {
	                l1 = legoSize[flagL];
	                l2 = legoSize[flagR];
	                isOkay = true;
	                break;
	            }
	            else if ( sum < size ){
	                flagL++;
	            }
	            else if ( sum > size ){
	                flagR--;
	            }
	        }
	        
	        if ( l1 != 0 && l2 != 0) {
	            System.out.printf("yes %d %d\n", l1, l2);
	        }
	        else {
	            System.out.printf("danger\n");
	        }
	    }
	    
	    static class sc { 
	        private static BufferedReader br;
	        private static StringTokenizer st;
	        
	        static void init() {
	            br = new BufferedReader(new InputStreamReader(System.in));
	            st = new StringTokenizer("");
	        }
	        
	        static String readLine() {
	            try{
	                return br.readLine();
	            } catch (IOException e){
	                e.printStackTrace();
	            }
	            return null;
	        }
	        
	        static String next() {
	            while (!st.hasMoreTokens() ){
	                try {
	                    st = new StringTokenizer(br.readLine());
	                } catch (IOException e){
	                    e.printStackTrace();
	                }
	            }
	            return st.nextToken();
	        }
	        
	        static int nextInt() {
	            return Integer.parseInt(next());
	        }
	        
	    }

}
