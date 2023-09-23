
import java.util.*;
import java.io.*;

/*
상담을 진행하고, 진행하지 않는 경우의 수를 전부 탐색하여, 최대 값을 산출한다.
이 경우 고려해야 할 점이, 상담을 진행하고 완료되는 날짜가 최대 날짜를 넘기면 안 된다는 것이다.
 */

public class Main {
	static int N;
	static int maxRes = 0; //결과물
	static int[][] work;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		work = new int[N + 1][2];
		
		for(int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			work[i][0] = Integer.parseInt(st.nextToken());
			work[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		//백트래킹 시작(현재 날짜, 수익)
		backTracking(1, 0);
		
		System.out.println(maxRes);
	}
	
	public static void backTracking(int days, int res) {
		if(days > N) {
			maxRes = Math.max(maxRes, res); //최대 결과 저장
			return;
		}
		//일을 수행할 수도 있고, 다음 날로 미룰 수도 있음
		backTracking(days + 1, res);
		//오늘 할당량을 할 수 있는지 판단 후 해당 일을 완수한 날로 이동
		int workAfter = days + work[days][0];
		if(workAfter <= N + 1) {
			res += work[days][1];
			backTracking(workAfter, res);
		}
	}
	
	

}
