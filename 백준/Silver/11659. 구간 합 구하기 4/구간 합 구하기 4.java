
import java.util.*;
import java.io.*;

public class Main {
	static int N, M, arr[]; 
	static long dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		dp = new long[N];
		
		st = new StringTokenizer(br.readLine());
		arr[0] = Integer.parseInt(st.nextToken());
		dp[0] = arr[0];
		for (int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = dp[i - 1] + arr[i];
		}

		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int min = Integer.parseInt(st.nextToken()) - 2;
			int max = Integer.parseInt(st.nextToken()) - 1;
			
			if(min >= 0) bw.write(dp[max] - dp[min] + "\n");
			else bw.write(dp[max] + "\n");
		}
		
		bw.close();
	}

}
