import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int W = sc.nextInt();	
		int V = sc.nextInt();	
		int[][] dp = new int[W + 1][V + 1];
		
		int[] weight = new int[W + 1];
		int[] values = new int[W + 1];
		
		for (int i = 1; i < W + 1; i++) {
			weight[i] = sc.nextInt();
			values[i] = sc.nextInt();
		}
		
		
		for (int i = 1; i < W + 1; i++) { 
			for (int j = 1; j < V + 1; j++) { 
				if(weight[i] > j) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(values[i] + dp[i - 1][j - weight[i]], dp[i - 1][j]);
			}
		}
		System.out.println(dp[W][V]);
	}
}
