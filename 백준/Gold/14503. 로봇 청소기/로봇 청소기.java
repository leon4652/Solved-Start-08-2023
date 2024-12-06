import java.util.*;
import java.io.*;

public class Main {
	static int M, N;
	static int[] DR = {-1, 0, 1, 0}; //북동남서
	static int[] DC = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visit;
	static int cnt = 0;
	
	static Pos robot;
	public static void main(String[] args) {
		input();
		while(true) {
			if(solve()) break;
		}
		System.out.println(cnt);
	}
	
	static boolean solve() {
		int r = robot.r;
		int c = robot.c;
		int d = robot.d;
		if(!visit[r][c]) {
			cnt++;
			visit[r][c] = true;
		}
		
		//빈 칸 존재하는지?
		if(check4Way(r, c)) {
			//반시계 회전
			if(d == 0) d = 3;
			else d -= 1;
			robot.d = d;
			//앞 칸이 갈 수 있는 청소x벽인지?
			int nr = r + DR[d];
			int nc = c + DC[d];
			if(!cantGo(nr, nc) && map[nr][nc] == 0 && !visit[nr][nc]) {
				robot.r = nr;
				robot.c = nc;
			}
			return false;
		} else { //빈 칸 없음
			int nr = r - DR[d];
			int nc = c - DC[d];
			if(cantGo(nr, nc) || map[nr][nc] == 1) return true; //이동 불가
			robot.setPos(nr, nc);
			return false;
		}
	}
	
	//빈 칸 존재하면 true
	static boolean check4Way(int r, int c) {
		for(int i = 0; i < 4; i++) {
			int nr = r + DR[i];
			int nc = c + DC[i];
			if(cantGo(nr, nc)) continue;
			if(map[nr][nc] == 0 && visit[nr][nc] == false) return true; //빈 칸 존재
		}
		return false;
	}
	
	static boolean cantGo(int r, int c) {
		return r < 0 || c < 0 || r >= N || c >= M;
	}
	
	static void input() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			robot = new Pos(r, c, d);
			map = new int[N][M];
			visit = new boolean[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
		} catch(Exception e) {}
	}
}

class Pos {
	int r, c, d;
	
	public Pos(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
	
	public void setPos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}