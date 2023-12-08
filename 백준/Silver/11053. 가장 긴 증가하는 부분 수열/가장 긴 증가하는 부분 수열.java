import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dp, arr;
    public static void main(String[] args) throws Exception {
    	int N = Integer.parseInt(br.readLine());
    	
    	dp = new int[N]; 
    	arr = new int[N];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < N; i++) {
    		dp[i] = check(i, arr[i]) + 1; //dp 배열에 LIS 개수 갱신
    	}
    	
        // 모든 dp 값 중 최대값 찾기
        int max = 0;
        for (int i = 0; i < N; i++) {
            if(dp[i] > max) max = dp[i];
        }

        System.out.println(max);
    }
    
    static int check(int idx, int value) {
    	int res = 0;
    	
    	for(int i = 0; i < idx; i++) {
    		if(dp[i] > res && arr[i] < value) res = dp[i];
    	}
    	return res;
    }
} //EOC
