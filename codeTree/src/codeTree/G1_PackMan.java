package codeTree;

import java.util.*;
import java.io.*;

public class G1_PackMan {
	static final int N = 4;
	static int M, T; //몬스터와 턴수
	static Field[][] map; //실제 몬스터 있는 필드
	static int[][] corpMap; //시체 보관 필드
	static PackMan packman;
	static Queue<Monster> eggs = new ArrayDeque<>(); //알 담을 큐
	static Queue<Monster> tempMon = new ArrayDeque<>(); //몬스터 임시 보관
	
	static int[][] testBoard = new int[N][N]; //막쓰는 임시용
	
	static final int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1}; 
	static final int[] DC = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static final int[] PDR = {-1, 0, 1, 0}; //상-좌-하-우 우선순위
	static final int[] PDC = {0, -1, 0, 1};
	
	//팩맨 이동 경로 측정용
	static int bestWayPoint = -1;
	static int[] bestWays;
	
	public static void main(String[] args) throws Exception {
		setting();
		
		while(T > 0) {
		summon();//1. 몬스터 복제 시도
		moveMonster(); //2. 몬스터 이동 : 시체, 팩맨, 격자 고려 / 반시계 이동 후 불가능할시 제자리 하여 tempMon에 저장
		setMonster(); //2. temp큐에서 다시 맵으로 할당
		
		findBestWay(); //3. 팩맨 이동 : 3칸 이동, 우선순위로 제일 많이 먹는 것 판정
//		System.out.println("최선값 : " + bestWayPoint + " " + Arrays.toString(bestWays));
		movePackMan(); //실제 이동하며 몹 지워버리고 시체 추가
		
		corpsRemove();//4. 시체 소멸 : 애초에 시체 만들때 3으로 만들어서 --
//		System.out.println(packman.r + " " + packman.c);
		eggsToMonster(); //5. 알 부화 -> 알 제거하고 몬스터 새로 생성
		
//		log();
		T--;
		}
		
		
		solve(); //계산
	}
	
	static void solve() {
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].queue.size() > 0) count += map[i][j].queue.size();
			}
		}
		
		System.out.println(count);
	}
	
	static void eggsToMonster() {
		while(!eggs.isEmpty()) {
			Monster mon = eggs.poll();
			map[mon.r][mon.c].queue.add(mon);
		}
	}
	
	static void corpsRemove() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(corpMap[i][j] > 0) corpMap[i][j]--; //시체 소멸
			}
		}
	}
	
	static void movePackMan() {
		//실제 이동하며 없애기
		int r = packman.r;
		int c = packman.c;
		for(int i = 0; i < bestWays.length; i++) {
			r = r + PDR[bestWays[i]]; //이동
			c = c + PDC[bestWays[i]];
			
			//몬스터 있다면 제거
			if(map[r][c].queue.size() > 0) {
				map[r][c].queue.clear(); //제거
				corpMap[r][c] = 3; //시체 남기기
			}
		}
		
		bestWayPoint = -1; //초기화
		packman.r = r; //이동 위치 갱신
		packman.c = c;
	}
	
	static void findBestWay() {
		//백트래킹 사용하여 모든 경우의 수 탐색 4 ^ 3
		int pr = packman.r;
		int pc = packman.c;
		backTracking(0, 0, pr, pc, new int[3]);
		backTracking(0, 1, pr, pc, new int[3]);
		backTracking(0, 2, pr, pc, new int[3]);
		backTracking(0, 3, pr, pc, new int[3]);
	}
	
	static void backTracking(int cnt, int d, int r, int c, int[] ways) {
		if(cnt == 2) {
			ways[cnt] = d;
			packManMoveTest(ways); //해당 경우의 수로 테스팅
			return;
		}
		
		ways[cnt] = d;
		backTracking(cnt + 1, 0, r, c, ways);
		backTracking(cnt + 1, 1, r, c, ways);
		backTracking(cnt + 1, 2, r, c, ways);
		backTracking(cnt + 1, 3, r, c, ways);
	}
	
	static void packManMoveTest(int[] ways) {
		
		//해당 이동 방식으로 이동 테스트
		//현재 몬스터 테스팅보드로 복사
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				testBoard[i][j] = map[i][j].queue.size();
			}
		}
		
		//이동 배열대로 이동해봄
		int r = packman.r; 
		int c = packman.c;
		int score = 0; //적립 추정치
		for(int i = 0; i < ways.length; i++) {
			int nr = r + PDR[ways[i]]; 
			int nc = c + PDC[ways[i]];
			if(cantGo(nr, nc)) {
				return; //이동 가능한지 판정 (불가능하면 그냥 리턴)
			}
			
			//이동하며 해당 몹을 잡으며 카운팅
			score += testBoard[nr][nc];
			testBoard[nr][nc] = 0; //초기화
			r = nr; //이동 
			c = nc;
		}
		
		//스코어 비교하여 최댓값 넣음, 이미 우선순위대로 실행하므로 큰값만 비교하면 됨
		if(score > bestWayPoint) {
			bestWayPoint = score;
			bestWays = new int[3];
			//배열 복사
			for(int i = 0; i < ways.length; i++) {
				bestWays[i] = ways[i];
			}
		}
	}
	
	
	static void setMonster() {
		//tempMon에서 Map으로
		while(!tempMon.isEmpty()) {
			Monster mon = tempMon.poll();
			int r = mon.r;
			int c = mon.c;
			map[r][c].queue.add(mon);
		}
	}
	
	static void moveMonster() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].queue.size() > 0) moveMonster_move(i, j, map[i][j].queue.size());
			}
		}
	}
	
	static void moveMonster_move(int r, int c, int size) {

		//해당 좌표의 몬스터 개수만큼 반복
		while(size > 0) {
			moveMonster_move_each(map[r][c].queue.poll());
			size--;
		}
	}
	
	static void moveMonster_move_each(Monster mon) {
		//해당 몬스터를 가져옴
		//몬스터 이동 : 시체, 팩맨, 격자 고려 / 반시계 이동 후 불가능할시 제자리
		//8번 시도하고 아닐 경우 패스
		int cnt = 8;
		int r = mon.r;
		int c = mon.c;
		int dist = mon.dist;
		
		while(cnt > 0) {
			int nr = r + DR[dist];
			int nc = c + DC[dist];
//			System.out.println(" 디버그, dist : " + dist);
			
			//갈 수 없는 경우 다음 시도 //격자, 팩맨, 시체
			if(cantGo(nr, nc) || (nr == packman.r && nc == packman.c) || corpMap[nr][nc] > 0) { 
				dist++; //방향 전환
				dist %= 8; //convert
				cnt--; //시도 차감
				continue;
			}
			
			//아닐 경우 해당 경우로 탈출
			tempMon.add(new Monster(nr, nc, dist));
//			System.out.println(mon.r + " " + mon.c +"에서 여기로 이동 : " + nr + " " + nc);
			return; //탈출(이동 가능한 곳으로 이동)
		}
		
		//제자리에 있기, 원래 정보 담음
		tempMon.add(mon);
	}
	
	static void summon() {
		//몬스터 복제, 몬스터가 있는 장소에서 실행
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].queue.size() > 0) summon_copy(i, j);
			}
		}
	}
	
	static void summon_copy(int r, int c) {
		//이 좌표에 있는 몬스터만큼 반복
		int size = map[r][c].queue.size();
		while(size > 0) {
			Monster now = map[r][c].queue.poll();
			eggs.add(now); //알 생성
			map[r][c].queue.add(now); //다시 집어넣음
			size--;
		}
	}
	
	static void log() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j].queue.size() + "\t");
			}
			System.out.println();
		}
		System.out.println("----------------------------------");
	}
	
	static void setting() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		//필드 초기화
		map = new Field[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new Field();
			}
		}
		
		corpMap = new int[N][N]; //시체
		
		//팩맨
		st = new StringTokenizer(br.readLine());
		packman = new PackMan();
		packman.r = Integer.parseInt(st.nextToken()) - 1;
		packman.c = Integer.parseInt(st.nextToken()) - 1;
		
		//몬스터 필드에 넣기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken()) - 1;
			map[r][c].queue.add(new Monster(r, c, dist));
			
		}
		
	}
	static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= N) return true;
		return false;
	}
}

class Monster {
	public Monster(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}

	int r, c, dist;
}

class PackMan {
	int r, c;
}

class Field {
	Queue<Monster> queue = new ArrayDeque<>();
}