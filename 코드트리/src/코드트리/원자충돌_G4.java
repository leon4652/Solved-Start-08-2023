package 코드트리;

import java.io.*;
import java.util.*;

public class 원자충돌_G4 {
	static int N, M, K, res;
	static Field[][] map;
	static final int dr[] = {-1, -1, 0, 1, 1, 1, 0, -1};
	static final int dc[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static ArrayDeque<Atom> tempQueue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Field[N][N];
		// Field 인스턴스 초기화
		for(int r = 0; r < N; r++) {
		    for(int c = 0; c < N; c++) {
		        map[r][c] = new Field();
		    }
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].atoms.add(new Atom(r, c, m, s, d)); //해당 맵의 필드 리스트에 원자 추가
		}
		
		solve(); //메인 로직
		System.out.println(res); //결과 출력
	}
	
	static void solve() {
		//K시간만큼 반복
		for(int i = 0; i < K; i++) {
			moveAllAtoms(); //모든 원자 이동, tempQueue에 정보 담김
			setAllAtoms(); //tempQueue에 담은 모든 원자 다시 map에 삽입
			makeAtoms(); //맵에 두개 이상 붙어있는 원자 합성 진행
			setAllAtoms(); //tempQueue에 담은 모든 원자 다시 map에 삽입
		}

		//계산
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].atoms.size() > 0) {
					calResult(i, j);
				}
			}
		}
	}
	
	static void calResult(int r, int c) {
		int size = map[r][c].atoms.size();
		for(int i = 0; i < size; i++) {
			res += map[r][c].atoms.poll().m;
		}
	}
	
	static void makeAtoms_unionAndSplit_make4Atoms(boolean cross, int r, int c, int m, int s) {
		if(cross) { //대각선 생성
			tempQueue.offer(new Atom(r, c, m, s, 1));
			tempQueue.offer(new Atom(r, c, m, s, 3));
			tempQueue.offer(new Atom(r, c, m, s, 5));
			tempQueue.offer(new Atom(r, c, m, s, 7));
		}
		else { //상하좌우 생성
			tempQueue.offer(new Atom(r, c, m, s, 0));
			tempQueue.offer(new Atom(r, c, m, s, 2));
			tempQueue.offer(new Atom(r, c, m, s, 4));
			tempQueue.offer(new Atom(r, c, m, s, 6));
		}
	}
	
	static void makeAtoms_unionAndSplit(int r, int c) {
		//a. 같은 칸에 있는 원자들은 각각의 질량과 속력을 모두 합한 하나의 원자로 합쳐집니다.
		int cross = 0; //대각
		int udlr = 0; //상하좌우
		int m = 0; //질량
		int s = 0; //속력
		int size = map[r][c].atoms.size();
		for(int i = 0; i < size; i++) {
			Atom now = map[r][c].atoms.poll();
			m += now.m;
			s += now.s;
			if(now.d % 2 == 0) udlr++;
			else cross++;
		}
		
		//b. 이후 합쳐진 원자는 4개의 원자로 나눠집니다.
		if(m < 5) return; //질량이 0으로 계산되니 리턴
		m = m / 5; //질량 
		s = s / size; //속력

		//방향 측정
		if(cross > 0 && udlr > 0) makeAtoms_unionAndSplit_make4Atoms(true, r, c, m, s);
		else makeAtoms_unionAndSplit_make4Atoms(false, r, c, m, s);
	}
	
	static void makeAtoms() {
		//두개이상 원자 찾아 진행
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].atoms.size() >= 2) makeAtoms_unionAndSplit(i, j);
			}
		}
	}
	
	static void setAllAtoms() {
		while(!tempQueue.isEmpty()) {
			Atom now = tempQueue.poll();
			map[now.r][now.c].atoms.offer(now);
		}
	}
	
	static void moveAllAtoms() {
		//방향 속력 이동, size() 1인 이상 원자 꺼내서 이동 위치 후 임시 큐에 담아놓음
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].atoms.size() > 0) { //만약 해당 위치에 원자들이 존재한다면
					moveAllAtoms_putAndMove(i, j); //해당 원자 꺼내서 이동
				}
			}
		}
	}
	
	static void moveAllAtoms_putAndMove(int r, int c) {
		//모두 꺼내서 tempQueue로 이관하는 작업 진행, 이 과정에서 이동 후처리	
		int size = map[r][c].atoms.size(); //원자 개수만큼 반복
		for(int i = 0; i < size; i++) {
			Atom now = map[r][c].atoms.poll(); //꺼내기
			//이동하기
			now.r = now.r + (now.s * dr[now.d]);
			now.c = now.c + (now.s * dc[now.d]);
			//이 중 인덱스 넘어가는거 처리하기
			now.r = convertOutOfMap(now.r);
			now.c = convertOutOfMap(now.c);
			//tempQueue에 삽입
			tempQueue.offer(now);
		}

	}

	static int convertOutOfMap(int r) {
		if(r < 0) {
			r = r % N; //N보다 클 경우 줄임, 결과값 음수
			r = N + r; //줄인 값만큼 왼쪽으로 시프트
			if(r == N) r = 0; //r이 0인 경우 변환
		}
		else if(r >= N) {
			r = r % N;
		}
		return r;
	}
	
	static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= N) return true;
		return false;
	}
}

class Atom {
	int r, c, m, s, d;
	Atom(int r, int c, int m, int s, int d) {
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

class Pos {
	int r;
	int c;
	Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Field {
	Queue<Atom> atoms = new ArrayDeque<>();
}
