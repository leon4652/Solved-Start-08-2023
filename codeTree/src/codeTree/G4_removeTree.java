//package codeTree;
//
///*
// 나무박멸
// https://www.codetree.ai/training-field/frequent-problems/problems/tree-kill-all/description?page=1&pageSize=20
// 12:57 start
// */
//
//import java.util.*;
//import java.io.*;
//
//public class G4_removeTree {
//	static int N, M, K, C, res;
//	static int[][] map;
//	static final int[] DR = new int[] {0, -1, 0, 1}; //LURD
//	static final int[] DC = new int[] {-1, 0, 1, 0};
//	
//	static final int[] CR = new int[] {-1, -1, 1, 1}; //cross 좌상 우상 좌하 우하
//	static final int[] CC = new int[] {-1, 1, -1, 1}; //cross
//	
//	static ArrayDeque<Jecho> jechoQueue = new ArrayDeque<>();
//	static ArrayDeque<Tree> treeQueue = new ArrayDeque<>(); //나무 보관
//	static ArrayDeque<Tree> tempTreeQueue = new ArrayDeque<>(); //아직 정확한 수치가 정해지지 않은 나무 임시 보관
//	
//	public static void main(String[] args) throws Exception {
//		//입력받기
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		K = Integer.parseInt(st.nextToken());
//		C = Integer.parseInt(st.nextToken());
//		
//		map = new int[N][N];
//		for(int i = 0; i < N; i++) {
//			st = new StringTokenizer(br.readLine());
//			for(int j = 0; j < N; j++) {
//				map[i][j] = Integer.parseInt(st.nextToken());
//			}
//		}
//		
//		solve(); //메인 로직
//		System.out.println(res);
//	}
//	
//	static void solve() {
//		while(M > 0) {
////			log();
//			growTree(); //상하좌우 개수만큼 탐색하여 해당 나무 성장
//			makeTree(); //마찬가지로 상하좌우 중 0인 칸(이면서 제초제가 없는 칸)에 번식 진행		
//			saveTreeToMap(); //TreeQueue의 정보를 맵에 저장 : 번식 값 심기
//			removeLastJecho(); //이전 년도 제초제가 있다면(현재 존재하는 제초제가 있다면) -1 년 진행 후 0년 되면 제거
//			dropNowJecho(); //가장 많은 제거 가능 칸 제초제 투하
//			M--;
//		}
//	}
//	
//	static void dropNowJecho() {
//		//1. 가장 많은 제초 효과를 거둘 수 있는 장소 찾기
//		int jechoCount = -1; //나무 소멸 카운트
//		int jr = 9999, jc = 9999; //최고 소멸 카운트 위치
//		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				//여기 뿌렸을때 제거되는 나무
//				int nowCount = dropNowJecho_checkMaxJechoCount(i, j);
//				
//				//1. 최고 기록 갱신 || 같을때 행 우선 || 같을때 열 우선
//				if(jechoCount < nowCount || (jechoCount == nowCount && i < jr) || (jechoCount == nowCount && i == jr && j <= jc)) {
//					jechoCount = nowCount;
//					jr = i; jc = j;
//				}
//			}
//		}
//		
//		//2. 해당 장소에 제초제 투하 후, 뿌려지는 사이드이펙트까지 고려
//		//2-1 일단 해당 장소 제초
//		res += map[jr][jc]; //점수
//		map[jr][jc] = 0;
//		jechoQueue.add(new Jecho(jr, jc, C)); //제초제
//		
//		//4방향 전파 로직
//		for(int i = 0; i < 4; i++) {
//			int val = 1; //가중치
//			while(true) {
//				if(val > K) break; //제초제 범위 이상임
//				int nr = jr + (val * CR[i]);
//				int nc = jc + (val * CC[i]);
//				
//				//장애물이거나, 나무가 없거나, 맵밖
//				if(cantGo(nr, nc) || map[nr][nc] <= 0) {
//					if(cantGo(nr, nc)) break;
//					if(map[nr][nc] == 0) jechoQueue.add(new Jecho(nr, nc, C)); //일단 제초제를 뿌리긴 해야함
//					break;
//				}
//								
//				res += map[nr][nc]; //점수
//				map[nr][nc] = 0; //나무 제거
//				jechoQueue.add(new Jecho(nr, nc, C)); //제초제
//				val++; //다음 위치로
//			}
//		}
//		
//	}
//	
//	static int dropNowJecho_checkMaxJechoCount(int r, int c) {
//		if(map[r][c] <= 0) return 0; //해당 위치는 아무것도 없음
//		int count = map[r][c]; //해당 위치 일단 제거
//		
//		
//		//4방향 전파 로직
//		for(int i = 0; i < 4; i++) {
//			int val = 1; //가중치
//			while(true) {
//				if(val > K) break; //제초제 범위 이상임
//				int nr = r + (val * CR[i]);
//				int nc = c + (val * CC[i]);
//				
//				//장애물이거나, 나무가 없거나, 맵밖
//				if(cantGo(nr, nc) || map[nr][nc] <= 0) {
//					break;
//				}
//								
//				count += map[nr][nc]; //제거되는 나무들
//				val++; //다음 위치로
//			}
//		}
//		
//		return count;
//	}
//	
//	static void removeLastJecho() {
//		//제초제 큐 안에 있는 년도 줄이기
//		int size = jechoQueue.size();
//		while(size > 0) {
//			Jecho now = jechoQueue.poll();
//			now.year -= 1; //1년 지남
//			
//			if(now.year == 0) {
//				size--;
//				continue; //해당 제초제는 만료됨
//			}
//			jechoQueue.offer(now);
//			size--;
//		}
//	}
//	
//	static void makeTree() {
//		//모든 좌표 순회하며 나무일 경우 로직 실행
//		for(int i = 0; i < N; i ++) {
//			for(int j = 0; j < N; j++) {
//				if(map[i][j] > 0) makeTree_checkMakeTree(i, j);
//			}
//		}
//	}
//	
//	static void saveTreeToMap() {
//		while(!treeQueue.isEmpty()) {
//			Tree t = treeQueue.poll();
//			if(growTree_growThisPlace_isJecho(t.r, t.c)) continue;
//			map[t.r][t.c] += t.grow; //해당 좌표에 나무 추가
//		}
//	}
//	
//	static void makeTree_checkMakeTree(int r, int c) {
//		//1. 번식이 가능한 칸 체크, tempTreeQueue에 일단 나무들 좌표가 저장되긴 했음. (수치는 저장 안됨 좌표만)
//		int count = makeTree_checkMakeTree_countNearBy(r, c);
//		
//		//2. tempTreeQueue에서 treeQueue로 이관하며 번식 정보 추가 : 결국 새로 생긴 나무들이 트리큐에 저장됨
//		if(count == 0) return; //번식 안함
//		int grow = map[r][c] / count; //나무 번식 수치
//		makeTree_checkMakeTree_addTreeQueue(grow);
//	}
//	
//	static void makeTree_checkMakeTree_addTreeQueue(int grow) {
//		while(!tempTreeQueue.isEmpty()) {
//			Tree tree = tempTreeQueue.poll();
//			tree.grow = grow;
//			treeQueue.add(tree);
//		}
//	}
//	
//	static int makeTree_checkMakeTree_countNearBy(int r, int c) {
//		int count = 0;
//		FORWAY:
//		for(int i = 0; i < 4; i++) {
//			int nr = r + DR[i];
//			int nc = c + DC[i];
//			if(cantGo(nr, nc) || map[nr][nc] != 0) continue; //맵밖이거나, 공터가 아닌 경우
//			
//			//제초제가 없을 경우도 판정, 제초제 큐 사이즈만큼 반복하여 해당 위치가 제초제 뿌린 것인지 판정
//			int size = jechoQueue.size();
//			for(int j = 0; j < size;j++) {
//				Jecho now = jechoQueue.poll();
//				if(nr == now.r && nc == now.c) { //만약 제초제 좌표와 현재 좌표가 똑같다면, 그 곳은 카운팅할 수 없음
//					jechoQueue.add(now); //다시 집어넣기
//					continue FORWAY;
//				}
//				jechoQueue.add(now); //다시 집어넣기
//			}
//			
//			count++; //점수 추가
//			tempTreeQueue.add(new Tree(nr, nc, 0)); //아직 정확한 value를 모름
//		}
//		return count;
//	}
//	
//	static void growTree() {
//		//인접한 네개의 칸 중 나무가 있는 칸만큼 성장
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				//나무가 있는 곳 탐색
//				if(map[i][j] > 0) growTree_growThisPlace(i, j);
//			}
//		}
//	}
//	
//	static void growTree_growThisPlace(int r, int c) {
//		//해당 스팟의 4방 탐색 후 성장
//		int count = 0;
//		for(int i = 0; i < 4; i++) {
//			int nr = r + DR[i];
//			int nc = c + DC[i];
//			if(cantGo(nr, nc) || map[nr][nc] <= 0) continue;
//			if(growTree_growThisPlace_isJecho(nr, nc)) continue; //제초제가 있는지 확인
//			count++;
//		}
//		
//		map[r][c] += count; //나무 성장
//	}
//	
//	static boolean growTree_growThisPlace_isJecho(int r, int c) {
//		int size = jechoQueue.size();
//		while(size > 0) {
//			Jecho now = jechoQueue.poll();
//			if(r == now.r && c == now.c) {
//				jechoQueue.add(now);
//				return true;
//			}
//			jechoQueue.add(now);
//			size--;
//		}
//		return false;
//	}
//
//	static boolean cantGo(int r, int c) {
//		if(r < 0 || c < 0 || r >= N || c >= N) return true;
//		return false;
//	}
//	
//	static void log() {
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + "\t");
//			}
//			System.out.println("");
//		}
//		System.out.println("-----------");
//	}
//}
//
//
//
//class Tree {
//	int r, c, grow;
//	public Tree(int r, int c, int grow) {
//		this.r = r;
//		this.c = c;
//		this.grow = grow;
//	}
//}
//
//
//class Jecho {
//	int r, c, year;
//	public Jecho(int r, int c, int year) {
//		this.r = r;
//		this.c = c;
//		this.year = year;
//	}
//}
//
