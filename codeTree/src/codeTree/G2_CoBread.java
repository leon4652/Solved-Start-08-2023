package codeTree;

//https://www.codetree.ai/training-field/frequent-problems/problems/codetree-mon-bread/description?page=1&pageSize=20
//코드트리빵
//1357

import java.util.*;
import java.io.*;
public class G2_CoBread {
	static int N, M;
	static int[][] map;
	
	static final int[] DR =  {-1, 0, 0, 1}; //U L R D
	static final int[] DC =  {0, -1, 1, 0};
	static List<Pos> baseCamps = new ArrayList<>();
	static List<Person> persons = new ArrayList<>();

	
	
	//베이스캠프 = 1, 편의점 = 2, 이제 갈수 없는곳 = -1
	public static void main(String[] args) throws Exception {
		setting();
		//로직
		//최단거리 찾기 로직이 제일 중요함!!
		int time = 0;
		while(true) {
//			System.out.println("[시간] : " + time);
			move(); //1. 최단거리 이동 : 그냥 거리 계산이 아니라 BFS 해야할듯 (못가는 곳이 있으니)
			check(); //2. 편의점 체크(이제 맵에서 -1 처리)
			if(AlluserIsOut()) break; //맵 탐색 로직 후 모두가 찾았다면
			if(time < M) input(time); //3. 베이스캠프 입장
			time++;
		}
		System.out.println(time + 1);
	}
	
	static boolean AlluserIsOut() {
		for(int i = 0; i < persons.size(); i++) {
			Person now = persons.get(i);
			if(now.r != -2 || now.c != -2) return false;
		}
		return true;
	}
	
	static void check() {
		for(int i = 0; i < persons.size(); i++) {
			check_each(persons.get(i), i); //만약 도착했다면 정보를 갱신한다 
		}
		
	}
	
	static void check_each(Person now, int index) {
		if(now.r == now.pr && now.c == now.pc) {
			map[now.r][now.c] = -1; //편의점 지우기
			now.r = -2; now.c = -2; //-2 : 완전 도착 후 제거
		}
	}
	
	static void move() {
		int size = persons.size();
		for(int i = 0; i < size; i++) {
			Person person = persons.get(i);
			if((person.r == -1 && person.c == -1) || (person.r == -2 && person.c == -2)) continue; // 아직 입장 안함, 나감
//			System.out.println(i + " 번째 유저 이동 ");
			move_eachOther(person);
		}
	}
	
	static void move_eachOther(Person now) {
		//편의점 방향을 향해 움직여야 함
		
		//최단거리 찾기
		int minDist = Integer.MAX_VALUE;
		int bestIdx = -1; //최단거리 방법
		Pos shop = new Pos(now.pr, now.pc);
		
		//4방향 우선순위 순서대로 탐색
		for(int i = 0; i < 4; i++) {
			int nr = now.r + DR[i];
			int nc = now.c + DC[i];
			int nowDist = bfs(new Pos(nr, nc), shop); //최단거리 탐색해보기
			if(nowDist < minDist) {
				minDist = nowDist;
				bestIdx = i;
			}
		}
		
//		System.out.println(now.r + " " + now.c + " 에서 방향 : ");
		//실제 이동
		now.r = now.r + DR[bestIdx];
		now.c = now.c + DC[bestIdx];
//		System.out.println(now.r + " " + now.c);
	}
	
	static void input(int time) throws Exception {
		Person now = persons.get(time);
		Pos shop = new Pos(now.pr, now.pc);
		
		//모든 베이스캠프 거리 탐색
		int minDist = Integer.MAX_VALUE;
		int br = 9999; int bc = 9999; int idx = 9999; //인덱스와 좌표
		for(int i = 0; i < baseCamps.size(); i++) {
			Pos nowCamp = baseCamps.get(i);
			if(nowCamp.r == -2 && nowCamp.c == -2) continue; //판매완료 캠프
			int nowDist = bfs(nowCamp, shop); //해당 베이스캠프와의 거리 판단 후 최단거리일 경우 갱신
			if(minDist > nowDist || 
					(minDist == nowDist && br > nowCamp.r) || 
					(minDist == nowDist && br == nowCamp.r && bc > nowCamp.c)
				) { //해당 정보로 갱신
				idx = i;
				br = nowCamp.r;
				bc = nowCamp.c;
				minDist = nowDist;
			}
		}
		
		//최적 베이스캠프 찾음
		map[br][bc] = -1; 		//해당 베이스캠프 -1 처리
		baseCamps.get(idx).r = -2;  //베이스캠프 리스트에서 제거
		baseCamps.get(idx).c = -2;  //베이스캠프 리스트에서 제거
		
		//사람 이동
		now.r = br;
		now.c = bc;
		
//		System.out.println("[input]사람 이동 : " + br + " " + bc);
	}
	
	static int bfs(Pos start, Pos end) {
		//시작 끝 좌표의 최단거리를 계산
		int minDist = Integer.MAX_VALUE;
		Queue<int[]> bfsQueue = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][N];
		
		bfsQueue.add(new int[] {start.r, start.c, 0});
		while(!bfsQueue.isEmpty()) {
			int[] now = bfsQueue.poll();
			int r = now[0];
			int c = now[1];
			int dist = now[2];
			if(cantGo(r, c) || visit[r][c] || map[r][c] == -1) continue; //후순위 로직
			visit[r][c] = true; //방문 처리
			if(r == end.r && c == end.c) { //목표 좌표일 경우
				minDist = Math.min(minDist, dist); //최단거리 비교
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + DR[i];
				int nc = c + DC[i];
				if(cantGo(nr, nc) || visit[nr][nc] || map[r][c] == -1) continue; //방문한 장소, 맵 바깥, 못 가는 위치 스킵
				bfsQueue.add(new int[] {nr, nc, dist + 1});
			}
		}
		return minDist;
	}
	
	static void log() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("--------------");
	}
	
	static void setting() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		//맵 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) baseCamps.add(new Pos(i, j));
			}
		}
		
		//사람 입력
		for(int i = 0; i < M; i++) {
			Person person = new Person();
			st = new StringTokenizer(br.readLine());
			person.r = -1;
			person.c = -1;
			person.pr = Integer.parseInt(st.nextToken()) - 1;
			person.pc = Integer.parseInt(st.nextToken()) - 1;
			persons.add(person);
		}
	}
	
	static boolean cantGo(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= N) return true;
		return false;
	}
}

class Pos {
	int r, c;
	Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
class Person {
	int r, c; //사람 좌표
	int pr, pc; //편의점
	

}