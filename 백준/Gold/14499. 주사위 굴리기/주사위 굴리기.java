
import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K, r,c;
	static int[][] map;
	static final int D[][] = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //동 서 북 남
	static int dice[] = {0,0,0,0,0,0}; // 위 앞(북) 오(동) 왼(서) 뒤(남) 아(바닥)
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//첫째 줄 파싱
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//맵 넣기
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		//굴리기
		st = new StringTokenizer(br.readLine());
		List<Integer> move = new ArrayList<>();
		while(st.hasMoreTokens()) {
			move.add(Integer.parseInt(st.nextToken()));
		}
		for(int i = 0; i < move.size(); i++) {
			solution(move.get(i) - 1); //인덱스 0부터 시작 고려
		}
		
		bw.close();
	}
	
	public static void solution(int move) throws Exception {
		//move에 따라 이동, 이동이 가능함을 먼저 탐지
		int nr = r + D[move][0];
		int nc = c + D[move][1];
		if(cantGo(nr, nc)) return;
		
		//이동 결과와 바닥에 찍힌 값에 따라 주사위 변경
		//주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 
		//주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 
		//0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
		r = nr;
		c = nc;
		move_dice(move); //주사위 굴림
		if(map[r][c] == 0) {
			map[r][c] = dice[5];
		}
		else {
			dice[5] = map[r][c]; //바닥 변경
			map[r][c] = 0; //찍힌 값 초기화
		}
		
		
		bw.write(dice[0] + "\n");
		
	}
	
	
	public static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) return true;
		return false;
	}
	
	public static void move_dice(int move) {
		//move = 동 서 북 남
		int up = dice[0];
		int n = dice[1];
		int e = dice[2];
		int w = dice[3];
		int s = dice[4];
		int down = dice[5];
		
		
		
		switch(move) {
		case 0 : 
			dice = new int[] {w, n, up, down, s,e}; //동쪽 이동
			break;
		case 1: 
			dice = new int[] {e, n,down ,up , s,w}; //서쪽 이동
			break;
		case 2:
			dice = new int[] {s, up , e, w, down, n}; //북쪽 이동
			break;
		case 3:
			dice = new int[] {n,down, e, w, up,s}; //남쪽 이동
			break;
		
		}
	}
	
	/*
	 * static int dice[] = {up,n,e,w,s,down}; // 위 앞(북) 오(동) 왼(서) 뒤(남) 아(바닥)
	동 : 
	 위 -> 오른
	 오른 -> 바닥
	 바닥 -> 왼
	 왼 -> 위
	 
	 서 :
	 위 > 왼
	 왼 > 바닥
	 바닥 > 오
	 오 > 위
	 
	 북 : 
	 위 -> 앞
	 앞 -> 바닥
	 바닥 -> 뒤
	 뒤 -> 위
	 
	 남 : 
	 위 -> 뒤
	 뒤 -> 바닥
	 바닥 -> 앞
	 앞 -> 위
	 

	 
	 */
	
}
