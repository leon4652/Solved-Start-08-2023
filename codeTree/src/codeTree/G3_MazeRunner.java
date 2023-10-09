package codeTree;

import java.io.*;
import java.util.*;

public class G3_MazeRunner {
	static int N, M, K,res;
	static int[][] map;
	static int[] exit = new int[2];
	static int[] square = new int[3]; //0, 1 정사각형 좌표, 2는 길이. exit좌표와 반대되는 양 끝 대각선 좌표이다.
	
	static final int[] DR = new int[] {-1, 1, 0, 0}; //상하좌우
	static final int[] DC = new int[] {0, 0, -1, 1}; //
	static ArrayDeque<Runner> runnerQueue = new ArrayDeque<Runner>(); //러너들 담을 큐
//	static ArrayDeque<Runner> tempQueue = new ArrayDeque<Runner>(); //러너들 임시로 담을 큐
	static PriorityQueue<Runner> tempQueue = new PriorityQueue<>(); //dist 우선순위큐
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//미로
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//참가자
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			runnerQueue.offer(new Runner(
					Integer.parseInt(st.nextToken()) - 1, //idx 좌표 고려
					Integer.parseInt(st.nextToken()) - 1,
					i
					));
		}
		
		//출구
		st = new StringTokenizer(br.readLine());
		exit[0] = Integer.parseInt(st.nextToken()) - 1; //idx 고려
		exit[1] = Integer.parseInt(st.nextToken()) - 1;
		
		//메인 로직
		solve();
		
		System.out.println(res);
	}
	
	static void solve() {
		for(int i = 0; i < K; i++) {
			move(); //1초마다 모든 참가자는 한 칸씩 움직입니다.
			rotateMaze(); //모든 참가자가 이동을 끝냈으면, 다음 조건에 의해 미로가 회전합니다.
//			if(gameSet()) break; //만약 모든 참가자가 탈출했을 경우 게임이 끝납니다.
		}
	}

	static void rotateMaze() {
		//한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 잡습니다.
		//가장 작은 크기를 갖는 정사각형이 2개 이상이라면, 좌상단 r 좌표가 작은 것이 우선되고, 그래도 같으면 c 좌표가 작은 것이 우선됩니다.
		//선택된 정사각형은 시계방향으로 90도 회전하며, 회전된 벽은 내구도가 1씩 깎입니다.
		
		
		//가장 가까운 모험가를 감싸는 정사각형 찾기
		//정사각형 우선순위 : 좌상 좌하 우상 우하
		//이미 PQ로 우선순위별 정렬했음. 해당 모험가 좌표와 맞는 정사각형 찾기
		rotateMaze_makeSquare(); //가까운 모험가를 둘러싸는 정사각형 꼭지 찾아 저장
		
		//다시 러너큐에 담기
	}
	static void rotateMaze_makeSquare() {
		Runner now = tempQueue.peek(); //정사각형 재료 모험가
		int rr = now.r, rc = now.c; //러너 좌표
		int er = exit[0], ec = exit[1]; //탈출구 좌표
		boolean fixVer = false;
		int len = Math.max(Math.abs(rr - er), Math.abs(rc - ec)); //어쨌든 러너를 감싸는 정사각형의 변 길이
		square[2] = len; //길이 저장
		if(len == (Math.abs(rr - er))) {
			fixVer = true; //세로 고정 true : 이제 정사각형은 가로로 움직여서 최대한 왼쪽으로 가야 함
			//R좌표 찾기 : er 기준으로 len 위로 돌려보고, 아래로 돌려보기
			if(cantGoNoWall(er - len, 0)) square[0] = er + len;
			else 
				
			//C좌표 찾기 exit 좌표를 기준으로 len길이만큼 왼쪽으로 가보고, 맵밖이면 한칸씩 오른쪽으로
		} 
		else {
			//아닐 경우 정사각형은 최대한 위로 가야 함
		}
		
		
		
		
//		//이제 이 정사각형이 어느 방향인지 알아야 함
//		//정사각형 우선순위 : 좌상 좌하 우상 우하, 기준 스팟은 탈출구 좌표
//		//좌상 : 러너 rc가 전부 탈출구보다 작거나 같음
//		//좌하 : r 작같, c 큼같 
//		//우상 : r 큼같, c 작같 
//		//우하 : r 큼같, c 큼같
//		System.out.println("타겟 모험가 : " + now.r + " " + now.c);
//		System.out.println("len : " + len);
//		if(rr <= er && rc <= ec) { //좌상
//			//꼭지점을 계산하고, 맵밖이라면 false를 출력하고 바로 다음 로직을 계산, true라면 전역변수에 해당 꼭짓점 저장
//			if(rotateMaze_makeSquare_setSquarePos(er - len, ec - len, len)) return;
//		}
//		if(rr >= er && rc <= ec) { //좌하
//			if(rotateMaze_makeSquare_setSquarePos(er + len, ec - len, len)) return;
//		}
//		if(rr <= ec && rc >= ec) { //우상
//			if(rotateMaze_makeSquare_setSquarePos(er - len, ec + len, len)) return;
//		}
//		if(rr >= ec && rc >= ec) { //우하
//			if(rotateMaze_makeSquare_setSquarePos(er + len, ec + len, len)) return;
//		}
//		System.out.println("버그 발생 : square좌표 찾기");
	}
	
	static boolean rotateMaze_makeSquare_setSquarePos(int r, int c, int len) {
		//만약 r, c가 맵밖인지 체크하고, 안된다면 false, 된다면 현재 전역변수에 적용
		if(r < 0 || c < 0 || r >= N || c >= N) {
			System.out.println("맵밖 : " + r + " " + c);
			return false; //맵 벗어남
		}
		square[0] = r;
		square[1] = c;
		square[2] = len;
		System.out.println("사각형 좌표 " + square[0] + " " + square[1] + " " + square[2]);
		return true;
	}
	
	static void move() {
		int size = runnerQueue.size(); //참가자 수
		while(size > 0) {
			Runner now = runnerQueue.poll(); //현재 러너
			move_findAndMove(now); //최단 거리 찾아서 이동시킨 뒤, tempQueue에 넣음
			size--;
		}

	}
	
	static void move_findAndMove(Runner now) {
		int nowDist = calDist(now.r, now.c); //지금 위치와의 거리 찾기
		//이동 로직
		for(int i = 0; i < 4; i++) { //상하좌우 우선순위 순서로 진행
			int r = now.r + DR[i];
			int c = now.c + DC[i];
			if(cantGo(r, c)) continue; //벽이 있거나 맵밖이라 갈 수 없다.
			int moveDist = calDist(r, c); //만약 이쪽으로 움직였을 경우의 거리 판단
			
			//만약 거리가 줄어든다면 움직이고 tempQueue로 이관 후 종료
			if(nowDist > moveDist) {
//				System.out.println(now.no+"번 모험가 이동 : " + i);
				now.r = r; //이동
				now.c = c;
				now.dist = moveDist; //최단거리
				tempQueue.offer(now);
				res++; //결과 이동거리
				return;
			}
		}
		//움직일 수 없는 상황 : 가만히 있고 그냥 tempQueue에 이관
//		System.out.println(now.no+"번 모험가 유지");
		now.dist = nowDist; //최단거리
		tempQueue.offer(now);
	}
	
	//거리계산
	
	//최단거리 공식
	static int calDist(int r, int c) {
		int er = exit[0];
		int ec = exit[1];
		return Math.abs(er - r) + Math.abs(ec - c); 
	}
	//맵밖판정 + 벽
	static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= N || map[r][c] > 0) return true;
		else return false;
	}
	static boolean cantGoNoWall(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= N) return true;
		else return false;
	}
}

class Runner implements Comparable<Runner>{
	int r, c, no;
	int dist = 9999; //출구와의 최단거리
	Runner(int r, int c, int no) {
		this.no = no;
		this.r = r;
		this.c = c;
	}
	
	@Override
	public int compareTo(Runner o) {
		if(this.dist == o.dist) {
			if(this.r == o.r) return this.c - o.c; //c가 작은 순서대로(조건 3)
			return this.r - o.r; //r이 작은 순서대로(조건2)
		}
		return this.dist - o.dist; //최단순위(조건 1)
	}
}
