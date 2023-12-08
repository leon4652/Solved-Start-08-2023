import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dp = new int[11];
    public static void main(String[] args) throws Exception {
    	int T = Integer.parseInt(br.readLine());
    	
    	dp[1] = 1; //1
    	dp[2] = 2; //1+1, 2
    	dp[3] = 4; //1+1+1, 2+1, 1+2, 3
    	for(int i = 4; i < 11; i++) {
    		dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
    	}
    	
    	while(T-- > 0) {
    		System.out.println(dp[Integer.parseInt(br.readLine())]);
    	}
    }
    
    
} //EOC
